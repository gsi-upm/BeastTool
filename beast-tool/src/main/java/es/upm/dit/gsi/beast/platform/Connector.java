package es.upm.dit.gsi.beast.platform;

import java.util.logging.Logger;

/**
 * Abstract Class that defines method
 * 
 * @author jjsolitario
 * @author a.carrera
 */
public interface Connector {

    /**
     * It starts the execution of the platform
     */
    public void launchPlatform();

    /**
     * To create one agent in the platform
     * 
     * @param agent_name
     *            The name of the agent
     * @param path
     *            where is the agent
     */
    public void createAgent(String agent_name, String path);
    
    
    /**
     * To create one agent in the platform
     * 
     * @param agentName The name of the agent
     * @param path where is the agent
     * @param containerName where the agent will be created
     * @param arguments for the agent
     */
    public void createAgent(String agentName, String path, String containerName, Object[] arguments);

    /**
     * To get the platform's ID of a created agent
     * 
     * @param agent_name
     *            The name of the agent
     * @return The agent's ID
     */
    public Object getAgentID(String agent_name);

    /**
     * Method that allows us sendind messages
     * 
     * @return The service to send messages
     */
    public Object getMessageService();

    /**
     * Method to get the external access of an agent, necessary to see its
     * behaviour
     * 
     * @param agent_name
     *            The name of the agent
     * @return The external access of an agent
     */
    public Object getAgentsExternalAccess(String agent_name);
    
    /**
     * @return Connector Logger 
     */
    public Logger getLogger();

}
