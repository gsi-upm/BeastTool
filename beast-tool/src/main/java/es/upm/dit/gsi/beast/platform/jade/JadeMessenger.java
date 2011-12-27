package es.upm.dit.gsi.beast.platform.jade;

import java.util.ArrayList;

import es.upm.dit.gsi.beast.platform.Connector;
import es.upm.dit.gsi.beast.platform.Messenger;

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
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see es.upm.dit.gsi.beast.platform.Messenger#sendMessageToAgentsWithExtraProperties(java.lang.String[], java.lang.String, java.lang.Object, java.util.ArrayList, es.upm.dit.gsi.beast.platform.Connector)
     */
    @Override
    public void sendMessageToAgentsWithExtraProperties(String[] agent_name,
            String msgtype, Object message_content,
            ArrayList<Object> properties, Connector connector) {
        // TODO Auto-generated method stub

    }

}
