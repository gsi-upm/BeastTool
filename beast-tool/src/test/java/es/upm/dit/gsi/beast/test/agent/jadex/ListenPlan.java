package es.upm.dit.gsi.beast.test.agent.jadex;

import jadex.bdi.runtime.Plan;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.test.agent.jadex.ListenPlan.java
 * 
 * JADEX plan developed to test mock agents.
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
public class ListenPlan extends Plan {

    /** Serial version UID of the serializable class BehaviourPlan. */
    private static final long serialVersionUID = 4476473302410302L;

//    private Logger logger = Logger.getLogger(ListenPlan.class.getName());

    public void body() {

//      WHEN HANDLING MESSAGE EVENTS:
//            IMessageEvent actReq = (IMessageEvent) getReason();
//
//            int count = (Integer) getBeliefbase().getBelief("message_count").getFact();
//            count++;
//            getBeliefbase().getBelief("message_count").setFact(count);
//
//            String type = (String) actReq.getParameter("performative").getValue();
//            Object content = actReq.getParameter(SFipa.CONTENT).getValue();
//            logger.info(type+content);
        
    }
}
