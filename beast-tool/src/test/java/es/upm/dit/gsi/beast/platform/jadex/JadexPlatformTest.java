package es.upm.dit.gsi.beast.platform.jadex;

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
public class JadexPlatformTest{

    public Logger logger = Logger.getLogger(JadexPlatformTest.class.getName());

    /**
     * Constructor to configure logging
     */
    public JadexPlatformTest() {
         Properties preferences = new Properties();
         try {
              FileInputStream configFile = new FileInputStream("src/test/java/es/upm/dit/gsi/beast/platform/jadex/jadexBeastLog.properties");
              preferences.load(configFile);
              LogManager.getLogManager().readConfiguration(configFile);
              LogActivator.logToFile(logger, JadexPlatformTest.class.getName(), Level.ALL);
         } catch (IOException ex) {
              logger.severe("WARNING: Could not open configuration file");
         }
    }

  /**
   * This is the scenario:  get believes from agent,
   * where the GIVEN is described as: that one agent is started in Jadex Platform,
   * the WHEN is described as: tester wants to get a belief from a Jadex agent
   * and the THEN is described as: the Jadex belief is retrieved
   */
    @Test
    public void getBelievesFromAgent(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jadex.jadexPlatformTest.GetBelievesFromAgent");
    }
  /**
   * This is the scenario:  testing mock repository,
   * where the GIVEN is described as: one bridge mock and one repository mock in Jadex Platform,
   * the WHEN is described as: bridge sends a message to repository
   * and the THEN is described as: repository answers with the correct message
   */
    @Test
    public void testingMockRepository(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jadex.jadexPlatformTest.TestingMockRepository");
    }
  /**
   * This is the scenario:  testing mock bridge case one,
   * where the GIVEN is described as: one bridge mock and one listener mock in Jadex Platform,
   * the WHEN is described as: bridge mocks has to send a message to listener
   * and the THEN is described as: listener receives the message
   */
    @Test
    public void testingMockBridgeCaseOne(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jadex.jadexPlatformTest.TestingMockBridgeCaseOne");
    }
  /**
   * This is the scenario:  testing mock listener,
   * where the GIVEN is described as: one listener mock in Jadex Platform,
   * the WHEN is described as: 10 messages are sent to listener
   * and the THEN is described as: listener receives all messages
   */
    @Test
    public void testingMockListener(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jadex.jadexPlatformTest.TestingMockListener");
    }
  /**
   * This is the scenario:  set believes in agent,
   * where the GIVEN is described as: that one agent is started in Jadex Platform,
   * the WHEN is described as: tester wants to set a belief inside a Jadex agent
   * and the THEN is described as: the Jadex belief is set
   */
    @Test
    public void setBelievesInAgent(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jadex.jadexPlatformTest.SetBelievesInAgent");
    }
  /**
   * This is the scenario:  testing mock bridge case two,
   * where the GIVEN is described as: one repository mock and one bridge mock in Jadex Platform,
   * the WHEN is described as: bridge mocks has to send a message to repository
   * and the THEN is described as: bridge receives the answer from repository
   */
    @Test
    public void testingMockBridgeCaseTwo(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jadex.jadexPlatformTest.TestingMockBridgeCaseTwo");
    }

}

