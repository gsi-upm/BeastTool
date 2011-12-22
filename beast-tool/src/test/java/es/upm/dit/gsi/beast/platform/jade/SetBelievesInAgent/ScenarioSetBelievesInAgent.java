package es.upm.dit.gsi.beast.platform.jade.SetBelievesInAgent;

import es.upm.dit.gsi.beast.story.testCase.Scenario;


/**  
 * This is the class that must create the Scenario.
 * It is related with the GIVEN part.
 *  
 * In startAgents method the following method must be used
 *    super.startAgent(agent_name,agent_path)
 * 
 * @author es.upm.dit.gsi.beast
 */

public class ScenarioSetBelievesInAgent extends Scenario {
/**  
   * Here the description given by the client must be written,
   * which is: 
   *  
   *   that one agent is started in Jade Platform in Main-Container
   * 
   */
    public void startAgents() {
        
        startAgent("myAgent", "es.upm.dit.gsi.beast.test.agent.jade.TesterAgent");
        
  }

}
