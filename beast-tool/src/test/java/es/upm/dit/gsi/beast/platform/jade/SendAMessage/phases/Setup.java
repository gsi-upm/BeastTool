package es.upm.dit.gsi.beast.platform.jade.SendAMessage.phases;

import jade.lang.acl.ACLMessage;

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
   *   tester wants to send a message with MessageService to TesterAgent
   * 
   */
    public void setStates() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("Hello My Friend!");
        sendMessageToAgent("TestAgent", "INFORM", msg);
  }

}
