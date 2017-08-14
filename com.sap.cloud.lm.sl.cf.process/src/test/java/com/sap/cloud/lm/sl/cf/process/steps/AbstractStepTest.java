package com.sap.cloud.lm.sl.cf.process.steps;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.activiti.engine.delegate.DelegateExecution;
import org.cloudfoundry.client.lib.CloudFoundryOperations;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.sap.activiti.common.ExecutionStatus;
import com.sap.activiti.common.impl.AbstractActivitiStep;
import com.sap.activiti.common.impl.MockDelegateExecution;
import com.sap.cloud.lm.sl.cf.client.ClientExtensions;
import com.sap.cloud.lm.sl.cf.core.cf.CloudFoundryClientProvider;
import com.sap.cloud.lm.sl.cf.process.Constants;
import com.sap.cloud.lm.sl.persistence.services.AbstractFileService;
import com.sap.cloud.lm.sl.persistence.services.ProgressMessageService;
import com.sap.cloud.lm.sl.slp.activiti.ActivitiFacade;
import com.sap.cloud.lm.sl.slp.services.ProcessLoggerProviderFactory;
import com.sap.cloud.lm.sl.slp.services.ProcessLogsPersistenceService;
import com.sap.cloud.lm.sl.slp.services.TaskExtensionService;

public abstract class AbstractStepTest<T extends AbstractActivitiStep> {

    protected static final String USER_NAME = "dummy";
    protected static final String ORG_NAME = "org";
    protected static final String SPACE_NAME = "space";
    protected String TEST_CORRELATION_ID = "test";

    protected DelegateExecution context = MockDelegateExecution.createSpyInstance();
    @Spy
    @InjectMocks
    protected ProcessLoggerProviderFactory processLoggerProviderFactory = new ProcessLoggerProviderFactory();
    @Mock
    protected ProcessLogsPersistenceService processLogsPersistenceService;
    @Mock
    protected ProgressMessageService progressMessageService;
    @Mock
    protected AbstractFileService fileService;
    @Mock
    protected TaskExtensionService taskExtensionService;
    @Mock(extraInterfaces = ClientExtensions.class)
    protected CloudFoundryOperations client;
    protected ClientExtensions clientExtensions;
    @Mock
    protected CloudFoundryClientProvider clientProvider;
    @Mock
    protected ActivitiFacade activitiFacade;
    @InjectMocks
    protected final T step = createStep();

    protected abstract T createStep();

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        this.clientExtensions = (ClientExtensions) client;
        context.setVariable(Constants.VAR_SPACE, SPACE_NAME);
        context.setVariable(Constants.VAR_USER, USER_NAME);
        context.setVariable(Constants.VAR_ORG, ORG_NAME);
        when(clientProvider.getCloudFoundryClient(anyString(), anyString(), anyString(), anyString())).thenReturn(client);
        context.setVariable("correlationId", getCorrelationId());
    }

    protected void assertStepFinishedSuccessfully() {
        assertEquals(ExecutionStatus.SUCCESS.toString(), getExecutionStatus());
    }

    protected String getExecutionStatus() {
        return (String) context.getVariable(com.sap.activiti.common.Constants.STEP_NAME_PREFIX + step.getLogicalStepName());
    }

    protected String getCorrelationId() {
        return TEST_CORRELATION_ID;
    }

}
