package es.upm.dit.gsi.beast.platform.jade.TestingMockListener.phases;

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
   *   one listener mock in Jade Platform
   * 
   */
    public void startAgents() {
       //ListenerAgent configuration
        AgentBehaviour myMockedBehaviour =   mock(AgentBehaviour.class);
        MockConfiguration mock_configuration = new MockConfiguration();
        mock_configuration.setDFServiceName("listen_service");
        mock_configuration.setBehaviour(myMockedBehaviour);
        MockManager.startMockJadeAgent("ListenerAgent",Definitions.JADE_LISTENER_MOCK_PATH,mock_configuration,this);
        
        AgentBehaviour myMockedBehaviour2 =   mock(AgentBehaviour.class);
        MockConfiguration mock_configuration2 = new MockConfiguration();
        mock_configuration2.setDFServiceName("adsfa");
        mock_configuration2.setBehaviour(myMockedBehaviour2);
        MockManager.startMockJadeAgent("ListenerAgent",Definitions.JADE_LISTENER_MOCK_PATH,mock_configuration2,this);
        
//        startAgent("ListenerMock", "es.upm.dit.gsi.beast.mock.jade.listenerMock.ListenerMock");

       //EXAMPLE for Jadex: startAgent("Steve", "org.example.Steve.agent.xml"); // This xml file is the jadex agent description file (ADF)
       //EXAMPLE for Jade: startAgent("Steve", "org.example.Steve"); // This string is the agent class Steve.java that extends Jade Agent class
  }

}
