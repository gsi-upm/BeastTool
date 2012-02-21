package es.upm.dit.gsi.beast.mock.jade.bridgeMock;

import java.util.logging.Level;

//import es.upm.dit.gsi.beast.mock.jade.listenerMock.ListenerMock;
import es.upm.dit.gsi.beast.mock.jade.common.AgentRegistration;
import es.upm.dit.gsi.beast.mock.common.MockConfiguration;
import es.upm.dit.gsi.beast.platform.PlatformSelector;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.logging.LogActivator;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.Envelope;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;
import jade.util.leap.Iterator;

/**
 * 
 * Allows communication between agents.
 * 
 * @author Alberto Mardomingo
 *
 */
public class BridgeMock extends Agent{
    
    /**
     * Serial number for the agent
     */
    private static final long serialVersionUID = 2768685097091062141L;
    
    /**
     *  Logger
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
     * Array with templates
     */
    // Deprecated
    //private ArrayList<MessageTemplate> msgTemplates;
    
    /**
     * List with the destinations
     */
    //Deprecated
    //private ArrayList<String[]> destinations;
    
    /**
     * IMPORTANT:
     * The index for a template's destinations corresponds with its own index.
     * Example:
     * Template --> msgTemplates[3]
     * Destinations --> destinations[3]
     * 
     */
    // TODO: Implement jade BridgeMock.
    
    /**
     * Initializes the Agent.
     * 
     * @see jade.core.Agent#setup()
     */
    public void setup(){
        introspector = JadeAgentIntrospector.getMyInstance(this);

        introspector.storeBeliefValue(this, "message_count", 0);
        
        MockConfiguration configuration = (MockConfiguration) this.getArguments()[0];
        
        LogActivator.logToFile(logger, this.getName(), Level.ALL);
        
        // Attemps to register the aggent.
        registered = false;
        try {
            AgentRegistration.registerAgent(this,configuration.getDFservice(), null);
            registered = true;
        } catch(FIPAException e) {
            logger.warning("Exception while registring the ListenerMock");
            logger.warning(e.getMessage()); // Will this show anything useful?
        }
        
        // Creates the instrospector
        introspector = (JadeAgentIntrospector) PlatformSelector.getAgentIntrospector("jade");
        // Adds the behavior to store the messages.
        addBehaviour(new MessageReceiver());
    }
    
    /**
     * 
     * Sends a message to the agent with the given agent
     * 
     * @param String - The name of the destination
     * @param string - The content of the message
     * @param int - 
     */
    public void sendMessage(String agentName, String content, int perf){
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
         * Constructor
         * Creates the message receiver
         * 
         */
        public MessageReceiver(){
            super(BridgeMock.this);
        }
        
        /*
         * (non-javaDoc) 
         * @see jade.core.CyclicBehaviour#action()
         */
        public void action(){
           ACLMessage msg = BridgeMock.this.receive();
           
           if(msg != null ){
               BridgeMock.this.logger.info("Listener: Message receive.");
               BridgeMock.this.logger.finer("Message: " + msg.toString());
               
               // Recover the envelope.
               Envelope env = msg.getEnvelope();
               
               // Not really sure which way is better
//               ACLMessage resend = new ACLMessage(msg.getPerformative());
//               resend.setContent(msg.getContent());
               ACLMessage resend = (ACLMessage) msg.clone();
               resend.clearAllReceiver();
               resend.clearAllReplyTo();
               
               // Iterate over the "intendedReceivers" and adds them to the resend message
               Iterator iter = env.getAllIntendedReceiver();    
               while(iter.hasNext()){
                   // Adds the receivers.
                   String receiver = (String) iter.next();
                   if (receiver != null) {
                       resend.addReceiver(BridgeMock.this.introspector.getAgent(receiver).getAID());
                   }
               }
               send(resend);
           } else {
               block(); // No received messages.
           }
        }
    }
}
