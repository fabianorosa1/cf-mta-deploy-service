package com.sap.cloud.lm.sl.cf.web.ds;

import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.PooledServiceConnectorConfig.PoolConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;

public class CloudDataSourceFactoryBean implements FactoryBean<DataSource>, InitializingBean {

    private String serviceName;
    private DataSource defaultDataSource;
    private DataSource dataSource;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public DataSource getDefaultDataSource() {
        return defaultDataSource;
    }

    public void setDefaultDataSource(DataSource dataSource) {
        this.defaultDataSource = dataSource;
    }

    @Override
    public void afterPropertiesSet() {
        DataSource ds = getCloudDataSource(serviceName);
        dataSource = (ds != null) ? ds : defaultDataSource;
    }

    @Override
    public DataSource getObject() {
        return dataSource;
    }

    @Override
    public Class<?> getObjectType() {
        return DataSource.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    private static DataSource getCloudDataSource(String serviceName) {
        DataSource dataSource = null;
        try {
            if (serviceName != null && !serviceName.isEmpty()) {
                CloudFactory cloudFactory = new CloudFactory();
                Cloud cloud = cloudFactory.getCloud();
                DataSourceConfig config = new DataSourceConfig(new PoolConfig(15, 30000), null);
                dataSource = cloud.getServiceConnector(serviceName, DataSource.class, config);
            }
        } catch (CloudException e) {
            // Do nothing
        }
        return dataSource;
    }
}