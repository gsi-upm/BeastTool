package es.upm.dit.gsi.beast.story.testCase;

import java.util.logging.Logger;

import org.junit.Assert;

import es.upm.dit.gsi.beast.story.testCase.Setup;

/**
 * This class checks the final state of the agents we are testing. It is related
 * with the THEN part of BDD.
 * 
 * @author Jorge Solitario
 */
public abstract class Evaluation {

    protected Logger logger;
    private Setup setup;

    public abstract void checkStates();

    /**
     * Once given the setup, checkStates() will be run
     * 
     * @param setup
     */
    public void setSetup(Setup setup, Logger logger) {
        this.logger = logger;
        this.setup = setup;
        checkStates();
    }

    /**
     * Checks the value of some agent's belief with the expected value
     * 
     * @param agent_name
     * @param belief_name
     * @param belief_value
     */
    protected void checkAgentsBeliefEquealsTo(String agent_name,
            String belief_name, Object belief_value) {
        logger.finer("Getting belief...");
        logger.finest("Agent name: " + agent_name);
        logger.finest("Belief name: " + belief_name);
        Object realbeliefValue = setup.getBeliefValue(agent_name, belief_name);
        logger.finer("Asserting...");
        logger.finest("Agent name: " + agent_name);
        logger.finest("Belief name: " + belief_name);
        logger.finest("Real Belief value: " + realbeliefValue);
        logger.finest("Expected Belief value: " + belief_value);
        Assert.assertEquals(realbeliefValue,
                belief_value);
        logger.finer("Assert passed");
    }

    /**
     * This method takes the value of an agent's belief through its external
     * access
     * 
     * @param agent_name
     * @param belief_name
     * @return the value of the belief
     */
    protected Object getBeliefValue(String agent_name, String belief_name) {
        return setup.getBeliefValue(agent_name, belief_name);
    }

    // /**
    // * Checks the value of some agent's goal with the espected value.
    // * Many states can be checked:
    // * Goal.isActive
    // * Goal.isSucceeded:
    // * Goal.isRetry
    // * Goal.isFinished
    // *
    // * @param agent_name
    // * @param goal_name
    // * @param goal_state =[isActive,isSucceeded,isRetry,isFinished]
    // * @param goal_value
    // */
    // protected void checkAgentsGoalStateEquealsTo(String agent_name, String
    // goal_name, String goal_state, boolean goal_value) {
    // goal = setup.getGoalValue(agent_name, goal_name);
    // Assert.assertEquals(goal.goal_state, goal_value);
    // }

}
