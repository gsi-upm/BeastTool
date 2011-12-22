package es.upm.dit.gsi.beast.platform.jade.GetBelievesFromAgent;

import junit.framework.Assert;
import es.upm.dit.gsi.beast.story.testCase.Setup;


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

public class SetupGetBelievesFromAgent extends Setup {
/**  
   * Here the description given by the client must be written,
   * which is: 
   *  
   *   tester wants to get a belief from a Jade agent
   * 
   */
    public void setStates() {
        logger.info(">> setStates method is empty because there is nothing to do in this method for this test.");        
  }

}
