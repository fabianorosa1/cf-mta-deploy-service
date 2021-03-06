package com.sap.cloud.lm.sl.cf.web.ds;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.sap.cloud.lm.sl.persistence.services.AbstractFileService;
import com.sap.cloud.lm.sl.persistence.services.DatabaseFileService;
import com.sap.cloud.lm.sl.persistence.services.FileSystemFileService;

public class FileServicePicker implements FactoryBean<AbstractFileService>, InitializingBean {

    private DatabaseFileService databaseFileService;
    private FileSystemFileService fileSystemFileService;
    private AbstractFileService fileService;

    public void setDatabaseFileService(DatabaseFileService databaseFileService) {
        this.databaseFileService = databaseFileService;
    }

    public void setFileSystemFileService(FileSystemFileService fileSystemFileService) {
        this.fileSystemFileService = fileSystemFileService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.fileService = fileSystemFileService != null ? fileSystemFileService : databaseFileService;
    }

    @Override
    public AbstractFileService getObject() throws Exception {
        return fileService;
    }

    @Override
    public Class<?> getObjectType() {
        return AbstractFileService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
