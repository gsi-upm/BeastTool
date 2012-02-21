package es.upm.dit.gsi.beast.platform.jade.TestingMockRepository.phases;

import static org.mockito.Mockito.mock;
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

        // Start and configure Bridge Agent
        MockConfiguration bridgeMockConfiguration = new MockConfiguration();
        bridgeMockConfiguration.setDFServiceName(Definitions.BRIDGE_SERVICE_NAME);
        bridgeMockConfiguration.setBehaviour(mock(AgentBehaviour.class));
        MockManager.startMockJadeAgent(Definitions.BRIDGE_AGENT_NAME, Definitions.JADE_BRIDGE_MOCK_PATH,
                                        bridgeMockConfiguration, this);

        MockConfiguration repositoryMockConfiguration = new MockConfiguration();
        repositoryMockConfiguration.setDFServiceName(Definitions.REPOSITORY_SERVICE_NAME);
        repositoryMockConfiguration.setBehaviour(mock(AgentBehaviour.class));
        MockManager.startMockJadeAgent(Definitions.REPOSITORY_AGENT_NAME, Definitions.JADE_REPOSITORY_MOCK_PATH,
                                        repositoryMockConfiguration, this);

    }

}
