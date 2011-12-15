package es.upm.dit.gsi.beast.platform;



/**
 * Abstract Class that defines method
 * 
 * @author Jorge Solitario
 */
public interface AgentIntrospector {

	public Object getBeliefValue(String agent_name, final String belief_name, Connector connector);
	
	public void setBeliefValue(String agent_name, final String belief_name, final Object new_value, Connector connector);
	
	public Object[] getAgentPlans (final String agent_name, Connector connector);
	
	public Object[] getAgentGoals (final String agent_name, Connector connector);
	
}
