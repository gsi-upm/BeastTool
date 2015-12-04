/**
 * beast.tutorial.jadex.agent.RecieveCallPlan.java
 */
package beast.tutorial.jadex.agent;

import jadex.base.fipa.IDF;
import jadex.base.fipa.IDFComponentDescription;
import jadex.base.fipa.IDFServiceDescription;
import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IGoal;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;
import jadex.bridge.ISearchConstraints;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.SServiceProvider;

import java.util.logging.Logger;

import beast.tutorial.model.Call;
import beast.tutorial.model.CallQueue;
import es.upm.dit.gsi.beast.mock.jadex.listenerMock.ListenPlan;

/**
 * Project: beast-tutorial File: beast.tutorial.jadex.agent.RecieveCallPlan.java
 * 
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @date 28/06/2013
 * @version 0.1
 * 
 */
public class ReceiveCallPlan extends Plan {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4357742910363913150L;

	private Logger logger = Logger.getLogger(ListenPlan.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see jadex.bdi.runtime.Plan#body()
	 */
	@Override
	public void body() {

		CallQueue queue = (CallQueue) getBeliefbase().getBelief("queue")
				.getFact();

		Call call = queue.getPendingCall();
		if (call.getCustormer().getLanguage().equalsIgnoreCase("English")) {

			String target_service = "report-service";
			String msgContent = "NewRecordedCall";

			// Looking for reporter agent
			IDF df = (IDF) SServiceProvider.getService(getServiceContainer(),
					IDF.class, RequiredServiceInfo.SCOPE_PLATFORM).get(this);
			IDFServiceDescription sd = df.createDFServiceDescription(
					target_service, null, null);
			IDFComponentDescription dfadesc = df.createDFComponentDescription(
					null, sd);

			ISearchConstraints constraints = df.createSearchConstraints(-1, 0);

			// Use a subgoal to search
			IGoal ft = createGoal("dfcap.df_search");
			ft.getParameter("description").setValue(dfadesc);
			ft.getParameter("constraints").setValue(constraints);

			dispatchSubgoalAndWait(ft);
			IDFComponentDescription[] agents = (IDFComponentDescription[]) ft
					.getParameterSet("result").getValues();

			if (agents.length > 0) {
				IMessageEvent msg = createMessageEvent("send_inform");
				msg.getParameter(SFipa.CONTENT).setValue(msgContent);
				msg.getParameterSet(SFipa.RECEIVERS).addValue(
						agents[0].getName());
				sendMessage(msg);
				logger.info("Message sent to: "
						+ agents[0].getName().getLocalName());
			} else {
				logger.severe("Service " + target_service + " not found.");
			}

		} else {

			System.out.println("Language: " + call.getCustormer().getLanguage());
			String target_service = "helpdesk-service";
			String msgContent = "UnknownLanguageCall";

			// Looking for reporter agent
			IDF df = (IDF) SServiceProvider.getService(getServiceContainer(),
					IDF.class, RequiredServiceInfo.SCOPE_PLATFORM).get(this);
			IDFServiceDescription sd = df.createDFServiceDescription(
					target_service, null, null);
			IDFComponentDescription dfadesc = df.createDFComponentDescription(
					null, sd);

			ISearchConstraints constraints = df.createSearchConstraints(-1, 0);

			// Use a subgoal to search
			IGoal ft = createGoal("dfcap.df_search");
			ft.getParameter("description").setValue(dfadesc);
			ft.getParameter("constraints").setValue(constraints);

			dispatchSubgoalAndWait(ft);
			IDFComponentDescription[] agents = (IDFComponentDescription[]) ft
					.getParameterSet("result").getValues();

			IMessageEvent msg = createMessageEvent("helpdesk-request");
			msg.getParameter(SFipa.CONTENT).setValue(msgContent);
			if (agents.length > 0) {
				msg.getParameterSet(SFipa.RECEIVERS).addValue(
						agents[0].getName());
				sendMessage(msg);
				System.out.println("Enviado");
			} else {
				System.out.println("Service " + target_service + " not found.");
				logger.severe("Service " + target_service + " not found.");
			}

			logger.info("Message sent to: " + agents[0].getName());

		}
	}
}
