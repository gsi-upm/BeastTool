package es.upm.dit.gsi.beast.test.agent.jade;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.Logger;

import java.util.logging.Level;

import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.logging.LogActivator;
import es.upm.dit.gsi.beast.test.TestObject;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.test.agent.jade.TesterAgent.java
 * 
 * JADE agent developed to test beast mocks 
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
public class TesterAgent extends Agent {

    /**
     * 
     */
    private static final long serialVersionUID = 3431816096407246858L;

    protected final Logger logger = Logger.getMyLogger(getClass().getName());

    JadeAgentIntrospector myIntrospector;

    private String status;
    
    private boolean readyToTest = false;
    private boolean isMessageReceived = false;

    private TestObject obj;
    private ACLMessage receivedMessage;


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
    
    
    public boolean isMessageReceived() {
        return isMessageReceived;
    }

    public void setMessageReceived(boolean isMessageReceived) {
        this.isMessageReceived = isMessageReceived;
    }

    public ACLMessage getReceivedMessage() {
        return receivedMessage;
    }

    public void setReceivedMessage(ACLMessage receivedMessage) {
        this.receivedMessage = receivedMessage;
    }
    
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
        case 5:
            this.setupConfiguration5(arguments);
            break;
        case 6:
            this.setupConfiguration6(arguments);
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

    
    private void setupConfiguration5(Object[] arguments) {
        MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        addBehaviour(new RequestServer(template, 0));
        logger.fine("RequestServer behaviour added");

        setMessageReceived(false);
        long timeToWait = 200;
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            logger.severe(this.getName() + " could not wait " + timeToWait
                    + " milliseconds. Exception: " + e);
        }      
        
    }

    
    private void setupConfiguration6(Object[] arguments) {
        MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        addBehaviour(new RequestServer(template, 1));
        logger.fine("RequestServer behaviour added");
        
        long timeToWait = 200;
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            logger.severe(this.getName() + " could not wait " + timeToWait
                    + " milliseconds. Exception: " + e);
        }
        
    }


    /**
     * Inner class RequestServer This behaviour serves the requests for
     * observations
     */
    private class RequestServer extends CyclicBehaviour {
        
        private static final long serialVersionUID = 286992171997750565L;

        private MessageTemplate template;
        private int configuration;
        
        public RequestServer(MessageTemplate template, int configuration) {
            super(TesterAgent.this);
            this.template=template;
            this.configuration = configuration;
        }

        public void action() {
            ACLMessage msg = myAgent.receive(template);
            if (msg != null) {
                setReceivedMessage(msg);
                setMessageReceived(true);
                myIntrospector.storeBeliefValue(TesterAgent.this, "ReceivedMessage", isMessageReceived());
                switch (configuration) {
                case 0:
                    logger.fine("Messege received");
                    break;
                case 1:
                    send(msg.createReply());
                    logger.fine("Reply sent");
                    break;
                }
            } else {
                block();
            }
        }
    }

}
