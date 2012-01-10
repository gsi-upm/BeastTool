package es.upm.dit.gsi.beast.test.agent.jadex;

import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;

import java.util.logging.Logger;

/**
 * Listener mock will not send any answer, it just print what it receives Other
 * options could be to ignore messages or to save them somewhere
 * 
 * @author Jorge Solitario
 */
public class ListenPlan extends Plan {

    /** Serial version UID of the serializable class BehaviourPlan. */
    private static final long serialVersionUID = 4476473302410302L;

    private Logger logger = Logger.getLogger(ListenPlan.class.getName());

    public void body() {
        IMessageEvent actReq = (IMessageEvent) getReason();

        int count = (Integer) getBeliefbase().getBelief("message_count").getFact();
        count++;
        getBeliefbase().getBelief("message_count").setFact(count);

        String type = (String) actReq.getParameter("performative").getValue();
        Object content = actReq.getParameter(SFipa.CONTENT).getValue();
    }
}
