package es.upm.dit.gsi.beast.platform;

import jadex.commons.Tuple;

import java.util.ArrayList;

/**
 * Abstract Class that defines method
 * 
 * @author Jorge Solitario
 */
public interface Messenger {

	public void sendMessageToAgents(String[] agent_name, String msgtype, Object message_content, Connector connector);
	
	public void sendMessageToAgentsWithExtraProperties(String[] agent_name, String msgtype, Object message_content, ArrayList<Tuple> properties, Connector connector);
}
