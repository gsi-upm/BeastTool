package es.upm.dit.gsi.beast.reader.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import junit.framework.Assert;

import org.junit.Test;

import es.upm.dit.gsi.beast.reader.Reader;

/**
 * Project: beast File: es.upm.dit.gsi.beast.reader.system.SystemReaderTest.java
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
public class SystemReaderTest {

    @Test
    public void MainSystemReaderTest() {
        this.cleanUp();
        try {
            SystemReader
                    .generateJavaFiles(
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
                "UserStoriesManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "SystemStory.java").exists());
        this.cleanUp();
    }

    @Test
    public void MainReaderWithoutLogPropTest() {
        this.cleanUp();
        try {
            SystemReader
                    .generateJavaFiles(
                            "src/test/java/es/upm/dit/gsi/beast/reader/system/SystemReaderTest.story",
                            "\"jade\"", "src/test/java",
                            "es.upm.dit.gsi.beast.reader.system.test",
                            "es.upm.dit.gsi.beast.reader.system.test", null);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "UserStoriesManager.java").exists());
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
                    "\"jade\"",
                    "src/test/java",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    Reader.SYSTEM);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "UserStoriesManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "SystemStory.java").exists());
        this.cleanUp();
    }

    @Test
    public void UserStoriesManagerDuplicatedMethodsTest() {
        this.cleanUp();
        boolean catched = false;
        String message = "";
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/SystemReaderTest.story",
                    "\"jade\"",
                    "src/test/java",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    Reader.SYSTEM);
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/SystemReaderTest.story",
                    "\"jade\"",
                    "src/test/java",
                    "es.upm.dit.gsi.beast.reader.system.test.algo",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    Reader.SYSTEM);
        } catch (Exception e) {
            message = e.getMessage();
            catched = true;
        }
        Assert.assertTrue(catched);
        Assert.assertTrue(message
                .startsWith("Two different stories with the same name (same method name) are being created in the same CaseManager file."));
        this.cleanUp();

    }

    @Test
    public void UserStoriesManagerNotDeletedSystemTest() throws Exception {
        this.cleanUp();
        boolean passed = false;
        try {
            Reader.generateJavaFiles(
                    "src/test/resources/",
                    "\"jade\"",
                    "src/test/java",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "es.upm.dit.gsi.beast.reader.system.test.manager",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    "SYSTEM");
        } catch (Exception e) {
            this.cleanUp();
            Assert.fail();
        }

        try {
            File folder = SystemReader.createFolder(
                    "es.upm.dit.gsi.beast.reader.system.test.manager",
                    "src/test/java");
            File caseManager = new File(folder, "UserStoriesManager.java");

            String targetLine1 = "     Result result = JUnitCore.runClasses(es.upm.dit.gsi.beast.reader.system.test.ExampleStories.A1.class);";
            String targetLine2 = "     Result result = JUnitCore.runClasses(es.upm.dit.gsi.beast.reader.system.test.ExampleStories.A2.class);";

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
                            "es.upm.dit.gsi.beast.reader.system.test",
                            "es.upm.dit.gsi.beast.reader.system.test", null);
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            File folder = SystemReader.createFolder(
                    "es.upm.dit.gsi.beast.reader.system.test", "src/test/java");
            File file = new File(folder, "UserStoriesManager.java");

            String targetLine1 = "   * so the user gets the benefit: I have a benefit and I am sure that it works.";

            BufferedReader r = new BufferedReader(new FileReader(file));
            String in;
            while ((in = r.readLine()) != null) {
                if (targetLine1.equals(in)) {
                    passed = true;
                    break;
                }
            }
            r.close();
            if (passed == false) {
                Assert.fail();
            }
            passed = false;
            folder = SystemReader.createFolder(
                    "es.upm.dit.gsi.beast.reader.system.test", "src/test/java");
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
    public void MultipleFormatToInputStoriesAndScenariosParseTest()
            throws Exception {
        // This test checks if the : and - symbols are properly handled by the
        // reder.
        this.cleanUp();
        boolean passed = false;
        try {
            SystemReader
                    .generateJavaFiles(
                            "src/test/java/es/upm/dit/gsi/beast/reader/system/AnotherSystemReaderTest.story",
                            "\"jade\"", "src/test/java",
                            "es.upm.dit.gsi.beast.reader.system.test",
                            "es.upm.dit.gsi.beast.reader.system.test", null);
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            File folder = SystemReader.createFolder(
                    "es.upm.dit.gsi.beast.reader.system.test", "src/test/java");
            File file = new File(folder, "UserStoriesManager.java");

            String targetLine1 = "     Result result = JUnitCore.runClasses(es.upm.dit.gsi.beast.reader.system.test.SystemStory.class);";

            BufferedReader r = new BufferedReader(new FileReader(file));
            String in;
            while ((in = r.readLine()) != null) {
                if (targetLine1.equals(in)) {
                    passed = true;
                    break;
                }
            }
            r.close();
            if (passed == false) {
                Assert.fail();
            }
            passed = false;
            folder = SystemReader.createFolder(
                    "es.upm.dit.gsi.beast.reader.system.test", "src/test/java");
            file = new File(folder, "SystemStory.java");

            targetLine1 = "    public void secondSystemScenario() {";
            String targetLine2 = "    public void thisIsOtherScenario() {";

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

    @Test
    public void DontDeleteExistingStoriesTest() {
        this.cleanUp();
        boolean passed = false;
        try {
            SystemReader
                    .generateJavaFiles(
                            "src/test/java/es/upm/dit/gsi/beast/reader/system/SystemReaderTest.story",
                            "\"jade\"", "src/test/java",
                            "es.upm.dit.gsi.beast.reader.system.test",
                            "es.upm.dit.gsi.beast.reader.system.test",
                            "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties");
            File folder = SystemReader.createFolder(
                    "es.upm.dit.gsi.beast.reader.system.test", "src/test/java");
            File file = new File(folder, "SystemStory.java");
            FileWriter fw = new FileWriter(file);
            fw.append("MyTest");
            fw.close();
            SystemReader
                    .generateJavaFiles(
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
                "UserStoriesManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "SystemStory.java").exists());

        try {
            File folder = SystemReader.createFolder(
                    "es.upm.dit.gsi.beast.reader.system.test", "src/test/java");
            File file = new File(folder, "SystemStory.java");

            String targetLine1 = "MyTest";

            BufferedReader r = new BufferedReader(new FileReader(file));
            String in;
            while ((in = r.readLine()) != null) {
                if (targetLine1.equals(in)) {
                    passed = true;
                    break;
                }
            }
            r.close();
            if (passed == false) {
                Assert.fail();
            }
        } catch (Exception e) {
            passed = false;
        }
        Assert.assertTrue(passed);
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
