/**
 * beast.tutorial.jade.agent.ReporterAgent.java
 */
package beast.tutorial.jade.agent;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.Logger;

import java.util.logging.Level;

import beast.tutorial.model.Report;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.logging.LogActivator;

/**
 * Project: beast-tutorial File: beast.tutorial.jade.agent.ReporterAgent.java
 * 
 * Grupo de Sistemas Inteligentes Departamento de Ingeniería de Sistemas
 * Telemáticos Universidad Politécnica de Madrid (UPM)
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @date 28/06/2013
 * @version 0.1
 * 
 */
public class ReporterAgent extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -495649975198526265L;

	private JadeAgentIntrospector myIntrospector;

	protected final Logger logger = Logger.getMyLogger(getClass().getName());

	/* (non-Javadoc)
	 * @see jade.core.Agent#setup()
	 */
	public void setup() {
		this.myIntrospector = JadeAgentIntrospector.getMyInstance((Agent) this);
		LogActivator.logToFile(logger, this.getName(), Level.ALL);
		// Register the service
		try {
			DFAgentDescription dfd = new DFAgentDescription();
			ServiceDescription sd = new ServiceDescription();
	        
	        sd.setType("server");
	        sd.setName("report-service");
	        // Sets the agent description
	        dfd.setName(this.getAID());
	        dfd.addServices(sd);
	        
	        // Register the agent
	        DFService.register(this, dfd);
			
		} catch (Exception e) {
			logger.severe("Exception registering agent in DF. Agent: " + this.getName());
		}

		MessageTemplate t = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		addBehaviour(new ReportServer(this,t));
	}
	


	/**
	 * Inner class CheckQueue This behaviour checks if any pending recorded call is sent by a RecorderAgent
	 */
	private class ReportServer extends CyclicBehaviour {

		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1928144977438290780L;
		private MessageTemplate template;
		
		/**
		 * Constructor
		 *
		 * @param reporterAgent
		 */
		public ReportServer(ReporterAgent reporterAgent, MessageTemplate template) {
			super(reporterAgent);
            this.template= template;
		}

		/* (non-Javadoc)
		 * @see jade.core.behaviours.Behaviour#action()
		 */
		@Override
		public void action() {

            ACLMessage msg = myAgent.receive(template);
            if (msg != null && msg.getContent().equalsIgnoreCase("NewRecordedCall")) {
            	Report report  = new Report();
            	ReporterAgent.this.myIntrospector.storeBeliefValue(ReporterAgent.this, "report", report);
            	ReporterAgent.this.myIntrospector.storeBeliefValue(ReporterAgent.this, "createdIssueReport", true);
            } else {
                block();
            }
			
		}
		
	}

}
