package es.upm.dit.gsi.beast.platform.jadex.TestingMockRepository.phases;

import jadex.base.fipa.SFipa;

/**  
 * This is the class that must create the Setup.
 * It is related with the WHEN part.
 *  
 * In setStates method the following methods must be used
 *    super.setBeliefValue (agent_name, belief_name, new_value )
 *    super.sendMessageToAgent(agent_name, msgtype, message_content)
 *    super.getAgentPlans(agent_name)
 *    super.getAgentGoals(agent_name )
 * 
 * @author es.upm.dit.gsi.beast
 */

public class Setup extends es.upm.dit.gsi.beast.story.phases.Setup {
/**  
   * Here the description given by the client must be written,
   * which is: 
   *  
   *   bridge sends a message to repository
   * 
   */
    public void setStates() {
        sendMessageToAgent("BridgeAgent", SFipa.INFORM, "send");
  }

}
