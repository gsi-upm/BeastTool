package es.upm.dit.gsi.beast.mock.jade.bridgeMock;

import java.util.logging.Level;

//import es.upm.dit.gsi.beast.mock.jade.listenerMock.ListenerMock;
import es.upm.dit.gsi.beast.platform.PlatformSelector;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.logging.LogActivator;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.Logger;

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
    
    // TODO: Implement jade BridgeMock.
    
    /**
     * Initializes the Agent.
     * 
     * @see jade.core.Agent#setup()
     */
    public void setup(){
        LogActivator.logToFile(logger, this.getName(), Level.ALL);
         
        // Agent registration
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        
        sd.setType("BridgeMock");
        sd.setName(this.getLocalName());
        
        //Add services??
        
        // Sets the agent description
        dfd.setName(this.getAID());
        dfd.addServices(sd);
        
        // Register the agent
        try {
            DFService.register(this, dfd);
        } catch (Exception e) {
            logger.warning("Exception while registring the Bridge Jade Mock:");
            logger.warning(e.getMessage());
            logger.warning(e.getCause().toString());
            // TODO: I should probably stop the setup, retry or something.
        }
        // Creates the instrospector
        this.introspector = (JadeAgentIntrospector) PlatformSelector.getAgentIntrospector("jade");
        
        /*
         * Add a Behavior to receive and process the messages.
         */
        addBehaviour(new MessageReceiver());
    }
    
    /**
     * 
     * Sends a message to the agent with the given UID
     * 
     * @param long - The serial UID of the agent
     */
    public void sendMessage(long ReceiverUID){
        //¿?¿?¿?
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
               //TODO: Implement MessageReceiver.action()
               BridgeMock.this.logger.info("Listener: Message receive.");
               BridgeMock.this.logger.finer("Message: " + msg.toString());
               
           } else {
               block();
           }
        }
    }
}
