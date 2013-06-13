package es.upm.dit.gsi.beast.reader.mas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Main class that generates the CaseManager.java File to run the MAS tests.
 * 
 * @author Alberto Mardomingo
 * @author Jorge Solitario
 * @author Alvaro Carrera
 */
public class CreateMASCaseManager {

    /**
     * This method creates CaseManager file and writes on it: the package, the
     * imports, its comments and the class name.
     * 
     * @param package_path
     *            as es.upm.dit...
     * @param dest_dir
     *            as src/main/java
     * 
     * @return the File with its first part written
     */
    public static File startMASCaseManager(String package_path, String dest_dir) {

        Logger logger = Logger.getLogger("CreateMASCaseManager.startMASCaseManager");

        File folder = MASReader.createFolder(package_path, dest_dir);
        File caseManager = new File(folder, "CaseManager.java");
        FileWriter caseManagerWriter;

        try {
            caseManagerWriter = new FileWriter(caseManager);
            caseManagerWriter.write("package " + package_path + ";\n");
            caseManagerWriter.write("\n");
            caseManagerWriter.write("import org.junit.Test;\n");
            caseManagerWriter.write("import org.junit.runner.JUnitCore;\n");
//            caseManagerWriter
//                    .write("import es.upm.dit.gsi.beast.story.BeastTestCaseRunner;\n");
//                    // import BeastTestCase
            caseManagerWriter.write("\n");
            caseManagerWriter.write("/**\n");
            caseManagerWriter
                    .write(" * Main class to launch all tests in a single run\n");
            caseManagerWriter.write(" *\n");
            caseManagerWriter.write(" * @author es.upm.dit.gsi.beast\n");
            caseManagerWriter.write(" */\n");
            caseManagerWriter.write("public class CaseManager {\n");
            caseManagerWriter.write("\n");
            caseManagerWriter.flush();
            caseManagerWriter.close();
            // logger.info("CaseManager has been created in "+dest_dir+Reader.createFolderPath(package_path));
        } catch (IOException e) {
            logger.severe("ERROR writing the file");
        }

        return caseManager;
    }

    /**
     * The second method to write caseManager. Its task is to write the Tests to
     * run in our platform, so this method is called once for each Test by
     * MasReader.java when there is no story Name.
     * 
     * @param caseManager
     *            the file where the test must be added
     * @param test_name
     *            the name of the test to be written
     * @param test_path
     *            the path where the test can be founded
     * @param scenario
     *            the name of the scenario of the test
     * @param given
     *            the GIVEN part of the plain text given by the client
     * @param when
     *            the WHEN part of the plain text given by the client
     * @param then
     *            the THEN part of the plain text given by the client
     */
    public static void createTest(File caseManager, String test_name,
            String test_path, String scenario, String given, String when,
            String then) {
        FileWriter caseManagerWriter;
        try {
            caseManagerWriter = new FileWriter(caseManager, true);
            caseManagerWriter.write("  /**\n");
            caseManagerWriter.write("   * This is the scenario: " + scenario
                    + ",\n");
            caseManagerWriter.write("   * where the GIVEN is described as: "
                    + given + ",\n");
            caseManagerWriter.write("   * the WHEN is described as: " + when
                    + "\n");
            caseManagerWriter.write("   * and the THEN is described as: "
                    + then + "\n");
            caseManagerWriter.write("   */\n");
            caseManagerWriter.write("  @Test\n");
            caseManagerWriter.write("  public void Scenario" + MASReader.createFirstLowCaseName(test_name)
                    + "() {\n");
            caseManagerWriter.write("  \n");
            caseManagerWriter.write("	  BeastTestCaseRunner.executeBeastTestCase(\""
                    + test_path + "\");\n");
            caseManagerWriter.write("  }\n");
            caseManagerWriter.write("\n");
            caseManagerWriter.flush();
            caseManagerWriter.close();
        } catch (IOException e) {
            Logger logger = Logger.getLogger("CreateMASCaseManager.createTest");
            logger.info("ERROR writing the file");
        }

    }

    
    /**
     * The third method to write caseManager. Its task is to write the call to 
     * the story to be run.
     * 
     * @param caseManager
     *            the file where the test must be written
     * @param storyName
     *            the name of the story
     * @param test_path
     *            the path where the story can be found
     * @param user
     *            the user requesting the story
     * @param feature
     *            the feature requested by the user
     * @param benefit
     *            the benefit provided by the feature
     */
    public static void addStory(File caseManager, String storyName,
            String testPath, String user, String feature, String benefit) {
        FileWriter caseManagerWriter;
        try {
            caseManagerWriter = new FileWriter(caseManager, true);
            caseManagerWriter.write("  /**\n");
            caseManagerWriter.write("   * This is the story: " + storyName
                    + ",\n");
            caseManagerWriter.write("   * requested by: "
                    + user + ",\n");
            caseManagerWriter.write("   * providing the feature: " + feature
                    + "\n");
            caseManagerWriter.write("   * so the user gets the benefit: "
                    + benefit + "\n");
            caseManagerWriter.write("   */\n");
            caseManagerWriter.write("  @Test\n");
            caseManagerWriter.write("  public void " + MASReader.createFirstLowCaseName(storyName)
                    + "() {\n");
            String storyClass =  MASReader.createClassName(storyName);
            caseManagerWriter.write("      JUnitCore.runClasses("
                    + testPath  + "." + storyClass+ ".class);\n");
            caseManagerWriter.write("  }\n");
            caseManagerWriter.write("\n");
            caseManagerWriter.flush();
            caseManagerWriter.close();
        } catch (IOException e) {
            Logger logger = Logger.getLogger("CreateMASCaseManager.createTest");
            logger.info("ERROR writing the file");
        }

    }
    
    /**
     * Method to close the file caseManager. It is called just one time, by
     * the MASReader, once every test and stroy have been added.
     * 
     * @param caseManager
     */
    public static void closeMASCaseManager(File caseManager) {

        FileWriter caseManagerWriter;
        try {
            caseManagerWriter = new FileWriter(caseManager, true);
            caseManagerWriter.write("}\n");
            caseManagerWriter.flush();
            caseManagerWriter.close();
        } catch (IOException e) {
            Logger logger = Logger
                    .getLogger("CreateMASCaseManager.closeMASCaseManager");
            logger.info("ERROR: There is a mistake closing caseManager file.\n");
        }

    }

}
