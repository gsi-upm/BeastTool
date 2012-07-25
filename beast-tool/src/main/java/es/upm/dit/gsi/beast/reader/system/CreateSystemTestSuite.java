package es.upm.dit.gsi.beast.reader.system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Class to create the system test suite, where the developers must add the 
 * necessary calls to the MAS test included in the feature required by the client
 * 
 * @author Alberto Mardomingo
 *
 */
public class CreateSystemTestSuite {

    /**
     * Method to create the java file that it's executed by the caseManager. Its
     * name comes from the Story that it's testing.
     * 
     * @param story_name - the name of the Story
     * @param platform_name - the name of the platform
     * @param package_path - the package path
     * @param dest_dir the main folder (typically src/main/java)
     * @param loggingPropFile
     * @param storyUser - The user launching the Story
     * @param userFeature - The feature requested by the user
     * @param userBenefit - The benefit the feature will provide
     * @param scenarios - A list with the tests to launch in the testSuite.
     */
    public static void createSystemTestSuite(String story_name,
            String platform_name, String package_path, String dest_dir,
            String loggingPropFile, String storyUser,
            String userFeature, String userBenefit, HashMap<String, String[]> scenarios) {

        Logger logger = Logger.getLogger(CreateSystemTestSuite.class.getName());

        File f = SystemReader.createFolder(package_path, dest_dir);

        String storyClass = SystemReader.createClassName(story_name);
        File javaFile = new File(f, storyClass + ".java");

        try {
            FileWriter fw = new FileWriter(javaFile);
            fw.write("package " + package_path + ";\n");
            fw.write("\n");

            // Adds all the necessary imports.
            fw.write("import es.upm.dit.gsi.beast.story.logging.LogActivator;\n");
            fw.write("import es.upm.dit.gsi.beast.story.BeastTestCaseRunner;\n");
            fw.write("import java.io.FileInputStream;\n");
            fw.write("import java.io.IOException;\n");
            fw.write("import java.util.logging.Logger;\n");
            fw.write("import java.util.logging.Level;\n");
            fw.write("import java.util.logging.LogManager;\n");
            fw.write("import java.util.Properties;\n");
            fw.write("import org.junit.Test;\n");
            fw.write("\n");

            // Class header
            fw.write("/**\n");
            fw.write(" * Main class to translate plain text into code, following the Given-When-Then\n");
            fw.write(" * language. In the GIVEN part it launchs the platform In the WHEN part it\n");
            fw.write(" * configures the state of its agents. In the THEN part it checks the correct\n");
            fw.write(" * behaviour. The main purpose of it consists of knowing agents' state/properties\n");
            fw.write(" * without changing its code.\n");
            fw.write(" * \n");
            fw.write(" * @author es.upm.dit.gsi.beast\n");
            fw.write(" */\n");
            fw.write("public class " + storyClass  + "{\n");
            fw.write("\n");
            fw.write("    public Logger logger = Logger.getLogger("
                    + storyClass + ".class.getName());\n");
            fw.write("\n");

            // Creates the constructor
            fw.write("    /**\n");
            fw.write("     * Constructor to configure logging\n");
            fw.write("     */\n");
            fw.write("    public " + storyClass + "() {\n");
            if (loggingPropFile == null) {
                // If there is no properties, creates a "Standard" logger.
                fw.write("         LogActivator.logToFile(logger, "
                        + storyClass + ".class.getName(), Level.ALL);\n");
            } else {
                fw.write("         Properties preferences = new Properties();\n");
                fw.write("         try {\n");
                fw.write("              FileInputStream configFile = new FileInputStream(\""
                        + loggingPropFile + "\");\n");
                fw.write("              preferences.load(configFile);\n");
                fw.write("              LogManager.getLogManager().readConfiguration(configFile);\n");
                fw.write("              LogActivator.logToFile(logger, "
                        + storyClass + ".class.getName(), Level.ALL);\n");
                fw.write("         } catch (IOException ex) {\n");
                fw.write("              logger.severe(\"WARNING: Could not open configuration file\");\n");
                fw.write("         }\n");
            }
            fw.write("    }\n");
            fw.write("\n");
            
            // Run each test
            // Remenber, scenarios:
            // { scenarioID1 => ["Given", "When", "then"], 
            // scenarioID2 => ["Given", "When", "then"], ...}
            for(String scenario : scenarios.keySet()){
                fw.write("  /**\n");
                fw.write("   * This is the scenario: " + scenario
                        + ",\n");
                fw.write("   * where the GIVEN is described as: "
                        + scenarios.get(scenario)[0] + ",\n");
                fw.write("   * the WHEN is described as: " + scenarios.get(scenario)[1]
                        + "\n");
                fw.write("   * and the THEN is described as: "
                        + scenarios.get(scenario)[2] + "\n");
                fw.write("   */\n");
                fw.write("    @Test\n");
                fw.write("    public void " + SystemReader.changeFirstLetterToLowerCase(scenario) + "(){\n");
                //fw.write("        BeastTestCaseRunner.executeBeastTestCase(" +
                //		"\"es.upm.dit.gsi.beast.reader.system.test." + story_name + "." + scenario + "\");\n");
                
                fw.write("        // Here you must call the MAS tests for this scenario\n");
                fw.write("        // EXAMPLE: BeastTestCaseRunner.executeBeastTestCase(" +
                      "\"es.upm.dit.gsi.beast.reader.mas.test.MASTestStory\");\n");
                
                fw.write("    }\n");
            }
           
            // Ends the class.
            // You don't say.
            fw.write("\n");
            fw.write("}\n");
            fw.write("\n");
            fw.flush();
            fw.close();

            // logger.info(scenario_name+" has been created in "+dest_dir+" "+Reader.createFolderPath(package_path));

        } catch (IOException e) {

            logger.severe("ERROR: The file " + story_name
                    + ".java can not be writed");
        }
    }   
}
