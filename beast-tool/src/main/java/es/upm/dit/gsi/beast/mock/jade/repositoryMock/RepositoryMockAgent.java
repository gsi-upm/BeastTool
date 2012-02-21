package es.upm.dit.gsi.beast.mock.jade.repositoryMock;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

import java.util.HashMap;
import java.util.logging.Level;

import es.upm.dit.gsi.beast.mock.common.Definitions;
import es.upm.dit.gsi.beast.mock.common.MockConfiguration;
import es.upm.dit.gsi.beast.mock.jade.common.AgentRegistration;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.logging.LogActivator;


public class RepositoryMockAgent extends Agent {


    /**
     *  Logger
     */
    protected final Logger logger = Logger.getMyLogger(getClass().getName());
    
    /**
     * All the received and unprocessed messages
     */
    private HashMap<String, String> db;
    
    /**
     * The introspector agent that allows the access to instances of agents of the platform.
     * Also, give access to a belief approach for Jade Agents.  
     */
    private JadeAgentIntrospector introspector;
    
    /**
     * Initializes the Agent.
     * 
     * @see jade.core.Agent#setup()
     */
    public void setup(){
        
        //TODO add code to include the user AgentBehaviour
        
        LogActivator.logToFile(logger, this.getName(), Level.ALL);
        MockConfiguration configuration = (MockConfiguration) this.getArguments()[0];
        
        // Add the agent to the introspector, an obtain an introspector instance.
        introspector = JadeAgentIntrospector.getMyInstance(this);
        logger.fine("Agent "+this.getLocalName()+" added to the Intrsopector");

        // Initialize the believes counts. 
        introspector.storeBeliefValue(this, Definitions.RECEIVED_MESSAGE_COUNT, 0);
        introspector.storeBeliefValue(this, Definitions.STORED_DATA_COUNT, 0);
        
        db = new HashMap<String, String>();
        
        // Attemps to register the aggent.
        try {
            AgentRegistration.registerAgent(this, configuration.getDFservice(), Definitions.REPOSITORY_SERVICE_TYPE);
        } catch(FIPAException e) {
            logger.warning("Exception while registering the RespositoryMockAgent");
            logger.warning(e.getStackTrace().toString()); 
        }
        
        // Adds the behavior to store the messages.
        addBehaviour(new Listen(this));
    }
    
    /**
     * Stores the content from the given message
     * 
     * @param ACLMessage - The message with the content to store 
     */
    public void store(ACLMessage msg) {
        if (msg != null){
            int id = db.size();
            db.put(""+id, msg.getContent());

            // Increases the stored count belief.
            this.increaseBeliefCount(Definitions.STORED_DATA_COUNT);
        }
        //TODO Check the exceptions treatment.
        // Confirm the correct sotre of the data. 
        ACLMessage response = new ACLMessage(ACLMessage.INFORM); 
        response.addReceiver(msg.getSender());
        response.setContent(""+ACLMessage.CONFIRM);
        this.send(response);
    }
    
    /**
     * If the belief its a count of some sort his counting its increased by one.
     *  
     * @param bName - the name of the belief count.
     */
    private void increaseBeliefCount(String bName) {
        int count = (Integer) this.getBelief(bName);
        this.setBelief(bName, count+1);
    }
    
    /**
     * Modifies the belief referenced by bName parameter. 
     * 
     * @param bName - the name of the belief to update. 
     * @param value - the new value for the belief
     */
    public void setBelief(String bName, Object value){
        introspector.storeBeliefValue(this, Definitions.RECEIVED_MESSAGE_COUNT, value);
    }
    
    /**
     * Returns the belief value referenced by bName parameter.
     * 
     * @return bName - the name of the belief to consult. 
     */
    public Object getBelief(String bName){
        return introspector.retrieveBelievesValue(this).get(bName);
//        if (count == null) count = 0; // Just in case, not really sure if this is necessary.
    }
    
    /**
     * This class define a listen behaviour that cyclically looks for incoming messages.
     * 
     * @author Danny
     *
     */
    private class Listen extends CyclicBehaviour {
        
        private RepositoryMockAgent myAgent;
        
        /**
         * Default Constructor
         * Take the current instance of the RepositoryMockAgent as myAgent parameter.   
         * 
         */
        public Listen(){
            super(RepositoryMockAgent.this);
            this.myAgent = RepositoryMockAgent.this; 
        }

        /**
         * Constructor
         * The target agent is passed as parameter. 
         *  
         * @param a
         */
        public Listen(Agent a){
            super(a);
            this.myAgent = (RepositoryMockAgent) a; 
        }
        
        
        /*
         * Retrieve the new message from the mail and consult his performative. 
         * If the message is a Fipa.REQUEST message then proceed to store his content and increase 
         * stored count belief. If not then just ignore the message.
         * 
         * In either case increase the received message count. 
         *  
         */
        public void action(){
           ACLMessage msg = myAgent.receive();
           if(msg != null ){
               myAgent.logger.info(myAgent.getLocalName()+": Message received.");
               myAgent.logger.finer("Message: " + msg.toString());
               
               if (msg.getPerformative() == ACLMessage.REQUEST){
                   // Store the message content and actualize the belief count
                   myAgent.store(msg);
               }
               
               //Increase the message count belief.
               myAgent.increaseBeliefCount(Definitions.STORED_DATA_COUNT);
           
           } else {
               block();
           }
        }
        
        private static final long serialVersionUID = -6323904311436847205L;
    }

    private static final long serialVersionUID = 7448955222643359821L;
}
