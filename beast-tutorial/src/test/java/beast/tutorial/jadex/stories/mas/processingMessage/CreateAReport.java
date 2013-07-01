package beast.tutorial.jadex.stories.mas.processingMessage;

import jadex.base.fipa.SFipa;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import es.upm.dit.gsi.beast.story.BeastTestCase;
import es.upm.dit.gsi.beast.story.logging.LogActivator;

/**
 * Main class to translate plain text into code, following the Given-When-Then
 * language. In the GIVEN part it launchs the platform In the WHEN part it
 * configures the state of its agents. In the THEN part it checks the correct
 * behaviour. The main purpose of it consists of knowing agents' state/properties
 * without changing its code.
 * 
 * 
 * This "AgentStory" is described as follows:
 * Story: Processing message
 * As a ReporterAgent,
 * I want to process any FIPA-INFORM message from a RecorderAgent which contains a recorded message,
 * So that I am able to generate an issue report based on that message content.
 * 
 * This specific scenario is described as follows:
 * Scenario: CreateAReport
 * Given a ReporterAgent has access to the database, and a RecorderAgent has received a call that can understand,
 * When that ReporterAgent receives a FIPA-INFORM message from that RecorderAgent,
 * Then the message is processed and a new issue report is created.
 * 
 * @author es.upm.dit.gsi.beast
 */
public class CreateAReport extends BeastTestCase {

    public Logger logger = Logger.getLogger(CreateAReport.class.getName());

    /**
     * Constructor to configure logging
     */
    public CreateAReport() {
         Properties preferences = new Properties();
         try {
              FileInputStream configFile = new FileInputStream("src/test/resources/beast-conf/logger.properties");
              preferences.load(configFile);
              LogManager.getLogManager().readConfiguration(configFile);
              LogActivator.logToFile(logger, CreateAReport.class.getName(), Level.ALL);
         } catch (IOException ex) {
              logger.severe("WARNING: Could not open configuration file");
         }
    }

    /**
     * This is the method that must create the Scenario.
     * It is related with the GIVEN part.
     * "GIVEN a ReporterAgent has access to the database, and a RecorderAgent has received a call that can understand,".
     * 
     * In setup method the following method must be used
     * startAgent(agent_name,agent_path)
     */
    public void setup() {


		startAgent("ReporterAgentUnderTesting", "beast/tutorial/jadex/agent/Reporter.agent.xml");

    }
    /**
     * This is the method that must create the Setup.
     * It is related with the WHEN part.
     * "WHEN that ReporterAgent receives a FIPA-INFORM message from that RecorderAgent,"
     *  
     * In launch method the following methods must be used
     *   setBeliefValue (agent_name, belief_name, new_value )
     *   sendMessageToAgent(agent_name, msgtype, message_content)
     *   getAgentPlans(agent_name)
     *   getAgentGoals(agent_name )
     */
    public void launch() {
         
    	sendMessageToAgent("ReporterAgentUnderTesting", SFipa.INFORM, "NewRecordedCall");
         
    }
    /**
     * This is the method that must create the Evaluation.
     * It is related with the THEN part.
     * "THEN the message is processed and a new issue report is created."
     *  
     * In verify method the following method must be used
     * checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)
     */
    public void verify() {
    	
    	checkAgentsBeliefEquealsTo("ReporterAgentUnderTesting", "createdIssueReport", true);

    }
    /**
     * The GIVEN part
     */
    @Given("$scenarioName")
    public void createScenario(String scenarioName) {

         if (scenarioName.equals("a ReporterAgent has access to the database, and a RecorderAgent has received a call that can understand,")){
              startPlatform("jadex", logger);
         } else {
              logger.severe("WARNING: "+scenarioName+" does not coincide with a ReporterAgent has access to the database, and a RecorderAgent has received a call that can understand," );
         }
    }

    /**
     * The WHEN part
     */
    @When("$setupName")
    public void configureScenario(String setupName) {

         if (setupName.equals("that ReporterAgent receives a FIPA-INFORM message from that RecorderAgent,")){
              setScenario();
         } else {
              logger.severe("WARNING: "+setupName+" does not coincide with that ReporterAgent receives a FIPA-INFORM message from that RecorderAgent,");
         }
    }

    /**
     * The THEN part, where the correct behaviour must be asserted 
     */
    @Then("$evaluationName")
    public void checkScenario(String evaluationName) {

        if (evaluationName.equals("the message is processed and a new issue report is created.")){
            this.setExecutionTime(BeastTestCase.SLEEP_TIME);
            verify();
        } else {
            logger.severe("WARNING: "+evaluationName+" does not coincide with the message is processed and a new issue report is created.");
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

