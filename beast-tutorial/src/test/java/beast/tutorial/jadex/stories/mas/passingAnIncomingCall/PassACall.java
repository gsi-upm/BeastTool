package beast.tutorial.jadex.stories.mas.passingAnIncomingCall;

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
 * Story: Passing an incoming call
 * As a HelpDeskAgent,
 * I want to process a FIPA-REQUEST message from a RecorderAgent,
 * So that I am able to pass an incoming call to a human operator.
 * 
 * This specific scenario is described as follows:
 * Scenario: PassACall
 * Given a HelpDeskAgent has connection to the desktop of the operator computer, and a RecorderAgent has received a call that cannot understand,
 * When that HelpDeskAgent receives a FIPA-REQUEST message from that RecorderAgent,
 * Then the request is accepted and the incoming call is transfered to the human operator.
 * 
 * @author es.upm.dit.gsi.beast
 */
public class PassACall extends BeastTestCase {

    public Logger logger = Logger.getLogger(PassACall.class.getName());

    /**
     * Constructor to configure logging
     */
    public PassACall() {
         Properties preferences = new Properties();
         try {
              FileInputStream configFile = new FileInputStream("src/test/resources/beast-conf/logger.properties");
              preferences.load(configFile);
              LogManager.getLogManager().readConfiguration(configFile);
              LogActivator.logToFile(logger, PassACall.class.getName(), Level.ALL);
         } catch (IOException ex) {
              logger.severe("WARNING: Could not open configuration file");
         }
    }

    /**
     * This is the method that must create the Scenario.
     * It is related with the GIVEN part.
     * "GIVEN a HelpDeskAgent has connection to the desktop of the operator computer, and a RecorderAgent has received a call that cannot understand,".
     * 
     * In setup method the following method must be used
     * startAgent(agent_name,agent_path)
     */
    public void setup() {

		startAgent("HelpDeskAgentUnderTesting", "beast/tutorial/jadex/agent/HelpDesk.agent.xml");

    }
    /**
     * This is the method that must create the Setup.
     * It is related with the WHEN part.
     * "WHEN that HelpDeskAgent receives a FIPA-REQUEST message from that RecorderAgent,"
     *  
     * In launch method the following methods must be used
     *   setBeliefValue (agent_name, belief_name, new_value )
     *   sendMessageToAgent(agent_name, msgtype, message_content)
     *   getAgentPlans(agent_name)
     *   getAgentGoals(agent_name )
     */
    public void launch() {
    	
    	sendMessageToAgent("HelpDeskAgentUnderTesting", SFipa.REQUEST, "UnknownLanguageCall");
    	
    }
    /**
     * This is the method that must create the Evaluation.
     * It is related with the THEN part.
     * "THEN the request is accepted and the incoming call is transfered to the human operator."
     *  
     * In verify method the following method must be used
     * checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)
     */
    public void verify() {
    	
    	checkAgentsBeliefEquealsTo("HelpDeskAgentUnderTesting", "operatorTalking", true);

    }
    /**
     * The GIVEN part
     */
    @Given("$scenarioName")
    public void createScenario(String scenarioName) {

         if (scenarioName.equals("a HelpDeskAgent has connection to the desktop of the operator computer, and a RecorderAgent has received a call that cannot understand,")){
              startPlatform("jadex", logger);
         } else {
              logger.severe("WARNING: "+scenarioName+" does not coincide with a HelpDeskAgent has connection to the desktop of the operator computer, and a RecorderAgent has received a call that cannot understand," );
         }
    }

    /**
     * The WHEN part
     */
    @When("$setupName")
    public void configureScenario(String setupName) {

         if (setupName.equals("that HelpDeskAgent receives a FIPA-REQUEST message from that RecorderAgent,")){
              setScenario();
         } else {
              logger.severe("WARNING: "+setupName+" does not coincide with that HelpDeskAgent receives a FIPA-REQUEST message from that RecorderAgent,");
         }
    }

    /**
     * The THEN part, where the correct behaviour must be asserted 
     */
    @Then("$evaluationName")
    public void checkScenario(String evaluationName) {

        if (evaluationName.equals("the request is accepted and the incoming call is transfered to the human operator.")){
            this.setExecutionTime(BeastTestCase.SLEEP_TIME);
            verify();
        } else {
            logger.severe("WARNING: "+evaluationName+" does not coincide with the request is accepted and the incoming call is transfered to the human operator.");
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

