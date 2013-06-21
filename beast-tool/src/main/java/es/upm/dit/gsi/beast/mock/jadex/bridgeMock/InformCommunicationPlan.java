package es.upm.dit.gsi.beast.mock.jadex.bridgeMock;

import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bridge.IComponentIdentifier;

import java.util.logging.Logger;

import es.upm.dit.gsi.beast.mock.common.AgentBehaviour;
import es.upm.dit.gsi.beast.mock.jadex.common.MockAgentPlan;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.mock.jadex.bridgeMock.InformCommunicationPlan.java
 * 
 * Class to send a Inform message
 * 
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * @author Jorge Solitario
 * @version 0.1
 * 
 */
public class InformCommunicationPlan extends MockAgentPlan {

    /** Serial version UID of the serializable class CommunicationPlan. */
    private static final long serialVersionUID = 4476473302410302L;

    /**
	 * 
	 */
    public void body() {
        Logger logger = Logger.getLogger(this.getClass().toString());
        /*
         * Retrieving information of sended message
         */
        IMessageEvent actReq = (IMessageEvent) getReason();
        
        int count = (Integer) getBeliefbase().getBelief("message_count")
                .getFact();
        count++;
        getBeliefbase().getBelief("message_count").setFact(count);
        
        String type = (String) actReq.getParameter("performative").getValue();
        String agent_name = null;
        try {
            agent_name = (String) ((IComponentIdentifier) actReq.getParameter(
                    SFipa.SENDER).getValue()).getLocalName();
        } catch (Exception e) {
            logger.info("Received message has no sender");
            agent_name = "no-one";
        }
        Object in_content = actReq.getParameter(SFipa.CONTENT).getValue();
        logger.info("IN: Type " + type + " ---- Sender_name: " + agent_name
                + " ---- Content: " + in_content);

        // The mock where the action to perform is saved
        AgentBehaviour behaviour = (AgentBehaviour) getBeliefbase().getBelief(
                "agent_behaviour").getFact();

        /*
         * Retrieving information of the messege to send
         */
        String df_service;
        String msgType;
        Object out_content;
        if (agent_name == "no-one") {
            df_service = (String) behaviour.processMessage(type, in_content);
            msgType = (String) behaviour.processMessage(type, in_content);
            out_content = behaviour.processMessage(type, in_content);
        } else {
            df_service = (String) behaviour.processMessage(type, agent_name,
                    in_content);
            msgType = (String) behaviour.processMessage(type, agent_name,
                    in_content);
            out_content = behaviour
                    .processMessage(type, agent_name, in_content);
        }
        logger.info("OUT: DF-Service " + df_service + " ---- MsgType "
                + msgType + " ---- Content " + out_content);

        if (msgType.equals(SFipa.REQUEST)) {
            sendRequestToDF(df_service, out_content);
        } else if (msgType.equals(SFipa.INFORM)){
            sendInformToDF(df_service, out_content);
        }
    }

    // /**
    // * This method is an skeleton for future code-changes.
    // * The idea is to process the arriving information (in_content) and create
    // the output (out_content),
    // * instead of being given directly by the Scenario.
    // *
    // * @param content The SFipa.CONTENT of the arriving message
    // * @return The content of the new messages that are gonna be sended
    // */
    // private Object processContent(Object content) {
    // return content;
    // }

    // protected Object sendRequestAndGetResponse(Object
    // request,IComponentIdentifier receiver) throws GoalFailureException {
    // // This method uses the Request.capability.xml in
    // jadex.bdi.planlib.protocols.request
    // IGoal goal = createGoal("rp_initiate");
    // goal.getParameter("action").setValue(request);
    // goal.getParameter("receiver").setValue(receiver);
    // goal.getParameter("timeout").setValue(5000);
    // dispatchSubgoalAndWait(goal);
    // Object response = goal.getParameter("result").getValue();
    // return response;
    // }
}
