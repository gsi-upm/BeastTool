package es.upm.dit.gsi.beast.platform.jade.agent;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.Logger;

import java.util.logging.Level;

import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.logging.LogActivator;

/**
 * @author a.carrera
 * @date 28/12/2011
 * @version 0.1
 */
public class MessengerAgent extends Agent {

    /**
     * 
     */
    private static final long serialVersionUID = -3802547296896248151L;

    protected final Logger logger = Logger.getMyLogger(getClass().getName());

    private MessageTemplate template;

    private boolean isReceived;
    private ACLMessage receivedMsg;

    /*
     * (non-Javadoc)
     * 
     * @see jade.core.Agent#setup()
     */
    public void setup() {
        JadeAgentIntrospector.getMyInstance(this);
        LogActivator.logToFile(logger, this.getName(), Level.ALL);
        this.template = MessageTemplate.MatchAll();
        addBehaviour(new RequestServer());
    }

    public boolean isReceived() {
        return isReceived;
    }

    public MessageTemplate getMyTemplate() {
        return template;
    }

    public void setMyTemplate(MessageTemplate template) {
        this.template = template;
    }

    public ACLMessage getReceivedMsg() {
        isReceived = false;
        return receivedMsg;
    }

    public void setReceivedMsg(ACLMessage receivedMsg) {
        this.receivedMsg = receivedMsg;
    }

    protected void takeDown() {
    }

    /**
     * Inner class RequestServer This behaviour serves the requests for
     * observations
     */
    private class RequestServer extends CyclicBehaviour {
        
        private static final long serialVersionUID = 286992171997750565L;

        public RequestServer() {
            super(MessengerAgent.this);
        }

        public void action() {
            ACLMessage msg = myAgent.receive(template);
            if (msg != null) {
                receivedMsg = msg;
                isReceived = true;
            } else {
                block();
            }
        }
    }
}
