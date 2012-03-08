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
 * @author a.carrera
 */
public class PlatformSelector {

    //TODO create one constant -> public static final String PLATFORM_JADEX="jadex" for each platform and use them to avoid mistakes
    
    /**
     * @param platform
     * @return the connector to the platform
     */
    public static Connector getConnector(String platform, Logger logger) {

        if (platform.equalsIgnoreCase("jadex")) {
            return new JadexConnector(logger);
        } else if (platform.equalsIgnoreCase("jade")){
            return new JadeConnector(logger);
        }
        return null;
    }

    /**
     * @param platform
     * @return the messenger of the platform
     */
    public static Messenger getMessenger(String platform) {

        if (platform.equalsIgnoreCase("jadex")) {
            return JadexMessenger.getInstance();
        } else if (platform.equalsIgnoreCase("jade")){
            return JadeMessenger.getInstance();
        }
        return null;
    }

    /**
     * @param platform
     * @return the introspector of the platform
     */
    public static AgentIntrospector getAgentIntrospector(String platform) {

        if (platform.equalsIgnoreCase("jadex")) {
            return JadexAgentIntrospector.getInstance();
        } else if (platform.equalsIgnoreCase("jade")){
            return JadeAgentIntrospector.getInstance();
        }
        return null;
    }
}
