package com.sap.cloud.lm.sl.cf.process;

public final class Constants {

    public static final String DEPLOY_SERVICE_ID = "xs2-deploy";
    public static final String BLUE_GREEN_DEPLOY_SERVICE_ID = "xs2-bg-deploy";
    public static final String UNDEPLOY_SERVICE_ID = "xs2-undeploy";
    public static final String CTS_PING_SERVICE_ID = "CTS_PING";
    public static final String CTS_DEPLOY_SERVICE_ID = "CTS_DEPLOY";
    public static final String SERVICE_VERSION_1_1 = "1.1";
    public static final String SERVICE_VERSION_1_0 = "1.0";

    public static final String PARAM_APP_ARCHIVE_ID = "appArchiveId";
    public static final String PARAM_PLATFORM_NAME = "targetPlatform";
    public static final String PARAM_EXT_DESCRIPTOR_FILE_ID = "mtaExtDescriptorId";
    public static final String PARAM_NO_START = "noStart";
    public static final String PARAM_START_TIMEOUT = "startTimeout";
    public static final String PARAM_UPLOAD_TIMEOUT = "uploadTimeout";
    public static final String PARAM_USE_NAMESPACES = "useNamespaces";
    public static final String PARAM_USE_NAMESPACES_FOR_SERVICES = "useNamespacesForServices";
    public static final String PARAM_ALLOW_INVALID_ENV_NAMES = "allowInvalidEnvNames";
    public static final String PARAM_KEEP_APP_ATTRIBUTES = "keepAppAttributes";
    public static final String PARAM_STREAM_APP_LOGS = "streamAppLogs";
    public static final String PARAM_SIM_ERROR_TASK = "simErrorTask";
    public static final String PARAM_INITIATOR = "initiator";
    public static final String PARAM_VERSION_RULE = "versionRule";
    public static final String PARAM_DELETE_SERVICES = "deleteServices";
    public static final String PARAM_DELETE_SERVICE_KEYS = "deleteServiceKeys";
    public static final String PARAM_DELETE_SERVICE_BROKERS = "deleteServiceBrokers";
    public static final String PARAM_FAIL_ON_CRASHED = "failOnCrashed";
    public static final String PARAM_MTA_ID = "mtaId";
    public static final String PARAM_KEEP_FILES = "keepFiles";
    public static final String PARAM_NO_CONFIRM = "noConfirm";
    public static final String PARAM_NO_RESTART_SUBSCRIBED_APPS = "noRestartSubscribedApps";
    public static final String PARAM_GIT_URI = "gitUri";
    public static final String PARAM_GIT_REF = "gitRef";
    public static final String PARAM_GIT_REPO_PATH = "gitRepoPath";
    public static final String PARAM_GIT_SKIP_SSL = "gitSkipSsl";

    public static final String VAR_USER = "user";

    public static final String VAR_MTA_MANIFEST = "mtaManifest";
    public static final String VAR_MTA_DEPLOYMENT_DESCRIPTOR_STRING = "mtaDeploymentDescriptorString";
    public static final String VAR_MTA_EXTENSION_DESCRIPTOR_STRINGS = "mtaExtensionDescriptorStrings";
    public static final String VAR_MTA_DEPLOYMENT_DESCRIPTOR = "mtaDeploymentDescriptor";
    public static final String VAR_MTA_MAJOR_SCHEMA_VERSION = "mtaMajorSchemaVersion";
    public static final String VAR_MTA_MINOR_SCHEMA_VERSION = "mtaMinorSchemaVersion";
    public static final String VAR_MTA_VERSION_ACCEPTED = "mtaVersionAccepted";
    public static final String VAR_MTA_MODULE_CONTENT_PREFIX = "mtaModuleContent_";
    public static final String VAR_MTA_MODULE_FILE_NAME_PREFIX = "mtaModuleFileName_";
    public static final String VAR_MTA_REQUIRES_FILE_NAME_PREFIX = "mtaRequiresFileName_";
    public static final String VAR_MTA_RESOURCE_FILE_NAME_PREFIX = "mtaResourceFileName_";
    public static final String VAR_MTA_ARCHIVE_MODULES = "mtaArchiveModules";
    public static final String VAR_MTA_MODULES = "mtaModules";
    public static final String VAR_NEW_MTA_VERSION = "newMtaVersion";

    public static final String VAR_XS_PLACEHOLDER_REPLACEMENT_VALUES = "xsPlaceholderReplacementValues";
    public static final String VAR_DEPLOYED_MTA = "deployedMta";
    public static final String VAR_SYSTEM_PARAMETERS = "systemParameters";
    public static final String VAR_PORT_BASED_ROUTING = "portBasedRouting";
    public static final String VAR_ALLOCATED_PORTS = "allocatedPorts";
    public static final String VAR_PLATFORM = "platform";
    public static final String VAR_PLATFORM_TYPE = "platformType";
    public static final String VAR_ORG = "org";
    public static final String VAR_SPACE = "space";
    public static final String VAR_CUSTOM_DOMAINS = "customDomains";
    public static final String VAR_DEPLOYED_APPS = "deployedApps";
    public static final String VAR_SERVICES_TO_CREATE = "servicesToCreate";
    public static final String VAR_SERVICE_KEYS_TO_CREATE = "serviceKeysToCreate";
    public static final String VAR_APPS_TO_DEPLOY = "appsToDeploy";
    public static final String VAR_APPS_INDEX = "appsIndex";
    public static final String VAR_APPS_SIZE = "appsSize";
    public static final String VAR_APPS_TO_UNDEPLOY = "appsToUndeploy";
    public static final String VAR_SERVICES_TO_DELETE = "servicesToDelete";
    public static final String VAR_SERVICE_URLS_TO_REGISTER = "serviceUrlsToRegister";
    public static final String VAR_SERVICE_BROKERS_TO_CREATE = "serviceBrokersToCreate";
    public static final String VAR_SUBSCRIPTIONS_TO_CREATE = "subscriptionsToCreate";
    public static final String VAR_SUBSCRIPTIONS_TO_DELETE = "subscriptionsToDelete";
    public static final String VAR_DEPENDENCIES_TO_PUBLISH = "dependenciesToPublish";
    public static final String VAR_EXISTING_APP = "existingApp";
    public static final String VAR_START_TIME = "startTime";
    public static final String VAR_START_PHASE = "startPhase";
    public static final String VAR_STARTING_INFO = "startingInfo";
    public static final String VAR_STARTING_INFO_CLASSNAME = "startingInfoClass";
    public static final String VAR_STREAMING_LOGS_TOKEN = "streamingLogsToken";
    public static final String VAR_OFFSET = "offset";
    public static final String VAR_UPLOAD_TOKEN = "uploadToken";
    public static final String VAR_CONTROLLER_POLLING_INTERVAL = "controllerPollingInterval";
    public static final String VAR_UPLOAD_APP_TIMEOUT = "uploadAppTimeout";
    public static final String VAR_PUBLISHED_ENTRIES = "publishedEntries";
    public static final String VAR_DELETED_ENTRIES = "deletedEntries";
    public static final String VAR_RESTART_APPLICATION = "restartApplication";
    public static final String VAR_APP_PROPERTIES_CHANGED = "appPropertiesChanged";
    public static final String VAR_UPDATED_SERVICES = "updatedServices";
    public static final String VAR_CTS_RETURN_CODE = "ctsReturnCode";
    public static final String VAR_CTS_CURRENT_FILE_INFO = "ctsCurrentFileInfo";

    public static final String START_PHASE_STAGING = "staging";
    public static final String START_PHASE_STARTUP = "startup";
    public static final String START_PHASE_EXECUTION = "execution";

    public static final String PARAM_TRANSFER_TYPE = "transferType";
    public static final String PARAM_CTS_PROCESS_ID = "ctsProcessId";
    public static final String PARAM_DEPLOY_URI = "deployUri";
    public static final String PARAM_APPLICATION_TYPE = "applType";
    public static final String PARAM_FILE_LIST = "fileList";
    public static final String PARAM_USERNAME = "userId";
    public static final String PARAM_PASSWORD = "password";

}