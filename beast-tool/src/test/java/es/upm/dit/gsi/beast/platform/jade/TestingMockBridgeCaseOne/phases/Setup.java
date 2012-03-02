package es.upm.dit.gsi.beast.platform.jade.TestingMockBridgeCaseOne.phases;

import jade.lang.acl.ACLMessage;
import es.upm.dit.gsi.beast.mock.common.Definitions;

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
   *   bridge mocks has to send a message to listener
   * 
   */
    public void setStates() {
        // Debug
        //System.out.println("About to send the message");
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("send");
        sendMessageToAgent(Definitions.BRIDGE_AGENT_NAME, ACLMessage.getPerformative(ACLMessage.INFORM), msg);
        // Debug
        //System.out.println("Message sent");
    }
}
