package com.sap.cloud.lm.sl.cf.core.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloudfoundry.client.lib.CloudFoundryOperations;
import org.cloudfoundry.client.lib.domain.CloudEntity;

import com.sap.cloud.lm.sl.cf.client.CloudFoundryOperationsExtended;
import com.sap.cloud.lm.sl.cf.client.util.TokenUtil;
import com.sap.cloud.lm.sl.cf.core.cf.CloudFoundryClientProvider;
import com.sap.cloud.lm.sl.cf.core.helpers.ClientHelper;
import com.sap.cloud.lm.sl.cf.core.message.Messages;
import com.sap.cloud.lm.sl.common.NotFoundException;
import com.sap.cloud.lm.sl.common.SLException;
import com.sap.cloud.lm.sl.common.util.Pair;

public class AuthorizationUtil {

    private static final String SPACE_CACHE_SEPARATOR = "|";

    public static String getSpaceId(CloudFoundryClientProvider clientProvider, UserInfo userInfo, String orgName, String spaceName,
        String processId) throws SLException {
        CloudFoundryOperations client = getCloudFoundryClient(clientProvider, userInfo, orgName, spaceName, processId);
        String spaceId = new ClientHelper(client).computeSpaceId(orgName, spaceName);
        if (spaceId == null) {
            throw new SLException(MessageFormat.format(Messages.COULD_NOT_COMPUTE_SPACE_ID, orgName, spaceName));
        }
        return spaceId;
    }

    private static Map<String, String> processSpaceCache = new HashMap<>();

    public static String getProcessSpaceId(String processId, CloudFoundryClientProvider clientProvider, UserInfo userInfo, String orgName,
        String spaceName) throws SLException {
        String spaceCacheKey = getSpaceCacheKey(orgName, spaceName, processId);
        String spaceId = processSpaceCache.get(spaceCacheKey);
        if (spaceId == null) {
            spaceId = getSpaceId(clientProvider, userInfo, orgName, spaceName, processId);
            if (processId != null) {
                processSpaceCache.put(spaceCacheKey, spaceId);
            }
        }
        return spaceId;
    }

    private static String getSpaceCacheKey(String orgName, String spaceName, String processId) {
        return new StringBuilder().append(orgName).append(SPACE_CACHE_SEPARATOR).append(spaceName).append(SPACE_CACHE_SEPARATOR).append(
            processId).toString();
    }

    public static boolean checkPermissions(CloudFoundryClientProvider clientProvider, UserInfo userInfo, String orgName, String spaceName,
        boolean readOnly, String processId) throws SLException {
        if (ConfigurationUtil.areDummyTokensEnabled() && isDummyToken(userInfo))
            return true;
        if (isAdminUser(userInfo) || hasAdminScope(userInfo))
            return true;
        CloudFoundryOperations client = getCloudFoundryClient(clientProvider, userInfo);
        return checkPermissions(client, userInfo, orgName, spaceName, readOnly);
    }

    public static boolean checkPermissions(CloudFoundryClientProvider clientProvider, UserInfo userInfo, String spaceGuid, boolean readOnly)
        throws SLException {
        if (ConfigurationUtil.areDummyTokensEnabled() && isDummyToken(userInfo))
            return true;
        if (isAdminUser(userInfo) || hasAdminScope(userInfo))
            return true;
        CloudFoundryOperations client = getCloudFoundryClient(clientProvider, userInfo);
        Pair<String, String> location = new ClientHelper(client).computeOrgAndSpace(spaceGuid);
        if (location == null) {
            throw new NotFoundException(Messages.ORG_AND_SPACE_NOT_FOUND, spaceGuid);
        }
        return checkPermissions(client, userInfo, location._1, location._2, readOnly);
    }

    private static boolean checkPermissions(CloudFoundryOperations client, UserInfo userInfo, String orgName, String spaceName,
        boolean readOnly) {
        CloudFoundryOperationsExtended clientx = (CloudFoundryOperationsExtended) client;

        return hasAccess(clientx, orgName, spaceName) && hasPermissions(clientx, userInfo.getId(), orgName, spaceName, readOnly);
    }

    private static boolean hasPermissions(CloudFoundryOperationsExtended client, String userId, String orgName, String spaceName,
        boolean readOnly) {
        if (client.getSpaceDevelopers2(orgName, spaceName).contains(userId))
            return true;
        if (readOnly) {
            if (client.getSpaceAuditors2(orgName, spaceName).contains(userId))
                return true;
            if (client.getSpaceManagers2(orgName, spaceName).contains(userId))
                return true;
        }
        return false;
    }

    private static boolean hasAccess(CloudFoundryOperationsExtended client, String orgName, String spaceName) {
        return containsEntity(client.getOrganizations(), orgName) && containsEntity(client.getSpaces(), spaceName);
    }

    private static boolean containsEntity(List<? extends CloudEntity> entities, String entityName) {
        for (CloudEntity entity : entities) {
            if (entity.getName().equals(entityName)) {
                return true;
            }
        }
        return false;
    }

    private static CloudFoundryOperations getCloudFoundryClient(CloudFoundryClientProvider clientProvider, UserInfo userInfo)
        throws SLException {
        return clientProvider.getCloudFoundryClient(userInfo.getToken());
    }

    private static CloudFoundryOperations getCloudFoundryClient(CloudFoundryClientProvider clientProvider, UserInfo userInfo,
        String orgName, String spaceName, String processId) throws SLException {
        return clientProvider.getCloudFoundryClient(userInfo.getToken());
    }

    private static boolean isDummyToken(UserInfo userInfo) {
        return userInfo.getToken().getValue().equals(TokenUtil.DUMMY_TOKEN);
    }

    private static boolean isAdminUser(UserInfo userInfo) {
        return userInfo.getName().equals(ConfigurationUtil.getAdminUsername());
    }

    private static boolean hasAdminScope(UserInfo userInfo) {
        return userInfo.getToken().getScope().contains(TokenUtil.SCOPE_CC_ADMIN);
    }
}