package es.upm.dit.gsi.beast.platform.jade;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

import es.upm.dit.gsi.beast.platform.Connector;
import es.upm.dit.gsi.beast.platform.Messenger;
import es.upm.dit.gsi.beast.platform.PlatformSelector;

/**
 * @author a.carrera
 *
 */
public class JadeMessenger implements Messenger {

    /**
     * 
     */
    private static JadeMessenger INSTANCE = new JadeMessenger();

    /**
     * 
     */
    private JadeMessenger() {
    }

    /**
     * @return The messenger for Jade
     */
    public static JadeMessenger getInstance() {
        return INSTANCE;
    }
    
    /* (non-Javadoc)
     * @see es.upm.dit.gsi.beast.platform.Messenger#sendMessageToAgents(java.lang.String[], java.lang.String, java.lang.Object, es.upm.dit.gsi.beast.platform.Connector)
     */
    @Override
    public void sendMessageToAgents(String[] agent_name, String msgtype,
            Object message_content, Connector connector) {
        Agent messenger = (Agent) connector.getMessageService();
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector.getAgentIntrospector("jade");

        ACLMessage msg = null;
        if(message_content instanceof ACLMessage) {
            msg = (ACLMessage) message_content;
        } else if (message_content instanceof String) {
            msg = new ACLMessage(ACLMessage.getInteger(msgtype)); 
            msg.setContent( (String) message_content);            
        } else {
            connector.getLogger().warning("Incorrect message_content value. It should be a ACLMessage or a String.");
        }

        for (String name : agent_name) {
            Agent agent = introspector.getAgent(name);
            AID aid = agent.getAID();
            msg.addReceiver(aid);
            connector.getLogger().finer("Receiver added to the message...");
        }
        messenger.send(msg);
        connector.getLogger().finer("Message sent...");
    }

    /* (non-Javadoc)
     * @see es.upm.dit.gsi.beast.platform.Messenger#sendMessageToAgentsWithExtraProperties(java.lang.String[], java.lang.String, java.lang.Object, java.util.ArrayList, es.upm.dit.gsi.beast.platform.Connector)
     */
    @Override
    public void sendMessageToAgentsWithExtraProperties(String[] agent_name,
            String msgtype, Object message_content,
            ArrayList<Object> properties, Connector connector) {
        connector.getLogger().warning("Non suported method for Jade Platform. There is no extra properties.");
        throw new java.lang.UnsupportedOperationException("Non suported method for Jade Platform. There is no extra properties.");
    }

}
