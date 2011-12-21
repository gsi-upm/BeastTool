package es.upm.dit.gsi.beast.platform.jade;

import java.util.ArrayList;

import es.upm.dit.gsi.beast.platform.Connector;
import es.upm.dit.gsi.beast.platform.Messenger;

public class JadeMessenger implements Messenger {

    private static JadeMessenger INSTANCE = new JadeMessenger();

    private JadeMessenger() {
    }

    public static JadeMessenger getInstance() {
        return INSTANCE;
    }
    
    @Override
    public void sendMessageToAgents(String[] agent_name, String msgtype,
            Object message_content, Connector connector) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendMessageToAgentsWithExtraProperties(String[] agent_name,
            String msgtype, Object message_content,
            ArrayList<Object> properties, Connector connector) {
        // TODO Auto-generated method stub

    }

}
