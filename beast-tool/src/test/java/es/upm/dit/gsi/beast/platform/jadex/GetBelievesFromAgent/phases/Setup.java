package es.upm.dit.gsi.beast.platform.jadex.GetBelievesFromAgent.phases;

/**  
 * This is the class that must create the Setup.
 * It is related with the WHEN part.
 *  
 * In setStates method the following methods must be used
 *    setBeliefValue (agent_name, belief_name, new_value )
 *    sendMessageToAgent(agent_name, msgtype, message_content)
 *    getAgentPlans(agent_name)
 *    getAgentGoals(agent_name )
 * 
 * @author es.upm.dit.gsi.beast
 */

public class Setup extends es.upm.dit.gsi.beast.story.phases.Setup {
/**  
   * Here the description given by the client must be written,
   * which is: 
   *  
   *   tester wants to get a belief from a Jadex agent
   * 
   */
    public void setStates() {
        logger.info(">> setStates method is empty because there is nothing to do in this method for this test.");        
  }

}
