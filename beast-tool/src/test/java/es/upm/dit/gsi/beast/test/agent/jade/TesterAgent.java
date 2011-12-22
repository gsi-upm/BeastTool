package es.upm.dit.gsi.beast.test.agent.jade;

import jade.core.Agent;
import jade.util.Logger;

import java.util.logging.Level;

import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.story.logging.LogActivator;

public class TesterAgent extends Agent {

    /**
     * 
     */
    private static final long serialVersionUID = 3431816096407246858L;

    protected final Logger logger = Logger.getMyLogger(getClass().getName());

    JadeAgentIntrospector myIntrospector;
    private String status;

    protected void setup() {
        myIntrospector = JadeAgentIntrospector.getMyInstance((Agent) this);
        LogActivator.logToFile(logger, this.getName(), Level.ALL);
        this.status = "started";
        logger.info("Agent status: " + this.status);
        this.myIntrospector.storeBeliefValue(this, "status", status);
        logger.info("Stored believes in instrospector.");

        // FIXME no actualiza, hay que hacer el retrieve... porque los atributos simples se pasan por valor y no por referencia
    }

}
