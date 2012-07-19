package es.upm.dit.gsi.beast.platform.jade.jadePlatformTest;

import org.jbehave.core.annotations.Given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import es.upm.dit.gsi.beast.mock.MockManager;
import es.upm.dit.gsi.beast.mock.common.AgentBehaviour;
import es.upm.dit.gsi.beast.mock.common.Definitions;
import es.upm.dit.gsi.beast.mock.common.MockConfiguration;
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
 * behaviour. The main purpose of it consists of knowing agents' state/properties
 * without changing its code.
 * 
 * @author es.upm.dit.gsi.beast
 */
public class TestingMockRepository extends BeastTestCase {

    public Logger logger = Logger.getLogger(TestingMockRepository.class.getName());

    /**
     * Constructor to configure logging
     */
    public TestingMockRepository() {
         Properties preferences = new Properties();
         try {
              FileInputStream configFile = new FileInputStream("src/test/java/es/upm/dit/gsi/beast/platform/jade/jadeBeastLog.properties");
              preferences.load(configFile);
              LogManager.getLogManager().readConfiguration(configFile);
              LogActivator.logToFile(logger, TestingMockRepository.class.getName(), Level.ALL);
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
        // Start and configure Bridge Agent
        MockConfiguration bridgeMockConfiguration = new MockConfiguration();

        // Add the personlized service name.
        bridgeMockConfiguration
                .setDFServiceName(Definitions.BRIDGE_SERVICE_NAME);

        // Add a mocked AgentBehaviour.
        AgentBehaviour bridgeMockedBehaviour = mock(AgentBehaviour.class);
        // when(bridgeMockedBehaviour.processMessage(eq("inform"), eq("send")))
        // .thenReturn("repository_service", "SFipa.REQUEST", "hi");
        when(
                bridgeMockedBehaviour.processMessage(
                        eq(ACLMessage.getPerformative(ACLMessage.REQUEST)),
                        eq("BeastMessenger"), eq("send"))).thenReturn(
                Definitions.REPOSITORY_AGENT_NAME,
                ACLMessage.getPerformative(ACLMessage.REQUEST), "hi");
        bridgeMockConfiguration.setBehaviour(bridgeMockedBehaviour);
        // Start the agent.
        MockManager.startMockJadeAgent(Definitions.BRIDGE_AGENT_NAME,
                Definitions.JADE_DANNY_BRIDGE_MOCK_PATH,
                bridgeMockConfiguration, this);

        // Start and configure Bridge Agent
        MockConfiguration repositoryMockConfiguration = new MockConfiguration();

        // Add the personlized service name.
        repositoryMockConfiguration
                .setDFServiceName(Definitions.REPOSITORY_SERVICE_NAME);

        // Add a mocked AgentBehaviour.
        AgentBehaviour repositoryMockBehaviour = mock(AgentBehaviour.class);
        when(
                repositoryMockBehaviour.processMessage(
                        eq(ACLMessage.getPerformative(ACLMessage.REQUEST)),
                        eq(Definitions.BRIDGE_AGENT_NAME), eq("hi")))
                .thenReturn(Definitions.STORE_ATTEMPT_OK);
        repositoryMockConfiguration.setBehaviour(repositoryMockBehaviour);
        // Start the agent.
        MockManager.startMockJadeAgent(Definitions.REPOSITORY_AGENT_NAME,
                Definitions.JADE_REPOSITORY_MOCK_PATH,
                repositoryMockConfiguration, this);
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
        sendMessageToAgent(Definitions.BRIDGE_AGENT_NAME,
                ACLMessage.getPerformative(ACLMessage.REQUEST), "send");
    }

    /**
     * This is the method that must create the Evaluation. It is related with
     * the THEN part.
     * 
     * In verify method the following method must be used
     * checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)
     */
    public void verify() {
        // while(((TesterAgent)((JadeAgentIntrospector)introspector).getAgent(Definitions.REPOSITORY_AGENT_NAME)).isReadyToTest()
        // == false){
        // // wait
        // }

        // The "proper way", above, is not working.
        // Probably because RepositoryMockAgent is not a TesterAgent.
        try {
            Thread.sleep(1000); // Wait a little
        } catch (Exception e) {
            Assert.fail();
        }
        checkAgentsBeliefEquealsTo(Definitions.REPOSITORY_AGENT_NAME,
                Definitions.RECEIVED_MESSAGE_COUNT, 1);
        checkAgentsBeliefEquealsTo(Definitions.REPOSITORY_AGENT_NAME,
                Definitions.STORED_DATA_COUNT, 1);
    }
    
    /**
     * The GIVEN part
     */
    @Given("$scenarioName")
    public void createScenario(String scenarioName) {

         if (scenarioName.equals("one bridge mock and one repository mock in Jade Platform")){
              startPlatform("jade", logger);
         } else {
              logger.severe("WARNING: "+scenarioName+" does not coincide with one bridge mock and one repository mock in Jade Platform" );
         }
    }

    /**
     * The WHEN part
     */
    @When("$setupName")
    public void configureScenario(String setupName) {

         if (setupName.equals("bridge sends a message to repository")){
              setScenario();
         } else {
              logger.severe("WARNING: "+setupName+" does not coincide with bridge sends a message to repository");
         }
    }

    /**
     * The THEN part, where the correct behaviour must be asserted 
     */
    @Then("$evaluationName")
    public void checkScenario(String evaluationName) {

        if (evaluationName.equals("repository answers with the correct message")){
            this.setExecutionTime(BeastTestCase.SLEEP_TIME);
            verify();
        } else {
            logger.severe("WARNING: "+evaluationName+" does not coincide with repository answers with the correct message");
        }
    }

}

