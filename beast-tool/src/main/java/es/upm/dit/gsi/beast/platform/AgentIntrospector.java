package es.upm.dit.gsi.beast.platform;



/**
 * Abstract Class that defines method
 * 
 * @author Jorge Solitario
 */
public abstract class AgentIntrospector {

	public abstract Object getBeliefValue(String agent_name, final String belief_name, Connector connector);
	
	public abstract void setBeliefValue(String agent_name, final String belief_name, final Object new_value, Connector connector);
	
	public abstract Object[] getAgentPlans (final String agent_name, Connector connector);
	
	public abstract Object[] getAgentGoals (final String agent_name, Connector connector);
	
}
