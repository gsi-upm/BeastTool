package es.upm.dit.gsi.beast.reader.mas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import junit.framework.Assert;

import org.junit.Test;

import es.upm.dit.gsi.beast.reader.Reader;
import es.upm.dit.gsi.beast.reader.system.SystemReader;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.reader.mas.MASReaderTest.java
 * 
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @version 0.1
 * 
 */
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
                    "src/test/java/es/upm/dit/gsi/beast/reader/mas/log.properties","MAS");
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
                    "es.upm.dit.gsi.beast.reader.mas.test", null, "MAS");
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
    public void CaseManagerDuplicatedMethodsTest() {
        this.cleanUp();
        boolean catched = false;
        String message="";
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/mas/ReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.mas.test",
                    "es.upm.dit.gsi.beast.reader.mas.test", null, "MAS");
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/mas/ReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.mas.test.algo",
                    "es.upm.dit.gsi.beast.reader.mas.test", null, "MAS");
        } catch (Exception e) {
            message = e.getMessage();
            catched = true;
        }
        Assert.assertTrue(catched);
        Assert.assertTrue(message.startsWith("Two different stories with the same name (same method name) are being created in the same CaseManager file."));
        this.cleanUp();
    }

    @Test
    public void CaseManagerNotDeletedMASTest() throws Exception {
        this.cleanUp();
        boolean passed = false;
        try {
            Reader.generateJavaFiles(
                    "src/test/resources/",
                    "\"jade\"",
                    "src/test/java",
                    "es.upm.dit.gsi.beast.reader.mas.test",
                    "es.upm.dit.gsi.beast.reader.mas.test.manager",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    "MAS");
        } catch (Exception e) {
            this.cleanUp();
            Assert.fail();
        }

        try {
            File folder = SystemReader
                    .createFolder("es.upm.dit.gsi.beast.reader.mas.test.manager",
                            "src/test/java");
            File caseManager = new File(folder, "CaseManager.java");

            String targetLine1 = "      JUnitCore.runClasses(es.upm.dit.gsi.beast.reader.mas.test.ExampleStories.A1.class);";
            String targetLine2 = "      JUnitCore.runClasses(es.upm.dit.gsi.beast.reader.mas.test.ExampleStories.A2.class);";

            BufferedReader r = new BufferedReader(new FileReader(caseManager));
            String in;
            while ((in = r.readLine()) != null) {
                if (targetLine1.equals(in)) {
                    while ((in = r.readLine()) != null) {
                        if (targetLine2.equals(in)) {
                            passed = true;
                            break;
                        }
                    }
                }
                if (passed) {
                    break;   
                }
            }
            r.close();
        } catch (Exception e) {
            this.cleanUp();
            throw e;
        }
        Assert.assertTrue(passed);
        this.cleanUp();
    }

    @Test
    public void AndLinesParsingInStoryFilesTest() throws Exception {
        this.cleanUp();
        boolean passed = false;
        try {
            SystemReader
                    .generateJavaFiles(
                            "src/test/java/es/upm/dit/gsi/beast/reader/system/AnotherSystemReaderTest.story",
                            "\"jade\"", "src/test/java",
                            "es.upm.dit.gsi.beast.reader.mas.test",
                            "es.upm.dit.gsi.beast.reader.mas.test", null);
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            File folder = SystemReader.createFolder(
                    "es.upm.dit.gsi.beast.reader.mas.test",
                    "src/test/java");
            File file = new File(folder, "CaseManager.java");

            String targetLine1 = "   * providing the feature: have a system and test it, and test it again,";

            BufferedReader r = new BufferedReader(new FileReader(file));
            String in;
            while ((in = r.readLine()) != null) {
                if (targetLine1.equals(in)) {
                    passed = true;
                    break;
                }
            }
            r.close();
            if (passed==false) {
                Assert.fail();
            }
            passed=false;
            folder = SystemReader.createFolder(
                    "es.upm.dit.gsi.beast.reader.mas.test",
                    "src/test/java");
            file = new File(folder, "SystemStory.java");

            targetLine1 = "   * and the THEN is described as: a proper response occurs and I want to test it and I want to test it again.";

            r = new BufferedReader(new FileReader(file));
            
            while ((in = r.readLine()) != null) {
                if (targetLine1.equals(in)) {
                    passed = true;
                    break;
                }
            }
            r.close();
        } catch (Exception e) {
            this.cleanUp();
            throw e;
        }
        Assert.assertTrue(passed);
        this.cleanUp();
    }

    @Test
    public void MultipleFormatToInputStoriesAndScenariosParseTest() throws Exception {
        //This test checks if the : and - symbols are properly handled by the reder. 
        this.cleanUp();
        boolean passed = false;
        try {
            SystemReader
                    .generateJavaFiles(
                            "src/test/java/es/upm/dit/gsi/beast/reader/mas/ReaderTest.story",
                            "\"jade\"", "src/test/java",
                            "es.upm.dit.gsi.beast.reader.mas.test",
                            "es.upm.dit.gsi.beast.reader.mas.test", null);
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            File folder = SystemReader.createFolder(
                    "es.upm.dit.gsi.beast.reader.mas.test",
                    "src/test/java");
            File file = new File(folder, "CaseManager.java");

            String targetLine1 = "     JUnitCore.runClasses(es.upm.dit.gsi.beast.reader.mas.test.TestStory.class);";

            BufferedReader r = new BufferedReader(new FileReader(file));
            String in;
            while ((in = r.readLine()) != null) {
                if (targetLine1.equals(in)) {
                    passed = true;
                    break;
                }
            }
            r.close();
            if (passed==false) {
                Assert.fail();
            }
            passed=false;
            folder = SystemReader.createFolder(
                    "es.upm.dit.gsi.beast.reader.mas.test",
                    "src/test/java");
            file = new File(folder, "TestStory.java");
            
            targetLine1 = "    public void getBelievesFromAgent(){";
            String targetLine2 = "    public void setBelievesInAgent(){";

            r = new BufferedReader(new FileReader(file));
            
            while ((in = r.readLine()) != null) {
                if (targetLine1.equals(in)) {
                    while ((in = r.readLine()) != null) {
                        if (targetLine2.equals(in)) {
                            passed = true;
                            break;
                        }
                    }
                }
                if (passed) {
                    break;
                }
            }
            r.close();
        } catch (Exception e) {
            this.cleanUp();
            throw e;
        }
        Assert.assertTrue(passed);
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
