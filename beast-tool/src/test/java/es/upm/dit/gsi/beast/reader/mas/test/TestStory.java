package es.upm.dit.gsi.beast.reader.mas.test;

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
public class TestStory{

    public Logger logger = Logger.getLogger(TestStory.class.getName());

    /**
     * Constructor to configure logging
     */
    public TestStory() {
         LogActivator.logToFile(logger, TestStory.class.getName(), Level.ALL);
    }

  /**
   * This is the scenario:  get believes from agent,
   * where the GIVEN is described as: that TesterAgent is started in Jade Platform in Main-Container with Configuration 1,
   * the WHEN is described as: tester wants to get a belief from a Jade agent
   * and the THEN is described as: the Jade belief is retrieved
   */
    @Test
    public void getBelievesFromAgent(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.reader.mas.test.testStory.GetBelievesFromAgent");
    }
  /**
   * This is the scenario:  this is other scenario,
   * where the GIVEN is described as: This is other Given,
   * the WHEN is described as: This is other When
   * and the THEN is described as: This is other Then
   */
    @Test
    public void thisIsOtherScenario(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.reader.mas.test.testStory.ThisIsOtherScenario");
    }
  /**
   * This is the scenario:  set believes in agent,
   * where the GIVEN is described as: that TesterAgent is started in Jade Platform in Main-Container with Configuration 4,
   * the WHEN is described as: tester wants to set a belief inside a Jade agent
   * and the THEN is described as: the Jade belief is set
   */
    @Test
    public void setBelievesInAgent(){
        BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.reader.mas.test.testStory.SetBelievesInAgent");
    }

}

