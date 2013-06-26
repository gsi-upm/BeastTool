package beast.tutorial.jade.stories.mas;

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
 * 
 * This "AgentStory" is described as follows:
 * Story: Record a message
 * As a RecorderAgent,
 * I want to record incoming calls,
 * So that I can pass the message to a ReporterAgent.
 * 
 * @author es.upm.dit.gsi.beast
 */
public class RecordAMessage{

    public Logger logger = Logger.getLogger(RecordAMessage.class.getName());

    /**
     * Constructor to configure logging
     */
    public RecordAMessage() {
         Properties preferences = new Properties();
         try {
              FileInputStream configFile = new FileInputStream("src/test/resources/beast-conf/logger.properties");
              preferences.load(configFile);
              LogManager.getLogManager().readConfiguration(configFile);
              LogActivator.logToFile(logger, RecordAMessage.class.getName(), Level.ALL);
         } catch (IOException ex) {
              logger.severe("WARNING: Could not open configuration file");
         }
    }

  /**
   * This is the scenario: Understanding the customer
   * where the GIVEN is described as: a RecordAgent and a ReporterAgent can communicate between them,
   * the WHEN is described as: a phone call is received and the RecordAgent understands the customer,
   * and the THEN is described as: the RecordAgent records his/her message and the RecordAgent sends that FIPA-INFORM message to a ReporterAgent.
   */
    @Test
    public void understandingTheCustomer() {
        BeastTestCaseRunner.executeBeastTestCase("beast.tutorial.jade.stories.mas.recordAMessage.UnderstandingTheCustomer");
    }
  /**
   * This is the scenario: Misunderstanding the customer
   * where the GIVEN is described as: a RecordAgent and a HelpDeskAgent can communicate between them,
   * the WHEN is described as: a phone call is received and the RecorderAgent does not understand the customer,
   * and the THEN is described as: the RecorderAgent sends a FIPA-REQUEST message to a HelpDeskAgent and the RecorderAgent pass the incoming call to a HelpDeskAgent.
   */
    @Test
    public void misunderstandingTheCustomer() {
        BeastTestCaseRunner.executeBeastTestCase("beast.tutorial.jade.stories.mas.recordAMessage.MisunderstandingTheCustomer");
    }

}

