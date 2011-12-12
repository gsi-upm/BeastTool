package es.upm.dit.gsi.beast.platform;

public abstract class Connector {

	
	public abstract void launchPlatform();

	public abstract void createAgent(String agent_name, String path);
}
