package es.upm.dit.gsi.beast.platform.jade.jadePlatformTest;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import jade.lang.acl.ACLMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import es.upm.dit.gsi.beast.mock.MockManager;
import es.upm.dit.gsi.beast.mock.common.AgentBehaviour;
import es.upm.dit.gsi.beast.mock.common.Definitions;
import es.upm.dit.gsi.beast.mock.common.MockConfiguration;
import es.upm.dit.gsi.beast.story.BeastTestCase;
import es.upm.dit.gsi.beast.story.logging.LogActivator;
/**
 * Main class to translate plain text into code, following the Given-When-Then
 * language. In the GIVEN part it launchs the platform In the WHEN part it
 * configures the state of its agents. In the THEN part it checks the correct
 * behaviour. The main purpose of it consists of knowing agents' state/properties
 * without changing its code.
 * 
 * @author es.upm.dit.gsi.beast
 */
public class TestingMockBridgeCaseTwo extends BeastTestCase {

    public Logger logger = Logger.getLogger(TestingMockBridgeCaseTwo.class.getName());

    /**
     * Constructor to configure logging
     */
    public TestingMockBridgeCaseTwo() {
         Properties preferences = new Properties();
         try {
              FileInputStream configFile = new FileInputStream("src/test/java/es/upm/dit/gsi/beast/platform/jade/jadeBeastLog.properties");
              preferences.load(configFile);
              LogManager.getLogManager().readConfiguration(configFile);
              LogActivator.logToFile(logger, TestingMockBridgeCaseTwo.class.getName(), Level.ALL);
         } catch (IOException ex) {
              logger.severe("WARNING: Could not open configuration file");
         }
    }

    /**
     * This is the mehtod that must create the Scenario. It is related with the
     * GIVEN part.
     * 
     * In setup method the following method must be used
     * startAgent(agent_name,agent_path)
     */
    public void setup() {
        // RepositoryAgent configuration
        AgentBehaviour myMockedBehaviour = mock(AgentBehaviour.class);
        MockConfiguration mock_configuration = new MockConfiguration();
        mock_configuration
                .setDFServiceName(Definitions.REPOSITORY_SERVICE_NAME);
        mock_configuration.setBehaviour(myMockedBehaviour);
        MockManager
                .startMockJadeAgent(Definitions.REPOSITORY_AGENT_NAME,
                        Definitions.JADE_REPOSITORY_MOCK_PATH,
                        mock_configuration, this);

        // BridgeBehaviour configuration
        AgentBehaviour myMockedBehaviour2 = mock(AgentBehaviour.class);
        // In jade, the FIPA Inform is a constant in ACLMessage, but is stored
        // as an int
        when(
                myMockedBehaviour2.processMessage(
                        eq(ACLMessage.getPerformative(ACLMessage.INFORM)),
                        eq("store"))).thenReturn(
                Definitions.REPOSITORY_AGENT_NAME,
                ACLMessage.getPerformative(ACLMessage.INFORM),
                "Message to the repository agent");
        MockConfiguration mock_configuration2 = new MockConfiguration();
        mock_configuration2.setDFServiceName(Definitions.BRIDGE_SERVICE_NAME);
        mock_configuration2.setBehaviour(myMockedBehaviour2);
        MockManager.startMockJadeAgent(Definitions.BRIDGE_AGENT_NAME,
                Definitions.JADE_BRIDGE_MOCK_PATH, mock_configuration2, this);
        System.out.println("Scenario complete");
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
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("store");
        System.out.println("About to send the message");
        sendMessageToAgent(Definitions.BRIDGE_AGENT_NAME,
                ACLMessage.getPerformative(ACLMessage.INFORM), msg);
        System.out.println("Setup complete");
    }

    /**
     * This is the method that must create the Evaluation. It is related with
     * the THEN part.
     * 
     * In verify method the following method must be used
     * checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)
     */
    public void verify() {
//         while(((TesterAgent)((JadeAgentIntrospector)introspector).getAgent(Definitions.BRIDGE_AGENT_NAME)).isReadyToTest() == false){
//         // wait
//         }

        // The "proper way", above, is not working.
        // Probably because BridgeMockAgent is not a TesterAgent.
        try {
            Thread.sleep(1000); // Wait a little
        } catch (Exception e) {
            Assert.fail();
        }

        System.out.println("Checking beliefs...");
        checkAgentsBeliefEquealsTo(Definitions.BRIDGE_AGENT_NAME,
                Definitions.RECEIVED_MESSAGE_COUNT, 2);
    }
    
    /**
     * The GIVEN part
     */
    @Given("$scenarioName")
    public void createScenario(String scenarioName) {

         if (scenarioName.equals("one repository mock and one bridge mock in Jade Platform")){
              startPlatform("jade", logger);
         } else {
              logger.severe("WARNING: "+scenarioName+" does not coincide with one repository mock and one bridge mock in Jade Platform" );
         }
    }

    /**
     * The WHEN part
     */
    @When("$setupName")
    public void configureScenario(String setupName) {

         if (setupName.equals("bridge mocks has to send a message to repository")){
              setScenario();
         } else {
              logger.severe("WARNING: "+setupName+" does not coincide with bridge mocks has to send a message to repository");
         }
    }

    /**
     * The THEN part, where the correct behaviour must be asserted 
     */
    @Then("$evaluationName")
    public void checkScenario(String evaluationName) {

        if (evaluationName.equals("bridge receives the answer from repository")){
            this.setExecutionTime(BeastTestCase.SLEEP_TIME);
            verify();
        } else {
            logger.severe("WARNING: "+evaluationName+" does not coincide with bridge receives the answer from repository");
        }
    }
    
    /**
     * Stop the agent platform.
     */
    @AfterScenario
    public void cleanUp() {
        super.getConnector().stopPlatform();
    }

}

