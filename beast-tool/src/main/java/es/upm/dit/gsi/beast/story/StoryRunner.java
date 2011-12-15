package es.upm.dit.gsi.beast.story;

import static java.util.Arrays.asList;

import java.net.URL;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.SilentStepMonitor;

/**
 * Class to run tests using JBehave
 * 
 * @author Jorge Solitario
 */
public class StoryRunner extends Embedder {

	public StoryRunner() {
		this.embedderControls().doGenerateViewAfterStories(false)
				.doIgnoreFailureInStories(true).doIgnoreFailureInView(true);
	}

	@Override
	public Configuration configuration() {

		Class<?> embedderClass = this.getClass();
		URL codeLocation = CodeLocations.codeLocationFromClass(embedderClass);

		Configuration configuration = new MostUsefulConfiguration();
		configuration.useStoryLoader(new LoadFromClasspath(embedderClass));

		StoryReporterBuilder builder = new StoryReporterBuilder();
		builder.withCodeLocation(codeLocation);
		builder.withDefaultFormats();
		builder.withFormats(Format.CONSOLE);

		configuration.useStoryReporterBuilder(builder);

		configuration.useStepMonitor(new SilentStepMonitor());
		return configuration;
	}

	/**
	 * 
	 * @param className
	 */
	public static void executeStory(String className) {
		Embedder embedder = new StoryRunner();
		embedder.runAsEmbeddables(asList(className));
	}
}
