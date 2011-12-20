package es.upm.dit.gsi.beast.platform.jadex;

import jadex.base.fipa.SFipa;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.IMessageService;
import jadex.commons.Tuple;

import java.util.ArrayList;
import java.util.HashMap;

import es.upm.dit.gsi.beast.platform.Connector;
import es.upm.dit.gsi.beast.platform.Messenger;

/**
 * To send messages to our jadex agents.
 * 
 * @author Jorge Solitario
 */
public class JadexMessenger implements Messenger {

    private static JadexMessenger INSTANCE = new JadexMessenger();

    private JadexMessenger() {
    }

    public static JadexMessenger getInstance() {
        return INSTANCE;
    }

    /**
     * This method sends the same message to many agents.
     * 
     * @param agent_name
     *            The id of the agents that receive the message
     * @param msgtype
     * @param message_content
     *            The content of the message
     * @param connector
     *            The connector to get the external access
     */
    public void sendMessageToAgents(String[] agent_name, String msgtype,
            Object message_content, Connector connector) {
        HashMap<String, Object> hm = new HashMap<String, Object>();
        hm.put("performative", msgtype);
        hm.put(SFipa.CONTENT, message_content);
        IComponentIdentifier[] ici = new IComponentIdentifier[agent_name.length];
        for (int i = 0; i < agent_name.length; i++) {
            ici[i] = (IComponentIdentifier) connector.getAgentID(agent_name[i]);
        }
        ((IMessageService) connector.getMessageService()).deliverMessage(hm,
                "fipa", ici);
    }

    /**
     * This method works as the one above, adding some properties to the message
     * 
     * @param agent_name
     *            The id of the agents that receive the message
     * @param msgtype
     * @param message_content
     *            The content of the message
     * @param properties
     *            to be added to the message
     * @param connector
     *            The connector to get the external access
     */
    public void sendMessageToAgentsWithExtraProperties(String[] agent_name,
            String msgtype, Object message_content,
            ArrayList<Tuple> properties, Connector connector) {
        HashMap<String, Object> hm = new HashMap<String, Object>();
        hm.put("performative", msgtype);
        hm.put(SFipa.CONTENT, message_content);

        for (Tuple property : properties) {
            // Logger logger = Logger.getLogger("JadexMessenger");
            // logger.info("Sending message with extra property: "+property.get(0)+", with value "+property.get(1));
            hm.put((String) property.get(0), property.get(1));
        }

        IComponentIdentifier[] ici = new IComponentIdentifier[agent_name.length];
        for (int i = 0; i < agent_name.length; i++) {
            ici[i] = (IComponentIdentifier) connector.getAgentID(agent_name[i]);
        }
        ((IMessageService) connector.getMessageService()).deliverMessage(hm,
                "fipa", ici);
    }

}
