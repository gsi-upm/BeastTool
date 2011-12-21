package es.upm.dit.gsi.beast.platform;

import java.util.logging.Logger;

import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.platform.jade.JadeConnector;
import es.upm.dit.gsi.beast.platform.jade.JadeMessenger;
import es.upm.dit.gsi.beast.platform.jadex.JadexAgentIntrospector;
import es.upm.dit.gsi.beast.platform.jadex.JadexConnector;
import es.upm.dit.gsi.beast.platform.jadex.JadexMessenger;

/**
 * Class to select the type of platform our code is going to run
 * 
 * @author Álvaro Carrera Barroso
 */
public class PlatformSelector {

    /**
     * @param platform
     * @return the connector to the platform
     */
    public static Connector getConnector(String platform, Logger logger) {

        if (platform.equals("jadex")) {
            return new JadexConnector(logger);
        } else if (platform.equals("jade")){
            return new JadeConnector(logger);
        }
        return null;
    }

    /**
     * @param platform
     * @return the messenger of the platform
     */
    public static Messenger getMessenger(String platform) {

        if (platform.equals("jadex")) {
            return JadexMessenger.getInstance();
        } else if (platform.equals("jade")){
            return new JadeMessenger();
        }
        return null;
    }

    /**
     * @param platform
     * @return the introspector of the platform
     */
    public static AgentIntrospector getAgentIntrospector(String platform) {

        if (platform.equals("jadex")) {
            return JadexAgentIntrospector.getInstance();
        } else if (platform.equals("jade")){
            return new JadeAgentIntrospector();
        }
        return null;
    }
}
