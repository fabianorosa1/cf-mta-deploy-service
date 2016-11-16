package com.sap.cloud.lm.sl.cf.core.cf.v2_0;

import static com.sap.cloud.lm.sl.common.util.ListUtil.asList;
import static com.sap.cloud.lm.sl.mta.util.PropertiesUtil.getAll;
import static com.sap.cloud.lm.sl.mta.util.PropertiesUtil.getPropertyValue;
import static com.sap.cloud.lm.sl.mta.util.PropertiesUtil.mergeProperties;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.cloudfoundry.client.lib.domain.Staging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.cloud.lm.sl.cf.client.lib.domain.CloudApplicationExtended;
import com.sap.cloud.lm.sl.cf.core.cf.HandlerFactory;
import com.sap.cloud.lm.sl.cf.core.helpers.XsPlaceholderResolver;
import com.sap.cloud.lm.sl.cf.core.message.Messages;
import com.sap.cloud.lm.sl.cf.core.model.SupportedParameters;
import com.sap.cloud.lm.sl.cf.core.parser.MemoryParametersParser;
import com.sap.cloud.lm.sl.cf.core.parser.StagingParametersParser;
import com.sap.cloud.lm.sl.common.ContentException;
import com.sap.cloud.lm.sl.common.SLException;
import com.sap.cloud.lm.sl.common.util.ListUtil;
import com.sap.cloud.lm.sl.common.util.MapUtil;
import com.sap.cloud.lm.sl.common.util.Pair;
import com.sap.cloud.lm.sl.mta.builders.v2_0.ParametersChainBuilder;
import com.sap.cloud.lm.sl.mta.builders.v2_0.PropertiesChainBuilder;
import com.sap.cloud.lm.sl.mta.handlers.v2_0.DescriptorHandler;
import com.sap.cloud.lm.sl.mta.model.SystemParameters;
import com.sap.cloud.lm.sl.mta.model.v2_0.DeploymentDescriptor;
import com.sap.cloud.lm.sl.mta.model.v2_0.Module;
import com.sap.cloud.lm.sl.mta.model.v2_0.RequiredDependency;
import com.sap.cloud.lm.sl.mta.model.v2_0.Resource;
import com.sap.cloud.lm.sl.mta.util.ValidatorUtil;

public class CloudModelBuilder extends com.sap.cloud.lm.sl.cf.core.cf.v1_0.CloudModelBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(CloudModelBuilder.class);

    private static final int MTA_MAJOR_VERSION = 2;

    protected final ParametersChainBuilder parametersChainBuilder;

    public CloudModelBuilder(DeploymentDescriptor deploymentDescriptor, SystemParameters systemParameters, boolean portBasedRouting,
        boolean prettyPrinting, boolean useNamespaces, boolean useNamespacesForServices, boolean allowInvalidEnvNames, String deployId,
        XsPlaceholderResolver xsPlaceholderResolver) {
        super(deploymentDescriptor, systemParameters, portBasedRouting, prettyPrinting, useNamespaces, useNamespacesForServices,
            allowInvalidEnvNames, deployId, new DescriptorHandler(), new PropertiesChainBuilder(deploymentDescriptor),
            xsPlaceholderResolver);
        this.parametersChainBuilder = new ParametersChainBuilder(deploymentDescriptor);
    }

    @Override
    protected HandlerFactory getHandlerFactory() {
        return new HandlerFactory(MTA_MAJOR_VERSION);
    }

    @Override
    protected List<String> getDomains(com.sap.cloud.lm.sl.mta.model.v1_0.Module module) {
        List<Map<String, Object>> parametersList = parametersChainBuilder.buildModuleChain(module.getName());
        List<String> domains = getAll(parametersList, SupportedParameters.DOMAIN, SupportedParameters.DOMAINS);
        return domains;
    }

    @Override
    protected CloudApplicationExtended getApplication(com.sap.cloud.lm.sl.mta.model.v1_0.Module module) throws SLException {
        List<Map<String, Object>> parametersList = parametersChainBuilder.buildModuleChain(module.getName());
        warnAboutUnsupportedParameters(parametersList);
        Staging staging = parseParameters(parametersList, new StagingParametersParser());
        int diskQuota = parseParameters(parametersList, new MemoryParametersParser(SupportedParameters.DISK_QUOTA, "0"));
        int memory = parseParameters(parametersList, new MemoryParametersParser(SupportedParameters.MEMORY, "0"));
        int instances = (Integer) getPropertyValue(parametersList, SupportedParameters.INSTANCES, 0);
        List<String> uris = getApplicationUris(module, parametersList);
        List<String> tempUris = getTempApplicationUris(module, parametersList);
        List<String> services = getApplicationServices(module, true);
        Map<Object, Object> env = getApplicationEnv(module, module.getProperties(), ((Module) module).getParameters(),
            getApplicationServices(module, false));
        Map<String, Map<String, Object>> bindingParameters = getBindingParameters((Module) module);
        return createCloudApplication(getApplicationName(module), module.getName(), staging, diskQuota, memory, instances, uris, tempUris,
            services, env, bindingParameters);
    }

    protected CloudApplicationExtended createCloudApplication(String name, String moduleName, Staging staging, int diskQuota, int memory,
        int instances, List<String> uris, List<String> tempUris, List<String> services, Map<Object, Object> env,
        Map<String, Map<String, Object>> bindingParameters) {
        CloudApplicationExtended app = super.createCloudApplication(name, moduleName, staging, diskQuota, memory, instances, uris, tempUris,
            services, env);
        if (bindingParameters != null) {
            app.setBindingParameters(bindingParameters);
        }
        return app;
    }

    protected Map<String, Map<String, Object>> getBindingParameters(Module module) throws SLException {
        Map<String, Map<String, Object>> result = new HashMap<>();
        for (RequiredDependency dependency : module.getRequiredDependencies2_0()) {
            addBindingParameters(result, dependency, module);
        }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    protected void addBindingParameters(Map<String, Map<String, Object>> result, RequiredDependency dependency, Module module)
        throws SLException {
        Resource resource = (Resource) getResource(dependency.getName());
        if (resource != null) {
            MapUtil.addNonNull(result, getServiceName(resource.getName()), getBindingParameters(dependency, module.getName()));
        }
    }

    @SuppressWarnings("unchecked")
    protected Map<String, Object> getBindingParameters(RequiredDependency dependency, String moduleName) throws ContentException {
        Object bindingParameters = dependency.getParameters().get(SupportedParameters.SERVICE_BINDING_CONFIG);
        if (bindingParameters == null) {
            return null;
        }
        if (!(bindingParameters instanceof Map)) {
            throw new ContentException(getInvalidServiceBindingConfigTypeErrorMessage(moduleName, dependency.getName(), bindingParameters));
        }
        return (Map<String, Object>) bindingParameters;
    }

    protected String getInvalidServiceBindingConfigTypeErrorMessage(String moduleName, String dependencyName, Object bindingParameters) {
        String prefix = ValidatorUtil.getPrefixedName(moduleName, dependencyName);
        return MessageFormat.format(com.sap.cloud.lm.sl.mta.message.Messages.INVALID_TYPE_FOR_KEY,
            ValidatorUtil.getPrefixedName(prefix, SupportedParameters.SERVICE_BINDING_CONFIG), Map.class.getSimpleName(),
            bindingParameters.getClass().getSimpleName());
    }

    protected void warnAboutUnsupportedParameters(List<Map<String, Object>> fullParametersList) {
        Map<String, Object> merged = mergeProperties(fullParametersList);
        removeSpecialApplicationProperties(merged);
        removeSpecialServiceProperties(merged);
        for (String parameterName : merged.keySet()) {
            LOGGER.warn(MessageFormat.format(Messages.UNSUPPORTED_PARAMETER, parameterName));
        }
    }

    @Override
    protected void addDependencies(Map<String, Object> env, com.sap.cloud.lm.sl.mta.model.v1_0.Module module) {
        Map<String, List<Object>> groups = new TreeMap<>();
        for (RequiredDependency requiredDependency : ((Module) module).getRequiredDependencies2_0()) {
            addDependency(requiredDependency, env, groups);
        }
        env.putAll(groups);
    }

    protected void addDependency(RequiredDependency dependency, Map<String, Object> env, Map<String, List<Object>> groups) {
        if (dependency.getList() != null) {
            dependency.setGroup(dependency.getList());
        }
        addToGroupsOrEnvironment(env, groups, asList(dependency.getGroup()), dependency.getName(), dependency.getProperties());
    }

    @Override
    protected List<String> getApplicationServices(com.sap.cloud.lm.sl.mta.model.v1_0.Module module, boolean addExisting)
        throws SLException {
        return getApplicationServices((Module) module, addExisting);
    }

    protected List<String> getApplicationServices(Module module, boolean addExisting) throws SLException {
        List<String> services = new ArrayList<>();
        for (RequiredDependency dependency : module.getRequiredDependencies2_0()) {
            Pair<com.sap.cloud.lm.sl.mta.model.v1_0.Resource, ServiceType> pair = getApplicationService(dependency.getName());
            if (pair != null && shouldAddServiceToList(pair._2, addExisting)) {
                ListUtil.addNonNull(services, getServiceName(pair._1, pair._2));
            }
        }
        return services;
    }

    @Override
    protected Pair<com.sap.cloud.lm.sl.mta.model.v1_0.Resource, ServiceType> getApplicationService(String dependencyName) {
        Resource resource = (Resource) getResource(dependencyName);
        if (resource != null && isService(resource)) {
            ServiceType serviceType = getServiceType(resource.getParameters());
            return new Pair<>(resource, serviceType);
        }
        return null;
    }

    @Override
    protected void addToGroup(Map<String, List<Object>> groups, String group, String name, Map<String, Object> properties) {
        groups.computeIfAbsent(group, key -> new ArrayList<>()).add(properties);
    }

}