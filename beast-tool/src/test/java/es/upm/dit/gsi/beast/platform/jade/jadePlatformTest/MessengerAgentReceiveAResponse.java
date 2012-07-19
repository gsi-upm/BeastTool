package es.upm.dit.gsi.beast.platform.jade.jadePlatformTest;

import org.jbehave.core.annotations.Given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import es.upm.dit.gsi.beast.platform.PlatformSelector;
import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.platform.jade.agent.MessengerAgent;
import es.upm.dit.gsi.beast.story.BeastTestCase;
import es.upm.dit.gsi.beast.story.logging.LogActivator;
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
public class MessengerAgentReceiveAResponse extends BeastTestCase {

    public Logger logger = Logger.getLogger(MessengerAgentReceiveAResponse.class.getName());

    /**
     * Constructor to configure logging
     */
    public MessengerAgentReceiveAResponse() {
         Properties preferences = new Properties();
         try {
              FileInputStream configFile = new FileInputStream("src/test/java/es/upm/dit/gsi/beast/platform/jade/jadeBeastLog.properties");
              preferences.load(configFile);
              LogManager.getLogManager().readConfiguration(configFile);
              LogActivator.logToFile(logger, MessengerAgentReceiveAResponse.class.getName(), Level.ALL);
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
         Object[] arguments = new Object[1];
         arguments[0] = (Integer) 6;
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
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setContent("Do you want to be my friend?");
        sendMessageToAgent("TestAgent", "REQUEST", msg);
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
        
        while (((MessengerAgent)introspector.getAgent("BeastMessenger")).isReceived()==false) {
            // Wait...
        }
        
        Assert.assertTrue(((MessengerAgent)introspector.getAgent("BeastMessenger")).isReceived());

    }
    
    /**
     * The GIVEN part
     */
    @Given("$scenarioName")
    public void createScenario(String scenarioName) {

         if (scenarioName.equals("that TesterAgent is started in Jade Platform in Main-Container with Configuration 6")){
              startPlatform("jade", logger);
         } else {
              logger.severe("WARNING: "+scenarioName+" does not coincide with that TesterAgent is started in Jade Platform in Main-Container with Configuration 6" );
         }
    }

    /**
     * The WHEN part
     */
    @When("$setupName")
    public void configureScenario(String setupName) {

         if (setupName.equals("tester wants to send a message with MessageService to TesterAgent and to receive a response")){
              setScenario();
         } else {
              logger.severe("WARNING: "+setupName+" does not coincide with tester wants to send a message with MessageService to TesterAgent and to receive a response");
         }
    }

    /**
     * The THEN part, where the correct behaviour must be asserted 
     */
    @Then("$evaluationName")
    public void checkScenario(String evaluationName) {

        if (evaluationName.equals("the message is received by the tester through MessengerAgent")){
            this.setExecutionTime(BeastTestCase.SLEEP_TIME);
            verify();
        } else {
            logger.severe("WARNING: "+evaluationName+" does not coincide with the message is received by the tester through MessengerAgent");
        }
    }

}

