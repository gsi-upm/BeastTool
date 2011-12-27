package es.upm.dit.gsi.beast.reader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

public class ReaderTest {

    @Test
    public void MainReaderTest() {
        this.cleanUp();
        try {
            new Reader(
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
                "src/test/java/es/upm/dit/gsi/beast/reader/test/ThisIsOtherScenario",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/SetBelievesInAgent/phases",
                "Scenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/SetBelievesInAgent/phases",
                "Setup.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/GetBelievesFromAgent/phases",
                "Evaluation.java").exists());

        this.cleanUp();
    }

    @Test
    public void MainReaderWithoutLogPropTest() {
        this.cleanUp();
        try {
            new Reader(
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
                "src/test/java/es/upm/dit/gsi/beast/reader/test/ThisIsOtherScenario",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/SetBelievesInAgent/phases",
                "Scenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/SetBelievesInAgent/phases",
                "Setup.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/GetBelievesFromAgent/phases",
                "Evaluation.java").exists());

        this.cleanUp();
    }
    


    @Test
    public void MainReaderWithoutClassDatabaseTest() {
        this.cleanUp();
        
        File inputFile = new File("ClassDatabase.xml");
        File outputFile = new File("RealClassDatabase.xml");

        FileReader in = null;
        FileWriter out = null;
        try {
            in = new FileReader(inputFile);
            out = new FileWriter(outputFile);

            int c;

            while ((c = in.read()) != -1)
              out.write(c);

            in.close();
            out.close();
        } catch (IOException e1) {
            if (outputFile.exists()) {
                outputFile.delete();
            }
            Assert.fail();
        }
        if (inputFile.exists()) {
            inputFile.delete();
        }
        
        try {
            new Reader(
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
                "src/test/java/es/upm/dit/gsi/beast/reader/test/ThisIsOtherScenario",
                "ThisIsOtherScenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/SetBelievesInAgent/phases",
                "Scenario.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/SetBelievesInAgent/phases",
                "Setup.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/test/GetBelievesFromAgent/phases",
                "Evaluation.java").exists());

        this.cleanUp();
        inputFile = new File("RealClassDatabase.xml");
        outputFile = new File("ClassDatabase.xml");

        in = null;
        out = null;
        try {
            in = new FileReader(inputFile);
            out = new FileWriter(outputFile);

            int c;

            while ((c = in.read()) != -1)
              out.write(c);

            in.close();
            out.close();
        } catch (IOException e1) {
            if (outputFile.exists()) {
                outputFile.delete();
            }
            Assert.fail();
        }
        if (inputFile.exists()) {
            inputFile.delete();
        }
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
