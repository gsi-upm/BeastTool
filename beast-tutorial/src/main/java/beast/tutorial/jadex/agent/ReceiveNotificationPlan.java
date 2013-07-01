/**
 * beast.tutorial.jadex.agent.ReceiveNotificationPlan.java
 */
package beast.tutorial.jadex.agent;

import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;
import beast.tutorial.model.Report;

/**
 * Project: beast-tutorial File:
 * beast.tutorial.jadex.agent.ReceiveNotificationPlan.java
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
public class ReceiveNotificationPlan extends Plan {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7021553883238783426L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jadex.bdi.runtime.Plan#body()
	 */
	@Override
	public void body() {

		IMessageEvent msg = (IMessageEvent) getReason();
		String content = (String) msg.getParameter("content").getValue();
		if (content.equalsIgnoreCase("NewRecordedCall")) {
			Report report = new Report();

			this.getBeliefbase().getBelief("report").setFact(report);
			this.getBeliefbase().getBelief("createdIssueReport").setFact(true);
		}

	}

}
