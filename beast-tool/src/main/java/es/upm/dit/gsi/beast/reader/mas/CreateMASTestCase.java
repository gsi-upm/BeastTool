package es.upm.dit.gsi.beast.reader.mas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import es.upm.dit.gsi.beast.exception.BeastException;

/**
 * Main class to create the java file that controls each Test. First it will
 * SETUP the scenario, then it will perform LAUNCH anything needed, and finally
 * it will VERIFY the result. The code is the same for each TEST, but we must
 * write one for each .story due to our BDD Tool (JBehave).
 * 
 * @author Alberto Mardomingo
 * @author Jorge Solitario
 */
public class CreateMASTestCase {

    /**
     * Method to create the java file that it's executed from caseManager. Its
     * name comes from the Scenario that it's testing. Its behaviour is written
     * in the .story file allocated in the same folder, which is the plain text
     * given by the client.
     * 
     * @param scenario_name
     *            the name of the Scenario
     * @param platform_name
     *            the agent platform in usage (i.e. jade or jadex)
     * @param package_path
     *            the package containing the tests
     * @param dest_dir
     *            the main folder main (typically src/main/java)
     * @param logginfPropFile
     *            the path to the logging properties file
     * @param givenComment
     *            the GIVEN part of the scenario
     * @param whenComment
     *            the WHEN part of the scenario
     * @param thenComment
     *            the THEN part of the scenario
     * @throws BeastException
     */
    public static void createBeastTestCase(String scenario_name,
            String platform_name, String package_path, String dest_dir,
            String loggingPropFile, String givenComment, String whenComment,
            String thenComment, String storyName, String story_agent, String agent_feature, String agent_benefit) throws BeastException {

        Logger logger = Logger.getLogger(CreateMASTestCase.class.getName());

        File f = MASReader.createFolder(package_path, dest_dir);

        File javaFile = new File(f, scenario_name + ".java");

        try {
            FileWriter fw = new FileWriter(javaFile);
            fw.write("package " + package_path + ";\n");
            fw.write("\n");

            // Adds all the necessary imports.
            fw.write("import org.jbehave.core.annotations.Given;\n");
            fw.write("import static org.mockito.Matchers.eq;\n");
            fw.write("import static org.mockito.Mockito.mock;\n");
            fw.write("import static org.mockito.Mockito.when;\n");

            fw.write("import org.jbehave.core.annotations.Then;\n");
            fw.write("import org.jbehave.core.annotations.When;\n");
            fw.write("import es.upm.dit.gsi.beast.story.BeastTestCase;\n");
            fw.write("import es.upm.dit.gsi.beast.story.logging.LogActivator;\n");
            fw.write("import java.io.FileInputStream;\n");
            fw.write("import java.io.IOException;\n");
            fw.write("import java.util.logging.Logger;\n");
            fw.write("import java.util.logging.Level;\n");
            fw.write("import java.util.logging.LogManager;\n");
            fw.write("import java.util.Properties;\n");
            fw.write("\n import junit.framework.Assert;");
            fw.write("\n");

            // Class header
            fw.write("/**\n");
            fw.write(" * Main class to translate plain text into code, following the Given-When-Then\n");
            fw.write(" * language. In the GIVEN part it launchs the platform In the WHEN part it\n");
            fw.write(" * configures the state of its agents. In the THEN part it checks the correct\n");
            fw.write(" * behaviour. The main purpose of it consists of knowing agents' state/properties\n");
            fw.write(" * without changing its code.\n");
            fw.write(" * \n");
            fw.write(" * \n");
            fw.write(" * This \"AgentStory\" is described as follows:\n");
            fw.write(" * Story: " + storyName + "\n");
            fw.write(" * As a " + story_agent + "\n");
            fw.write(" * I want to " + agent_feature + "\n");
            fw.write(" * So that " + agent_benefit + "\n");
            fw.write(" * \n");
            fw.write(" * This specific scenario is described as follows:\n");
            fw.write(" * Scenario: " + scenario_name+ "\n");
            fw.write(" * Given " + givenComment + "\n");
            fw.write(" * When " + whenComment + "\n");
            fw.write(" * Then " + thenComment + "\n");
            fw.write(" * \n");
            fw.write(" * @author es.upm.dit.gsi.beast\n");
            fw.write(" */\n");
            fw.write("public class " + scenario_name
                    + " extends BeastTestCase {\n");
            fw.write("\n");
            fw.write("    public Logger logger = Logger.getLogger("
                    + scenario_name + ".class.getName());\n");
            fw.write("\n");

            // Creates the constructor
            fw.write("    /**\n");
            fw.write("     * Constructor to configure logging\n");
            fw.write("     */\n");
            fw.write("    public " + scenario_name + "() {\n");
            if (loggingPropFile == null) {
                // If there is no properties, creates a "Standard" logger.
                fw.write("         LogActivator.logToFile(logger, "
                        + scenario_name + ".class.getName(), Level.ALL);\n");
            } else {
                fw.write("         Properties preferences = new Properties();\n");
                fw.write("         try {\n");
                fw.write("              FileInputStream configFile = new FileInputStream(\""
                        + loggingPropFile + "\");\n");
                fw.write("              preferences.load(configFile);\n");
                fw.write("              LogManager.getLogManager().readConfiguration(configFile);\n");
                fw.write("              LogActivator.logToFile(logger, "
                        + scenario_name + ".class.getName(), Level.ALL);\n");
                fw.write("         } catch (IOException ex) {\n");
                fw.write("              logger.severe(\"WARNING: Could not open configuration file\");\n");
                fw.write("         }\n");
            }
            fw.write("    }\n");
            fw.write("\n");

            // Creates the scenario
            fw.write("    /**\n");
            fw.write("     * This is the method that must create the Scenario.\n");
            fw.write("     * It is related with the GIVEN part.\n");
            fw.write("     * \"GIVEN " + givenComment + "\".\n");
            fw.write("     * \n");
            fw.write("     * In setup method the following method must be used\n");
            fw.write("     * startAgent(agent_name,agent_path)\n");
            fw.write("     */\n");
            fw.write("    public void setup() {\n");
            fw.write("         // TODO: implement this method to represent the @Given part of the test in Java code.\n");
            fw.write("         \n");
            fw.write("         logger.warning(\"Implement setup() method in "
                    + package_path
                    + scenario_name
                    + ".java -> Auto-generated stub by Beast -> es.upm.dit.gsi.beast-tool\");\n");
            fw.write("\n");
            fw.write("         //EXAMPLE for Jadex: startAgent(\"Steve\", \"org.example.Steve.agent.xml\"); // This xml file is the jadex agent description file (ADF)\n");
            fw.write("         //EXAMPLE for Jade: startAgent(\"Steve\", \"org.example.Steve\"); // This string is the agent class Steve.java that extends Jade Agent class\n");
            fw.write("\n");
            fw.write("    }\n");

            // Creates the Setup
            fw.write("    /**\n");
            fw.write("     * This is the method that must create the Setup.\n");
            fw.write("     * It is related with the WHEN part.\n");
            fw.write("     * \"WHEN " + whenComment + "\"\n");
            fw.write("     *  \n");
            fw.write("     * In launch method the following methods must be used\n");
            fw.write("     *   setBeliefValue (agent_name, belief_name, new_value )\n");
            fw.write("     *   sendMessageToAgent(agent_name, msgtype, message_content)\n");
            fw.write("     *   getAgentPlans(agent_name)\n");
            fw.write("     *   getAgentGoals(agent_name )\n");
            fw.write("     */\n");

            fw.write("    public void launch() {\n");
            fw.write("         // TODO implement this method to represent the @When part of the test in Java code.\n");
            fw.write("         \n");
            fw.write("         logger.warning(\"Implement launch() method in "
                    + package_path
                    + scenario_name
                    + ".java -> Auto-generated stub by Beast -> es.upm.dit.gsi.beast-tool\");\n");
            fw.write("         \n             //EXAMPLE: setBeliefValue(\"Steve\", \"age\", 21);\n");
            fw.write("         \n");

            fw.write("    }\n");

            // Creates the evaluation
            fw.write("    /**\n");
            fw.write("     * This is the method that must create the Evaluation.\n");
            fw.write("     * It is related with the THEN part.\n");
            fw.write("     * \"THEN " + thenComment + "\"\n");
            fw.write("     *  \n");
            fw.write("     * In verify method the following method must be used\n");
            fw.write("     * checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)\n");
            fw.write("     */\n");

            fw.write("    public void verify() {\n");
            fw.write("         // TODO implement this method to represent the @Then part of the test in Java code.\n");
            fw.write("         \n");
            fw.write("         logger.warning(\"Implement verify() method in "
                    + package_path
                    + scenario_name
                    + ".java -> Auto-generated stub by Beast -> es.upm.dit.gsi.beast-tool\");\n");
            fw.write("         System.out.println(\"IMPORTANT!! -> Not implemented Test. Auto-generated stub by Beast -> es.upm.dit.gsi.beast-tool in class\"+ this.getClass().getName());\n");
            fw.write("         Assert.fail(\"Not implemented Test. Auto-generated stub by Beast -> es.upm.dit.gsi.beast-tool\");\n");
            fw.write("\n        //EXAMPLE: checkAgentsBeliefEquealsTo(\"Steve\", \"age\", 21);\n");
            fw.write("\n");

            fw.write("    }\n");

            // Writes the "Given something-and-something" part
            fw.write("    /**\n");
            fw.write("     * The GIVEN part\n");
            fw.write("     */\n");
            fw.write("    @Given(\"$scenarioName\")\n");
            fw.write("    public void createScenario(String scenarioName) {\n");
            fw.write("\n");
            fw.write("         if (scenarioName.equals(\"" + givenComment
                    + "\")){\n");
            fw.write("              startPlatform(" + platform_name
                    + ", logger);\n");
            fw.write("         } else {\n");
            fw.write("              logger.severe(\"WARNING: \"+scenarioName+\" does not coincide with "
                    + givenComment + "\" );\n");
            fw.write("         }\n");
            fw.write("    }\n");
            fw.write("\n");

            // Writes the "When EVENT" part
            fw.write("    /**\n");
            fw.write("     * The WHEN part\n");
            fw.write("     */\n");
            fw.write("    @When(\"$setupName\")\n");
            fw.write("    public void configureScenario(String setupName) {\n");
            fw.write("\n");
            fw.write("         if (setupName.equals(\"" + whenComment
                    + "\")){\n");
            fw.write("              setScenario();\n");
            fw.write("         } else {\n");
            fw.write("              logger.severe(\"WARNING: \"+setupName+\" does not coincide with "
                    + whenComment + "\");\n");
            fw.write("         }\n");
            fw.write("    }\n");
            fw.write("\n");

            // Writes the "Then ACTION" part
            fw.write("    /**\n");
            fw.write("     * The THEN part, where the correct behaviour must be asserted \n");
            fw.write("     */\n");
            fw.write("    @Then(\"$evaluationName\")\n");
            fw.write("    public void checkScenario(String evaluationName) {\n");
            fw.write("\n");
            fw.write("        if (evaluationName.equals(\"" + thenComment
                    + "\")){\n");
            fw.write("            this.setExecutionTime(BeastTestCase.SLEEP_TIME);\n");
            fw.write("            verify();\n");
            fw.write("        } else {\n");
            fw.write("            logger.severe(\"WARNING: \"+evaluationName+\" does not coincide with "
                    + thenComment + "\");\n");
            fw.write("        }\n");
            fw.write("    }\n");
            // Ends the class.
            // You don't say.
            fw.write("\n");
            fw.write("}\n");
            fw.write("\n");
            fw.flush();
            fw.close();

            // logger.info(scenario_name+" has been created in "+dest_dir+" "+Reader.createFolderPath(package_path));

        } catch (IOException e) {

            logger.severe("ERROR: The file " + scenario_name
                    + ".java can not be writed");
        }
    }
}
