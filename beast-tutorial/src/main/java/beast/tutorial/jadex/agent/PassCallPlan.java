/**
 * beast.tutorial.jadex.agent.PassCallPlan.java
 */
package beast.tutorial.jadex.agent;

import es.upm.dit.gsi.beast.mock.common.AgentBehaviour;
import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;
import jadex.bridge.IComponentIdentifier;

/**
 * Project: beast-tutorial File: beast.tutorial.jadex.agent.PassCallPlan.java
 * 
 * Grupo de Sistemas Inteligentes Departamento de Ingeniería de Sistemas
 * Telemáticos Universidad Politécnica de Madrid (UPM)
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @date 01/07/2013
 * @version 0.1
 * 
 */
public class PassCallPlan extends Plan {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8991008802360743498L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jadex.bdi.runtime.Plan#body()
	 */
	@Override
	public void body() {

		IMessageEvent msg = (IMessageEvent) getReason();

		String content = (String) msg.getParameter("content").getValue();

		if (content.equalsIgnoreCase("UnknownLanguageCall")) {
			this.getBeliefbase().getBelief("operatorTalking").setFact(true);

			try {

				String type = (String) msg.getParameter("performative")
						.getValue();
				String agent_name = (String) ((IComponentIdentifier) msg
						.getParameter(SFipa.SENDER).getValue()).getLocalName();

				String answer = (String) ((AgentBehaviour) getBeliefbase()
						.getBelief("agent_behaviour").getFact())
						.processMessage(type, agent_name, content);
				if (answer == null)
					answer = "Unknown required action";

				IMessageEvent msgResp = getEventbase().createReply(msg,
						"send_inform");
				msgResp.getParameter(SFipa.CONTENT).setValue(answer);

				sendMessage(msgResp);

			} catch (Exception e) {
				// Not important for tutorial purposes.
			}
		}

	}

}
