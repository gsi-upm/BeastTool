package es.upm.dit.gsi.beast.reader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Main class that generates the CaseManager.java File to run all the test given
 * by our client.
 * 
 * @author Jorge Solitario
 */
public class CreateCaseManager {

    /**
     * This method creates CaseManager file and writes on it: the package, the
     * imports, its comments and the class name.
     * 
     * @param package_path
     *            as es.upm.dit...
     * @param dest_dir
     *            as src/main/java
     * 
     * @return the File the its first part written
     */
    public static File startCaseManager(String package_path, String dest_dir) {

        Logger logger = Logger.getLogger("CreateCaseManager.startCaseManager");

        File folder = Reader.createFolder(package_path, dest_dir);
        File caseManager = new File(folder, "CaseManager.java");
        FileWriter caseManagerWriter;

        try {
            caseManagerWriter = new FileWriter(caseManager);
            caseManagerWriter.write("package " + package_path + ";\n");
            caseManagerWriter.write("\n");
            caseManagerWriter.write("import org.junit.Test;\n");
            caseManagerWriter
                    .write("import es.upm.dit.gsi.beast.story.StoryRunner;\n"); // import
                                                                                // StoryRunner
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
     * Reader.java.
     * 
     * @param caseManager
     *            the file where the test must de written
     * @param test_name
     *            the name of its test
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
            caseManagerWriter.write("  public void Scenario" + test_name
                    + "() {\n");
            caseManagerWriter.write("  \n");
            caseManagerWriter.write("	  StoryRunner.executeStory(\""
                    + test_path + "\");\n");
            caseManagerWriter.write("  }\n");
            caseManagerWriter.write("\n");
            caseManagerWriter.flush();
            caseManagerWriter.close();
        } catch (IOException e) {
            Logger logger = Logger.getLogger("CreateCaseManager.createTest");
            logger.info("ERROR writing the file");
        }

    }

    /**
     * Method to close the file caseManager. It is called just one time, by
     * Reader.java.
     * 
     * @param caseManager
     */

    public static void closeCaseManager(File caseManager) {

        FileWriter caseManagerWriter;
        try {
            caseManagerWriter = new FileWriter(caseManager, true);
            caseManagerWriter.write("}\n");
            caseManagerWriter.flush();
            caseManagerWriter.close();
        } catch (IOException e) {
            Logger logger = Logger
                    .getLogger("CreateCaseManager.closeCaseManager");
            logger.info("ERROR: There is a mistake closing caseManager file.\n");
        }

    }

}
