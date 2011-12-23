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
        this.status = "status1";
        this.obj = new TestObject("test", 10.5, false);
        obj.multiplyDouble(3);
        obj.negateBoolean();
        obj.concactString("1");
        logger.info("Agent status: " + this.status);
        this.myIntrospector.storeBeliefValue(this, "testStatus", status);
        this.myIntrospector.storeBeliefValue(this, "testObject", obj);
        logger.info("Stored believes in instrospector.");

        long timeToWait = 200;
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            logger.severe(this.getName() + " could not wait " + timeToWait
                    + " milliseconds. Exception: " + e);
        }

        obj.setDoubleTest(100.1);
        obj.setBooleanTest(false);
        obj.setStringTest("test2");
        status = "status2";
        logger.info("Agent status: " + this.status);
        this.myIntrospector.storeBeliefValue(this, "testStatus", status);

        // HashMap<String, Object> believes =
        // this.myIntrospector.retrieveBelievesValue(this);
        // this.status=(String) believes.get("status");
        // logger.info("Belief status: " + this.status);
        // if(status.equals("updated")) {
        // this.received = true;
        // this.myIntrospector.storeBeliefValue(this, "receivedBelief",
        // this.received);
        // } else {
        // logger.severe("Belief not updated");
        // }

        // FIXME no actualiza, hay que hacer el retrieve... porque los atributos
        // simples se pasan por valor y no por referencia
    }

}
