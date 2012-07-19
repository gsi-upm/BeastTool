package es.upm.dit.gsi.beast.platform.jade;

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
public class JadePlatformTest{

    public Logger logger = Logger.getLogger(JadePlatformTest.class.getName());

    /**
     * Constructor to configure logging
     */
    public JadePlatformTest() {
         Properties preferences = new Properties();
         try {
              FileInputStream configFile = new FileInputStream("src/test/java/es/upm/dit/gsi/beast/platform/jade/jadeBeastLog.properties");
              preferences.load(configFile);
              LogManager.getLogManager().readConfiguration(configFile);
              LogActivator.logToFile(logger, JadePlatformTest.class.getName(), Level.ALL);
         } catch (IOException ex) {
              logger.severe("WARNING: Could not open configuration file");
         }
    }

  /**
   * This is the scenario:  messenger agent receive a response,
   * where the GIVEN is described as: that TesterAgent is started in Jade Platform in Main-Container with Configuration 6,
   * the WHEN is described as: tester wants to send a message with MessageService to TesterAgent and to receive a response
   * and the THEN is described as: the message is received by the tester through MessengerAgent
   */
    @Test
    public void messengerAgentReceiveAResponse(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jade.jadePlatformTest.MessengerAgentReceiveAResponse");
    }
  /**
   * This is the scenario:  get believes from agent,
   * where the GIVEN is described as: that TesterAgent is started in Jade Platform in Main-Container with Configuration 1,
   * the WHEN is described as: tester wants to get a belief from a Jade agent
   * and the THEN is described as: the Jade belief is retrieved
   */
    @Test
    public void getBelievesFromAgent(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jade.jadePlatformTest.GetBelievesFromAgent");
    }
  /**
   * This is the scenario:  testing mock repository,
   * where the GIVEN is described as: one bridge mock and one repository mock in Jade Platform,
   * the WHEN is described as: bridge sends a message to repository
   * and the THEN is described as: repository answers with the correct message
   */
    @Test
    public void testingMockRepository(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jade.jadePlatformTest.TestingMockRepository");
    }
  /**
   * This is the scenario:  testing mock bridge case one,
   * where the GIVEN is described as: one bridge mock and one listener mock in Jade Platform,
   * the WHEN is described as: bridge mocks has to send a message to listener
   * and the THEN is described as: listener receives the message
   */
    @Test
    public void testingMockBridgeCaseOne(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jade.jadePlatformTest.TestingMockBridgeCaseOne");
    }
  /**
   * This is the scenario:  testing mock listener,
   * where the GIVEN is described as: one listener mock in Jade Platform,
   * the WHEN is described as: 10 messages are sent to listener
   * and the THEN is described as: listener receives all messages
   */
    @Test
    public void testingMockListener(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jade.jadePlatformTest.TestingMockListener");
    }
  /**
   * This is the scenario:  set believes in agent,
   * where the GIVEN is described as: that TesterAgent is started in Jade Platform in Main-Container with Configuration 4,
   * the WHEN is described as: tester wants to set a belief inside a Jade agent
   * and the THEN is described as: the Jade belief is set
   */
    @Test
    public void setBelievesInAgent(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jade.jadePlatformTest.SetBelievesInAgent");
    }
  /**
   * This is the scenario:  testing mock bridge case two,
   * where the GIVEN is described as: one repository mock and one bridge mock in Jade Platform,
   * the WHEN is described as: bridge mocks has to send a message to repository
   * and the THEN is described as: bridge receives the answer from repository
   */
    @Test
    public void testingMockBridgeCaseTwo(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jade.jadePlatformTest.TestingMockBridgeCaseTwo");
    }
  /**
   * This is the scenario:  send a message,
   * where the GIVEN is described as: that TesterAgent is started in Jade Platform in Main-Container with Configuration 5,
   * the WHEN is described as: tester wants to send a message with MessageService to TesterAgent
   * and the THEN is described as: the message is received by TesterAgent
   */
    @Test
    public void sendAMessage(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jade.jadePlatformTest.SendAMessage");
    }

}

