package es.upm.dit.gsi.beast.platform.jade.TestingMockBridgeCaseTwo.phases;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import jade.lang.acl.ACLMessage;
import es.upm.dit.gsi.beast.mock.MockManager;
import es.upm.dit.gsi.beast.mock.common.AgentBehaviour;
import es.upm.dit.gsi.beast.mock.common.Definitions;
import es.upm.dit.gsi.beast.mock.common.MockConfiguration;

/**  
 * This is the class that must create the Scenario.
 * It is related with the GIVEN part.
 *  
 * In startAgents method the following method must be used
 *    startAgent(agent_name,agent_path)
 * 
 * @author es.upm.dit.gsi.beast
 */

public class Scenario extends es.upm.dit.gsi.beast.story.phases.Scenario {
/**  
   * Here the description given by the client must be written,
   * which is: 
   *  
   *   one repository mock and one bridge mock in Jade Platform
   * 
   */
    public void startAgents() {
        // RepositoryAgent configuration
        AgentBehaviour myMockedBehaviour =   mock(AgentBehaviour.class);
        MockConfiguration mock_configuration = new MockConfiguration();
        mock_configuration.setDFServiceName(Definitions.REPOSITORY_SERVICE_NAME);
        mock_configuration.setBehaviour(myMockedBehaviour);
        MockManager.startMockJadeAgent(Definitions.REPOSITORY_AGENT_NAME,Definitions.JADE_REPOSITORY_MOCK_PATH,mock_configuration,this);
        
        //BridgeBehaviour configuration
        AgentBehaviour myMockedBehaviour2 =  mock(AgentBehaviour.class);
        // In jade, the FIPA Inform is a constant in ACLMessage, but is stored as an int
        when(myMockedBehaviour2.processMessage(eq(ACLMessage.getPerformative(ACLMessage.INFORM)),eq("store")))
            .thenReturn(Definitions.REPOSITORY_AGENT_NAME, ACLMessage.getPerformative(ACLMessage.INFORM), "Message to the repository agent");
        MockConfiguration mock_configuration2 = new MockConfiguration();
        mock_configuration2.setDFServiceName(Definitions.BRIDGE_SERVICE_NAME);
        mock_configuration2.setBehaviour(myMockedBehaviour2);
        MockManager.startMockJadeAgent(Definitions.BRIDGE_AGENT_NAME,Definitions.JADE_BRIDGE_MOCK_PATH,mock_configuration2,this);
        System.out.println("Scenario complete");
  }

}
