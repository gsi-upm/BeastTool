package es.upm.dit.gsi.beast.reader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Main class to create the java file that controls each Test.
 * First it will launch its SCENARIO, then it will perform the SETUP,
 * and FINALLY it will check the EVALUATION.
 * The code is the same for each TEST, but we must write one for each
 * .story due to our BDD Tool (JBehave).  
 * 
 * @author Jorge Solitario
 */
public class CreateStory {

	/**
	 * Method to create the java file that it's executed from caseManager.
	 * Its name comes from the Scenario that it's testing.
	 * Its behaviour is writen in the .story file allocated in the same folder,
	 * which is the plain text given by the client.
	 * 
	 * @param scenario_name the name of the Scenario
	 * @param package_path the package
	 * @param dest_dir the main folder main (typically src/main/java)
	 */
	public static void createStory(String scenario_name, String package_path, String dest_dir){

		File f = Reader.createFolder(package_path, dest_dir);

		File javaFile = new File(f, scenario_name+".java");

		try {
			FileWriter fw = new FileWriter(javaFile);
			fw.write("package "+package_path+";\n");
	        fw.write("\n");
	        fw.write("import org.jbehave.core.annotations.Given;\n");
	        fw.write("import org.jbehave.core.annotations.Then;\n");
	        fw.write("import org.jbehave.core.annotations.When;\n");
	        fw.write("import es.upm.dit.gsi.beast.story.Story;\n");  
	        fw.write("\n");
	        fw.write("/**\n");
	        fw.write(" * Main class to translate plain text into code, following the Given-When-Then\n");
	        fw.write(" * language. In the GIVEN part it launchs the platform In the WHEN part it\n");
	        fw.write(" * configures the state of its agents. In the THEN part it checks the correct\n");
	        fw.write(" * behaviour. The main purpose of it consists of knowing agents' state/properties\n");
	        fw.write(" * without changing its code.\n");
	        fw.write(" * \n");
	        fw.write(" * @author Jorge Solitario\n");
	        fw.write(" */\n");
	        fw.write("  public class "+scenario_name+" extends Story {\n");
	        fw.write("\n");
	        fw.write(" 	/**\n");
	        fw.write("	 * The GIVEN part\n");
	        fw.write("	 */\n");
	        fw.write("	@Given(\"$scenarioName\")\n");
	        fw.write("	public void createScenario(String scenarioName) {\n");
	        fw.write("\n");
	        fw.write("		super.createScenario(scenarioName);\n");
	        fw.write("	}\n");
	        fw.write("\n");
			fw.write("  /**\n");
			fw.write("   * The WHEN part\n");
	        fw.write("   */\n");
			fw.write("  @When(\"$setupName\")\n");
			fw.write("  public void configureScenario(String setupName) {\n");
			fw.write("\n");
			fw.write("  super.setup(setupName);\n");
			fw.write("  }\n");
			fw.write("\n");
			fw.write("  /**\n");
			fw.write("   * The THEN part, where the correct behaviour must be asserted \n");
			fw.write("   */\n");
			fw.write("  @Then(\"$evaluationName\")\n");
			fw.write("  public void checkScenario(String evaluationName) {\n");
			fw.write("\n");
			fw.write("    super.executeEvaluation(evaluationName);\n");
			fw.write("  }\n");
			fw.write("\n");
			fw.write("}\n");
			fw.write("\n");   
			fw.flush();
			fw.close();
			
		} catch (IOException e) {
			Logger logger = Logger.getLogger("CreateStory.java");
			logger.severe("ERROR: The file "+scenario_name+".java can not be writed");
		}
	}
}


