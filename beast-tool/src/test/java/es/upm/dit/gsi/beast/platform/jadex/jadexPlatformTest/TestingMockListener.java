package es.upm.dit.gsi.beast.platform.jadex.jadexPlatformTest;

import static org.mockito.Mockito.mock;
import jadex.base.fipa.SFipa;

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
public class TestingMockListener extends BeastTestCase {

    public Logger logger = Logger.getLogger(TestingMockListener.class.getName());

    /**
     * Constructor to configure logging
     */
    public TestingMockListener() {
         Properties preferences = new Properties();
         try {
              FileInputStream configFile = new FileInputStream("src/test/java/es/upm/dit/gsi/beast/platform/jadex/jadexBeastLog.properties");
              preferences.load(configFile);
              LogManager.getLogManager().readConfiguration(configFile);
              LogActivator.logToFile(logger, TestingMockListener.class.getName(), Level.ALL);
         } catch (IOException ex) {
              logger.severe("WARNING: Could not open configuration file");
         }
    }

    /**
     * This is the mehtod that must create the Scenario.
     * It is related with the GIVEN part.
     * 
     * In setup method the following method must be used
     * startAgent(agent_name,agent_path)
     */
    public void setup() {
         //ListenerAgent configuration
        AgentBehaviour myMockedBehaviour =   mock(AgentBehaviour.class);
        MockConfiguration mock_configuration = new MockConfiguration();
        mock_configuration.setDFServiceName("listen_service");
        mock_configuration.setBehaviour(myMockedBehaviour);
        MockManager.startMockJadexAgent("ListenerAgent",Definitions.JADEX_LISTENER_MOCK_PATH,mock_configuration,this);
    }
    /**
     * This is the method that must create the Setup.
     * It is related with the WHEN part.
     *  
     * In launch method the following methods must be used
     *   setBeliefValue (agent_name, belief_name, new_value )
     *   sendMessageToAgent(agent_name, msgtype, message_content)n     *   getAgentPlans(agent_name)
     *   getAgentGoals(agent_name )
     */
    public void launch(){
        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "1");
        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "2");
        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "3");
        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "4");
        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "5");
        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "6");
        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "7");
        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "8");
        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "9");
        sendMessageToAgent("ListenerAgent", SFipa.INFORM, "10");
    }
    /**
     * This is the method that must create the Evaluation.
     * It is related with the THEN part.
     *  
     * In verify method the following method must be used
     * checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)
     */
    public void verify(){
        try{
            Thread.sleep(1000); // Wait a little
        } catch (Exception e) {
            Assert.fail();
        }
        checkAgentsBeliefEquealsTo("ListenerAgent", Definitions.RECEIVED_MESSAGE_COUNT, 10);
    }
    
    /**
     * The GIVEN part
     */
    @Given("$scenarioName")
    public void createScenario(String scenarioName) {

         if (scenarioName.equals("one listener mock in Jadex Platform")){
              startPlatform("jadex", logger);
         } else {
              logger.severe("WARNING: "+scenarioName+" does not coincide with one listener mock in Jadex Platform" );
         }
    }

    /**
     * The WHEN part
     */
    @When("$setupName")
    public void configureScenario(String setupName) {

         if (setupName.equals("10 messages are sent to listener")){
              setScenario();
         } else {
              logger.severe("WARNING: "+setupName+" does not coincide with 10 messages are sent to listener");
         }
    }

    /**
     * The THEN part, where the correct behaviour must be asserted 
     */
    @Then("$evaluationName")
    public void checkScenario(String evaluationName) {

        if (evaluationName.equals("listener receives all messages")){
            this.setExecutionTime(BeastTestCase.SLEEP_TIME);
            verify();
        } else {
            logger.severe("WARNING: "+evaluationName+" does not coincide with listener receives all messages");
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

