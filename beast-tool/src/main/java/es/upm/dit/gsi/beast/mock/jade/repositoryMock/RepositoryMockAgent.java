package es.upm.dit.gsi.beast.mock.jade.repositoryMock;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import es.upm.dit.gsi.beast.mock.common.AgentBehaviour;
import es.upm.dit.gsi.beast.mock.common.Definitions;
import es.upm.dit.gsi.beast.mock.common.MockConfiguration;
import es.upm.dit.gsi.beast.mock.jade.common.AgentRegistration;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.logging.LogActivator;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.mock.jade.repositoryMock.RepositoryMockAgent.java
 *
 *  
 * The implementation of a Mock Agent that perform the behavior 
 * of an agent repository. This behaviour has to be personalized
 * by the user moking the AgentBehaviour class on the Scenario. 
 *  
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 *   
 * @author darofar
 *
 * @version 0.1
 * 
 */
public class RepositoryMockAgent extends Agent {

    /**
     * Logger
     */
    protected final Logger logger = Logger.getMyLogger(getClass().getName());

    /**
     * The personalized information for the current mock agent.
     */
    private MockConfiguration myMockConfiguration;

    /**
     * The introspector agent that allows the access to instances of agents of
     * the platform. Also, give access to a belief approach for Jade Agents.
     */
    private JadeAgentIntrospector introspector;

    /**
     * All received message from the current simulation will be stored on this
     * list. To make complex testing this list could be initialized to a custom
     * value.
     * 
     */
    private List<ACLMessage> mailbox;

    /**
     * Initializes the Agent.
     * 
     * @see jade.core.Agent#setup()
     */
    public void setup() {

        LogActivator.logToFile(logger, this.getName(), Level.ALL);

        // Add the agent to the introspector and obtain the introspector instance.
        introspector = JadeAgentIntrospector.getMyInstance(this);
        logger.fine("Agent " + this.getLocalName()+ " added to the Intrsopector");

        try {
            this.myMockConfiguration = (MockConfiguration) this.getArguments()[0];
        } catch (Exception e) {
            logger.warning("There was an error reading the RepositoryMockAgent configuration.");
            logger.fine("The stack trace: \n" + e.getStackTrace());
            // TODO think about implement this exception or not.
            // throw new BadConfigurationException();
            throw new IllegalArgumentException(
                    "The mock agent didn't receive a configuration"
                  + " object. The first argument have to be a MockConfiguration object");
        }

        // Initialize the believes counts and mailbox.
        introspector.storeBeliefValue(this, Definitions.RECEIVED_MESSAGE_COUNT, 0);
        introspector.storeBeliefValue(this, Definitions.STORED_DATA_COUNT, 0);
        this.mailbox = new ArrayList<ACLMessage>();
        
        // Attempts to register the agent.
        boolean register = false; 
        for(int i=0; !register; i++) {
            try {
                AgentRegistration.registerAgent(this, myMockConfiguration.getDFservice(),
                        Definitions.REPOSITORY_SERVICE_TYPE);
                register = true;
            } catch (FIPAException e) {
                logger.warning("Exception while registering the RespositoryMockAgent");
            }
            if(i >= Definitions.REG_ATTEMPTS) {
                break;
                //TODO check if is necessary to implement this exception.
                // throw new UnableToRegisterException(e.getStackTrace().toString());
            }
         }

        // Adds the behavior that listen for incoming messages.
        addBehaviour(new Listen(this));
    }

    /**
     * If the belief its a count of some sort his counting its increased by one.
     * 
     * @param bName
     *            - the name of the belief count.
     */
    private void increaseBeliefCount(String bName) {
        int count = (Integer) this.getBelief(bName);
        this.setBelief(bName, count + 1);
    }

    /**
     * Modifies the belief referenced by bName parameter.
     * 
     * @param bName
     *            - the name of the belief to update.
     * @param value
     *            - the new value for the belief
     */
    private void setBelief(String bName, Object value) {
        introspector.setBeliefValue(this.getLocalName(), bName, value, null);
    }

    /**
     * Returns the belief value referenced by bName parameter.
     * 
     * @return bName - the name of the belief to consult.
     */
    private Object getBelief(String bName) {
        return introspector.getBeliefValue(this.getLocalName(),bName, null);
    }

    /**
     * @return the mailbox
     */
    public List<ACLMessage> getStoredMessages() {
        return mailbox;
    }

    /**
     * @param new_msg
     */
    public void addMessageToMailbox(ACLMessage new_msg) {
        this.mailbox.add(new_msg);
        this.increaseBeliefCount(Definitions.RECEIVED_MESSAGE_COUNT);
    }

    /**
     * @param mailbox
     *            the mailbox to set
     */
    public void setMailbox(List<ACLMessage> mailbox) {
        this.mailbox = mailbox;
        this.setBelief(Definitions.RECEIVED_MESSAGE_COUNT, mailbox.size());
    }
    
    
    /**
     * This class define a listen behaviour that cyclically looks for incoming
     * messages.
     * 
     * @author darofar
     * 
     */
    private class Listen extends CyclicBehaviour {

        private RepositoryMockAgent myAgent;

//        /**
//         * Default Constructor Take the current instance of the
//         * RepositoryMockAgent as myAgent parameter.
//         * 
//         */
//        public Listen() {
//            super(RepositoryMockAgent.this);
//            this.myAgent = RepositoryMockAgent.this;
//        }

        /**
         * Constructor The target agent is passed as parameter.
         * 
         * @param a
         */
        public Listen(Agent a) {
            super(a);
            this.myAgent = (RepositoryMockAgent) a;
        }
        
        
        /* (non-Javadoc)
         * @see jade.core.behaviours.Behaviour#action()
         */
        public void action() {

            ACLMessage msg = myAgent.receive();

            if (msg != null) {
                
                myAgent.logger.info(myAgent.getLocalName()+ ": Message received.");
                myAgent.logger.fine("Message: " + msg.toString());
                
                //We got a message. Increase the corresponding belief. 
                myAgent.addMessageToMailbox(msg);
                
                String answer = null;
                // Obtain the information of the current message.                 
                String type = ACLMessage.getPerformative(msg.getPerformative());
                String sender = null;
                try {
                    sender = msg.getSender().getLocalName();
                } catch (Exception e) {
                    logger.info("Received message has no sender");
                    sender = "no-one";
                }
                Object content = msg.getContent();
                //Check the behaviour response for the received message.  
                answer = (String) (((AgentBehaviour) myAgent.myMockConfiguration
                        .getBehaviour()).processMessage(type, sender,content));
                
                if (answer == null) {
                    // The action has non be mocked.
                    answer = "Unknown required action";
                } else if (answer.equals(Definitions.STORE_ATTEMPT_OK)){
                    // All OK! The message has been stored. 
                    myAgent.increaseBeliefCount(Definitions.STORED_DATA_COUNT);
                }
                logger.info("Answer: " + answer);
                
                //We respond to the sender with the answer from the mocked behaviour. 
                ACLMessage response = msg.createReply();
                response.setContent(answer);
                response.setPerformative(ACLMessage.INFORM);
                myAgent.send(response);

            } else {
                block();
            }
        }

        private static final long serialVersionUID = -6323904311436847205L;
    }

    private static final long serialVersionUID = 7448955222643359821L;
}
