package es.upm.dit.gsi.beast.test.agent.jade;

import jade.core.Agent;
import jade.util.Logger;

import java.util.logging.Level;

import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.logging.LogActivator;
import es.upm.dit.gsi.beast.test.TestObject;

public class TesterAgent extends Agent {

    /**
     * 
     */
    private static final long serialVersionUID = 3431816096407246858L;

    protected final Logger logger = Logger.getMyLogger(getClass().getName());

    JadeAgentIntrospector myIntrospector;

    private String status;
    
    private boolean readyToTest=false;

    public boolean isReadyToTest() {
        return readyToTest;
    }

    public void setReadyToTest(boolean readyToTest) {
        this.readyToTest = readyToTest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TestObject getObj() {
        return obj;
    }

    public void setObj(TestObject obj) {
        this.obj = obj;
    }

    private TestObject obj;

    protected void setup() {
        myIntrospector = JadeAgentIntrospector.getMyInstance((Agent) this);
        LogActivator.logToFile(logger, this.getName(), Level.ALL);
        
        Object[] arguments = getArguments();
        int testConfiguration = (Integer) arguments[0];
        logger.fine("Configuration: " + testConfiguration);
        switch (testConfiguration) {
        case 0:
            this.setupConfiguration0(arguments);
        case 1:
            this.setupConfiguration1(arguments);
            break;
        case 2:
            this.setupConfiguration2(arguments);
            break;
        case 3:
            this.setupConfiguration3(arguments);
            break;
        case 4:
            this.setupConfiguration4(arguments);
            break;
        }
        readyToTest=true;
    }
    
    private void setupConfiguration0(Object[] arguments) {
        this.status = "status1";
        this.obj = new TestObject("test", 10.5, false);
        obj.multiplyDouble(3);
        obj.negateBoolean();
        obj.concactString("1");
        logger.info("Agent status: " + this.status);
        this.myIntrospector.storeBeliefValue(this, "testStatus", status);
        this.myIntrospector.storeBeliefValue(this, "testObject", obj);
        logger.info("Stored believes in instrospector.");

        long timeToWait =200;
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            logger.severe(this.getName() + " could not wait " + timeToWait
                    + " milliseconds. Exception: " + e);
        }
    }
    
    private void setupConfiguration1(Object[] arguments) {
        this.status = "status1";
        this.obj = new TestObject("test", 10.5, false);
        logger.info("Agent status: " + this.status);
        this.myIntrospector.storeBeliefValue(this, "testStatus", status);
        this.myIntrospector.storeBeliefValue(this, "testObject", obj);
        logger.info("Stored believes in instrospector.");

        obj.setDoubleTest(100.1);
        obj.setBooleanTest(false);
        obj.setStringTest("test2");

        long timeToWait =200;
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            logger.severe(this.getName() + " could not wait " + timeToWait
                    + " milliseconds. Exception: " + e);
        }
        
        status = "status2";
        logger.info("Agent status: " + this.status);
        this.myIntrospector.storeBeliefValue(this, "testStatus", status);
    }
    
    private void setupConfiguration2(Object[] arguments) {
        this.status = "status1";
        this.obj = new TestObject("test", 10.5, false);
        obj.multiplyDouble(3);
        obj.negateBoolean();
        obj.concactString("1");
        this.myIntrospector.storeBeliefValue(this, "testObject", obj);
        logger.info("Stored believes in instrospector.");

        long timeToWait = 200;
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            logger.severe(this.getName() + " could not wait " + timeToWait
                    + " milliseconds. Exception: " + e);
        }
        this.obj = (TestObject) this.myIntrospector.retrieveBelievesValue(this).get("testObject");
        logger.info("TestObject data ->");
        logger.info("Double: " + obj.getDoubleTest());
        logger.info("String: " + obj.getStringTest());
        boolean retrieved = false;
        if(obj.getDoubleTest()==1.11 && obj.getStringTest().equals("OK")) {
            retrieved = true;
        }
        this.myIntrospector.storeBeliefValue(this, "testRetrieved", retrieved);
    }

    
    private void setupConfiguration3(Object[] arguments) {
        this.status = "status1";
        this.obj = new TestObject("test", 10.5, false);
        obj.multiplyDouble(3);
        obj.negateBoolean();
        obj.concactString("1");
        this.myIntrospector.storeBeliefValue(this, "testObject", obj);
        logger.info("Stored believes in instrospector.");

        long timeToWait = 200;
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            logger.severe(this.getName() + " could not wait " + timeToWait
                    + " milliseconds. Exception: " + e);
        }
        logger.info("TestObject data ->");
        logger.info("Double: " + obj.getDoubleTest());
        logger.info("String: " + obj.getStringTest());
        boolean retrieved = false;
        if(obj.getDoubleTest()==1.11 && obj.getStringTest().equals("OK")) {
            retrieved = true;
        }
        this.myIntrospector.storeBeliefValue(this, "testRetrieved", retrieved);
    }

    
    private void setupConfiguration4(Object[] arguments) {
        this.status = "status1";
        this.myIntrospector.storeBeliefValue(this, "testStatus", status);
        logger.info("Stored believes in instrospector.");

        logger.fine("Belief testStatus: " + status);
        
        long timeToWait = 5000;
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            logger.severe(this.getName() + " could not wait " + timeToWait
                    + " milliseconds. Exception: " + e);
        }
        String aux = (String) this.myIntrospector.retrieveBelievesValue(this).get("testStatus");
        this.status = aux;
        logger.fine("Retrieved Belief testStatus: " + aux);
        boolean retrieved = false;
        if (status.equals("updated")) {
            logger.fine("Belief is updated");
            retrieved = true;
        }
        this.myIntrospector.storeBeliefValue(this, "setBelief", retrieved);
        
        
    }

}
