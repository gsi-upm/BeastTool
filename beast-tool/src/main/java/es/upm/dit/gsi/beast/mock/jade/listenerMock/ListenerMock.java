package es.upm.dit.gsi.beast.mock.jade.listenerMock;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.logging.Level;

import es.upm.dit.gsi.beast.mock.jade.common.AgentRegistration;
import es.upm.dit.gsi.beast.platform.PlatformSelector;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.logging.LogActivator;
//import jade.content.onto.Introspector;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
//import jade.lang.acl.MessageTemplate;
import jade.util.Logger;

/**
 * Listens the received messages, and stores then for future study
 * 
 * @author Alberto Mardomingo
 *
 */
public class ListenerMock extends Agent{

    /**
     * 
     */
    private static final long serialVersionUID = 7448955222643359821L;

    /**
     *  Logger
     */
    protected final Logger logger = Logger.getMyLogger(getClass().getName());
    
    /**
     * All the received and unprocessed messages
     */
    private ArrayList<ACLMessage> messages;
    
    /**
     * The agent introspector to store the "beliefs"
     */
    private JadeAgentIntrospector introspector;
    
    /**
     * Indicates if the agent have been registered
     */
    private boolean registered;
    /**
     * Initializes the Agent.
     * 
     * @see jade.core.Agent#setup()
     */
    public void setup(){
        
        LogActivator.logToFile(logger, this.getName(), Level.ALL);
        
        messages = new ArrayList<ACLMessage>();
        
        // Attemps to register the aggent.
        registered = false;
        try {
            AgentRegistration.registerAgent(this, "ListenerMock");
        } catch(FIPAException e) {
            logger.warning("Exception while registring the ListenerMock");
            logger.warning(e.getCause().toString()); // Will this show anything useful?
        }
        
        // Creates the instrospector
        introspector = (JadeAgentIntrospector) PlatformSelector.getAgentIntrospector("jade");
        // Adds the behavior to store the messages.
        addBehaviour(new MessageReceiver());
    }
    
    /**
     * Stores a given message
     * 
     * @param ACLMessage - The message to store 
     */
    public void storeMessage(ACLMessage msg){
        if (msg != null){
            messages.add(msg);
            // Increases the count.
            modifyBeliefCount(1);
        }
    }
    
    /**
     * Returns the first message stored, and delete it
     * 
     * @return ACLMessage - the first message stored
     */
    public ACLMessage getFirstMessage(){
        // Decreases the count.
        modifyBeliefCount(-1);
        return messages.remove(0);
    }
    
    /**
     * Returns all the stored messages.
     * If the given argument is true, also deletes the list
     * 
     * @param boolean - If true, deletes the list of messages
     * @return ArrayList<ACLMessage> - the list of messages
     */
    public ArrayList<ACLMessage> getAllMessages(boolean delete) {
        ArrayList<ACLMessage> toReturn = (ArrayList<ACLMessage>)messages.clone();
        // Deletes everything
        if (delete){
            messages.clear();
            modifyBeliefCount(- getBeliefCount());
        }
        return toReturn;
        // Not really sure if this is the best way...
    }
    
    /**
     * Modifies the "msgCount" belief
     * 
     * @param int - the number to add or remove
     */
    private void modifyBeliefCount(int count){
        introspector.storeBeliefValue(ListenerMock.this, "message_count", getBeliefCount() + count);
    }
    
    /**
     * Returns the "msgCount" belief
     * 
     * @return int - the count
     */
    private int getBeliefCount(){
        Integer count = (Integer)introspector.retrieveBelievesValue(ListenerMock.this).get("message_count");
        if (count == null) count = 0; // Just in case, not really sure if this is necessary.
        return count;
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
            super(ListenerMock.this);
        }
        
        /*
         * (non-javaDoc) 
         * @see jade.core.CyclicBehaviour#action()
         */
        public void action(){
           ACLMessage msg = ListenerMock.this.receive();
           if(msg != null ){
               ListenerMock.this.logger.info("Listener: Message received.");
               ListenerMock.this.logger.finer("Message: " + msg.toString());
               
               // Stores the message
               ListenerMock.this.storeMessage(msg);
           } else {
               block();
           }
        }
    }
}
