package es.upm.dit.gsi.beast.platform.jadex;

import jadex.bdi.runtime.IBDIInternalAccess;
import jadex.bdi.runtime.IGoal;
import jadex.bdi.runtime.IPlan;
import jadex.bdi.runtime.Plan;
import jadex.bridge.IComponentStep;
import jadex.bridge.IInternalAccess;
import jadex.commons.future.IFuture;
import jadex.commons.future.ThreadSuspendable;
import es.upm.dit.gsi.beast.platform.AgentIntrospector;

/**
 * To get information about our jadex agents.
 * 
 * @author Jorge Solitario
 */
public class JadexAgentIntrospector extends AgentIntrospector{

	
	private static Object belief_value;
	private static IPlan[] plans;
	private static IGoal[] goals;
	
	
	/**
	 * This method takes the value of an agent's belief through its external access
	 * 
	 * @param agent_name The name of the agent
	 * @param belief_name The aame of the belief inside agent's adf
	 * @return belief_value The value of the requested belief
	 */
	public static Object getBeliefValue(String agent_name, final String belief_name, JadexConnector jadexconnector) {
		
		jadexconnector.getAgentsExternalAccess(agent_name).scheduleStep(new IComponentStep<Integer>()		{
			
			public IFuture<Integer> execute(IInternalAccess ia) {
				IBDIInternalAccess bia = (IBDIInternalAccess)ia;
			    belief_value = bia.getBeliefbase().getBelief(belief_name).getFact();			    
			    return null;
			}
		}).get(new ThreadSuspendable());
		return belief_value;
	}
	
	/**
	 * This method changes the value of an agent's belief through its external access
	 * 
	 * @param agent_name The name of the agent to change a belief
	 * @param belief_name The name of the belief to change
	 * @param new_value The new value of the belief to be changed
	 */
	public static void setBeliefValue(String agent_name, final String belief_name,
			final Object new_value, JadexConnector jadexConnector) {
		
		jadexConnector.getAgentsExternalAccess(agent_name).scheduleStep(new IComponentStep<Integer>()		{
			
			public IFuture<Integer> execute(IInternalAccess ia) {
				IBDIInternalAccess bia = (IBDIInternalAccess)ia;
			    bia.getBeliefbase().getBelief(belief_name).setFact(new_value);			    
			    return null;
			}
		}).get(new ThreadSuspendable());
	}

	/**
	 * This method prints plan information of an agent through its external access.
	 * It can be used to check the correct behaviour of the agent.
	 * 
	 * @param agent_name The name of the agent
	 */
	public static IPlan[] getAgentPlans (final String agent_name, JadexConnector jadexConnector) {

		jadexConnector.getAgentsExternalAccess(agent_name).scheduleStep(new IComponentStep<Plan>()		{
			
			public IFuture<Plan> execute(IInternalAccess ia) {
				
				IBDIInternalAccess bia = (IBDIInternalAccess)ia;	
			    plans = bia.getPlanbase().getPlans();
			    for (IPlan plan : plans) {
			    	/*
			    	 * The following code its an example of what can be retrieved using getAgentPlans
			    	 */
				    System.out.println("\nActived Plans : "+plan.getBody().toString());
			    	System.out.println("Plan Life Cycle State: "+plan.getLifecycleState());
//			    	System.out.println("Plan Reason: "+plan.getReason());
				    System.out.println("Plan Waitqueue.isEmpty: "+plan.getWaitqueue().isEmpty());
//			    	System.out.println("Plan Body Class: " + plan.PLANLIFECYCLESTATE_BODY);
			    }
			    return null;
			}
		}).get(new ThreadSuspendable());
		
		return plans;
	}	
	
	/**
	 * This method prints goal information of an agent through its external access.
	 * It can be used to check the correct behaviour of the agent.
	 * 
	 * @param agent_name
	 * @return
	 */
	public static IGoal[] getAgentGoals (final String agent_name, JadexConnector jadexConnector) {

		jadexConnector.getAgentsExternalAccess(agent_name).scheduleStep(new IComponentStep<Plan>()		{
			
			public IFuture<Plan> execute(IInternalAccess ia) {
				IBDIInternalAccess bia = (IBDIInternalAccess)ia;
						
			    goals = bia.getGoalbase().getGoals();
			    for (IGoal goal : goals) {
			    	/*
			    	 * The following code its an example of what can be retrieved using getAgentGoals
			    	 */
			    	System.out.println("\nGoal Name: "+goal.getType());
			    	System.out.println("Goal Life-Cycle State: "+goal.getLifecycleState());
			    	System.out.println("Goal.isActive: "+goal.isActive());
			    	System.out.println("Goal.isSucceeded: "+goal.isSucceeded());
			    	System.out.println("Goal.isretry: "+goal.isRetry());
			    	System.out.println("Goal.isFinished: "+goal.isFinished());
			    }		    
			    return null;
			}
		}).get(new ThreadSuspendable());
		
		return goals;
	}	
	
}
