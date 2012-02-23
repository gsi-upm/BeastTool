package es.upm.dit.gsi.beast.platform.jade.TestingMockBridgeCaseOne.phases;

import java.util.ArrayList;

import es.upm.dit.gsi.beast.mock.jade.listenerMock.ListenerMock;
import es.upm.dit.gsi.beast.platform.PlatformSelector;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.test.agent.jade.TesterAgent;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
//import junit.framework.Assert;


/**  
 * This is the class that must create the Evaluation.
 * It is related with the THEN part.
 *  
 * In checkStates method the following method must be used
 *    checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)
 * 
 * @author es.upm.dit.gsi.beast
 */

public class Evaluation extends es.upm.dit.gsi.beast.story.phases.Evaluation {
/**  
   * Here the description given by the client must be written,
   * which is: 
   *  
   *   listener receives the message
   * 
   */
    public void checkStates() {
        //while (((TesterAgent)introspector.getAgent("ListenerAgent")).isReadyToTest()==false) {
        //    // Wait...
        //}
        
        // Debug
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector.getAgentIntrospector("jade");
        ListenerMock listener = (ListenerMock) introspector.getAgent("ListenerAgent");
        ArrayList<ACLMessage> msgs = listener.getAllMessages(false);
        for (ACLMessage msg : msgs) {
            System.out.println(msg.getContent());
        }
        checkAgentsBeliefEquealsTo("ListenerAgent", "message_count", 1);
  }

}
