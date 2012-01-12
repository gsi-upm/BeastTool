package es.upm.dit.gsi.beast.platform.jadex.TestingMockRepository.phases;

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
 *    startAgent(agent_name,agent_path)
 * 
 * @author es.upm.dit.gsi.beast
 */

public class Scenario extends es.upm.dit.gsi.beast.story.phases.Scenario {
/**  
   * Here the description given by the client must be written,
   * which is: 
   *  
   *   one bridge mock and one repository mock in Jadex Platform
   * 
   */
    public void startAgents() {
        
        //RepositoryAgent configuration
        AgentBehaviour myMockedBehaviour =   mock(AgentBehaviour.class);
        when(myMockedBehaviour.processMessage(eq("request"), eq("BridgeAgent"),eq("hi")))
            .thenReturn("hello");        
        MockConfiguration mock_configuration = new MockConfiguration();
        mock_configuration.setDFServiceName("repository_service");
        mock_configuration.setBehaviour(myMockedBehaviour);      
        MockManager.startMockJadexAgent("RepositoryAgent",Definitions.repositoryMockPath,mock_configuration,this);
        
        
        //BridgeAgent configuration
        AgentBehaviour myMockedBehaviour2 =  mock(AgentBehaviour.class);
        when(myMockedBehaviour2.processMessage(eq("inform"), eq("send")))
            .thenReturn("repository_service", "SFipa.REQUEST", "hi");   
        MockConfiguration mock_configuration2 = new MockConfiguration();
        mock_configuration2.setDFServiceName("bridge_service");
        mock_configuration2.setBehaviour(myMockedBehaviour2);
        MockManager.startMockJadexAgent("BridgeAgent",Definitions.bridgeMockPath,mock_configuration2,this);
  }

}
