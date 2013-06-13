package es.upm.dit.gsi.beast.reader.system;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import es.upm.dit.gsi.beast.reader.Reader;

public class SystemReaderTest {

    @Test
    public void MainSystemReaderTest() {
        this.cleanUp();
        try {
            SystemReader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/SystemReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties");
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "CaseManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "SystemStory.java").exists());
            this.cleanUp();
    }

    @Test
    public void MainReaderWithoutLogPropTest() {
        this.cleanUp();
        try {
            SystemReader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/SystemReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "es.upm.dit.gsi.beast.reader.system.test", null);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "CaseManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "SystemStory.java").exists());

        this.cleanUp();
    }
    
    @Test
    public void MainReaderTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/SystemReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    Reader.SYSTEM);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "CaseManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "SystemStory.java").exists());
        this.cleanUp();
    }

    private void cleanUp() {
        this.deleteDirectory(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test"));
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
