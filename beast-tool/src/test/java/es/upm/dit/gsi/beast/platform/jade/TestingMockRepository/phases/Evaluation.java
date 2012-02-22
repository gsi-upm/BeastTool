package es.upm.dit.gsi.beast.platform.jade.TestingMockRepository.phases;

import es.upm.dit.gsi.beast.mock.common.Definitions;
import junit.framework.Assert;


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
   *   repository answers with the correct message
   * 
   */
    public void checkStates() {

        checkAgentsBeliefEquealsTo(Definitions.REPOSITORY_AGENT_NAME, Definitions.RECEIVED_MESSAGE_COUNT, 1);
        checkAgentsBeliefEquealsTo(Definitions.REPOSITORY_AGENT_NAME, Definitions.STORED_DATA_COUNT, 1);
    //EXAMPLE: checkAgentsBeliefEquealsTo("Steve", "age", 21);
  }

}
