package es.upm.dit.gsi.beast.mock.jade.bridgeMock;

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

public class DannyBridgeMockAgent extends Agent {

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

        // Add the agent to the introspector and obtain the introspector
        // instance.
        introspector = JadeAgentIntrospector.getMyInstance(this);
        logger.fine("Agent " + this.getLocalName()
                + " added to the Intrsopector");

        try {
            this.myMockConfiguration = (MockConfiguration) this.getArguments()[0];
        } catch (Exception e) {
            logger.warning("There was an error reading the BridgeMockAgent configuration.");
            logger.fine("The stack trace: \n" + e.getStackTrace());
            // TODO think about implement this exception or not.
            // throw new BadConfigurationException();
            throw new IllegalArgumentException(
                    "The mock agent didn't receive a configuration"
                            + " object. The first argument have to be a MockConfiguration object");
        }

        // Initialize the believes counts and the mailbox list. 
        introspector.storeBeliefValue(this, Definitions.RECEIVED_MESSAGE_COUNT, 0);
        introspector.storeBeliefValue(this, Definitions.SENDED_MESSAGE_COUNT, 0);
        this.mailbox = new ArrayList<ACLMessage>();

        // Attemps to register the aggent.
        boolean register = false;
        for (int i = 0; !register; i++) {
            try {
                AgentRegistration.registerAgent(this,
                        myMockConfiguration.getDFservice(),
                        Definitions.BRIDGE_SERVICE_TYPE);
                // TODO Service_Type could be a custom field of
                // MockCondiguration.
                register = true;
            } catch (FIPAException e) {
                logger.warning("Exception while registering the RespositoryMockAgent");
            }
            if (i >= Definitions.REG_ATTEMPTS) {
                break;
                // TODO check if is necessary to implement this exception.
                // throw new
                // UnableToRegisterException(e.getStackTrace().toString());
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
        return introspector.getBeliefValue(this.getLocalName(), bName, null);
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
     * This class define a listen behavior that cyclically looks for incoming
     * messages.
     * 
     * @author darofar
     * 
     */
    private class Listen extends CyclicBehaviour {

        private DannyBridgeMockAgent myAgent;

        /**
         * Default Constructor Take the current instance of the
         * DannyBridgeMockAgent as myAgent parameter.
         * 
         */
        @SuppressWarnings("unused")
        public Listen() {
            super(DannyBridgeMockAgent.this);
            this.myAgent = DannyBridgeMockAgent.this;
        }

        /**
         * Constructor The target agent is passed as parameter.
         * 
         * @param a
         */
        public Listen(Agent a) {
            super(a);
            this.myAgent = (DannyBridgeMockAgent) a;
        }

        /*
         * (non-Javadoc)
         * 
         * @see jade.core.behaviours.Behaviour#action()
         */
        public void action() {

            //TODO make exceptions for a bad mocked behavior. For example: if 
            // the mocked behavior do not provide a typeMessage for the target
            // or do not provide a target receiver, etc...
            
            ACLMessage msg = myAgent.receive();

            if (msg != null) {

                myAgent.logger.info(myAgent.getLocalName()
                        + ": Message received.");
                myAgent.logger.fine("Message: " + msg.toString());

                /*
                 * We got a message! First store the message on the Mailbox and
                 * then increase the corresponding belief.
                 */
                myAgent.addMessageToMailbox(msg);

                // Obtain the information of the current message.
                AgentBehaviour myBehaviour = (AgentBehaviour) myAgent.myMockConfiguration
                        .getBehaviour();
                String answer = null;
                String type = ACLMessage.getPerformative(msg.getPerformative());
                String sender = null;
                try {
                    sender = msg.getSender().getLocalName();
                } catch (Exception e) {
                    logger.info("Received message has no sender");
                    sender = "no-one";
                }
                Object content = msg.getContent();

                // Check the behavior response for the received message.
                answer = (String) (myBehaviour.processMessage(type, content));

                if (answer != null) {

                    // Default process for linked type-content messages.
                    String targetService = answer;
                    String targetType = (String) (myBehaviour.processMessage(type, content));
                    String targetContent = (String) (myBehaviour.processMessage(type, content));
                    this.sendMessage(targetService, targetType, targetContent);

                    // NOTE
                    /*
                     * This code could not be necessary, because the mocked
                     * targetType must provide the type of the message to send.
                     * 
                     * switch(ACLMessage.getInteger(type)){ case
                     * ACLMessage.INFORM: this.sendInform(targetService,
                     * targetType, targetContent); break; case
                     * ACLMessage.REQUEST: this.sendRequest(targetService,
                     * targetType, targetContent); break; default:
                     * 
                     * }
                     */
                } else {
                    answer = (String) (myBehaviour.processMessage(type, sender,
                            content));
                    if (answer != null) {
                        // There must be a complex behavior defined for the
                        // sender agent.
                        String targetService = answer;
                        String targetType = (String) (myBehaviour.processMessage(type, sender, content));
                        String targetContent = (String) (myBehaviour.processMessage(type, sender, content));
                        this.sendMessage(targetService, targetType, targetContent);
                    } else {
                        logger.info("There is not mocked behaviour defined for the received message.");
                        logger.fine("Message: "+msg);
                    }
                }

            } else {
                block();
            }
        }

        private void sendMessage(String targetService, String targetType,
                String targetContent) {
            // TODO el mock debería devolver targetServiceName o targetAgentName
            // ? Por el momento
            // esta hecho con targetAgentName.
            ACLMessage new_msg = new ACLMessage(
                    ACLMessage.getInteger(targetType));
            new_msg.setContent(targetContent);
            new_msg.addReceiver(introspector.getAgent(targetService).getAID());
            myAgent.send(new_msg);
            myAgent.increaseBeliefCount(Definitions.SENDED_MESSAGE_COUNT);
        }

        private static final long serialVersionUID = -5643599458512659962L;

    }

    private static final long serialVersionUID = -4908727832673636734L;
}
