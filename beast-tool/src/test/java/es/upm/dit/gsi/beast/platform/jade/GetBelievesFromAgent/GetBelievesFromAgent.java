package es.upm.dit.gsi.beast.platform.jade.GetBelievesFromAgent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import es.upm.dit.gsi.beast.story.Story;
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
  public class GetBelievesFromAgent extends Story {

     public Logger logger = Logger.getLogger(GetBelievesFromAgent.class.getName());
  /**
   * Constructor to configure logging
   */
  public GetBelievesFromAgent() {
     Properties preferences = new Properties();
     try {
         FileInputStream configFile = new FileInputStream("src/test/java/es/upm/dit/gsi/beast/platform/jade/jadeBeastLog.properties");
         preferences.load(configFile);
         LogManager.getLogManager().readConfiguration(configFile);
         LogActivator.logToFile(logger, GetBelievesFromAgent.class.getName(), Level.ALL);
     } catch (IOException ex) {
         logger.severe("WARNING: Could not open configuration file");
     }
  }

 	/**
	 * The GIVEN part
	 */
	@Given("$scenarioName")
	public void createScenario(String scenarioName) {

      if (scenarioName.equals("that TesterAgent is started in Jade Platform in Main-Container with Configuration 1")){
		    super.createScenario("es.upm.dit.gsi.beast.platform.jade.GetBelievesFromAgent.phases.Scenario", "jade", logger);
      } else {
          logger.severe("WARNING: "+scenarioName+" does not coincide with that TesterAgent is started in Jade Platform in Main-Container with Configuration 1" );
      }
	}

  /**
   * The WHEN part
   */
  @When("$setupName")
  public void configureScenario(String setupName) {

      if (setupName.equals("tester wants to get a belief from a Jade agent")){
          super.setup("es.upm.dit.gsi.beast.platform.jade.GetBelievesFromAgent.phases.Setup");
      } else {
          logger.severe("WARNING: "+setupName+" does not coincide with tester wants to get a belief from a Jade agent");
      }
  }

  /**
   * The THEN part, where the correct behaviour must be asserted 
   */
  @Then("$evaluationName")
  public void checkScenario(String evaluationName) {

      if (evaluationName.equals("the Jade belief is retrieved")){
          super.executeEvaluation("es.upm.dit.gsi.beast.platform.jade.GetBelievesFromAgent.phases.Evaluation");
      } else {
          logger.severe("WARNING: "+evaluationName+" does not coincide with the Jade belief is retrieved");
      }
  }

}

