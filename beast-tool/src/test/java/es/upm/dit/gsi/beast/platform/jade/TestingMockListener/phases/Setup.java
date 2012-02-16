package es.upm.dit.gsi.beast.platform.jade.TestingMockListener.phases;

import jade.lang.acl.ACLMessage;
import jadex.base.fipa.SFipa;

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
   *   10 messages are sent to listener
   * 
   */
    public void setStates() {
        
        for (int i=0; i<10; i++) {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setContent("MESSAGE#"+i);
            sendMessageToAgent("ListenerAgent", SFipa.INFORM, msg);
        }
        
        
//        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "2");
//        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "3");
//        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "4");
//        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "5");
//        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "6");
//        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "7");
//        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "8");
//        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "9");
//        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "10");
  }

}
