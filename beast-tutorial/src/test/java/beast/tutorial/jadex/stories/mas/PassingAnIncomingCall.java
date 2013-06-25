package beast.tutorial.jadex.stories.mas;

import es.upm.dit.gsi.beast.story.logging.LogActivator;
import es.upm.dit.gsi.beast.story.BeastTestCaseRunner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.Properties;
import org.junit.Test;

/**
 * Main class to translate plain text into code, following the Given-When-Then
 * language. In the GIVEN part it launchs the platform In the WHEN part it
 * configures the state of its agents. In the THEN part it checks the correct
 * behaviour. The main purpose of it consists of knowing agents' state/properties
 * without changing its code.
 * 
 * @author es.upm.dit.gsi.beast
 */
public class PassingAnIncomingCall{

    public Logger logger = Logger.getLogger(PassingAnIncomingCall.class.getName());

    /**
     * Constructor to configure logging
     */
    public PassingAnIncomingCall() {
         Properties preferences = new Properties();
         try {
              FileInputStream configFile = new FileInputStream("src/test/resources/beast-conf/logger.properties");
              preferences.load(configFile);
              LogManager.getLogManager().readConfiguration(configFile);
              LogActivator.logToFile(logger, PassingAnIncomingCall.class.getName(), Level.ALL);
         } catch (IOException ex) {
              logger.severe("WARNING: Could not open configuration file");
         }
    }

  /**
   * This is the scenario: Pass a call,
   * where the GIVEN is described as: a HelpDeskAgent has connection to the desktop of the operator computer,,
   * the WHEN is described as: that agent receives a FIPA-REQUEST message from a RecorderAgent,
   * and the THEN is described as: the request is accepted and the incoming call is transfered to the human operator.
   */
    @Test
    public void passACall() {
        BeastTestCaseRunner.executeBeastTestCase("beast.tutorial.jadex.stories.mas.passingAnIncomingCall.PassACall");
    }

}

