package es.upm.dit.gsi.beast.mock.jade.bridgeMock;

import es.upm.dit.gsi.beast.mock.jade.common.AgentRegistration;
import es.upm.dit.gsi.beast.mock.common.AgentBehaviour;
import es.upm.dit.gsi.beast.mock.common.MockConfiguration;
import es.upm.dit.gsi.beast.platform.PlatformSelector;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.logging.LogActivator;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

import java.util.logging.Level;
/**
 * 
 * Allows communication between agents.
 * 
 * @author Alberto Mardomingo
 * 
 */
public class BridgeMock extends Agent {

    /**
     * Serial number for the agent
     */
    private static final long serialVersionUID = 2768685097091062141L;

    /**
     * Logger
     */
    protected final Logger logger = Logger.getMyLogger(getClass().getName());

    /**
     * The agent introspector to store the "beliefs"
     */
    private JadeAgentIntrospector introspector;

    /**
     * Indicates if the agent have been registered
     */
    private boolean registered;
    
    /**
     * The agent behavior
     */
    private AgentBehaviour behaviour;

    /**
     * Initializes the Agent.
     * 
     * @see jade.core.Agent#setup()
     */
    public void setup() {
        introspector = JadeAgentIntrospector.getMyInstance(this);

        introspector.storeBeliefValue(this, "message_count", 0);

        MockConfiguration configuration = (MockConfiguration) this
                .getArguments()[0];

        LogActivator.logToFile(logger, this.getName(), Level.ALL);

        behaviour = configuration.getBehaviour();
        // Attemps to register the aggent.
        registered = false;
        try {
            System.out.println(this.getLocalName());
            AgentRegistration.registerAgent(this, configuration.getDFservice(),
                    this.getLocalName());
            registered = true;
            System.out.println("Registered");
        } catch (FIPAException e) {
            System.out.println("Exception while registring the BridgeMock");
            logger.warning("Exception while registring the BridgeMock");
            logger.warning(e.getMessage()); // Will this show anything useful?
        }

        // Creates the instrospector
        introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");

        // Adds the behavior to store the messages.
        addBehaviour(new MessageReceiver());
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    /**
     * 
     * Sends a message to the agent with the given agent
     * 
     * @param String
     *            - The name of the destination
     * @param string
     *            - The content of the message
     * @param int -
     */
    public void sendMessage(String agentName, String content, int perf) {
        ACLMessage msg = new ACLMessage(perf);
        msg.addReceiver(introspector.getAgent(agentName).getAID());
        msg.setContent(content);
        send(msg);
    }

    /**
     * Private class to receive the messages
     * 
     * @author Alberto Mardomingo
     * 
     */
    private class MessageReceiver extends CyclicBehaviour {

        public static final long serialVersionUID = 0;

        /**
         * Constructor Creates the message receiver
         * 
         */
        public MessageReceiver() {
            super(BridgeMock.this);
        }

        /*
         * (non-javaDoc)
         * 
         * @see jade.core.CyclicBehaviour#action()
         */
        public void action() {
            ACLMessage msg = BridgeMock.this.receive();

            if (msg != null) {
                BridgeMock.this.logger.info("BridgeMock: Message receive.");
                BridgeMock.this.logger.finer("Message: " + msg.toString());

                // Recover the data to extract the message information

                String type = ACLMessage.getPerformative(msg.getPerformative());
                String sender_id = msg.getSender().getLocalName();
                String content = msg.getContent();

                if (sender_id.equals("BeastMessenger")) {
                    /*
                     * The sender_id var is of no real use, because this will
                     * crash if you call
                     * 
                     * BridgeMock.this.behaviour.processMessage(type, sender_id,
                     * content)
                     * 
                     * So, I first try using sender_id. If it fails, I try
                     * without it.
                     */
                    // Intended destination
                    String agentName;

                    // Message Type
                    int msgType = -1;

                    // The content of the message
                    String msgcontent;

                    try {
                        agentName = (String) BridgeMock.this.behaviour
                                .processMessage(type, sender_id, content);
                        msgType = ACLMessage.getInteger((String) BridgeMock.this.behaviour
                                .processMessage(type, sender_id, content));
                        msgcontent = (String) BridgeMock.this.behaviour
                                .processMessage(type, sender_id, content);
                    } catch (Exception e) {
                        logger.fine("Exception while using sender_id. Attempting without it...");
                        agentName = (String) BridgeMock.this.behaviour
                                .processMessage(type, content);
                        msgType = ACLMessage.getInteger((String) BridgeMock.this.behaviour
                                .processMessage(type, content));
                        msgcontent = (String) BridgeMock.this.behaviour
                                .processMessage(type, content);
                        logger.fine("Success");
                    }
                    /*
                     * A. Mardomingo:
                     * I think that an explanation of what I just did may come in handy.
                     * 
                     * When the BridgeMock is created, a mocked behavior is
                     * given, something like this:
                     * 
                     * AgentBehaviour myMockedBehaviour = mock(AgentBehaviour.class); 
                     * // In jade, the FIPA Inform is a constant in ACLMessage, but is stored as an int
                     * when(myMockedBehaviour.processMessage(
                     *              eq(ACLMessage.getPerformative(ACLMessage.CHOOSE_PERFORMATIVE)),
                     *              eq("send")))
                     *      .thenReturn("SomeAgent",
                     *                  ACLMessage.getPerformative(ACLMessage.CHOOSE_PERFORMATIVE),
                     *                   "A message");
                     * 
                     * // More strings can be added after the message.
                     * 
                     * Then, when a message sent to the BridgeMock that meets
                     * the given Performative and content (in this case, the
                     * performative would be CHOOSE_PERFORMATIVE and the message
                     * "Send"), it will trigger the behavior.
                     * 
                     * Here, we want to recover the "real" destination
                     * (SomeAgent), the Performative (CHOOSE_PERFORMATIVE), and
                     * the message (A message). In order to do so, we need to
                     * call
                     * 
                     * behaviour.processMessage(type, content);
                     * 
                     * -- OR --
                     * 
                     * behaviour.processMessage(type, sender_id, content)
                     * 
                     * Each call to this method will return one of the given
                     * strings, in the entered order. In this case, the first
                     * call will return the Agent (SomeAgent), the second call
                     * will return the Performative (CHOOSE_PREFORMATIVE), and
                     * the third call will return the message (A message)
                     * 
                     * FML
                     */

                    // Send the new message
                    if (agentName != null && msgcontent != null && msgType != -1) {
                        sendMessage(agentName, msgcontent, msgType);
                    } else {
                        block();
                    }
                } else {
                    block();
                }
            } else {
                block(); // No received messages.
            }
        }
    }
}
