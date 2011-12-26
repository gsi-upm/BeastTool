package es.upm.dit.gsi.beast.platform.jade.SetBelievesInAgent.phases;

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
   *   that TesterAgent is started in Jade Platform in Main-Container with Configuration 4
   * 
   */
    public void startAgents() {
        
        Object[] arguments = new Object[1];
        arguments[0] = (Integer) 4;
        startAgent("TestAgent",
                "es.upm.dit.gsi.beast.test.agent.jade.TesterAgent",
                "My-Container", arguments);
        
  }

}
