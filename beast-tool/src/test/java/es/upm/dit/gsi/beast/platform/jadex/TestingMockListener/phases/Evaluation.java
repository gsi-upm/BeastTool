package es.upm.dit.gsi.beast.platform.jadex.TestingMockListener.phases;


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
   *   listener receives all messages
   * 
   */
    public void checkStates() {
        checkAgentsBeliefEquealsTo("ListenerAgent", "message_count", 10);
  }

}