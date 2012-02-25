package es.upm.dit.gsi.beast.platform.jade.TestingMockBridgeCaseTwo.phases;

import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

import es.upm.dit.gsi.beast.mock.common.Definitions;
import es.upm.dit.gsi.beast.mock.jade.bridgeMock.BridgeMockAgent;
import es.upm.dit.gsi.beast.mock.jade.listenerMock.ListenerMockAgent;
import es.upm.dit.gsi.beast.platform.PlatformSelector;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;


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
   *   bridge receives the answer from repository
   * 
   */
    public void checkStates() {
//        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector.getAgentIntrospector("jade");
//        BridgeMockAgent bridge = (BridgeMockAgent) introspector.getAgent(Definitions.BRIDGE_AGENT_NAME);
//        System.out.println( bridge.getIntegerBelief(Definitions.RECEIVED_MESSAGE_COUNT));
        checkAgentsBeliefEquealsTo(Definitions.BRIDGE_AGENT_NAME, Definitions.RECEIVED_MESSAGE_COUNT, 2);
    }

}
