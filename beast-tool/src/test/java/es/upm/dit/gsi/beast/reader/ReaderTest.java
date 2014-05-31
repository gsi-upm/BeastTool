package es.upm.dit.gsi.beast.reader;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Project: beast File: es.upm.dit.gsi.beast.reader.ReaderTest.java
 * 
 * Grupo de Sistemas Inteligentes Departamento de Ingeniería de Sistemas
 * Telemáticos Universidad Politécnica de Madrid (UPM)
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @version 0.1
 * 
 */
public class ReaderTest {

    @Test
    public void MainReaderTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/ReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.test",
                    "es.upm.dit.gsi.beast.reader.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/log.properties",
                    "MAS");
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "AgentStoriesManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "MyTestStory.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/myTestStory",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/myTestStory",
                "SetBelievesInAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/myTestStory",
                "GetBelievesFromAgent.java").exists());

        this.cleanUp();
    }

    @Test
    public void MainReaderWithoutLogPropTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/ReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.test",
                    "es.upm.dit.gsi.beast.reader.test", null, "MAS");
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "AgentStoriesManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "MyTestStory.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/myTestStory",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/myTestStory",
                "SetBelievesInAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/myTestStory",
                "GetBelievesFromAgent.java").exists());

        this.cleanUp();
    }

    @Test
    public void NonSpecificationPhase1ReaderTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/ReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.test",
                    "es.upm.dit.gsi.beast.reader.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/log.properties",
                    null);
        } catch (Exception e) {
            Assert.assertTrue(e != null);
        }
        this.cleanUp();
    }

    @Test
    public void NonSpecificationPhase2ReaderTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/ReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.test",
                    "es.upm.dit.gsi.beast.reader.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/log.properties",
                    "");
        } catch (Exception e) {
            Assert.assertTrue(e != null);
        }
        this.cleanUp();
    }

    @Test
    public void NonSpecificationPhase3ReaderTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/ReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.test",
                    "es.upm.dit.gsi.beast.reader.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/log.properties",
                    "Frasklin");
        } catch (Exception e) {
            Assert.assertTrue(e != null);
        }
        this.cleanUp();
    }

    @Test
    public void RequirementsPathOneStoryMASReaderTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader", "\"jade\"",
                    "src/test/java", "es.upm.dit.gsi.beast.reader.test",
                    "es.upm.dit.gsi.beast.reader.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/log.properties",
                    "MAS");
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "AgentStoriesManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "MyTestStory.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/myTestStory",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/myTestStory",
                "SetBelievesInAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/myTestStory",
                "GetBelievesFromAgent.java").exists());

        this.cleanUp();
    }

    @Test
    public void RequirementsPathOneStorySystemReaderTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader",
                    "\"jade\"",
                    "src/test/java",
                    "es.upm.dit.gsi.beast.reader.test",
                    "es.upm.dit.gsi.beast.reader.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    "SYSTEM");
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "UserStoriesManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "MyTestStory.java").exists());
        this.cleanUp();
    }

    @Test
    public void RequirementsPathMultipleStorySystemReaderTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/resources/ExampleStories/",
                    "\"jade\"",
                    "src/test/java",
                    "es.upm.dit.gsi.beast.reader.test",
                    "es.upm.dit.gsi.beast.reader.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    "SYSTEM");
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "UserStoriesManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test", "A1.java")
                .exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test", "A2.java")
                .exists());
        this.cleanUp();
    }

    @Test
    public void RequirementsPathMultipleStoryMASReaderTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/resources/ExampleStories/",
                    "\"jade\"",
                    "src/test/java",
                    "es.upm.dit.gsi.beast.reader.test",
                    "es.upm.dit.gsi.beast.reader.test.manager",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    "MAS");
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/manager",
                "AgentStoriesManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test", "A1.java")
                .exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/a1",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/a1",
                "this_is_other_scenario.story").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/a1",
                "SetBelievesInAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/a1",
                "set_believes_in_agent.story").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/a1",
                "GetBelievesFromAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/a1",
                "get_believes_from_agent.story").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test", "A2.java")
                .exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/a2",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/a2",
                "this_is_other_scenario.story").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/a2",
                "SetBelievesInAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/a2",
                "set_believes_in_agent.story").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/a2",
                "GetBelievesFromAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/a2",
                "get_believes_from_agent.story").exists());
        this.cleanUp();
    }

    @Test
    public void RequirementsPathMultipleStoriesWithSubfolderSystemReaderTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/resources/",
                    "\"jade\"",
                    "src/test/java",
                    "es.upm.dit.gsi.beast.reader.test",
                    "es.upm.dit.gsi.beast.reader.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    "SYSTEM");
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "UserStoriesManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/exampleStories",
                "A1.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/exampleStories",
                "A2.java").exists());
        this.cleanUp();
        this.cleanUp();
    }

    @Test
    public void RequirementsPathMultipleStoriesWithSubfolderMASReaderTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/resources/",
                    "\"jade\"",
                    "src/test/java",
                    "es.upm.dit.gsi.beast.reader.test",
                    "es.upm.dit.gsi.beast.reader.test.manager",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    "MAS");
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/manager",
                "AgentStoriesManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/exampleStories",
                "A1.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/exampleStories/a1",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/exampleStories/a1",
                "SetBelievesInAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/exampleStories/a1",
                "GetBelievesFromAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/exampleStories",
                "A2.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/exampleStories/a2",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/exampleStories/a2",
                "SetBelievesInAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/exampleStories/a2",
                "GetBelievesFromAgent.java").exists());
        this.cleanUp();
    }

    private void cleanUp() {
        this.deleteDirectory(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test"));
    }

    private boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }
}
