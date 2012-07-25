package es.upm.dit.gsi.beast.reader;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

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
                    "src/test/java/es/upm/dit/gsi/beast/reader/log.properties");
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "CaseManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "SetBelievesInAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
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
                    "es.upm.dit.gsi.beast.reader.test", null);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "CaseManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
                "SetBelievesInAgent.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test",
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
