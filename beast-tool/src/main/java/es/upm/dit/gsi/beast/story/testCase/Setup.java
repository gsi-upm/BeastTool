package es.upm.dit.gsi.beast.story.testCase;

import jadex.bdi.runtime.IGoal;
import jadex.bdi.runtime.IPlan;
import jadex.commons.Tuple;

import java.util.ArrayList;

import es.upm.dit.gsi.beast.platform.jadex.JadexAgentIntrospector;


/**
 * The setup of our scenario. We can use it to send messages, to ask for
 *  values of beliefs and for changing the value of beliefs.
 *  It is related with the WHEN part of BDD. 
 *  Generic information about plans and goals can be asked.
 * 
 * @author Jorge solitario
 */
public abstract class Setup {

	private Scenario scenario;
	public abstract void setStates();
	
	/**
	 * Once given the scenario, child's setSetup() will be run 
	 * 
	 * @param scenario2
	 */
	public void setScenario(Scenario scenario2) {
		this.scenario = scenario2;
		setStates();		
	}
	
	/**
	 * This method changes the value of an agent's belief through its external access
	 * 
	 * @param agent_name The name of the agent to change a belief
	 * @param belief_name The name of the belief to change
	 * @param new_value The new value of the belief to be changed
	 */
	public void setBeliefValue (String agent_name, final String belief_name, final Object new_value ) {

		JadexAgentIntrospector.setBeliefValue (agent_name, belief_name, new_value, scenario.jadexConnector);	
	}
	
	/**
	 * This method takes the value of an agent's belief through its external access
	 * 
	 * @param agent_name The name of the agent
	 * @param belief_name The name of the belief inside agent's adf
	 * @return belief_value The value of the requested belief
	 */
	public Object getBeliefValue (String agent_name, final String belief_name) {

		return JadexAgentIntrospector.getBeliefValue (agent_name, belief_name, scenario.jadexConnector);
	}

	
	/**
	 * It sends one message of requested type to an agent
	 * 
	 * @param agent_name The name of the agent that receives the message
	 * @param msgtype The type of the message (SFipa.INFORM - SFipa.REQUEST)
	 * @param message_content The content of the message
	 */
	public void sendMessageToAgent(String agent_name, String msgtype, Object message_content) {	
		scenario.sendMessageToAgent(agent_name, msgtype, message_content);		
	}
	
	
	
	
	/**
	 * The same as above but with many agents
	 * 
	 * @param agent_name The name of the agent that receives the message
	 * @param msgtype The type of the message (SFipa.INFORM - SFipa.REQUEST)
	 * @param message_content The content of the message
	 */
	public void sendMessageToAgents(String[] agent_name, String msgtype, Object message_content) {
		scenario.sendMessageToAgents(agent_name, msgtype, message_content);	
	}
	
	/**
	 * This method prints plan information of an agent through its external access.
	 * It can be used to check the correct behaviour of the agent.
	 * 
	 * @param agent_name The name of the agent
	 * @return IPlan[] so the tester can get the plans information
	 */
	public IPlan[] getAgentPlans (final String agent_name) {

		return JadexAgentIntrospector.getAgentPlans(agent_name,scenario.jadexConnector);
	}		
	
	
	/**
	 * This method prints goal information of an agent through its external access.
	 * It can be used to check the correct behaviour of the agent.
	 * 
	 * @param agent_name  The name of the agent
	 * @return IGoal[] so the tester can get the goals information
	 */
	public IGoal[] getAgentGoals (final String agent_name) {
		return JadexAgentIntrospector.getAgentGoals(agent_name,scenario.jadexConnector);
	}

	/**
	 * It sends one message of requested type to an agent, including some extra
	 * parameters in the message event, such as ontology or language.
	 * 
	 * @param agent_name The name of the agent
	 * @param msgtype The type of the message (SFipa.INFORM - SFipa.REQUEST)
	 * @param message_content  The content of the message
	 * @param properties to add to the message
	 */
	public void sendMessageToAgentWithExtraProperties(String agent_name,String msgtype, Object message_content, ArrayList<Tuple> properties) {
		scenario.sendMessageToAgentsWithExtraProperties(agent_name, msgtype, message_content, properties);		
	}		
}
