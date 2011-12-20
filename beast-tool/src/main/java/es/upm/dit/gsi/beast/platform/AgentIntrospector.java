package es.upm.dit.gsi.beast.platform;

/**
 * Abstract Class that defines method
 * 
 * @author Jorge Solitario
 */
public interface AgentIntrospector {

    /**
     * 
     * @param agent_name
     *            The name of the agent
     * @param belief_name
     *            The name of the belief
     * @param connector
     *            The connector to the platform
     * @return The value of the belief
     */
    public Object getBeliefValue(String agent_name, final String belief_name,
            Connector connector);

    /**
     * 
     * @param agent_name
     *            The name of the agent
     * @param belief_name
     *            The name of the belief
     * @param new_value
     *            The new value of the belief
     * @param connector
     *            The connector to the platform
     */
    public void setBeliefValue(String agent_name, final String belief_name,
            final Object new_value, Connector connector);

    /**
     * 
     * @param agent_name
     *            The name of the agent
     * @param connector
     *            The connector to the platform
     * @return The current plans of the agent
     */
    public Object[] getAgentPlans(final String agent_name, Connector connector);

    /**
     * 
     * @param agent_name
     *            The name of the agent
     * @param connector
     *            The connector to the platform
     * @return The current goals of the agent
     */
    public Object[] getAgentGoals(final String agent_name, Connector connector);

}
