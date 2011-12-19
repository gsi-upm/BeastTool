package es.upm.dit.gsi.beast.platform;

import jadex.commons.Tuple;

import java.util.ArrayList;

/**
 * Abstract Class that defines method
 * 
 * @author Jorge Solitario
 */
public interface Messenger {

	/**
	 * Method to send one message to many agents
	 * 
	 * @param agent_name The Array with the Receivers name
	 * @param msgtype SFipa.Inform or SFipa.Request
	 * @param message_content The content of the message
	 * @param connector The connector to the platform
	 */
	public void sendMessageToAgents(String[] agent_name, String msgtype, Object message_content, Connector connector);
	
	/**
	 * Method to send one message with extra properties to many agents
	 * 
	 * @param agent_name The Array with the Receivers name
	 * @param msgtype SFipa.Inform or SFipa.Request
	 * @param message_content The content of the message
	 * @param properties The properties to add to the message
	 * @param connector The connector to the platform
	 */
	public void sendMessageToAgentsWithExtraProperties(String[] agent_name, String msgtype, Object message_content, ArrayList<Tuple> properties, Connector connector);
}
