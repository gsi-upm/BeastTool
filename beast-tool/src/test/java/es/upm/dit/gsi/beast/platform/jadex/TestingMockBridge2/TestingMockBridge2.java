package es.upm.dit.gsi.beast.platform.jadex.TestingMockBridge2;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import es.upm.dit.gsi.beast.story.Story;
import es.upm.dit.gsi.beast.story.logging.LogActivator;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.Properties;

/**
 * Main class to translate plain text into code, following the Given-When-Then
 * language. In the GIVEN part it launchs the platform In the WHEN part it
 * configures the state of its agents. In the THEN part it checks the correct
 * behaviour. The main purpose of it consists of knowing agents' state/properties
 * without changing its code.
 * 
 * @author es.upm.dit.gsi.beast
 */
  public class TestingMockBridge2 extends Story {

     public Logger logger = Logger.getLogger(TestingMockBridge2.class.getName());
  /**
   * Constructor to configure logging
   */
  public TestingMockBridge2() {
     Properties preferences = new Properties();
     try {
         FileInputStream configFile = new FileInputStream("src/test/java/es/upm/dit/gsi/beast/platform/jadex/jadexBeastLog.properties");
         preferences.load(configFile);
         LogManager.getLogManager().readConfiguration(configFile);
         LogActivator.logToFile(logger, TestingMockBridge2.class.getName(), Level.ALL);
     } catch (IOException ex) {
         logger.severe("WARNING: Could not open configuration file");
     }
  }

 	/**
	 * The GIVEN part
	 */
	@Given("$scenarioName")
	public void createScenario(String scenarioName) {

		super.createScenario(scenarioName, "jadex", logger);
	}

  /**
   * The WHEN part
   */
  @When("$setupName")
  public void configureScenario(String setupName) {

  super.setup(setupName);
  }

  /**
   * The THEN part, where the correct behaviour must be asserted 
   */
  @Then("$evaluationName")
  public void checkScenario(String evaluationName) {

    super.executeEvaluation(evaluationName);
  }

}

