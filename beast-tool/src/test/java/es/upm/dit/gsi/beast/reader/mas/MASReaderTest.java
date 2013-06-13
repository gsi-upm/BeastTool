package es.upm.dit.gsi.beast.reader.mas;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import es.upm.dit.gsi.beast.reader.Reader;

public class MASReaderTest {

    @Test
    public void MainReaderTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/mas/ReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.mas.test",
                    "es.upm.dit.gsi.beast.reader.mas.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/mas/log.properties");
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test",
                "CaseManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test",
                "TestStory.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test/testStory",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test/testStory",
                "SetBelievesInAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test/testStory",
                "GetBelievesFromAgent.java").exists());

        this.cleanUp();
    }
    
    @Test
    public void MainReaderTestWithType() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/mas/ReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.mas.test",
                    "es.upm.dit.gsi.beast.reader.mas.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/mas/log.properties",
                    Reader.MAS);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test",
                "CaseManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test",
                "TestStory.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test/testStory",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test/testStory",
                "SetBelievesInAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test/testStory",
                "GetBelievesFromAgent.java").exists());

        this.cleanUp();
    }

    @Test
    public void MainReaderWithoutLogPropTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/mas/ReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.mas.test",
                    "es.upm.dit.gsi.beast.reader.mas.test", null);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test",
                "CaseManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test",
                "TestStory.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test/testStory",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test/testStory",
                "SetBelievesInAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test/testStory",
                "GetBelievesFromAgent.java").exists());

        this.cleanUp();
    }

    private void cleanUp() {
        this.deleteDirectory(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/mas/test"));
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
