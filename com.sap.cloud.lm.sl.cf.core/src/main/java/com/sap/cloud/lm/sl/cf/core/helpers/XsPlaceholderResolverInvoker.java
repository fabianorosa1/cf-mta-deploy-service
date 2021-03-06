package com.sap.cloud.lm.sl.cf.core.helpers;

import java.util.Map;
import java.util.Set;

import com.sap.cloud.lm.sl.cf.core.cf.HandlerFactory;
import com.sap.cloud.lm.sl.cf.core.helpers.v1_0.PropertiesAccessor;
import com.sap.cloud.lm.sl.cf.core.model.SupportedParameters;
import com.sap.cloud.lm.sl.common.util.MapUtil;
import com.sap.cloud.lm.sl.mta.helpers.SimplePropertyVisitor;
import com.sap.cloud.lm.sl.mta.helpers.VisitableObject;
import com.sap.cloud.lm.sl.mta.model.ElementContext;
import com.sap.cloud.lm.sl.mta.model.PropertiesContainer;
import com.sap.cloud.lm.sl.mta.model.Visitor;
import com.sap.cloud.lm.sl.mta.model.v1_0.Module;
import com.sap.cloud.lm.sl.mta.model.v1_0.ProvidedDependency;
import com.sap.cloud.lm.sl.mta.model.v1_0.Resource;
import com.sap.cloud.lm.sl.mta.model.v2_0.RequiredDependency;

public class XsPlaceholderResolverInvoker extends Visitor implements SimplePropertyVisitor {

    private XsPlaceholderResolver resolver;
    private int majorSchemaVersion;
    private PropertiesAccessor propertiesAccessor;

    public XsPlaceholderResolverInvoker(int majorSchemaVersion, XsPlaceholderResolver resolver) {
        this.resolver = resolver;
        this.majorSchemaVersion = majorSchemaVersion;
        this.propertiesAccessor = new HandlerFactory(majorSchemaVersion).getPropertiesAccessor();
    }

    @Override
    public void visit(ElementContext context, Module module) {
        resolveParameters(module, SupportedParameters.APP_PROPS);
    }

    @Override
    public void visit(ElementContext context, ProvidedDependency providedDependency) {
        if (majorSchemaVersion != 1) {
            return; // Only v1 of the MTA spec supports 'parameters' in provided dependencies.
        }
        resolveParameters(providedDependency, SupportedParameters.APP_PROPS);
    }

    @Override
    public void visit(ElementContext context, RequiredDependency requiredDependency) {
        resolveParameters(requiredDependency, SupportedParameters.APP_PROPS);
    }

    @Override
    public void visit(ElementContext context, Resource resource) {
        resolveParameters(resource, SupportedParameters.SERVICE_PROPS);
    }

    @SuppressWarnings("unchecked")
    private void resolveParameters(PropertiesContainer propertiesContainer, Set<String> supportedParameters) {
        Map<String, Object> parameters = propertiesAccessor.getParameters(propertiesContainer, supportedParameters);
        Map<String, Object> properties = propertiesAccessor.getProperties(propertiesContainer);
        Map<String, Object> resolvedParameters = (Map<String, Object>) new VisitableObject(parameters).accept(this);
        propertiesAccessor.setParameters(propertiesContainer, MapUtil.merge(properties, resolvedParameters));
    }

    @Override
    public Object visit(String key, String value) {
        return resolver.resolve(value);
    }

}
