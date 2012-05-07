package es.upm.dit.gsi.beast.platform.jade.TestingMockListener;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.mockito.Mockito.mock;
import es.upm.dit.gsi.beast.mock.MockManager;
import es.upm.dit.gsi.beast.mock.common.AgentBehaviour;
import es.upm.dit.gsi.beast.mock.common.Definitions;
import es.upm.dit.gsi.beast.mock.common.MockConfiguration;

import es.upm.dit.gsi.beast.mock.common.Definitions;
import es.upm.dit.gsi.beast.story.BeastTestCase;
import es.upm.dit.gsi.beast.story.logging.LogActivator;
import jade.lang.acl.ACLMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.Properties;

import junit.framework.Assert;

/**
 * Main class to translate plain text into code, following the Given-When-Then
 * language. In the GIVEN part it launchs the platform In the WHEN part it
 * configures the state of its agents. In the THEN part it checks the correct
 * behaviour. The main purpose of it consists of knowing agents'
 * state/properties without changing its code.
 * 
 * @author es.upm.dit.gsi.beast
 */
public class TestingMockListener extends BeastTestCase {

    public Logger logger = Logger
            .getLogger(TestingMockListener.class.getName());

    /**
     * Constructor to configure logging
     */
    public TestingMockListener() {
        Properties preferences = new Properties();
        try {
            FileInputStream configFile = new FileInputStream(
                    "src/test/java/es/upm/dit/gsi/beast/platform/jade/jadeBeastLog.properties");
            preferences.load(configFile);
            LogManager.getLogManager().readConfiguration(configFile);
            LogActivator.logToFile(logger, TestingMockListener.class.getName(),
                    Level.ALL);
        } catch (IOException ex) {
            logger.severe("WARNING: Could not open configuration file");
        }
    }

    /**
     * This is the method that must create the Scenario. It is related with the
     * GIVEN part.
     * 
     * In setup method the following method must be used
     * startAgent(agent_name,agent_path)
     */
    public void setup() {
        // ListenerAgent configuration
        AgentBehaviour myMockedBehaviour = mock(AgentBehaviour.class);
        MockConfiguration mock_configuration = new MockConfiguration();
        mock_configuration.setDFServiceName(Definitions.LISTENER_SERVICE_NAME);
        mock_configuration.setBehaviour(myMockedBehaviour);
        MockManager.startMockJadeAgent(Definitions.LISTENER_AGENT_NAME,
                Definitions.JADE_LISTENER_MOCK_PATH, mock_configuration, this);
    }

    /**
     * This is the method that must create the Setup. It is related with the
     * WHEN part.
     * 
     * In launch method the following methods must be used setBeliefValue
     * (agent_name, belief_name, new_value ) sendMessageToAgent(agent_name,
     * msgtype, message_content)n * getAgentPlans(agent_name)
     * getAgentGoals(agent_name )
     */
    public void launch() {
        for (int i = 0; i < 10; i++) {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setContent("MESSAGE#00" + i);
            sendMessageToAgent(Definitions.LISTENER_AGENT_NAME,
                    ACLMessage.getPerformative(ACLMessage.INFORM), msg);
        }
    }

    /**
     * This is the method that must create the Evaluation. It is related with
     * the THEN part.
     * 
     * In verify method the following method must be used
     * checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)
     */
    public void verify() {
        // while(((ListenerMockAgent)((JadeAgentIntrospector)introspector).getAgent(Definitions.LISTENER_AGENT_NAME)).isReadyToTest()
        // == false){
        // // wait
        // }

        // The "proper way", above, is not working.
        // Probably because ListenerMockAgent is not a Tester agent.
        try {
            Thread.sleep(1000); // Wait a little
        } catch (Exception e) {
            Assert.fail();
        }
        checkAgentsBeliefEquealsTo(Definitions.LISTENER_AGENT_NAME,
                Definitions.RECEIVED_MESSAGE_COUNT, 10);
    }

    /**
     * The GIVEN part
     */
    @Given("$scenarioName")
    public void createScenario(String scenarioName) {

        if (scenarioName.equals("one listener mock in Jade Platform")) {
            startPlatform("jade", logger);
        } else {
            logger.severe("WARNING: "
                    + scenarioName
                    + " does not coincide with one listener mock in Jade Platform");
        }
    }

    /**
     * The WHEN part
     */
    @When("$setupName")
    public void configureScenario(String setupName) {

        if (setupName.equals("10 messages are sent to listener")) {
            setScenario();
        } else {
            logger.severe("WARNING: "
                    + setupName
                    + " does not coincide with 10 messages are sent to listener");
        }
    }

    /**
     * The THEN part, where the correct behaviour must be asserted
     */
    @Then("$evaluationName")
    public void checkScenario(String evaluationName) {

        if (evaluationName.equals("listener receives all messages")) {
            verify();
        } else {
            logger.severe("WARNING: " + evaluationName
                    + " does not coincide with listener receives all messages");
        }
    }

}
