package es.upm.dit.gsi.beast.story;

import jadex.bdi.runtime.IGoal;
import jadex.bdi.runtime.IPlan;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

import es.upm.dit.gsi.beast.platform.AgentIntrospector;
import es.upm.dit.gsi.beast.platform.Connector;
import es.upm.dit.gsi.beast.platform.Messenger;
import es.upm.dit.gsi.beast.platform.PlatformSelector;

/**
 * Main class to translate plain text into code, following the Given-When-Then
 * language In the GIVEN part it launches the platform In the WHEN part it
 * configures the state of its agents In the THEN part it checks the correct
 * behaviour The main purpose of it consists of knowing agents' state/properties
 * without changing its code.
 * 
 * @author Jorge Solitario
 */
public abstract class BeastTestCase extends JUnitStory {

    public static final int SLEEP_TIME = 2000;

    public Logger logger;

    // From Scenario
    Connector connector;
    String platform;
    Messenger messenger;

    // From evaluation
    // Yeah. Nothing

    // From Setup
    protected AgentIntrospector introspector;

    /**
     * This method launches the setup, related with the WHEN part
     * 
     * @param setupName
     * @deprecated
     */
    // public void setup() {
    // setScenario();
    // }

    /**
     * This method checks the THEN part
     * 
     * @param evaluationName
     * @deprecated
     */
    // public void executeEvaluation() {
    // verify();
    // }

    // /**
    // * It assigns one direction to each Scenario, Setup and Evaluation given
    // by
    // * the client in the plain text. This information is saved in
    // * Classdatabase.xml, which is typically located in the root of our
    // project.
    // *
    // * @param stepName
    // * , the plain text written by the client.
    // * @return the path where the step is saved
    // */
    // @SuppressWarnings("unchecked")
    // private String getPath(String stepName) {
    //
    // //FIXME important!!! when there are two identical step names, there is
    // conflict and it do not work properly. Return one of them... but not the
    // correct one
    // XStream xstream = new XStream();
    // String answer = null;
    // try {
    // HashMap<String, String> hm = (HashMap<String, String>) xstream
    // .fromXML(new FileInputStream("ClassDatabase.xml"));
    // answer = (String) hm.get(stepName);
    // } catch (FileNotFoundException e) {
    // logger.severe("Error loading from ClassDatabase.xml");
    // }
    // return answer;
    // }

    @Override
    /**
     * Internal method for jBehave
     */
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), this)
                .createCandidateSteps();
    }

    public abstract void setup(); // Scenario

    public abstract void launch(); // Setup

    public abstract void verify(); // Evaluation

    // Methods from Scenario
    /**
     * Main constructor of the class, launches the platform
     */
    public void startPlatform(String platform, Logger logger) {
        this.logger = logger;
        this.platform = platform;

        logger.fine("Creating Scenario...");

        logger.finest(">> startPlatform");

        this.connector = PlatformSelector.getConnector(platform, logger);

        logger.info("Platform " + platform + " is not launched yet.");
        this.connector.launchPlatform();

        logger.info("Platform " + platform + " launched.");

        this.messenger = PlatformSelector.getMessenger(platform);

        setup();
    }

    /**
     * @return the platform connector
     */
    public Connector getConnector() {
        return this.connector;
    }

    /**
     * Creates a real agent in the platform
     * 
     * @param agent_name
     *            The name that the agent is gonna have in the platform
     * @param path
     *            The path of the description of the agent
     */
    public void startAgent(String agent_name, String path) {
        this.connector.createAgent(agent_name, path);
    }

    /**
     * Creates a real agent in the platform in a given container
     * 
     * @param agent_name
     *            The name of the agent
     * @param path
     *            The path of the Agent
     * @param containerName
     *            The name of the container, if it does not exist, beast-tool
     *            creates it.
     * @param arguments
     *            For the agent
     */
    public void startAgent(String agent_name, String path,
            String containerName, Object[] arguments) {
        this.connector.createAgent(agent_name, path, containerName, arguments);
    }

    /**
     * It sends one message of requested type to an agent
     * 
     * @param agent_name
     *            The name of the agent that receives the message
     * @param msgtype
     *            The type of the message (SFipa.INFORM - SFipa.REQUEST)
     * @param message_content
     *            The content of the message
     */
    public void sendMessageToAgent(String agent_name, String msgtype,
            Object message_content) {

        String[] names = new String[1];
        names[0] = agent_name;
        this.messenger.sendMessageToAgents(names, msgtype, message_content,
                this.connector);
    }

    /**
     * The same as above, but this method sends the same message to many agents.
     * 
     * @param agent_name
     *            The name of the agent that receives the message
     * @param msgtype
     *            The type of the message (SFipa.INFORM - SFipa.REQUEST)
     * @param message_content
     *            The content of the message
     */
    public void sendMessageToAgents(String[] agent_name, String msgtype,
            Object message_content) {

        this.messenger.sendMessageToAgents(agent_name, msgtype,
                message_content, this.connector);
    }

    /**
     * It sends one message of requested type to an agent, including some extra
     * parameters in the message event, such as ontology or language.
     * 
     * @param agent_name
     *            The name of the agent that receives the message
     * @param msgtype
     *            The type of the message (SFipa.INFORM - SFipa.REQUEST)
     * @param message_content
     *            The content of the message
     * @param properties
     *            to add to the message
     */
    public void sendMessageToAgentsWithExtraProperties(String agent_name,
            String msgtype, Object message_content, ArrayList<Object> properties) {

        String[] names = new String[1];
        names[0] = agent_name;
        this.messenger.sendMessageToAgentsWithExtraProperties(names, msgtype,
                message_content, properties, this.connector);
    }

    // From Evaluation

    /**
     * Once given the setup, checkStates() will be run
     * 
     * @param setup
     * @deprecated
     */
    // public void setSetup() {
    // verify();
    // }

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
        // System.out.print("Recovering value...");
        Object realbeliefValue = getBeliefValue(agent_name, belief_name);
        // System.out.println("done!");
        logger.finer("Asserting...");
        logger.finest("Agent name: " + agent_name);
        logger.finest("Belief name: " + belief_name);
        logger.finest("Real Belief value: " + String.valueOf(realbeliefValue));
        logger.finest("Expected Belief value: " + String.valueOf(belief_value));
        // System.out.println("Asserting...");
        Assert.assertEquals(realbeliefValue, belief_value);
        logger.finer("Assert passed");
    }

    // I simply can't delete this.
    // Not until I can submit it to The Daily WTF anyway...
    // /**
    // * This method takes the value of an agent's belief through its external
    // * access
    // *
    // * @param agent_name
    // * @param belief_name
    // * @return the value of the belief
    // */
    // protected Object getBeliefValue(String agent_name, String belief_name) {
    // return getBeliefValue(agent_name, belief_name);
    // }

    /**
     * This method takes the value of an agent's belief through its external
     * access
     * 
     * @param agent_name
     * @param belief_name
     * @return the value of the belief
     */
    protected Object getBeliefValue(String agent_name, String belief_name) {
        return introspector.getBeliefValue(agent_name, belief_name, connector);
    }

    protected Object getGoals(String agent_name) {
        return getAgentGoals(agent_name);
    }

    protected Object getPlans(String agent_name) {
        return getAgentPlans(agent_name);
    }

    // From Setup
    /**
     * Once given the scenario, child's setSetup() will be run
     * 
     * @param scenario
     */
    public void setScenario() {
        logger.fine("Getting agent introspector...");
        introspector = PlatformSelector.getAgentIntrospector(this.platform);
        logger.fine("Agent Introspector retrieved sucessfully");
        launch();
    }

    /**
     * This method changes the value of an agent's belief through its external
     * access
     * 
     * @param agent_name
     *            The name of the agent to change a belief
     * @param belief_name
     *            The name of the belief to change
     * @param new_value
     *            The new value of the belief to be changed
     */
    public void setBeliefValue(String agent_name, final String belief_name,
            final Object new_value) {

        introspector.setBeliefValue(agent_name, belief_name, new_value,
                this.connector);
        logger.fine("Belief configuration successful. Agent: " + agent_name
                + " Belief: " + belief_name + " Value: " + new_value);
    }

    // /**
    // * This method takes the value of an agent's belief through its external
    // * access
    // *
    // * @param agent_name
    // * The name of the agent
    // * @param belief_name
    // * The name of the belief inside agent's adf
    // * @return belief_value The value of the requested belief
    // */
    // public Object getBeliefValue(String agent_name, final String belief_name)
    // {
    //
    // return introspector.getBeliefValue(agent_name, belief_name,
    // scenario.connector);
    // }
    //
    // /**
    // * It sends one message of requested type to an agent
    // *
    // * @param agent_name
    // * The name of the agent that receives the message
    // * @param msgtype
    // * The type of the message (SFipa.INFORM - SFipa.REQUEST)
    // * @param message_content
    // * The content of the message
    // */
    // public void sendMessageToAgent(String agent_name, String msgtype,
    // Object message_content) {
    // scenario.sendMessageToAgent(agent_name, msgtype, message_content);
    // }
    //
    // /**
    // * The same as above but with many agents
    // *
    // * @param agent_name
    // * The name of the agent that receives the message
    // * @param msgtype
    // * The type of the message (SFipa.INFORM - SFipa.REQUEST)
    // * @param message_content
    // * The content of the message
    // */
    // public void sendMessageToAgents(String[] agent_name, String msgtype,
    // Object message_content) {
    // scenario.sendMessageToAgents(agent_name, msgtype, message_content);
    // }

    /**
     * This method prints plan information of an agent through its external
     * access. It can be used to check the correct behaviour of the agent.
     * 
     * @param agent_name
     *            The name of the agent
     * @return IPlan[] so the tester can get the plans information
     */
    public IPlan[] getAgentPlans(final String agent_name) {

        return (IPlan[]) introspector.getAgentPlans(agent_name, this.connector);
    }

    /**
     * This method prints goal information of an agent through its external
     * access. It can be used to check the correct behaviour of the agent.
     * 
     * @param agent_name
     *            The name of the agent
     * @return IGoal[] so the tester can get the goals information
     */
    public IGoal[] getAgentGoals(final String agent_name) {
        return (IGoal[]) introspector.getAgentGoals(agent_name, this.connector);
    }

    /**
     * It sends one message of requested type to an agent, including some extra
     * parameters in the message event, such as ontology or language.
     * 
     * @param agent_name
     *            The name of the agent
     * @param msgtype
     *            The type of the message (SFipa.INFORM - SFipa.REQUEST)
     * @param message_content
     *            The content of the message
     * @param properties
     *            to add to the message
     */
    public void sendMessageToAgentWithExtraProperties(String agent_name,
            String msgtype, Object message_content, ArrayList<Object> properties) {
        sendMessageToAgentsWithExtraProperties(agent_name, msgtype,
                message_content, properties);
    }

    /**
     * This method set the execution time of the test.
     * 
     * @param millis
     *            Time in milliseconds
     */
    public void setExecutionTime(long millis) {
        try {
            Thread.sleep(millis);
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        } catch (InterruptedException e) {
            logger.warning("Execution time aborted: " + e);
            logger.severe("It was not possible to wait the execution time to perform the test. Exception: "
                    + e);
        }
    }
}
