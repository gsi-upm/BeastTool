package es.upm.dit.gsi.beast.platform.jadex.GetBelievesFromAgent;

import org.jbehave.core.annotations.Given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import es.upm.dit.gsi.beast.story.BeastTestCase;
import es.upm.dit.gsi.beast.story.logging.LogActivator;
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
public class GetBelievesFromAgent extends BeastTestCase {

    public Logger logger = Logger.getLogger(GetBelievesFromAgent.class.getName());

    /**
     * Constructor to configure logging
     */
    public GetBelievesFromAgent() {
         Properties preferences = new Properties();
         try {
              FileInputStream configFile = new FileInputStream("src/test/java/es/upm/dit/gsi/beast/platform/jadex/jadexBeastLog.properties");
              preferences.load(configFile);
              LogManager.getLogManager().readConfiguration(configFile);
              LogActivator.logToFile(logger, GetBelievesFromAgent.class.getName(), Level.ALL);
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
        startAgent("Tester", "es/upm/dit/gsi/beast/test/agent/jadex/TesterAgent.agent.xml");
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
        logger.info(">> setStates method is empty because there is nothing to do in this method for this test.");       
    }
    /**
     * This is the method that must create the Evaluation.
     * It is related with the THEN part.
     *  
     * In verify method the following method must be used
     * checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)
     */
    public void verify(){
//        try{
//            Thread.sleep(1000); // Wait a little
//        } catch (Exception e) {
//            Assert.fail();
//        }
        System.out.println(getBeliefValue("Tester", "message_count"));
        checkAgentsBeliefEquealsTo("Tester", "message_count", 0);
    }
    /**
     * The GIVEN part
     */
    @Given("$scenarioName")
    public void createScenario(String scenarioName) {

         if (scenarioName.equals("that one agent is started in Jadex Platform")){
              startPlatform("jadex", logger);
         } else {
              logger.severe("WARNING: "+scenarioName+" does not coincide with that one agent is started in Jadex Platform" );
         }
    }

    /**
     * The WHEN part
     */
    @When("$setupName")
    public void configureScenario(String setupName) {

         if (setupName.equals("tester wants to get a belief from a Jadex agent")){
             this.setExecutionTime(BeastTestCase.SLEEP_TIME);
              setScenario();
         } else {
              logger.severe("WARNING: "+setupName+" does not coincide with tester wants to get a belief from a Jadex agent");
         }
    }

    /**
     * The THEN part, where the correct behaviour must be asserted 
     */
    @Then("$evaluationName")
    public void checkScenario(String evaluationName) {

        if (evaluationName.equals("the Jadex belief is retrieved")){
            this.setExecutionTime(BeastTestCase.SLEEP_TIME);
            verify();
        } else {
            logger.severe("WARNING: "+evaluationName+" does not coincide with the Jadex belief is retrieved");
        }
    }

}

