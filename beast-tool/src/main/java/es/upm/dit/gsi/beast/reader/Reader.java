package es.upm.dit.gsi.beast.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import es.upm.dit.gsi.beast.exception.BeastException;
import es.upm.dit.gsi.beast.reader.mas.MASReader;
import es.upm.dit.gsi.beast.reader.system.SystemReader;

/**
 * Project: beast File: es.upm.dit.gsi.beast.reader.Reader.java
 * 
 * 
 * Main class to transform the plain text given by the client to the necessary
 * classes to run each Test. These classes are the Scenario, Setup and
 * Evaluation, which emulate the GIVEN, WHEN and THEN parts of the plain text;
 * the .story file whit the plain text of the test and the its .java class with
 * the same name to interpret it. Furthermore, one casemanager must be created,
 * which will run all the tests.
 * 
 * Grupo de Sistemas Inteligentes Departamento de Ingeniería de Sistemas
 * Telemáticos Universidad Politécnica de Madrid (UPM)
 * 
 * 
 * @author Jorge Solitario
 * @author Alberto Mardomingo
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * 
 * @version 0.2
 * 
 */
public class Reader {

    /**
     * Multi Agent System Test Reader
     */
    public static final String MAS = "MAS";
    /**
     * System Test Reader
     */
    public static final String SYSTEM = "SYSTEM";

    private static Logger logger = Logger.getLogger(Reader.class.getName());

    /**
     * Main method of the class, which handles all the process to create all
     * tests.
     * 
     * @param requirementsFolder
     *            , it is the folder where the plain text given by the client is
     *            stored
     * @param platformName
     *            , to choose the MAS platform (JADE, JADEX, etc.)
     * @param src_test_dir
     *            , the folder where our classes are created
     * @param tests_package
     *            , the name of the package where the stories are created
     * @param casemanager_package
     *            , the path where casemanager must be created
     * @param loggingPropFile
     *            , properties file
     * @param specificationPhase
     *            , the Type of reader (MAS or SYSTEM). By default, uses a MAS
     *            Reader
     * @throws Exception
     *             , if any error is found in the configuration
     */
    public static void generateJavaFiles(String requirementsFolder,
            String platformName, String src_test_dir, String tests_package,
            String casemanager_package, String loggingPropFile,
            String specificationPhase) throws Exception {
        if (specificationPhase == null || specificationPhase == "") {
            throw new BeastException(
                    "Specification phase property not found. Check it in Beast properties configuration file. It should be SYSTEM or MAS.");
        } else if (specificationPhase.equalsIgnoreCase(MAS)) {
            if (platformName == null || platformName == "") {
            throw new BeastException(
                    "MASPlatform property not found. Check it in Beast properties configuration file. It should be, for example: JADEX or JADE");
        }
        }
        if (specificationPhase.equalsIgnoreCase(MAS)) {
            MASReader.generateJavaFiles(requirementsFolder, platformName,
                    src_test_dir, tests_package, casemanager_package,
                    loggingPropFile);
        } else if (specificationPhase.equalsIgnoreCase(SYSTEM)) {
            SystemReader.generateJavaFiles(requirementsFolder, platformName,
                    src_test_dir, tests_package, casemanager_package,
                    loggingPropFile);
        } else {
            logger.severe("Cannot recognize specification phase (System or MAS) property.");
            throw new BeastException(
                    "Specification phase property unknown. Check it in properties configuration file. It should be SYSTEM or MAS.");
        }
    }

    /**
     * Method used to write the name of the scenarios
     * 
     * @param word
     * @return the same word starting with capital letter
     */
    public static String changeFirstLetterToCapital(String word) {
        char[] letras = word.toCharArray();
        char a = letras[0];
        letras[0] = Character.toUpperCase(a);
        return new String(letras);
    }

    /**
     * Method used to write the name of the scenarios methods
     * 
     * @param word
     * @return the same word starting with lower case
     */
    public static String changeFirstLetterToLowerCase(String word) {
        char[] letras = word.toCharArray();
        char a = letras[0];
        letras[0] = Character.toLowerCase(a);
        return new String(letras);
    }

    /**
     * Given a string with the scenario or story name, creates a Class Name with
     * no spaces and first letter capital
     * 
     * @param String
     *            - The name of the scenario/story. It should be in lower case.
     * @returns String - The class name
     */
    public static String createClassName(String scenarioDescription) {
        String[] words = scenarioDescription.trim().split(" ");
        String name = "";
        for (int i = 0; i < words.length; i++) {
            name += changeFirstLetterToCapital(words[i]);
        }
        return name;
    }

    /**
     * Given a string with method or package name, creates a Class Name with no
     * spaces and first letter lower case
     * 
     * @param String
     *            - The name of the scenario/story. It should be in lower case.
     * @returns String - The class name
     */
    public static String createFirstLowCaseName(String scenarioDescription) {
        String[] words = scenarioDescription.trim().split(" ");
        String name = "";
        for (int i = 0; i < words.length; i++) {
            name += changeFirstLetterToCapital(words[i]);
        }
        return changeFirstLetterToLowerCase(name);
    }

    /**
     * Method to know if already exists one file with the same name in the same
     * folder
     * 
     * @param scenario_name
     * @param path
     * @param dest_dir
     * @return true when the file does not exist
     */
    protected static boolean fileDoesNotExist(String file, String path,
            String dest_dir) {

        File f = new File(dest_dir);
        if (!f.isDirectory())
            return false;

        String folderPath = createFolderPath(path);

        f = new File(f, folderPath);

        File javaFile = new File(f, file);
        boolean result = !javaFile.exists();

        return result;
    }

    /**
     * This method returns the existing folder, and if it does not exist, the
     * method generates it.
     * 
     * @param path
     * @param dest_dir
     * @return the folder
     * @throws BeastException
     */
    public static File createFolder(String path, String dest_dir)
            throws BeastException {
        File f = new File(dest_dir);
        if (!f.isDirectory()) {
            try {
                f.mkdirs();
            } catch (Exception e) {
                logger.severe("Problem creating directory: " + path
                        + File.separator + dest_dir);
            }
        }

        String folderPath = createFolderPath(path);

        f = new File(f, folderPath);
        if (!f.isDirectory()) {
            try {
                f.mkdirs();
            } catch (Exception e) {
                String message = "Problem creating directory: " + path
                        + File.separator + dest_dir;
                logger.severe(message);
                throw new BeastException(message, e);
            }
        }

        return f;
    }

    /**
     * This method changes package_path into folder's path
     * 
     * @param path
     *            , as es.upm.gsi
     * @return the new path, es/upm/gsi
     */
    public static String createFolderPath(String path) {

        String[] pathParts = path.split("\\.");
        String path2 = "";
        for (String part : pathParts) {
            if (path2.equals("")) {
                path2 = part;
            } else {
                path2 = path2 + File.separator
                        + changeFirstLetterToLowerCase(createClassName(part));
            }
        }

        return path2;
    }

    /**
     * This method will generate the paths that casemanager must include in its
     * code
     * 
     * @param aux_package_path
     *            the path of the .story files, similar than es.upm.dit.gsi...
     * @param scenarioName
     * @return the new path, similar than es.upm.dit.gsi...
     */
    protected static String createTestPath(String aux_package_path,
            String scenarioName) {

        return aux_package_path + "." + scenarioName;
    }

    /**
     * Method to read our client's plain text
     * 
     * @param file_name
     * @return the filereader to translate client's plain text into our files
     * @throws BeastException
     *             if any problem is found whit the file
     */
    protected static BufferedReader createFileReader(String file_name)
            throws BeastException {
        try {
            return new BufferedReader(new FileReader(file_name));
        } catch (FileNotFoundException e) {
            Logger logger = Logger.getLogger(MASReader.class.getName());
            logger.severe("ERROR: " + e.toString());
            throw new BeastException("ERROR: " + e.toString(), e);
        }
    }

    /**
     * Creates the .story file necessary for every Beast Test Case.
     * 
     * @param scenarioName
     *            - The name of the scenario, with spaces
     * @param srcTestRootFolder
     *            - The test root folder
     * @param packagePath
     *            - The package of the BeastTestCase
     * @param scenarioDescription
     *            - the scenario name
     * @param givenDescription
     *            - The given description
     * @param whenDescription
     *            - The when description
     * @param thenDescription
     *            - The then description
     * @throws BeastException
     */
    public static void createDotStoryFile(String scenarioName,
            String srcTestRootFolder, String packagePath,
            String givenDescription, String whenDescription,
            String thenDescription) throws BeastException {
        String[] folders = packagePath.split(".");
        for (String folder : folders) {
            srcTestRootFolder += "/" + folder;
        }

        FileWriter writer = createFileWriter(createDotStoryName(scenarioName),
                packagePath, srcTestRootFolder);
        try {
            writer.write("Scenario: " + scenarioName + "\n");
            writer.write("Given " + givenDescription + "\n");
            writer.write("When " + whenDescription + "\n");
            writer.write("Then " + thenDescription + "\n");
            writer.close();
        } catch (Exception e) {
            String message = "Unable to write the .story file for the scenario "
                    + scenarioName;
            logger.severe(message);
            logger.severe(e.getMessage());
            throw new BeastException(message, e);
        }
    }

    /**
     * Method to get the file writer required for the .story files
     * 
     * @param scenarioName
     * @param aux_package_path
     * @param dest_dir
     * @return The file writer that generates the .story files for each test
     * @throws BeastException
     */
    protected static FileWriter createFileWriter(String scenarioName,
            String aux_package_path, String dest_dir) throws BeastException {
        try {
            return new FileWriter(new File(createFolder(aux_package_path,
                    dest_dir), scenarioName + ".story"));
        } catch (IOException e) {
            String message = "ERROR writing the " + scenarioName
                    + ".story file: " + e.toString();
            logger.severe(message);
            throw new BeastException(message, e);
        }
    }

    /**
     * Creates the name of the .story file to be wrote with the testcase. The
     * name of the scenario must be given with spaces.
     * 
     * @param scenarioName
     *            - The scenario name, with spaces
     * @return the .story file name.
     */
    protected static String createDotStoryName(String scenarioName) {
        String[] words = scenarioName.trim().split(" ");
        String result = "";
        for (int i = 0; i < words.length; i++) {
            result += words[i].toLowerCase();
            // I don't add the '_' to the last word.
            if (!(i == words.length - 1))
                result += "_";
        }
        return result;
    }

    /**
     * Main method to start reading the plain text given by the client
     * 
     * @param args
     *            , where arg[0] is Beast configuration file (beast.properties)
     *            and arg[1] is Logger configuration file (logger.properties)
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getLogger("Reader.main");

        Properties beastConfigProperties = new Properties();
        String beastConfigPropertiesFile = null;
        if (args.length > 0) {
            beastConfigPropertiesFile = args[0];
            beastConfigProperties.load(new FileInputStream(
                    beastConfigPropertiesFile));
            logger.info("Properties file selected -> "
                    + beastConfigPropertiesFile);
        } else {
            logger.severe("No properties file found. Set the path of properties file as argument.");
            throw new BeastException(
                    "No properties file found. Set the path of properties file as argument.");
        }
        String loggerConfigPropertiesFile;
        if (args.length > 1) {
            Properties loggerConfigProperties = new Properties();
            loggerConfigPropertiesFile = args[1];
            try {
                FileInputStream loggerConfigFile = new FileInputStream(
                        loggerConfigPropertiesFile);
                loggerConfigProperties.load(loggerConfigFile);
                LogManager.getLogManager().readConfiguration(loggerConfigFile);
                logger.info("Logging properties configured successfully. Logger config file: "
                        + loggerConfigPropertiesFile);
            } catch (IOException ex) {
                logger.warning("WARNING: Could not open configuration file");
                logger.warning("WARNING: Logging not configured (console output only)");
            }
        } else {
            loggerConfigPropertiesFile = null;
        }

        Reader.generateJavaFiles(
                beastConfigProperties.getProperty("requirementsFolder"), "\""
                        + beastConfigProperties.getProperty("MASPlatform")
                        + "\"",
                beastConfigProperties.getProperty("srcTestRootFolder"),
                beastConfigProperties.getProperty("storiesPackage"),
                beastConfigProperties.getProperty("caseManagerPackage"),
                loggerConfigPropertiesFile,
                beastConfigProperties.getProperty("specificationPhase"));
    }
}
