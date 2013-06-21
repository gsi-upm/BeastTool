package es.upm.dit.gsi.beast.reader.system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import es.upm.dit.gsi.beast.exception.BeastException;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.reader.system.CreateSystemCaseManager.java
 * 
 * Main class that generates the CaseManager.java File to run the System Tests.
 * 
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * @author Jorge Solitario
 * @author Alberto Mardomingo
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @version 0.1
 * 
 */
public class CreateSystemCaseManager {

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
     * @throws BeastException 
     */
    public static File startSystemCaseManager(String package_path, String dest_dir) throws BeastException {

        Logger logger = Logger.getLogger("CreateSystemCaseManager.startSystemCaseManager");

        File folder = SystemReader.createFolder(package_path, dest_dir);
        File caseManager = new File(folder, "CaseManager.java");
        FileWriter caseManagerWriter;

        try {
            caseManagerWriter = new FileWriter(caseManager);
            caseManagerWriter.write("package " + package_path + ";\n");
            caseManagerWriter.write("\n");
            caseManagerWriter.write("import org.junit.Test;\n");
            caseManagerWriter
                    .write("import org.junit.runner.JUnitCore;\n");
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
     * The second method to write caseManager. Its task is to write the call to 
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
            
            String storyClass = SystemReader.createClassName(storyName);
            caseManagerWriter.write("  public void " + storyClass
                    + "() {\n");
            caseManagerWriter.write("     JUnitCore.runClasses("
                    + testPath + "." + storyClass + ".class);\n");
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
     * Reader.java.
     * 
     * @param caseManager
     */

    public static void closeSystemCaseManager(File caseManager) {

        FileWriter caseManagerWriter;
        try {
            caseManagerWriter = new FileWriter(caseManager, true);
            caseManagerWriter.write("}\n");
            caseManagerWriter.flush();
            caseManagerWriter.close();
        } catch (IOException e) {
            Logger logger = Logger
                    .getLogger("CreateSystemCaseManager.closeSystemCaseManager");
            logger.info("ERROR: There is a mistake closing caseManager file.\n");
        }

    }
}
