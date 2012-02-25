package es.upm.dit.gsi.beast.platform.jade.TestingMockBridgeCaseTwo.phases;

import es.upm.dit.gsi.beast.mock.common.Definitions;
import jade.lang.acl.ACLMessage;

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
   *   bridge mocks has to send a message to repository
   * 
   */
    public void setStates() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("store");
        System.out.println("About to send the message");
        sendMessageToAgent(Definitions.BRIDGE_AGENT_NAME, ACLMessage.getPerformative(ACLMessage.INFORM), msg);
        System.out.println("Setup complete");
    }

}
