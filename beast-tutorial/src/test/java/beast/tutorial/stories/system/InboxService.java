package beast.tutorial.stories.system;

import es.upm.dit.gsi.beast.story.logging.LogActivator;
import es.upm.dit.gsi.beast.story.BeastTestCaseRunner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.Properties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Main class to translate plain text into code, following the Given-When-Then
 * language. In the GIVEN part it launchs the platform In the WHEN part it
 * configures the state of its agents. In the THEN part it checks the correct
 * behaviour. The main purpose of it consists of knowing agents' state/properties
 * without changing its code.
 * 
 * @author es.upm.dit.gsi.beast
 */
public class InboxService{

    public Logger logger = Logger.getLogger(InboxService.class.getName());

    /**
     * Constructor to configure logging
     */
    public InboxService() {
         Properties preferences = new Properties();
         try {
              FileInputStream configFile = new FileInputStream("src/test/resources/beast-conf/logger.properties");
              preferences.load(configFile);
              LogManager.getLogManager().readConfiguration(configFile);
              LogActivator.logToFile(logger, InboxService.class.getName(), Level.ALL);
         } catch (IOException ex) {
              logger.severe("WARNING: Could not open configuration file");
         }
    }

  /**
   * This is the scenario: PassingIncomingCall,
   * where the GIVEN is described as: a customer is calling,,
   * the WHEN is described as: the voice recognition system does not understand to the customer,
   * and the THEN is described as: the call is attended by a helpdesk operator.
   */
    @Test
    public void passingIncomingCall() {
    	
    	//JADE tests
    	BeastTestCaseRunner.executeBeastTestCase("beast.tutorial.jade.stories.mas.recordAMessage.UnderstandingTheCustomer");

        Result jadeResult = JUnitCore.runClasses(beast.tutorial.jade.stories.mas.PassingAnIncomingCall.class);
        Assert.assertTrue(jadeResult.wasSuccessful());
        
        //JADEX tests
    	BeastTestCaseRunner.executeBeastTestCase("beast.tutorial.jadex.stories.mas.recordAMessage.UnderstandingTheCustomer");
        
    	Result jadexResult = JUnitCore.runClasses(beast.tutorial.jadex.stories.mas.PassingAnIncomingCall.class);
        Assert.assertTrue(jadexResult.wasSuccessful());
        
    }

  /**
   * This is the scenario: ReportCreation,
   * where the GIVEN is described as: a customer has a problem,,
   * the WHEN is described as: a phone call is received and the voice recognition system understands to the customer,
   * and the THEN is described as: the system records the message and a new issue report is created.
   */
    @Test
    public void reportCreation() {
    	
        // JADE tests
    	BeastTestCaseRunner.executeBeastTestCase("beast.tutorial.jade.stories.mas.recordAMessage.MisunderstandingTheCustomer");

        Result jadeResult = JUnitCore.runClasses(beast.tutorial.jade.stories.mas.ProcessingMessage.class);
        Assert.assertTrue(jadeResult.wasSuccessful());

        //JADEX tests
    	BeastTestCaseRunner.executeBeastTestCase("beast.tutorial.jadex.stories.mas.recordAMessage.MisunderstandingTheCustomer");

        Result jadexResult = JUnitCore.runClasses(beast.tutorial.jadex.stories.mas.ProcessingMessage.class);
        Assert.assertTrue(jadexResult.wasSuccessful());
        
    }

}

