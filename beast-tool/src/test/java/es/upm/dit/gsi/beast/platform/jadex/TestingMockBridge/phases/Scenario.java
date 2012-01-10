package es.upm.dit.gsi.beast.platform.jadex.TestingMockBridge.phases;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import es.upm.dit.gsi.beast.mock.MockManager;
import es.upm.dit.gsi.beast.mock.jadex.common.AgentBehaviour;
import es.upm.dit.gsi.beast.mock.jadex.common.Definitions;
import es.upm.dit.gsi.beast.mock.jadex.common.MockConfiguration;

/**  
 * This is the class that must create the Scenario.
 * It is related with the GIVEN part.
 *  
 * In startAgents method the following method must be used
 *    super.startAgent(agent_name,agent_path)
 * 
 * @author es.upm.dit.gsi.beast
 */

public class Scenario extends es.upm.dit.gsi.beast.story.phases.Scenario {
/**  
   * Here the description given by the client must be written,
   * which is: 
   *  
   *   one bridge mock and one listener mock in Jadex Platform
   * 
   */
    public void startAgents() {
       //ListenerAgent configuration
        MockConfiguration mock_configuration = new MockConfiguration();
        mock_configuration.setDFServiceName("listen_service");
        AgentBehaviour myMockedBehaviour =   mock(AgentBehaviour.class);
        mock_configuration.setBehaviour(myMockedBehaviour);
        MockManager.startMockJadexAgent("ListenerAgent",Definitions.listenerMockPath,mock_configuration,this);

        
        //BridgeAgent configuration
        AgentBehaviour myMockedBehaviour2 =  mock(AgentBehaviour.class);
        when(myMockedBehaviour2.processMessage(eq("inform"), eq("send")))
            .thenReturn("listen_service", "SFipa.INFORM", "Message to the listener agent");   
        MockConfiguration mock_configuration2 = new MockConfiguration();
        mock_configuration2.setDFServiceName("bridge_service");
        mock_configuration2.setBehaviour(myMockedBehaviour2);        
        MockManager.startMockJadexAgent("BridgeAgent",Definitions.bridgeMockPath,mock_configuration2,this);
  }

}
