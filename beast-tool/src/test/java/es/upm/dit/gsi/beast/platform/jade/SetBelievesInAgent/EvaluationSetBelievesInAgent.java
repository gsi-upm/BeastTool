package es.upm.dit.gsi.beast.platform.jade.SetBelievesInAgent;

import es.upm.dit.gsi.beast.platform.PlatformSelector;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.testCase.Evaluation;
import es.upm.dit.gsi.beast.test.agent.jade.TesterAgent;


/**  
 * This is the class that must create the Evaluation.
 * It is related with the THEN part.
 *  
 * In checkStates method the following method must be used
 *    super.checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)
 * 
 * @author es.upm.dit.gsi.beast
 */

public class EvaluationSetBelievesInAgent extends Evaluation {
/**  
   * Here the description given by the client must be written,
   * which is: 
   *  
   *   the belief is set
   * 
   */
    public void checkStates() {

        
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector.getAgentIntrospector("jade");
        
        while (((TesterAgent)introspector.getAgent("TestAgent")).isReadyToTest()==false) {
            // Wait...
        }

        checkAgentsBeliefEquealsTo("TestAgent", "setBelief", true);
        //TODO este test tira a veces...
        
  }

}
