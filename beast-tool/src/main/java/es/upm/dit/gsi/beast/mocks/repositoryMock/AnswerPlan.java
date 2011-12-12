package es.upm.dit.gsi.beast.mocks.repositoryMock;

import es.upm.dit.gsi.beast.mocks.common.AgentBehaviour;

import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;
import jadex.bridge.IComponentIdentifier;

/**
 * Plan to answer arriving messages
 * 
 * @author Jorge Solitario
 */
public class AnswerPlan extends Plan{
	
	/** Serial version UID of the serializable class BehaviourPlan. */
	private static final long serialVersionUID = 4476473302410302L;
	
	public void body()
	{	
		IMessageEvent actReq = (IMessageEvent)getReason();
		
		String type = (String) actReq.getParameter("performative").getValue(); 
		Object content = actReq.getParameter(SFipa.CONTENT).getValue();
		String agent_name = (String) ((IComponentIdentifier) actReq.getParameter(SFipa.SENDER).getValue()).getLocalName();	

		String answer = (String) ((AgentBehaviour) getBeliefbase().getBelief("agent_behaviour").getFact()).processMessage( type, agent_name, content);
		if(answer==null) answer="Unknown required action";
		System.out.println("Answer: " +answer);
				
		//createReply solo devuelve tipo String
		IMessageEvent msgResp = getEventbase().createReply(actReq,"send_inform");
		msgResp.getParameter(SFipa.CONTENT).setValue(answer);
		sendMessage(msgResp);	
	}   
}
