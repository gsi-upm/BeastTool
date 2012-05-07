package es.upm.dit.gsi.beast.story;

import static java.util.Arrays.asList;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.steps.SilentStepMonitor;

/**
 * Class to run tests using JBehave.
 *
 * @author Jorge Solitario
 */
public class BeastTestCaseRunner extends Embedder {

    /**
     * Internal method of JBehave.
     */
    public BeastTestCaseRunner() {
        this.embedderControls().doGenerateViewAfterStories(false)
                .doIgnoreFailureInStories(false).doIgnoreFailureInView(false);
    }

    /**
     * Internal method of JBehave.
     *
     * @return Configuration of this StoryRunner
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
     * Internal method of JBehave.
     *
     * @param className
     *            the name of the class, inside its path: es/upm/...
     */
    public static void executeBeastTestCase(String className) {
        Embedder embedder = new BeastTestCaseRunner();
        embedder.runAsEmbeddables(asList(className));
    }
}
