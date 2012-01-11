package es.upm.dit.gsi.beast.platform.jadex.TestingMockRepository;

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
  public class TestingMockRepository extends Story {

     public Logger logger = Logger.getLogger(TestingMockRepository.class.getName());
  /**
   * Constructor to configure logging
   */
  public TestingMockRepository() {
     Properties preferences = new Properties();
     try {
         FileInputStream configFile = new FileInputStream("src/test/java/es/upm/dit/gsi/beast/platform/jadex/jadexBeastLog.properties");
         preferences.load(configFile);
         LogManager.getLogManager().readConfiguration(configFile);
         LogActivator.logToFile(logger, TestingMockRepository.class.getName(), Level.ALL);
     } catch (IOException ex) {
         logger.severe("WARNING: Could not open configuration file");
     }
  }

 	/**
	 * The GIVEN part
	 */
	@Given("$scenarioName")
	public void createScenario(String scenarioName) {

      if (scenarioName.equals("one bridge mock and one repository mock in Jadex Platform")){
		    super.createScenario("es.upm.dit.gsi.beast.platform.jadex.TestingMockRepository.phases.Scenario", "jadex", logger);
      } else {
          logger.severe("WARNING: "+scenarioName+" does not coincide with one bridge mock and one repository mock in Jadex Platform" );
      }
	}

  /**
   * The WHEN part
   */
  @When("$setupName")
  public void configureScenario(String setupName) {

      if (setupName.equals("bridge sends a message to repository")){
          super.setup("es.upm.dit.gsi.beast.platform.jadex.TestingMockRepository.phases.Setup");
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
          super.executeEvaluation("es.upm.dit.gsi.beast.platform.jadex.TestingMockRepository.phases.Evaluation");
      } else {
          logger.severe("WARNING: "+evaluationName+" does not coincide with repository answers with the correct message");
      }
  }

}

