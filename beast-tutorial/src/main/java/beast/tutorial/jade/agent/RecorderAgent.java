/**
 * beast.tutorial.jade.agent.RecorderAgent.java
 */
package beast.tutorial.jade.agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.Logger;

import java.util.logging.Level;

import beast.tutorial.model.Call;
import beast.tutorial.model.CallQueue;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.logging.LogActivator;

/**
 * Project: beast-tutorial File: beast.tutorial.jade.agent.RecorderAgent.java
 * 
 * Grupo de Sistemas Inteligentes Departamento de Ingeniería de Sistemas
 * Telemáticos Universidad Politécnica de Madrid (UPM)
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @date 26/06/2013
 * @version 0.1
 * 
 */
public class RecorderAgent extends Agent {

	/**
     * 
     */
	private static final long serialVersionUID = 3431816096407246858L;

	protected final Logger logger = Logger.getMyLogger(getClass().getName());

	private CallQueue queue;

	private JadeAgentIntrospector myIntrospector;

	public CallQueue getQueue() {
		return queue;
	}

	public void setQueue(CallQueue queue) {
		this.queue = queue;
	}

	protected void setup() {
		this.myIntrospector = JadeAgentIntrospector.getMyInstance((Agent) this);
		LogActivator.logToFile(logger, this.getName(), Level.ALL);

		this.queue = new CallQueue();
		this.myIntrospector.storeBeliefValue(this, "queue", queue);
		addBehaviour(new CheckQueue(this));
	}

	/**
	 * Inner class CheckQueue This behaviour checks if any pending call is in
	 * the queue.
	 */
	private class CheckQueue extends CyclicBehaviour {

		private static final long serialVersionUID = 286992171997750565L;

		public CheckQueue(Agent ag) {
			super(ag);
		}

		public void action() {
			RecorderAgent.this
					.setQueue((CallQueue) RecorderAgent.this.myIntrospector
							.retrieveBelievesValue(RecorderAgent.this).get(
									"queue"));
			CallQueue queue = RecorderAgent.this.getQueue();
			if (queue != null) {
				Call pendingCall = queue.getPendingCall();
				if (pendingCall != null) {
					// Every time a new call is detected
					String customerLanguage = pendingCall.getCustormer()
							.getLanguage();

					// Detect language of the customer
					if (customerLanguage.equalsIgnoreCase("English")) {
						// Inform to ReporterAgent
						try {
							DFAgentDescription dfd = new DFAgentDescription();
							ServiceDescription sd = new ServiceDescription();
							sd.setName("report-service");
							dfd.addServices(sd);
							DFAgentDescription[] agdescriptions = DFService
									.search(RecorderAgent.this, dfd);
							if (agdescriptions.length > 0) {
								AID agentid = agdescriptions[0].getName();

								ACLMessage msg = new ACLMessage(
										ACLMessage.INFORM);
								msg.setContent("NewRecordedCall");
								// msg.setContentObject(new
								// Message(pendingCall));
								msg.addReceiver(agentid);

								send(msg);
							} else {
								throw new Exception("ReporterAgent not found");
							}
						} catch (Exception e) {
							logger.severe(RecorderAgent.this.getLocalName()
									+ ": Impossible to connect with a ReporterAgent.");
						}
					} else {
						try {
							// Request to HelpDeskAgenttry
							DFAgentDescription dfd = new DFAgentDescription();
							ServiceDescription sd = new ServiceDescription();
							sd.setName("helpdesk-service");
							dfd.addServices(sd);
							DFAgentDescription[] ags = DFService.search(
									this.myAgent, dfd);
							if (ags.length > 0) {
								AID agentid = ags[0].getName();

								ACLMessage msg = new ACLMessage(
										ACLMessage.REQUEST);
								msg.setContent("UnknownLanguageCall");
								// msg.setContentObject(new
								// Message(pendingCall));
								msg.addReceiver(agentid);

								send(msg);

								MessageTemplate template = MessageTemplate
										.MatchPerformative(ACLMessage.INFORM);
								ACLMessage msg2 = RecorderAgent.this
										.blockingReceive(template);
								String content = msg2.getContent();
								if (content.equalsIgnoreCase("Agree")) {
									RecorderAgent.this.myIntrospector
											.storeBeliefValue(
													RecorderAgent.this,
													"passedCall", true);
								}
							} else {
								throw new Exception("HelpDeskAgent not found");
							}

						} catch (Exception e) {
							logger.severe(RecorderAgent.this.getLocalName()
									+ ": Exception -> " + e.getMessage());
						}
					}
				}
			} else {
				this.block(50);
			}
		}
	}

}
