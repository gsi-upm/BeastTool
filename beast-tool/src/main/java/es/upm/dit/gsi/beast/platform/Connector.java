package es.upm.dit.gsi.beast.platform;

/**
 * Abstract Class that defines method
 * 
 * @author Jorge Solitario
 */
public abstract class Connector {

	public abstract void launchPlatform();

	public abstract void createAgent(String agent_name, String path);
}
