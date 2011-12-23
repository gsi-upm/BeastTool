package es.upm.dit.gsi.beast.platform.jade.GetBelievesFromAgent;

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

public class ScenarioGetBelievesFromAgent extends Scenario {
/**  
   * Here the description given by the client must be written,
   * which is: 
   *  
   *   that TesterAgent is started in Jade Platform in Main-Container with Configuration 1
   * 
   */
    public void startAgents() {
        
        Object[] arguments = new Object[1];
        arguments[0] = "configuration1";
        startAgent("TestAgent",
                "es.upm.dit.gsi.beast.test.agent.jade.TesterAgent",
                "My-Container", arguments);
        
  }

}
