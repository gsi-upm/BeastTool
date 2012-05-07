package es.upm.dit.gsi.beast.platform.jade.SendAMessage;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import es.upm.dit.gsi.beast.platform.PlatformSelector;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.BeastTestCase;
import es.upm.dit.gsi.beast.story.logging.LogActivator;
import es.upm.dit.gsi.beast.test.agent.jade.TesterAgent;

import jade.lang.acl.ACLMessage;

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
public class SendAMessage extends BeastTestCase {

    public Logger logger = Logger.getLogger(SendAMessage.class.getName());

    /**
     * Constructor to configure logging
     */
    public SendAMessage() {
         Properties preferences = new Properties();
         try {
              FileInputStream configFile = new FileInputStream("src/test/java/es/upm/dit/gsi/beast/platform/jade/jadeBeastLog.properties");
              preferences.load(configFile);
              LogManager.getLogManager().readConfiguration(configFile);
              LogActivator.logToFile(logger, SendAMessage.class.getName(), Level.ALL);
         } catch (IOException ex) {
              logger.severe("WARNING: Could not open configuration file");
         }
    }

    /**
     * This is the mehtod that must create the Scenario.
     * It is related with the GIVEN part.
     * 
     * In setup method the following method must be used
     * startAgent(agent_name,agent_path)
     */
    public void setup() {
        logger.setLevel(Level.FINEST);
        Object[] arguments = new Object[1];
        arguments[0] = (Integer) 5;
        startAgent("TestAgent",
                "es.upm.dit.gsi.beast.test.agent.jade.TesterAgent",
                "MyContainer", arguments);

    }
    /**
     * This is the method that must create the Setup.
     * It is related with the WHEN part.
     *  
     * In launch method the following methods must be used
     *   setBeliefValue (agent_name, belief_name, new_value )
     *   sendMessageToAgent(agent_name, msgtype, message_content)n     *   getAgentPlans(agent_name)
     *   getAgentGoals(agent_name )
     */
    public void launch(){
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("Hello My Friend!");
        sendMessageToAgent("TestAgent", "INFORM", msg);
    }
    /**
     * This is the method that must create the Evaluation.
     * It is related with the THEN part.
     *  
     * In verify method the following method must be used
     * checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)
     */
    public void verify(){
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector.getAgentIntrospector("jade");
        
        while (((TesterAgent)introspector.getAgent("TestAgent")).isMessageReceived()==false) {
            // Wait...
        }
        
        checkAgentsBeliefEquealsTo("TestAgent", "ReceivedMessage", true);
    }
    /**
     * The GIVEN part
     */
    @Given("$scenarioName")
    public void createScenario(String scenarioName) {

         if (scenarioName.equals("that TesterAgent is started in Jade Platform in Main-Container with Configuration 5")){
             startPlatform("jade", logger);
         } else {
              logger.severe("WARNING: "+scenarioName+" does not coincide with that TesterAgent is started in Jade Platform in Main-Container with Configuration 5" );
         }
    }

    /**
     * The WHEN part
     */
    @When("$setupName")
    public void configureScenario(String setupName) {

         if (setupName.equals("tester wants to send a message with MessageService to TesterAgent")){
              setScenario();
         } else {
              logger.severe("WARNING: "+setupName+" does not coincide with tester wants to send a message with MessageService to TesterAgent");
         }
    }

    /**
     * The THEN part, where the correct behaviour must be asserted 
     */
    @Then("$evaluationName")
    public void checkScenario(String evaluationName) {

        if (evaluationName.equals("the message is received by TesterAgent")){
            verify();
        } else {
            logger.severe("WARNING: "+evaluationName+" does not coincide with the message is received by TesterAgent");
        }
    }

}

