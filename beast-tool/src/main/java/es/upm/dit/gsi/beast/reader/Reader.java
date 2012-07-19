package es.upm.dit.gsi.beast.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import es.upm.dit.gsi.beast.reader.mas.MASReader;
import es.upm.dit.gsi.beast.reader.system.SystemReader;

/**
 * Main class to transform the plain text given by the client to the necessary
 * classes to run each Test. These classes are the Scenario, Setup and
 * Evaluation, which emulate the GIVEN, WHEN and THEN parts of the plain text;
 * the .story file whit the plain text of the test and the its .java class with
 * the same name to interpret it. Furthermore, one casemanager must be created,
 * which will run all the tests.
 * 
 * @author Jorge Solitario
 */
public class Reader {

    private static final String MAS = "MAS";
    private static final String SYSTEM = "SYSTEM";

    private static Logger logger = Logger.getLogger(Reader.class.getName());
    
    /**
     * Main method of the class, which handles all the process to create all
     * tests.
     * 
     * @param ScenariosList
     *            , it the plain text given by the client
     * @param platformName
     *            , to choose the platform
     * @param dest_dir
     *            , the folder where our classes are created
     * @param tests_package_path
     *            , the name of the package
     * @param casemanager_path
     *            , the path where casemanager must be created
     * @param loggingPropFile
     *            , properties file
     */
    public static void generateJavaFiles(String ScenariosList,
            String platformName, String dest_dir, String tests_package_path,
            String casemanager_path, String loggingPropFile) {
        // By default, I use a MAS Reader.
        MASReader.generateJavaFiles(ScenariosList, platformName, dest_dir,
                tests_package_path, casemanager_path, loggingPropFile);
    }

    /**
     * Main method of the class, which handles all the process to create all
     * tests.
     * 
     * @param ScenariosList
     *            , it the plain text given by the client
     * @param platformName
     *            , to choose the platform
     * @param dest_dir
     *            , the folder where our classes are created
     * @param tests_package_path
     *            , the name of the package
     * @param casemanager_path
     *            , the path where casemanager must be created
     * @param loggingPropFile
     *            , properties file
     */
    public static void generateJavaFiles(String ScenariosList,
            String platformName, String dest_dir, String tests_package_path,
            String casemanager_path, String loggingPropFile, String type) {
        if (type.equalsIgnoreCase(MAS)) {
            MASReader.generateJavaFiles(ScenariosList, platformName, dest_dir,
                    tests_package_path, casemanager_path, loggingPropFile);
        } else if (type.equalsIgnoreCase(SYSTEM)) {
            SystemReader.generateJavaFiles(ScenariosList, platformName,
                    dest_dir, tests_package_path, casemanager_path,
                    loggingPropFile);
        } else {
            logger.severe("Cannot recognize System or MAS");
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
        if (!f.isDirectory())
            return false;

        File javaFile = new File(f, file);
        boolean result = !javaFile.exists();

        return result;
    } // Excuse me, I'm gonna go cry in a corner calling for my mom.

    /**
     * This method returns the existing folder, and if it does not exist, the
     * method generates it.
     * 
     * @param path
     * @param dest_dir
     * @return the folder
     */
    public static File createFolder(String path, String dest_dir) {
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
                logger.severe("Problem creating directory: " + path
                        + File.separator + dest_dir);
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
     * @return the new path, similar than es/upm/dit/gsi...
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
     */
    protected static BufferedReader createFileReader(String file_name) {
        try {
            return new BufferedReader(new FileReader(file_name));
        } catch (FileNotFoundException e) {
            Logger logger = Logger.getLogger(MASReader.class.getName());
            logger.severe("ERROR: " + e.toString());
            return null;
        }
    }

    /**
     * Creates the .story file necessary for every Beast Test Case.
     * 
     * @param scenarioName - The name of the scenario, with spaces
     * @param packagePath - The package of the BeastTestCase
     * @param scenarioDescription - the scenario name
     * @param givenDescription - The given description
     * @param whenDescription - The when description
     * @param thenDescription - The then description
     */
    public static void createDotStoryFile(String scenarioName, String packagePath,
            String givenDescription, String whenDescription, String thenDescription) {
        String[] folders = packagePath.split(".");
        String destDir = "src/test/java";
        for (String folder : folders) {
            destDir += "/" + folder;
        }
        System.out.println("scenarioName: " + scenarioName);
        FileWriter writer = createFileWriter(createDotStoryName(scenarioName),
                packagePath, destDir);
        try {
            writer.write("Scenario: " + scenarioName + "\n");
            writer.write("Given " + givenDescription + "\n");
            writer.write("When " + whenDescription + "\n");
            writer.write("Then " + thenDescription + "\n");
            writer.close();
        } catch (Exception e) {
            logger.severe("Unable to write the .story file for the scenario " + scenarioName);
            logger.severe(e.getMessage());
        }
    }

    /**
     * Method to get the file writer required for the .story files
     * 
     * @param scenarioName
     * @param aux_package_path
     * @param dest_dir
     * @return The file writer that generates the .story files for each test
     */
    protected static FileWriter createFileWriter(String scenarioName,
            String aux_package_path, String dest_dir) {
        try {
            return new FileWriter(new File(createFolder(aux_package_path,
                    dest_dir), scenarioName + ".story"));
        } catch (IOException e) {
            logger.severe("ERROR writing the " + scenarioName + ".story file: "
                    + e.toString());
            return null;
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
     *            , where arg[0] is beast.properties and arg[1] is
     *            beastLog.properties
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getLogger("Reader.main");

        Properties properties = new Properties();
        String propertiesFile = null;
        if (args.length > 0) {
            propertiesFile = args[0];
            properties.load(new FileInputStream(propertiesFile));
            logger.info("Properties file selected -> " + propertiesFile);
        } else {
            logger.severe("No properties file found. Set the path of properties file as argument.");
        }
        if (args.length > 1) {
            Properties preferences = new Properties();
            try {
                FileInputStream configFile = new FileInputStream(args[1]);
                preferences.load(configFile);
                LogManager.getLogManager().readConfiguration(configFile);

                if (properties.containsKey("type")) {
                    Reader.generateJavaFiles(
                            properties.getProperty("scenarioListPath"),
                            "\"" + properties.getProperty("platform") + "\"",
                            properties.getProperty("mainDirectory"),
                            properties.getProperty("testPath"),
                            properties.getProperty("caseManagerPath"), args[1],
                            properties.getProperty("type"));
                } else {
                    Reader.generateJavaFiles(
                            properties.getProperty("scenarioListPath"),
                            "\"" + properties.getProperty("platform") + "\"",
                            properties.getProperty("mainDirectory"),
                            properties.getProperty("testPath"),
                            properties.getProperty("caseManagerPath"), args[1]);
                }
            } catch (IOException ex) {
                logger.severe("WARNING: Could not open configuration file");
                logger.severe("WARNING: Logging not configured (console output only)");
            }
        } else {
            if (properties.containsKey("type")) {
                Reader.generateJavaFiles(
                        properties.getProperty("scenarioListPath"), "\""
                                + properties.getProperty("platform") + "\"",
                        properties.getProperty("mainDirectory"),
                        properties.getProperty("testPath"),
                        properties.getProperty("caseManagerPath"), null,
                        properties.getProperty("type"));
            } else {
                Reader.generateJavaFiles(
                        properties.getProperty("scenarioListPath"), "\""
                                + properties.getProperty("platform") + "\"",
                        properties.getProperty("mainDirectory"),
                        properties.getProperty("testPath"),
                        properties.getProperty("caseManagerPath"), null);
            }
            logger.warning("No logging properties file found. Set the path of properties file as argument.");
        }

    }
}
