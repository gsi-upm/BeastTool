package es.upm.dit.gsi.beast.platform.jade;

import jadex.commons.Tuple;

import java.util.ArrayList;

import es.upm.dit.gsi.beast.platform.Connector;
import es.upm.dit.gsi.beast.platform.Messenger;

public class JadeMessenger implements Messenger {

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
