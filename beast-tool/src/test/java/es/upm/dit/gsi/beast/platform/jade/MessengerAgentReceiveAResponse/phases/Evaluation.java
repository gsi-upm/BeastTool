package es.upm.dit.gsi.beast.platform.jade.MessengerAgentReceiveAResponse.phases;

import junit.framework.Assert;
import es.upm.dit.gsi.beast.platform.PlatformSelector;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.platform.jade.agent.MessengerAgent;


/**  
 * This is the class that must create the Evaluation.
 * It is related with the THEN part.
 *  
 * In checkStates method the following method must be used
 *    super.checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)
 * 
 * @author es.upm.dit.gsi.beast
 */

public class Evaluation extends es.upm.dit.gsi.beast.story.phases.Evaluation {
/**  
   * Here the description given by the client must be written,
   * which is: 
   *  
   *   the message is received by the tester through MessengerAgent
   * 
   */
    public void checkStates() {
        
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector.getAgentIntrospector("jade");
        
        while (((MessengerAgent)introspector.getAgent("BeastMessenger")).isReceived()==false) {
            // Wait...
        }
        
        Assert.assertTrue(((MessengerAgent)introspector.getAgent("BeastMessenger")).isReceived());
  }

}
