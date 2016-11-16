package com.sap.cloud.lm.sl.cf.core.message;

/**
 * A collection of string constants used for exception and logging messages.
 */
public final class Messages {

    // Exception messages
    public static final String NO_VALID_TOKEN_FOUND = "No valid access token was found for user \"{0}\"";
    public static final String TOKEN_EXPIRED = "The access token asocieated with user \"{0}\" has expired";
    public static final String CANT_CREATE_CLIENT = "Could not create client";
    public static final String CANT_CREATE_CLIENT_2 = "Could not create client in organization \"{0}\" and space \"{1}\"";
    public static final String UNAUTHORISED_OPERATION_ORG_SPACE = "Not authorized to perform operation \"{0}\" in organization \"{1}\" and space \"{2}\"";
    public static final String UNAUTHORISED_OPERATION_SPACE_ID = "Not authorized to perform operation \"{0}\" in space with ID \"{1}\"";
    public static final String PERMISSION_CHECK_FAILED_ORG_SPACE = "Could not check for permission to perform operation \"{0}\" in organization \"{1}\" and space \"{2}\"";
    public static final String PERMISSION_CHECK_FAILED_SPACE_ID = "Could not check for permission to perform operation \"{0}\" in space with ID \"{1}\"";
    public static final String ORG_AND_SPACE_NOT_FOUND = "Could not find organization and space by space ID \"{0}\"";
    public static final String ARCHIVE_MODULE_NOT_INTENDED_FOR_DEPLOYMENT = "MTA module \"{0}\" is part of MTA archive, but is not intended for deployment";
    public static final String ONGOING_OPERATION_NOT_FOUND = "Ongoing MTA operation with id \"{0}\" does not exist";
    public static final String ONGOING_OPERATION_ALREADY_EXISTS = "Ongoing MTA operation with id \"{0}\" already exists";
    public static final String MULTIPLE_OPERATIONS_WITH_LOCK_FOUND = "Multiple operations found with lock for MTA \"{0}\" in space \"{1}\"";
    public static final String PATH_SHOULD_NOT_BE_ABSOLUTE = "Path \"{0}\" should not be absolute";
    public static final String PATH_SHOULD_BE_NORMALIZED = "Path \"{0}\" should be normalized (should not contain any \".\", \"..\" or \"//\" path segments)!";
    public static final String PATH_MUST_NOT_CONTAIN_WINDOWS_SEPARATORS = "Path \"{0}\" must use only Unix separators \"/\", but contain Windows separators \"\\\"";
    public static final String UNRESOLVED_MTA_MODULES = "Unresolved MTA modules {0}, these modules are neither part of MTA archive, nor already deployed";
    public static final String INVALID_ENVIRONMENT_VARIABLE_NAME = "The name \"{0}\" is not a valid environment variable name";
    public static final String UNKNOWN_PLATFORM = "Unknown platform \"{0}\"";
    public static final String UNKNOWN_PLATFORM_TYPE = "Unknown platform type \"{0}\" for platform \"{1}\"";
    public static final String UNKNOWN_MODULE = "Unknown module \"{0}\"";
    public static final String COULD_NOT_CREATE_VALID_DOMAIN = "Could not create a valid domain from \"{0}\"";
    public static final String COULD_NOT_CREATE_VALID_HOST = "Could not create a valid host from \"{0}\"";
    public static final String CANNOT_CORRECT_PARAMETER = "Value for parameter \"{0}\" is not valid and cannot be corrected";
    public static final String TARGET_PLATFORM_ALREADY_EXISTS = "Target platform with name \"{0}\" already exists";
    public static final String TARGET_PLATFORM_NOT_FOUND = "Target platform with name \"{0}\" does not exist";
    public static final String CONFIGURATION_ENTRY_ALREADY_EXISTS = "Configuration entry with namespace ID \"{0}\", ID \"{1}\", version \"{2}\" and target space \"{3}\" already exists";
    public static final String CONFIGURATION_SUBSCRIPTION_ALREADY_EXISTS = "Configuration subscription for MTA \"{0}\", app \"{1}\" and resource \"{2}\" already exists in space \"{3}\"";
    public static final String CONFIGURATION_ENTRY_NOT_FOUND = "Configuration entry with ID \"{0}\" does not exist";
    public static final String CONFIGURATION_SUBSCRIPTION_NOT_FOUND = "Configuration subscription with ID \"{0}\" does not exist";
    public static final String ERROR_STORING_TOKEN_DUE_TO_INTEGRITY_VIOLATION = "Cannot store access token due to data integrity violation. The exception is ignored as the token and authentication are persisted by another client";
    public static final String NO_CONFIGURATION_ENTRIES_WERE_FOUND = "No configuration entries were found matching the filter specified in resource \"{0}\"";
    public static final String MULTIPLE_CONFIGURATION_ENTRIES_WERE_FOUND = "Multiple configuration entries were found matching the filter specified in resource \"{0}\"";
    public static final String CONFLICTING_APP_COLORS = "There are both blue and green applications already deployed for MTA \"{0}\"";
    public static final String UNABLE_TO_PARSE_SUBSCRIPTION = "Unable to parse configuration subscription: {0}";
    public static final String COLUMN_VALUE_SHOULD_NOT_BE_NULL = "Configuration subscription''s \"{0}\" column value should not be null";
    public static final String CONTEXT_EXTENSION_ENTRY_NOT_FOUND = "Context extension element with ID \"{0}\" does not exist";
    public static final String CONTEXT_EXTENSION_ENTRY_ALREADY_EXISTS = "Context extension element with Process ID \"{0}\", Name \"{1}\" and Value \"{2}\" already exists";
    public static final String COULD_NOT_COMPUTE_SPACE_ID = "Could not compute space ID for org \"{0}\" and space \"{1}\"";
    public static final String THE_DEPLOYMENT_DESCRIPTOR_0_SPECIFIES_NOT_SUPPORTED_MTA_VERSION_1 = "The deployment descriptor \"{0}\" specifies a non-supported MTA version \"{1}\"";
    public static final String CANNOT_CLEAN_MULTI_TARGET_APP_ASSEMBLY_TARGET_DIR_0 = "Cannot clean multi-target app assembly target dir \"{0}\"";
    public static final String FAILED_TO_COPY_FILE_0_TO_ASSEMBLY_DIRECTORY = "Failed to copy file \"{0}\" to assembly directory";
    public static final String PATH_IS_RESOLVED_TO_NOT_EXISTING_FILE = "Path \"{0}\" is resolved to a non-existing file \"{1}\"";
    public static final String FAILED_TO_READ_DEPLOYMENT_DESCRIPTOR_0 = "Failed to read deployment descriptor \"{0}\"";
    public static final String DIRECTORY_0_DOES_NOT_CONTAIN_MANDATORY_DEPLOYMENT_DESCRIPTOR_FILE_1 = "Directory \"{0}\" does not contain mandatory deployment descriptor file \"{1}\"";
    public static final String FAILED_TO_LIST_MULTI_TARGET_APP_DIRECTORY_0 = "Failed to list multi-target app directory \"{0}\"";
    public static final String CANNOT_SHORTEN_NAME_TO_N_CHARACTERS = "Cannot shorten name \"{0}\" to {1} characters";
    public static final String ERROR_GETTING_APPLICATIONS = "Error getting Cloud Foundry applications";
    public static final String ERROR_RETRIEVING_RECENT_LOGS = "Error retrieving recent logs";
    public static final String ERROR_READING_PROTOCOL_BUFFER_LOGS = "Error reading protocol buffer logs";

    // Warning messages
    public static final String ENVIRONMENT_VARIABLE_NOT_SET = "Environment variable \"{0}\" not set";
    public static final String UNKNOWN_XS_TYPE = "Unknown XS type \"{0}\", using default \"{1}\"";
    public static final String INVALID_XS_TARGET_URL = "Invalid XS target URL \"{0}\", using default \"{1}\"";
    public static final String UNKNOWN_DB_TYPE = "Unknown database type \"{0}\", using default \"{1}\"";
    public static final String INVALID_PLATFORM_TYPES = "Invalid platform types \"{0}\", using default \"{1}\"";
    public static final String INVALID_PLATFORMS = "Invalid platforms \"{0}\", using default \"{1}\"";
    public static final String INVALID_MAX_UPLOAD_SIZE = "Invalid max upload size \"{0}\", using default \"{1}\"";
    public static final String INVALID_MAX_MTA_DESCRIPTOR_SIZE = "Invalid max mta descriptor size \"{0}\", using default \"{1}\"";
    public static final String INVALID_VCAP_APPLICATION_SPACE_ID = "Invalid VCAP_APPLICATION \"{0}\", using default space ID \"{1}\"";
    public static final String INVALID_VCAP_APPLICATION_DEPLOY_SERVICE_URI = "Could not determine deploy service URI from VCAP_APPLICATION \"{0}\"";
    public static final String INVALID_VCAP_APPLICATION_ROUTER_PORT = "Invalid VCAP_APPLICATION \"{0}\", using default router port \"{1}\"";
    public static final String UNSUPPORTED_PARAMETER = "Parameter \"{0}\" is not supported, it will be ignored";
    public static final String IGNORING_LABEL_FOR_USER_PROVIDED_SERVICE = "Ignoring label \"{0}\" for service \"{1}\", as user-provided services do not support labels!";

    // Info messages
    public static final String XS_TYPE_NOT_SPECIFIED = "XS type not specified in environment, using default \"{0}\"";
    public static final String XS_TARGET_URL_NOT_SPECIFIED = "XS target URL not specified in environment, using default \"{0}\"";
    public static final String DB_TYPE_NOT_SPECIFIED = "Database type not specified in environment, using default \"{0}\"";
    public static final String PLATFORM_TYPES_NOT_SPECIFIED = "Platform types not specified in environment, using default \"{0}\"";
    public static final String PLATFORMS_NOT_SPECIFIED = "Platforms not specified in environment, using default \"{0}\"";
    public static final String MAX_UPLOAD_SIZE_NOT_SPECIFIED = "Max upload size not specified in environment, using default \"{0}\"";
    public static final String MAX_MTA_DESCRIPTOR_SIZE_NOT_SPECIFIED = "Max mta descriptor size not specified in environment, using default \"{0}\"";
    public static final String SPACE_ID_NOT_SPECIFIED = "Space ID not specified in environment, using default \"{0}\"";
    public static final String NO_APPLICATION_URIS_SPECIFIED = "No application uris specified in environment";
    public static final String ATTEMPTING_TO_RELEASE_STUCK_LOCK = "Change log lock is presumed to be stuck. Attempting to release it...";
    public static final String CURRENT_LOCK = "Change log lock was acquired at {1} by {0}";
    public static final String CURRENT_DATE = "The current date is {0}";

    public static final String XS_TYPE = "XS type: {0}";
    public static final String XS_TARGET_URL = "XS target URL: {0}";
    public static final String DB_TYPE = "Database type: {0}";
    public static final String PLATFORM_TYPES = "Platform types: {0}";
    public static final String PLATFORMS = "Platforms: {0}";
    public static final String MAX_UPLOAD_SIZE = "Max upload size: {0}";
    public static final String MAX_MTA_DESCRIPTOR_SIZE = "Max mta descriptor size: {0}";
    public static final String SCAN_UPLOADS = "Scan uploads: {0}";
    public static final String SPACE_ID = "Space ID: {0}";
    public static final String DUMMY_TOKENS_ENABLED = "Dummy tokens enabled: {0}";
    public static final String BASIC_AUTH_ENABLED = "Basic authentication enabled: {0}";
    public static final String ADMIN_USERNAME = "Superuser username: {0}";
    public static final String USE_XS_AUDIT_LOGGING = "Use XSA audit logging: {0}";
    public static final String XS_CLIENT_CORE_THREADS = "Platform client core pool size: {0}";
    public static final String XS_CLIENT_MAX_THREADS = "Platform client max threads: {0}";
    public static final String XS_CLIENT_QUEUE_CAPACITY = "Platform client queue capacity: {0}";
    public static final String XS_CLIENT_KEEP_ALIVE = "Platform client thread keep alive time: {0}";
    public static final String ASYNC_EXECUTOR_CORE_THREADS = "Async task executor core pool size: {0}";
    public static final String CONTROLLER_POLLING_INTERVAL = "Controller polling interval: {0}";
    public static final String UPLOAD_APP_TIMEOUT = "Upload app timeout: {0}";
    public static final String SKIP_SSL_VALIDATION = "Skip SSL validation: {0}";
    public static final String XS_PLACEHOLDERS_SUPPORTED = "XS placeholders supported: {0}";
    public static final String DS_VERSION = "Deploy service version: {0}";
    public static final String CHANGE_LOG_LOCK_WAIT_TIME = "Change log lock wait time: {0}";
    public static final String CHANGE_LOG_LOCK_DURATION = "Change log lock duration: {0}";
    public static final String CHANGE_LOG_LOCK_ATTEMPTS = "Change log lock attempts: {0}";

    public static final String AUDIT_LOG_ABOUT_TO_PERFORM_ACTION = "About to perform action \"{0}\"";
    public static final String AUDIT_LOG_ABOUT_TO_PERFORM_ACTION_WITH_PARAMS = "About to perform action \"{0}\" with parameters \"{1}\"";
    public static final String AUDIT_LOG_ACTION_SUCCESS = "Succesfuly performed action \"{0}\"";
    public static final String AUDIT_LOG_ACTION_FAILURE = "Failed to perform action \"{0}\"";
    public static final String AUDIT_LOGGING_FAILED = "Failed to write message to the audit log";
    public static final String AUDIT_LOG_FULL_CONFIG = "Full deploy service configuration : {0}";
    public static final String AUDIT_LOG_UPDATE_CONFIG = "Updating configuration \"{0}\" with value \"{1}\"";
    public static final String AUDIT_LOG_CREATE_CONFIG = "Creating configuration \"{0}\" with value \"{1}\"";
    public static final String AUDIT_LOG_DELETE_CONFIG = "Deleting configuration \"{0}\"";
    public static final String AUDIT_LOG_CONFIG_UPDATED = "Configuration was updated";
    public static final String AUDIT_LOG_CONFIG_UPDATE_FAILED = "Configuration update failed";
    public static final String PURGING_SUBSCRIPTIONS = "Purging configuration subscriptions for target: {0}";
    public static final String PURGING_ENTRIES = "Purging entries for target: {0}";

    // Debug messages
    public static final String EXTENSION_DESCRIPTOR = "Extension descriptor \"{0}\": {1}";
    public static final String DEPLOYMENT_DESCRIPTOR_AFTER_PARAMETER_CORRECTION = "Deployment descriptor after parameter correction: {0}";
    public static final String DEPLOYMENT_DESCRIPTOR = "Deployment descriptor: {0}";
    public static final String DEPLOYMENT_DESCRIPTOR_AFTER_CROSS_MTA_DEPENDENCY_RESOLUTION = "Deployment descriptor after cross-MTA dependency resolution: {0}";
    public static final String MERGED_DESCRIPTOR = "Merged deployment descriptor: {0}";
    public static final String RESOLVED_DEPLOYMENT_DESCRIPTOR = "Resolved deployment descriptor: {0}";
    public static final String SUBSCRIPTIONS = "Subscriptions: {0}";
    public static final String REMOVING_SENSITIVE_ELEMENT = "Removing sensitive element: {0}";
    public static final String DELETING_SUBSCRIPTION = "Deleting configuration subscription: {0}";
    public static final String DELETING_ENTRY = "Deleting configuration entry: {0}";
}