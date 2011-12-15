package es.upm.dit.gsi.beast.mocks.common;

/**
 * Empty skeleton to be copied by mocks, it will provide the actions to perform by mock agents.
 * Each time processMessage is called, the mock will retrieve an String, which
 * will be used to know the receiver, type or content of answer message
 * 
 * @author Jorge Solitario
 */
public class AgentBehaviour {

	public Object processMessage(String message_type, String sender_id, Object content) {
		return null;
	}

	public Object processMessage(String message_type, Object content) {		
		return null;
	}
}
