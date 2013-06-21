package es.upm.dit.gsi.beast.story;

import static java.util.Arrays.asList;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.steps.SilentStepMonitor;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.story.BeastTestCaseRunner.java
 * 
 * Class to run tests using JBehave.
 *
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * s@author Jorge Solitario
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @date 21/06/2013
 * @version 0.1
 * 
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
