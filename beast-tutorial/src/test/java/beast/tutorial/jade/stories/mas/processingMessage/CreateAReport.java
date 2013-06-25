package beast.tutorial.jade.stories.mas.processingMessage;

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
     * "GIVEN a ReporterAgent has access to the database,".
     * 
     * In setup method the following method must be used
     * startAgent(agent_name,agent_path)
     */
    public void setup() {
         // TODO: implement this method to represent the @Given part of the test in Java code.
         
         logger.warning("Implement setup() method in beast.tutorial.jade.stories.mas.processingMessageCreateAReport.java -> Auto-generated stub by Beast -> es.upm.dit.gsi.beast-tool");

         //EXAMPLE for Jadex: startAgent("Steve", "org.example.Steve.agent.xml"); // This xml file is the jadex agent description file (ADF)
         //EXAMPLE for Jade: startAgent("Steve", "org.example.Steve"); // This string is the agent class Steve.java that extends Jade Agent class

    }
    /**
     * This is the method that must create the Setup.
     * It is related with the WHEN part.
     * "WHEN that agent receives a FIPA-INFORM message from a recorder agent,"
     *  
     * In launch method the following methods must be used
     *   setBeliefValue (agent_name, belief_name, new_value )
     *   sendMessageToAgent(agent_name, msgtype, message_content)n     *   getAgentPlans(agent_name)
     *   getAgentGoals(agent_name )
     */
    public void launch() {
         // TODO implement this method to represent the @When part of the test in Java code.
         
         logger.warning("Implement launch() method in beast.tutorial.jade.stories.mas.processingMessageCreateAReport.java -> Auto-generated stub by Beast -> es.upm.dit.gsi.beast-tool");
         
             //EXAMPLE: setBeliefValue("Steve", "age", 21);
         
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
         // TODO implement this method to represent the @Then part of the test in Java code.
         
         logger.warning("Implement verify() method in beast.tutorial.jade.stories.mas.processingMessageCreateAReport.java -> Auto-generated stub by Beast -> es.upm.dit.gsi.beast-tool");
         System.out.println("IMPORTANT!! -> Not implemented Test. Auto-generated stub by Beast -> es.upm.dit.gsi.beast-tool in class"+ this.getClass().getName());
         Assert.fail("Not implemented Test. Auto-generated stub by Beast -> es.upm.dit.gsi.beast-tool");

        //EXAMPLE: checkAgentsBeliefEquealsTo("Steve", "age", 21);

    }
    /**
     * The GIVEN part
     */
    @Given("$scenarioName")
    public void createScenario(String scenarioName) {

         if (scenarioName.equals("a ReporterAgent has access to the database,")){
              startPlatform("jade", logger);
         } else {
              logger.severe("WARNING: "+scenarioName+" does not coincide with a ReporterAgent has access to the database," );
         }
    }

    /**
     * The WHEN part
     */
    @When("$setupName")
    public void configureScenario(String setupName) {

         if (setupName.equals("that agent receives a FIPA-INFORM message from a recorder agent,")){
              setScenario();
         } else {
              logger.severe("WARNING: "+setupName+" does not coincide with that agent receives a FIPA-INFORM message from a recorder agent,");
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

}

