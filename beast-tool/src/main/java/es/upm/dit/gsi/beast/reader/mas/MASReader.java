package es.upm.dit.gsi.beast.reader.mas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import es.upm.dit.gsi.beast.reader.Reader;
//import java.util.StringTokenizer;

// FIXME: Update javadoc

/**
 * Main class to transform the plain text given by the designer to the necessary
 * classes to run each Test. These classes are the Scenario, Setup and
 * Evaluation, which emulate the GIVEN, WHEN and THEN parts of the plain text;
 * the .story file with the plain text of the test and the java class with
 * the same name to interpret it. Furthermore, one caseManager must be created,
 * which will run all the tests.
 * 
 * @author Alberto Mardomingo
 * @author Jorge Solitario
 */
public class MASReader extends Reader{

    private static Logger logger = Logger.getLogger(MASReader.class.getName());

    /**
     * Main method of the class, which handles the process of creating the tests
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
    public static void generateJavaFiles(String ScenariosList, String platformName,
            String dest_dir, String tests_package_path,
            String casemanager_path, String loggingPropFile) {

        HashMap<String, String[]>scenarios = new HashMap<String, String[]>();
        String storyName = null;
        String story_user = null;
        String user_feature = null;
        String user_benefit = null;
        BufferedReader fileReader = createFileReader(ScenariosList);
        if (fileReader == null) {
            logger.severe("ERROR Reading the file " + ScenariosList);
        } else {// if(fileDoesNotExist("CaseManager.java", casemanager_path, dest_dir)) {
            
            //Starting with the CaseManager
            File caseManager = CreateMASCaseManager.startMASCaseManager(
                  casemanager_path, dest_dir);
            try {
                String nextLine = null;
                while((nextLine = fileReader.readLine()) != null){
                    
                    if (nextLine.startsWith("Story -")) {
                        storyName = nextLine.replaceFirst("Story -", "").trim();
                    } else if (nextLine.startsWith("As a")) {
                        story_user = nextLine.replaceFirst("As a", "").trim();
                    } else if (nextLine.startsWith("I want to")) {
                        user_feature = nextLine.replaceFirst("I want to", "").trim();
                    } else if (nextLine.startsWith("So that")) {
                        user_benefit = nextLine.replaceFirst("So that", "").trim();
                    } else if (nextLine.startsWith("Scenario:")) {
                        
                        // I am assuming that the file is properly formated
                        // TODO: Check that it actually is properly formated.
                        
                        String scenarioID = nextLine.replaceFirst("Scenario:", "").toLowerCase();
                        while (!fileReader.ready()) {
                            Thread.yield();
                        }
                        nextLine = fileReader.readLine();
                        String givenDescription = nextLine.replaceFirst("Given", "").trim();

                        while (!fileReader.ready()) {
                            Thread.yield();
                        }
                        nextLine = fileReader.readLine();
                        String whenDescription = nextLine.replaceFirst("When", "").trim();
                        
                        while (!fileReader.ready()) {
                            Thread.yield();
                        }
                        nextLine = fileReader.readLine();
                        String thenDescription = nextLine.replaceFirst("Then", "").trim();
                        
                        String[] scenarioData = new String[3];
                        scenarioData[0] = givenDescription;
                        scenarioData[1] = whenDescription;
                        scenarioData[2] = thenDescription;
                        
                        scenarios.put(scenarioID, scenarioData);
                    } else if (!nextLine.trim().isEmpty()){
                        // Is not an empty line, but has not been recognized.
                        logger.severe("ERROR: The test writen in the plain text can not be handed");
                        logger.severe("Try again whit the following key-words: {Story -," +
                        		" As a, I want to, So that, Scenario:, Given, When, Then}");
                    } // The only possibility here is to get an empty line,
                      // so I don't have to do anything.
                }

                fileReader.close();
                
                // Now, I should have all the variables set.
                
                if (storyName != null ) {
                    // I have a story, so...
                    if (fileDoesNotExist(createClassName(storyName) +
                            ".java", tests_package_path , dest_dir)) {
                        CreateMASTestStory.createMASTestStory(storyName, platformName,
                            tests_package_path, dest_dir, loggingPropFile, story_user,
                            user_feature, user_benefit, scenarios);
                    }
                    
                    // I create the testCases.
                    for(String entry : scenarios.keySet()) {
                        if (fileDoesNotExist(createClassName(entry + ".java"),
                                tests_package_path + "." + createFirstLowCaseName(storyName), dest_dir)) {
                        CreateMASTestCase.createBeastTestCase(createClassName(entry), platformName,
                                tests_package_path + "." + createFirstLowCaseName(storyName), dest_dir, loggingPropFile,
                                scenarios.get(entry)[0], scenarios.get(entry)[1],
                                scenarios.get(entry)[2]);
                        createDotStoryFile(entry, tests_package_path + "." + 
                                createFirstLowCaseName(createClassName(storyName)),
                                scenarios.get(entry)[0], scenarios.get(entry)[1],
                                scenarios.get(entry)[2]);
                        }
                    }
                    
                    CreateMASCaseManager.addStory(caseManager, storyName,
                            tests_package_path,
                            story_user, user_feature, user_benefit);
                } else {
                    // There is no storyName, so I just create the scenarios, no story.
                    for(String scenarioID : scenarios.keySet()){
                        if (fileDoesNotExist(createClassName(scenarioID + ".java"),
                                tests_package_path, dest_dir)) {
                        CreateMASTestCase.createBeastTestCase(createClassName(scenarioID), platformName,
                                tests_package_path, dest_dir, loggingPropFile,
                                scenarios.get(scenarioID)[0], scenarios.get(scenarioID)[1],
                                scenarios.get(scenarioID)[2]);
                        createDotStoryFile(scenarioID, tests_package_path, scenarios.get(scenarioID)[0], scenarios.get(scenarioID)[1],
                                scenarios.get(scenarioID)[2]);
                        CreateMASCaseManager.createTest(caseManager, createClassName(scenarioID),
                                createTestPath(tests_package_path, createClassName(scenarioID)),
                                changeFirstLetterToLowerCase(scenarioID), scenarios.get(scenarioID)[0],
                                scenarios.get(scenarioID)[1], scenarios.get(scenarioID)[2]);
                        // Simple, uh?
                        }
                    }
                }
                
                CreateMASCaseManager.closeMASCaseManager(caseManager);
                
            } catch (Exception e) {
                logger.severe("ERROR: ");
                e.printStackTrace();
            }
        /*} else {
            // There already is a CaseManager
            logger.info("CaseManager.java found in " + casemanager_path);*/
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

                if (properties.containsKey("type")) {
                    MASReader.generateJavaFiles(properties.getProperty("scenarioListPath"), "\""
                            + properties.getProperty("platform") + "\"",
                            properties.getProperty("mainDirectory"),
                            properties.getProperty("testPath"),
                            properties.getProperty("caseManagerPath"), args[1],
                            properties.getProperty("type"));
                } else {
                    MASReader.generateJavaFiles(properties.getProperty("scenarioListPath"), "\""
                            + properties.getProperty("platform") + "\"",
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
                MASReader.generateJavaFiles(properties.getProperty("scenarioListPath"), "\""
                        + properties.getProperty("platform") + "\"",
                        properties.getProperty("mainDirectory"),
                        properties.getProperty("testPath"),
                        properties.getProperty("caseManagerPath"), null,
                        properties.getProperty("type"));
            } else {
                MASReader.generateJavaFiles(properties.getProperty("scenarioListPath"), "\""
                        + properties.getProperty("platform") + "\"",
                        properties.getProperty("mainDirectory"),
                        properties.getProperty("testPath"),
                        properties.getProperty("caseManagerPath"), null);
            }
            logger.warning("No logging properties file found. Set the path of properties file as argument.");
        }

    }
}