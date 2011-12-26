package es.upm.dit.gsi.beast.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.thoughtworks.xstream.XStream;

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

    private static Logger logger = Logger.getLogger(Reader.class.getName());
    private String scenarioJavaName = null;
    private String scenarioStoryName = null;
    private String givenDescription = null;
    private String whenDescription = null;
    private String thenDescription = null;
    private String aux_package_path = null;
    private String testCase_package_path = null;
    private FileWriter story_file_writer = null;

    /**
     * 
     * Constructor that calls to the main method of the class to create all java
     * classes.
     * 
     * @param ScenariosList
     *            , it the plain text given by the client
     * @param dest_dir
     *            , the folder where our classes are created
     * @param tests_package_path
     *            , the name of the package
     * @param casemanager_path
     *            , the path where casemanager must be created
     * @param loggingPropFile
     *            , properties file
     */
    public Reader(String ScenariosList, String platformName, String dest_dir,
            String tests_package_path, String casemanager_path, String loggingPropFile) {
        this.generateJavaFiles(ScenariosList, platformName, dest_dir,
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
    private void generateJavaFiles(String ScenariosList, String platformName,
            String dest_dir, String tests_package_path, String casemanager_path, String loggingPropFile) {

        BufferedReader fileReader = createFileReader(ScenariosList);
        if (fileReader == null) {
            logger.severe("ERROR Reading the file " + ScenariosList);
        } else {
            // start generation of CaseManager
            File caseManager = CreateCaseManager.startCaseManager(
                    casemanager_path, dest_dir);
//FIXME no meter el nombre del escenario en los Scenario, Setup y Evaluation porque se ven muchas letras y no te enteras, con que este en el nombre del paquete vale...
            //Meterlos en un paquete "testCase"
            try {
                String nextLine = null;
                while ((nextLine = fileReader.readLine()) != null) {

                    StringTokenizer line_words = new StringTokenizer(nextLine);
                    if (line_words.hasMoreTokens()) {
                        String next = line_words.nextToken();

                        if (next.matches("Scenario:")) {
                            scenarioJavaName = line_words.nextToken();
                            scenarioStoryName = scenarioJavaName.toLowerCase();

                            scenarioJavaName = changeFirstLetterToCapital(scenarioJavaName);

                            while (line_words.hasMoreTokens()) {
                                String newWord = line_words.nextToken();
                                scenarioJavaName = scenarioJavaName
                                        + changeFirstLetterToCapital(newWord
                                                .toLowerCase());
                                scenarioStoryName = scenarioStoryName + "_"
                                        + newWord.toLowerCase();
                            }
                            aux_package_path = tests_package_path + "."
                                    + scenarioJavaName;
                            Reader.createFolder(aux_package_path, dest_dir);
                            this.testCase_package_path = aux_package_path + ".test";
                            Reader.createFolder(testCase_package_path, dest_dir);
                            if (fileDoesNotExist(scenarioJavaName + ".java",
                                    aux_package_path, dest_dir)) {
                                // Writes StoryExample.java
                                CreateStory.createStory(scenarioJavaName,
                                        platformName, aux_package_path,
                                        dest_dir, loggingPropFile);
                            }
                            // Writes story_example.story
                            story_file_writer = createFileWriter(
                                    scenarioStoryName, aux_package_path,
                                    dest_dir);
                            story_file_writer.write(nextLine + "\n");

                        } else if (next.matches("Given")) {
                            givenDescription = line_words.nextToken();
                            while (line_words.hasMoreTokens()) {
                                givenDescription = givenDescription + " "
                                        + line_words.nextToken();
                            }
                            if (fileDoesNotExist("Scenario.java", testCase_package_path, dest_dir)) {
                                CreateScenario.createScenario(scenarioJavaName,
                                        testCase_package_path, givenDescription,
                                        dest_dir);
                            }
                            story_file_writer.write(nextLine + "\n");
                            writeClassDatabase(givenDescription,
                                    testCase_package_path + ".Scenario");

                        } else if (next.matches("When")) {
                            whenDescription = line_words.nextToken();
                            while (line_words.hasMoreTokens()) {
                                whenDescription = whenDescription + " "
                                        + line_words.nextToken();
                            }
                            if (fileDoesNotExist("Setup.java", testCase_package_path, dest_dir)) {
                                CreateSetup.createSetup(scenarioJavaName,
                                        testCase_package_path, whenDescription,
                                        dest_dir);
                            }
                            story_file_writer.write(nextLine + "\n");
                            writeClassDatabase(whenDescription,
                                    testCase_package_path + ".Setup");

                        } else if (next.matches("Then")) {
                            thenDescription = line_words.nextToken();
                            while (line_words.hasMoreTokens()) {
                                thenDescription = thenDescription + " "
                                        + line_words.nextToken();
                            }
                            if (fileDoesNotExist("Evaluation.java",
                                    testCase_package_path, dest_dir)) {
                                CreateEvaluation.createEvaluation(
                                        scenarioJavaName, testCase_package_path,
                                        thenDescription, dest_dir);
                            }
                            story_file_writer.write(nextLine + "\n");
                            writeClassDatabase(thenDescription,
                                    testCase_package_path + ".Evaluation");
                            
                            story_file_writer.flush();
                            story_file_writer.close();
                            String test_path = createTestPath(aux_package_path,
                                    scenarioJavaName);
                            CreateCaseManager.createTest(caseManager,
                                    scenarioJavaName, test_path,
                                    scenarioJavaName, givenDescription,
                                    whenDescription, thenDescription);

                        } else {
                            logger.severe("ERROR: The test writen in the plain text can not be handed");
                            logger.severe("Try again whit the following key-words: {Scenario:, Given, When, Then}");
                        }
                    }
                }
            } catch (IOException e) {
                logger.severe("ERROR in Reader class");
            }
            CreateCaseManager.closeCaseManager(caseManager);
            logger.info("Plain text has been read and test files are ready");

        }
    }

    /**
     * Method used to write the name of the scenarios
     * 
     * @param word
     * @return the same word starting with capital letter
     */
    private String changeFirstLetterToCapital(String word) {
        char[] letras = word.toCharArray();
        char a = letras[0];
        letras[0] = Character.toUpperCase(a);
        return new String(letras);
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
    private boolean fileDoesNotExist(String file, String path, String dest_dir) {

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
    }

    /**
     * Method that generates the ClassDatabase which Story.java will use to
     * translate plain text into java classes.
     * 
     * @param key
     *            , the plain text given by the client
     * @param value
     *            , the java class to generate key's step
     */
    @SuppressWarnings("unchecked")
    private void writeClassDatabase(String key, String value) {
        XStream xstream = new XStream();
        try {
            File f = new File("ClassDatabase.xml");
            //TODO review this delete because the size of this file could be really large
//            f.delete();
            if (!f.exists()) {
                try {
                    FileWriter w = new FileWriter(f);
                    w.write("<map>");
                    w.write("</map>");
                    w.flush();
                    w.close();
                    logger.fine("ClassDatabase.xml created.");
                } catch (IOException e) {
                    logger.severe("ClassDatabase.xml could not be created.");
                    e.printStackTrace();
                }
            }
            HashMap<String, String> hm = (HashMap<String, String>) xstream
                    .fromXML(new FileInputStream("ClassDatabase.xml"));
            if (!hm.containsKey(key)) {
                hm.put(key, value);
            } else {
                hm.remove(key);
                hm.put(key, value);
            }
            xstream.toXML(hm, new FileOutputStream("ClassDatabase.xml", false));

        } catch (FileNotFoundException e) {
            Logger logger = Logger.getLogger(this.getClass().toString());
            logger.info("ERROR: File ClassDatabase.xml can not be found");
        }
    }

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
                path2 = path2 + File.separator + part;
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
    private String createTestPath(String aux_package_path, String scenarioName) {

        return aux_package_path + "." + scenarioName;
    }

    /**
     * Method to read our client's plain text
     * 
     * @param file_name
     * @return the filereader to translate client's plain text into our files
     */
    private BufferedReader createFileReader(String file_name) {
        try {
            return new BufferedReader(new FileReader(file_name));
        } catch (FileNotFoundException e) {
            Logger logger = Logger.getLogger(Reader.class.getName());
            logger.severe("ERROR: " + e.toString());
            return null;
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
    private FileWriter createFileWriter(String scenarioName,
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

                new Reader(properties.getProperty("scenarioListPath"), "\""
                        + properties.getProperty("platform") + "\"",
                        properties.getProperty("mainDirectory"),
                        properties.getProperty("testPath"),
                        properties.getProperty("caseManagerPath"),args[1]);
            } catch (IOException ex) {
                logger.severe("WARNING: Could not open configuration file");
                logger.severe("WARNING: Logging not configured (console output only)");
            }
        } else {
            new Reader(properties.getProperty("scenarioListPath"), "\""
                    + properties.getProperty("platform") + "\"",
                    properties.getProperty("mainDirectory"),
                    properties.getProperty("testPath"),
                    properties.getProperty("caseManagerPath"),null);
            logger.warning("No logging properties file found. Set the path of properties file as argument.");
        }

    }
}
