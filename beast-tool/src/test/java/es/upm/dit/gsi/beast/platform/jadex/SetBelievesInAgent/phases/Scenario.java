package es.upm.dit.gsi.beast.platform.jadex.SetBelievesInAgent.phases;

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
   *   that one agent is started in Jadex Platform
   * 
   */
    public void startAgents() {
        startAgent("Tester", "es/upm/dit/gsi/beast/test/agent/jadex/TesterAgent.agent.xml");
  }

}
