package es.upm.dit.gsi.beast.platform;

import es.upm.dit.gsi.beast.platform.jadex.JadexAgentIntrospector;
import es.upm.dit.gsi.beast.platform.jadex.JadexConnector;
import es.upm.dit.gsi.beast.platform.jadex.JadexMessenger;

public class PlatformSelector {

	public static Connector getConnector(String platform) {

		if (platform == "jadex") {
			return new JadexConnector();
		}

		return null;
	}
	
	public static Messenger getMessenger(String platform) {

		if (platform == "jadex") {
			return JadexMessenger.getInstance();
		}

		return null;
	}
	
	public static AgentIntrospector getAgentIntrospector(String platform) {

		if (platform == "jadex") {
			return JadexAgentIntrospector.getInstance();
		}

		return null;
	}
}
