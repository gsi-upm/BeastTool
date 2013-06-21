package es.upm.dit.gsi.beast.mock.jade.listenerMock;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

import java.util.ArrayList;
import java.util.logging.Level;

import es.upm.dit.gsi.beast.mock.common.Definitions;
import es.upm.dit.gsi.beast.mock.common.MockConfiguration;
import es.upm.dit.gsi.beast.mock.jade.common.AgentRegistration;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.logging.LogActivator;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.mock.jade.listenerMock.ListenerMockAgent.java
 * 
 * Listens the received messages, and stores then for future study
 * 
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * @author Alberto Mardomingo
 * @version 0.1
 * 
 */
public class ListenerMockAgent extends Agent{

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
     * @return if the agent is registered in DF service
     */
    public boolean isRegistered() {
        return registered;
    }
    
    /**
     * Initializes the Agent.
     * 
     * @see jade.core.Agent#setup()
     */
    public void setup(){
        LogActivator.logToFile(logger, this.getName(), Level.ALL);
        
        introspector = JadeAgentIntrospector.getMyInstance(this);
        introspector.storeBeliefValue(this, Definitions.RECEIVED_MESSAGE_COUNT, 0);
        MockConfiguration configuration = (MockConfiguration) this.getArguments()[0];

        messages = new ArrayList<ACLMessage>();
        
        // Attemps to register the aggent.
        registered = false;
        try {
            AgentRegistration.registerAgent(this, configuration.getDFservice(), this.getLocalName());
            registered = true;
        } catch(FIPAException e) {
            logger.warning("Exception while registring the ListenerMock");
            logger.warning(e.getMessage()); // Will this show anything useful?
        }

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
     *  
     * @return ArrayList<ACLMessage> - the list of messages
     */
    public ArrayList<ACLMessage> getAllMessages() {
        return this.messages;
    }
    
    /**
     * Modifies the "msgCount" belief
     * 
     * @param int - the number to add or remove
     */
    private void modifyBeliefCount(int count){
        introspector.setBeliefValue(getLocalName(), Definitions.RECEIVED_MESSAGE_COUNT, getBeliefCount()+count, null);
    }
    
    /**
     * Returns the "msgCount" belief
     * 
     * @return int - the count
     */
    private int getBeliefCount(){
        Integer count = (Integer)introspector.retrieveBelievesValue(ListenerMockAgent.this).get(Definitions.RECEIVED_MESSAGE_COUNT);
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

        private static final long serialVersionUID = 8785592026926424080L;
        
        /**
         * Constructor
         * Creates the message receiver
         * 
         */
        public MessageReceiver(){
            super(ListenerMockAgent.this);
        }
        
        /*
         * (non-javaDoc) 
         * @see jade.core.CyclicBehaviour#action()
         */
        public void action(){
           ACLMessage msg = ListenerMockAgent.this.receive();
           if(msg != null ){
               ListenerMockAgent.this.logger.info("Listener: Message received.");
               ListenerMockAgent.this.logger.finer("Message: " + msg.toString());
               
               // Stores the message
               ListenerMockAgent.this.storeMessage(msg);
               System.out.println("Message received");
           } else {
               block();
           }
        }
    }
}
