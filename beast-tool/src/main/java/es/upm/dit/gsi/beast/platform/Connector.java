package es.upm.dit.gsi.beast.platform;

import es.upm.dit.gsi.beast.platform.jadex.JadexConnector;




/**
 * Abstract Class that defines method
 * 
 * @author Jorge Solitario
 */
public abstract class Connector {

	public abstract void launchPlatform();

	public abstract void createAgent(String agent_name, String path);
	
	public abstract Object getAgentID(String agent_name);

	public abstract Object getMessageService();

	public abstract Object getAgentsExternalAccess(String agent_name);

}
