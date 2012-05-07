package es.upm.dit.gsi.beast.platform.jadex.TestingMockRepository;

import org.jbehave.core.annotations.Given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import es.upm.dit.gsi.beast.mock.MockManager;
import es.upm.dit.gsi.beast.mock.common.AgentBehaviour;
import es.upm.dit.gsi.beast.mock.common.Definitions;
import es.upm.dit.gsi.beast.mock.common.MockConfiguration;
import es.upm.dit.gsi.beast.story.BeastTestCase;
import es.upm.dit.gsi.beast.story.logging.LogActivator;
import jadex.base.fipa.SFipa;

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
public class TestingMockRepository extends BeastTestCase {

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
     * This is the mehtod that must create the Scenario.
     * It is related with the GIVEN part.
     * 
     * In setup method the following method must be used
     * startAgent(agent_name,agent_path)
     */
    public void setup() {
      //RepositoryAgent configuration
        AgentBehaviour myMockedBehaviour =   mock(AgentBehaviour.class);
        when(myMockedBehaviour.processMessage(eq(SFipa.REQUEST), eq("BridgeAgent"),eq("hi")))
            .thenReturn("hello");        
        MockConfiguration mock_configuration = new MockConfiguration();
        mock_configuration.setDFServiceName("repository_service");
        mock_configuration.setBehaviour(myMockedBehaviour);      
        MockManager.startMockJadexAgent("RepositoryAgent",Definitions.JADEX_REPOSITORY_MOCK_PATH,mock_configuration,this);
        
        
        //BridgeAgent configuration
        AgentBehaviour myMockedBehaviour2 =  mock(AgentBehaviour.class);
        when(myMockedBehaviour2.processMessage(eq(SFipa.INFORM), eq("send")))
            .thenReturn("repository_service", SFipa.REQUEST, "hi");   
        MockConfiguration mock_configuration2 = new MockConfiguration();
        mock_configuration2.setDFServiceName("bridge_service");
        mock_configuration2.setBehaviour(myMockedBehaviour2);
        MockManager.startMockJadexAgent("BridgeAgent",Definitions.JADEX_BRIDGE_MOCK_PATH,mock_configuration2,this);

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
        this.setExecutionTime(2000);
        sendMessageToAgent("BridgeAgent", SFipa.INFORM, "send");
    }
    /**
     * This is the method that must create the Evaluation.
     * It is related with the THEN part.
     *  
     * In verify method the following method must be used
     * checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)
     */
    public void verify(){
        System.out.println("Rcvd msgs by bridge: " + getBeliefValue("BridgeAgent", "message_count"));
        System.out.println("Checking...");

        this.setExecutionTime(2000);
        checkAgentsBeliefEquealsTo("RepositoryAgent", "message_count", 1);
    }
    /**
     * The GIVEN part
     */
    @Given("$scenarioName")
    public void createScenario(String scenarioName) {

         if (scenarioName.equals("one bridge mock and one repository mock in Jadex Platform")){
              startPlatform("jadex", logger);
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
             this.setExecutionTime(BeastTestCase.SLEEP_TIME);
              setScenario();
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
            this.setExecutionTime(BeastTestCase.SLEEP_TIME);
            verify();
        } else {
            logger.severe("WARNING: "+evaluationName+" does not coincide with repository answers with the correct message");
        }
    }

}

