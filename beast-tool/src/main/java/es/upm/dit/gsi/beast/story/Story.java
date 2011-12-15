package es.upm.dit.gsi.beast.story;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

import com.thoughtworks.xstream.XStream;

import es.upm.dit.gsi.beast.story.testCase.Evaluation;
import es.upm.dit.gsi.beast.story.testCase.Scenario;
import es.upm.dit.gsi.beast.story.testCase.Setup;

/**
 * Main class to translate plain text into code, following the Given-When-Then
 * language In the GIVEN part it launches the platform In the WHEN part it
 * configures the state of its agents In the THEN part it checks the correct
 * behaviour The main purpose of it consists of knowing agents' state/properties
 * without changing its code.
 * 
 * @author Jorge Solitario
 */
public abstract class Story extends JUnitStory {

	Logger logger = Logger.getLogger(this.getClass().toString());
	Scenario scenario;
	Setup setup;
	Evaluation evaluation;
	String platform;

	/**
	 * This method creates the scenario, which is the GIVEN part
	 * 
	 * @param scenarioName
	 */
	public void createScenario(String scenarioName, String platform) {
		String path = getPath(scenarioName);
		ClassLoader loader = ClassLoader.getSystemClassLoader();

		try {
			Class<?> c = loader.loadClass(path);
			scenario = (Scenario) c.newInstance();
		} catch (ClassNotFoundException e) {
			logger.severe("Error loading " + path);
			Assert.fail();
		} catch (InstantiationException e) {
			logger.severe("Error InstantiationException " + e);
			Assert.fail();
		} catch (IllegalAccessException e) {
			logger.severe("Error IllegalAccessException " + e);
			Assert.fail();
		}

		logger.info("The platform is almost started...");

		scenario.startPlatform(platform);
	}

	/**
	 * This method launches the setup, related with the WHEN part
	 * 
	 * @param setupName
	 */
	public void setup(String setupName) {

		String path = getPath(setupName);

		ClassLoader loader = ClassLoader.getSystemClassLoader();
		try {
			Thread.sleep(1000);
			Class<?> c = loader.loadClass(path);
			setup = (Setup) c.newInstance();
		} catch (Exception e) {
			logger.severe("Error loading the setup " + path);
			Assert.fail();
		}
		setup.setScenario(scenario);
	}

	/**
	 * This method checks the THEN part
	 * 
	 * @param evaluationName
	 */
	public void executeEvaluation(String evaluationName) {
		String path = getPath(evaluationName);

		ClassLoader loader = ClassLoader.getSystemClassLoader();
		try {
			Thread.sleep(1000);
			Class<?> c = loader.loadClass(path);
			evaluation = (Evaluation) c.newInstance();
		} catch (Exception e) {
			logger.severe("Error loading the evaluation " + path);
			Assert.fail();
		}
		evaluation.setSetup(setup);
	}

	/**
	 * It assigns one direction to each Scenario, Setup and Evaluation given by
	 * the client in the plain text. This information is saved in
	 * Classdatabase.xml, which is typically located in the root of our project.
	 * 
	 * @param stepName
	 *            , the plain text written by the client.
	 * @return the path where the step is saved
	 */
	@SuppressWarnings("unchecked")
	private String getPath(String stepName) {
		XStream xstream = new XStream();
		String answer = null;
		try {
			HashMap<String, String> hm = (HashMap<String, String>) xstream
					.fromXML(new FileInputStream("ClassDatabase.xml"));
			answer = (String) hm.get(stepName);
		} catch (FileNotFoundException e) {
			logger.info("Error loading from ClassDatabase.xml");
			;
		}
		return answer;
	}

	@Override
	/**
	 * Internal method for jBehave
	 */
	public List<CandidateSteps> candidateSteps() {
		return new InstanceStepsFactory(configuration(), this)
				.createCandidateSteps();
	}
}
