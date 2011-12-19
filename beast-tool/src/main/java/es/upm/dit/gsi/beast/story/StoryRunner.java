package es.upm.dit.gsi.beast.story;

import static java.util.Arrays.asList;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.steps.SilentStepMonitor;

/**
 * Class to run tests using JBehave
 * 
 * @author Jorge Solitario
 */
public class StoryRunner extends Embedder {

	/**
	 * Internal method of JBehave
	 */
	public StoryRunner() {
		this.embedderControls().doGenerateViewAfterStories(false)
				.doIgnoreFailureInStories(true).doIgnoreFailureInView(true);
	}

	/**
	 * Internal method of JBehave
	 */
	@Override
	public Configuration configuration() {

		Class<?> embedderClass = this.getClass();
		Configuration configuration = new MostUsefulConfiguration();
		configuration.useStoryLoader(new LoadFromClasspath(embedderClass));
		configuration.useStepMonitor(new SilentStepMonitor());
		return configuration;
	}

	/**
	 * Internal method of JBehave
	 * 
	 * @param className
	 *            the name of the class, inside its path: es/upm/...
	 */
	public static void executeStory(String className) {
		Embedder embedder = new StoryRunner();
		embedder.runAsEmbeddables(asList(className));
	}
}
