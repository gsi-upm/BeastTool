package es.upm.dit.gsi.beast.platform.jade;

import java.util.HashMap;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Test;

import es.upm.dit.gsi.beast.platform.PlatformSelector;
import es.upm.dit.gsi.beast.test.TestObject;
import es.upm.dit.gsi.beast.test.agent.jade.TesterAgent;

/**
 * Test class to test JadeAgentIntrospector
 * 
 * @author a.carrera
 * @version 1.0
 *
 */
/**
 * @author a.carrera
 * 
 */
public class JadeAgentIntrospectorTest {
    // TODO mirar porque no pilla esto maven surefire
    /**
     * 
     */
    @Test
    public void setBeliefStringWithoutDownCasting() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent",
                new HashMap<String, Object>());// The same that
                                               // JadeAgentIntrospector.getMyInstance((Agent)
                                               // this);

        // Set
        introspector.setBeliefValue("myAgent", "test", "true", connector);
        // Get
        Object value = introspector
                .getBeliefValue("myAgent", "test", connector);

        // Assert
        Assert.assertEquals("true", value);
    }

    /**
     * 
     */
    @Test
    public void setBeliefStringWithDownCasting() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent",
                new HashMap<String, Object>());// The same that
                                               // JadeAgentIntrospector.getMyInstance((Agent)
                                               // this);

        // Set
        introspector.setBeliefValue("myAgent", "test", "true", connector);
        // Get
        String value = (String) introspector.getBeliefValue("myAgent", "test",
                connector);

        // Assert
        Assert.assertEquals("true", value);
    }

    /**
     * 
     */
    @Test
    public void setBeliefBooleanWithoutDownCasting() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent",
                new HashMap<String, Object>());// The same that
                                               // JadeAgentIntrospector.getMyInstance((Agent)
                                               // this);

        // Set
        introspector.setBeliefValue("myAgent", "test", true, connector);
        // Get
        Object value = introspector
                .getBeliefValue("myAgent", "test", connector);

        // Assert
        Assert.assertEquals(true, value);
    }

    /**
     * 
     */
    @Test
    public void setBeliefBooleanWithDownCasting() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent",
                new HashMap<String, Object>());// The same that
                                               // JadeAgentIntrospector.getMyInstance((Agent)
                                               // this);

        // Set
        introspector.setBeliefValue("myAgent", "test", true, connector);
        // Get
        boolean value = (Boolean) introspector.getBeliefValue("myAgent",
                "test", connector);

        // Assert
        Assert.assertEquals(true, value);
    }

    /**
    * 
    */
    @Test
    public void setBeliefIntegerWithoutDownCasting() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent",
                new HashMap<String, Object>());// The same that
                                               // JadeAgentIntrospector.getMyInstance((Agent)
                                               // this);

        // Set
        introspector.setBeliefValue("myAgent", "test", 10, connector);
        // Get
        Object value = introspector
                .getBeliefValue("myAgent", "test", connector);

        // Assert
        Assert.assertEquals(10, value);
    }

    /**
    * 
    */
    @Test
    public void setBeliefIntegerWithDownCasting() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent",
                new HashMap<String, Object>());// The same that
                                               // JadeAgentIntrospector.getMyInstance((Agent)
                                               // this);

        // Set
        introspector.setBeliefValue("myAgent", "test", 10, connector);
        // Get
        int value = (Integer) introspector.getBeliefValue("myAgent", "test",
                connector);

        // Assert
        Assert.assertEquals(10, value);
    }

    /**
   * 
   */
    @Test
    public void setBeliefDoubleWithoutDownCasting() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent",
                new HashMap<String, Object>());// The same that
                                               // JadeAgentIntrospector.getMyInstance((Agent)
                                               // this);

        // Set
        introspector.setBeliefValue("myAgent", "test", 10.5, connector);
        // Get
        Object value = introspector
                .getBeliefValue("myAgent", "test", connector);

        // Assert
        Assert.assertEquals(10.5, value);
    }

    /**
   * 
   */
    @Test
    public void setBeliefDoubleWithDownCasting() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent",
                new HashMap<String, Object>());// The same that
                                               // JadeAgentIntrospector.getMyInstance((Agent)
                                               // this);

        // Set
        introspector.setBeliefValue("myAgent", "test", 10.5, connector);
        // Get
        double value = (Double) introspector.getBeliefValue("myAgent", "test",
                connector);

        // Assert
        Assert.assertEquals(10.5, value);
    }

    /**
      * 
      */
    @Test
    public void setBeliefComplexObjectWithoutDownCasting() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent",
                new HashMap<String, Object>());// The same that
                                               // JadeAgentIntrospector.getMyInstance((Agent)
                                               // this);

        TestObject obj = new TestObject("test", 10.5, false);
        obj.multiplyDouble(3);
        obj.negateBoolean();
        obj.concactString("1");

        // Set
        introspector.setBeliefValue("myAgent", "test", obj, connector);
        // Get
        Object value = introspector
                .getBeliefValue("myAgent", "test", connector);

        // Assert
        Assert.assertEquals(10.5 * 3, ((TestObject) value).getDoubleTest());
        Assert.assertEquals(true, ((TestObject) value).isBooleanTest());
        Assert.assertEquals("test1", ((TestObject) value).getStringTest());
    }

    /**
      * 
      */
    @Test
    public void setBeliefComplexObjectWithDownCasting() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent",
                new HashMap<String, Object>());// The same that
                                               // JadeAgentIntrospector.getMyInstance((Agent)
                                               // this);
        TestObject obj = new TestObject("test", 10.5, false);
        obj.multiplyDouble(3);
        obj.negateBoolean();
        obj.concactString("1");

        // Set
        introspector.setBeliefValue("myAgent", "test", obj, connector);
        // Get
        TestObject value = (TestObject) introspector.getBeliefValue("myAgent",
                "test", connector);

        // Assert
        Assert.assertEquals(10.5 * 3, ((TestObject) value).getDoubleTest());
        Assert.assertEquals(true, ((TestObject) value).isBooleanTest());
        Assert.assertEquals("test1", ((TestObject) value).getStringTest());
    }

    /**
     * 
     */
    @Test
    public void setBeliefComplexObjectByReferenceWithoutDownCasting() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent",
                new HashMap<String, Object>());// The same that
                                               // JadeAgentIntrospector.getMyInstance((Agent)
                                               // this);

        TestObject obj = new TestObject("test", 10.5, false);

        // Set
        introspector.setBeliefValue("myAgent", "test", obj, connector);
        // Get
        Object value = introspector
                .getBeliefValue("myAgent", "test", connector);
        obj.multiplyDouble(3);
        obj.negateBoolean();
        obj.concactString("1");

        // Assert
        Assert.assertEquals(10.5 * 3, ((TestObject) value).getDoubleTest());
        Assert.assertEquals(true, ((TestObject) value).isBooleanTest());
        Assert.assertEquals("test1", ((TestObject) value).getStringTest());

        ((TestObject) value).setStringTest("test2");
        ((TestObject) value).setDoubleTest(0.95);
        ((TestObject) value).setBooleanTest(false);
        Assert.assertEquals("test2", obj.getStringTest());
        Assert.assertEquals(0.95, obj.getDoubleTest());
        Assert.assertEquals(false, obj.isBooleanTest());
    }

    /**
    * 
    */
    @Test
    public void setBeliefComplexObjectByReferenceWithDownCasting() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent",
                new HashMap<String, Object>());// The same that
                                               // JadeAgentIntrospector.getMyInstance((Agent)
                                               // this);
        TestObject obj = new TestObject("test", 10.5, false);

        // Set
        introspector.setBeliefValue("myAgent", "test", obj, connector);
        obj.multiplyDouble(3);
        obj.negateBoolean();
        obj.concactString("1");
        // Get
        TestObject value = (TestObject) introspector.getBeliefValue("myAgent",
                "test", connector);

        // Assert
        Assert.assertEquals(10.5 * 3, ((TestObject) value).getDoubleTest());
        Assert.assertEquals(true, ((TestObject) value).isBooleanTest());
        Assert.assertEquals("test1", ((TestObject) value).getStringTest());

        ((TestObject) value).setStringTest("test2");
        ((TestObject) value).setDoubleTest(0.95);
        ((TestObject) value).setBooleanTest(false);
        Assert.assertEquals("test2", obj.getStringTest());
        Assert.assertEquals(0.95, obj.getDoubleTest());
        Assert.assertEquals(false, obj.isBooleanTest());

    }

    @Test
    public void JadeAgentStoreBeliefInMainContainerNotNull() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        connector.launchPlatform();
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");

        // Set
        connector.createAgent("TestAgent",
                "es.upm.dit.gsi.beast.test.agent.jade.TesterAgent");

        // Get
        TestObject value = (TestObject) introspector.getBeliefValue(
                "TestAgent", "testObject", connector);

        // Assert
        Assert.assertNotNull(value);
    }

    @Test
    public void JadeAgentStoreBeliefInMainContainer() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        connector.launchPlatform();
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");

        // Set
        connector.createAgent("TestAgent",
                "es.upm.dit.gsi.beast.test.agent.jade.TesterAgent");

        // Get
        TestObject value = (TestObject) introspector.getBeliefValue(
                "TestAgent", "testObject", connector);

        // Assert
        Assert.assertEquals(10.5 * 3, ((TestObject) value).getDoubleTest());
        Assert.assertEquals(true, ((TestObject) value).isBooleanTest());
        Assert.assertEquals("test1", ((TestObject) value).getStringTest());
    }

    @Test
    public void JadeAgentStoreComplexBeliefByReferenceInMainContainer() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        connector.launchPlatform();
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");

        // Set
        connector.createAgent("TestAgent",
                "es.upm.dit.gsi.beast.test.agent.jade.TesterAgent");

        // Get
        TestObject value = (TestObject) introspector.getBeliefValue(
                "TestAgent", "testObject", connector);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Assert.fail("It was not possible to sleep the thead...");
        }

        // Assert
        Assert.assertEquals(100.1, ((TestObject) value).getDoubleTest());
        Assert.assertEquals(false, ((TestObject) value).isBooleanTest());
        Assert.assertEquals("test2", ((TestObject) value).getStringTest());
    }

    @Test
    public void JadeAgentStorePrimitiveBeliefByReferenceInMainContainer() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        connector.launchPlatform();
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");

        // Set
        connector.createAgent("TestAgent",
                "es.upm.dit.gsi.beast.test.agent.jade.TesterAgent");

        // Get
        String value = (String) introspector.getBeliefValue("TestAgent",
                "testStatus", connector);

        // Assert
        Assert.assertEquals("status1", value);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Assert.fail("It was not possible to sleep the thead...");
        }
        value = (String) introspector.getBeliefValue("TestAgent",
                "testStatus", connector);
        Assert.assertEquals("status2", value);
    }
    
    @Test
    public void JadeAgentStorePrimitiveBeliefThroughGetByReferenceInMainContainer() {
        // Setup
        Logger logger = Logger.getLogger(JadeAgentIntrospectorTest.class
                .getName());
        JadeConnector connector = (JadeConnector) PlatformSelector
                .getConnector("jade", logger);
        connector.launchPlatform();
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector
                .getAgentIntrospector("jade");

        // Set
        connector.createAgent("TestAgent",
                "es.upm.dit.gsi.beast.test.agent.jade.TesterAgent");

        // Get

        // Assert
        Assert.assertEquals("status1", (String) ((TesterAgent) introspector.getAgent("TestAgent")).getStatus());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Assert.fail("It was not possible to sleep the thead...");
        }
        Assert.assertEquals("status2", (String) ((TesterAgent) introspector.getAgent("TestAgent")).getStatus());
    }

}
