package es.upm.dit.gsi.beast.platform.jadex.TestingMockListener.phases;

import static org.mockito.Mockito.mock;
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
   *   one listener mock in Jadex Platform
   * 
   */
    public void startAgents() {
       //ListenerAgent configuration
        AgentBehaviour myMockedBehaviour =   mock(AgentBehaviour.class);
        MockConfiguration mock_configuration = new MockConfiguration();
        mock_configuration.setDFServiceName("listen_service");
        mock_configuration.setBehaviour(myMockedBehaviour);
        MockManager.startMockJadexAgent("ListenerAgent",Definitions.JADEX_LISTENER_MOCK_PATH,mock_configuration,this);
  }

}
