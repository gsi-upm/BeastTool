package es.upm.dit.gsi.beast.platform.jade.TestingMockRepository.phases;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import es.upm.dit.gsi.beast.mock.MockManager;
import es.upm.dit.gsi.beast.mock.common.AgentBehaviour;
import es.upm.dit.gsi.beast.mock.common.Definitions;
import es.upm.dit.gsi.beast.mock.common.MockConfiguration;

/**
 * This is the class that must create the Scenario. It is related with the GIVEN
 * part.
 * 
 * In startAgents method the following method must be used
 * startAgent(agent_name,agent_path)
 * 
 * @author es.upm.dit.gsi.beast
 */

public class Scenario extends es.upm.dit.gsi.beast.story.phases.Scenario {
    /**
     * Here the description given by the client must be written, which is:
     * 
     * one bridge mock and one repository mock in Jade Platform
     * 
     */
    public void startAgents() {

//        // Start and configure Bridge Agent
//        MockConfiguration bridgeMockConfiguration = new MockConfiguration();
//        
//        //Add the personlized service name.  
//        bridgeMockConfiguration.setDFServiceName(Definitions.BRIDGE_SERVICE_NAME);
//        
//        //Add a mocked AgentBehaviour. 
//        AgentBehaviour bridgeMockedBehaviour = mock(AgentBehaviour.class);
//        when(bridgeMockedBehaviour.processMessage(eq("inform"), eq("send")))
//        .thenReturn("repository_service", "SFipa.REQUEST", "hi");   
//        bridgeMockConfiguration.setBehaviour(bridgeMockedBehaviour);
//        //Start the agent. 
//        MockManager.startMockJadeAgent(Definitions.BRIDGE_AGENT_NAME, Definitions.JADE_BRIDGE_MOCK_PATH,
//                                        bridgeMockConfiguration, this);
 
        
        //Start and configure Bridge Agent
        MockConfiguration repositoryMockConfiguration = new MockConfiguration();
        
        //Add the personlized service name.
        repositoryMockConfiguration.setDFServiceName(Definitions.REPOSITORY_SERVICE_NAME);
        
        //Add a mocked AgentBehaviour.
        AgentBehaviour repositoryMockBehaviour = mock(AgentBehaviour.class);
        when(repositoryMockBehaviour.processMessage(eq("REQUEST"), eq("BeastMessenger"),eq("hi")))
        .thenReturn(Definitions.STORE_ATTEMPT_OK);   
        repositoryMockConfiguration.setBehaviour(repositoryMockBehaviour);
        //Start the agent. 
        MockManager.startMockJadeAgent(Definitions.REPOSITORY_AGENT_NAME, Definitions.JADE_REPOSITORY_MOCK_PATH,
                                        repositoryMockConfiguration, this);

    }

}
