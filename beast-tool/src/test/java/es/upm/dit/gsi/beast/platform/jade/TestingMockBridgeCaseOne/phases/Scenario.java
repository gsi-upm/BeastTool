package es.upm.dit.gsi.beast.platform.jade.TestingMockBridgeCaseOne.phases;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import jade.core.behaviours.CyclicBehaviour;
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
   *   one bridge mock and one listener mock in Jade Platform
   * 
   */
    public void startAgents() {

        //ListenerAgent configuration
        AgentBehaviour myMockedBehaviour =   mock(AgentBehaviour.class);
        MockConfiguration mock_configuration = new MockConfiguration();
        mock_configuration.setDFServiceName("listen_service");
        mock_configuration.setBehaviour(myMockedBehaviour);
        MockManager.startMockJadeAgent("ListenerAgent",Definitions.JADE_LISTENER_MOCK_PATH,mock_configuration,this);
        
        //BridgeBehaviour configuration
        AgentBehaviour myMockedBehaviour2 =  mock(AgentBehaviour.class);
        // In jade, the FIPA Inform is a constant in ACLMessage, but is stored as an int
        when(myMockedBehaviour2.processMessage(eq(ACLMessage.getPerformative(ACLMessage.INFORM)),eq("BeastMessenger"),eq("send")))
            .thenReturn("ListenerAgent", ACLMessage.getPerformative(ACLMessage.INFORM), "Message to the listener agent");
        MockConfiguration mock_configuration2 = new MockConfiguration();
        mock_configuration2.setDFServiceName("bridge_service");
        mock_configuration2.setBehaviour(myMockedBehaviour2);
        MockManager.startMockJadeAgent("BridgeAgent",Definitions.JADE_BRIDGE_MOCK_PATH,mock_configuration2,this);

        // Debug
        //System.out.println("Scenario completed");


       //EXAMPLE for Jadex: startAgent("Steve", "org.example.Steve.agent.xml"); // This xml file is the jadex agent description file (ADF)
       //EXAMPLE for Jade: startAgent("Steve", "org.example.Steve"); // This string is the agent class Steve.java that extends Jade Agent class
  }
}
