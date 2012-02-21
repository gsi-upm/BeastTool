package es.upm.dit.gsi.beast.mock.jadex.common;

import jadex.base.fipa.IDF;
import jadex.base.fipa.IDFComponentDescription;
import jadex.base.fipa.IDFServiceDescription;
import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IGoal;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;
import jadex.bridge.ISearchConstraints;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.SServiceProvider;

import java.util.logging.Logger;

/**
 * MockAgentPlan is the class all Agents-plan must extend from.
 * 
 * @author Jorge Solitario
 */
public abstract class MockAgentPlan extends Plan {
    // -------- attributes --------

    /** Serial version UID of the serializable class MockAgentPlan. */
    private static final long serialVersionUID = 5211108972962782274L;

    private static final int LEASE_TIME = 60000;

    Logger logger = Logger.getLogger(this.getClass().toString());

    // -------- methods --------

    /**
     * Method to send Request messages to a specific df_service
     * 
     * @param df_service
     *            The name of the df_service
     * @param msgContent
     *            The content of the message to be sent
     * @return Message sent to + the name of the df_service
     */
    protected String sendRequestToDF(String df_service, Object msgContent) {

        IDFComponentDescription[] receivers = getReceivers(df_service);
        if (receivers.length > 0) {
            IMessageEvent mevent = createMessageEvent("send_request");
            mevent.getParameter(SFipa.CONTENT).setValue(msgContent);
            for (int i = 0; i < receivers.length; i++) {
                mevent.getParameterSet(SFipa.RECEIVERS).addValue(
                        receivers[i].getName());
                logger.info("The receiver is " + receivers[i].getName());
            }
            sendMessage(mevent);
        }
        logger.info("Message sended to " + df_service + " to "
                + receivers.length + " receivers");
        return ("Message sended to " + df_service);
    }

    /**
     * Method to send Inform messages to a specific df_service
     * 
     * @param df_service
     *            The name of the df_service
     * @param msgContent
     *            The content of the message to be sent
     * @return Message sent to + the name of the df_service
     */
    protected String sendInformToDF(String df_service, Object msgContent) {

        IDFComponentDescription[] receivers = getReceivers(df_service);
        if (receivers.length > 0) {
            IMessageEvent mevent = createMessageEvent("send_inform");
            mevent.getParameter(SFipa.CONTENT).setValue(msgContent);
            for (int i = 0; i < receivers.length; i++) {
                mevent.getParameterSet(SFipa.RECEIVERS).addValue(
                        receivers[i].getName());
                logger.info("The receiver is " + receivers[i].getName());
            }
            sendMessage(mevent);
        }
        logger.info("Message sended to " + df_service + " to "
                + receivers.length + " receivers");
        return ("Message sended to " + df_service);
    }

    /**
     * This method will be used to know the IDFComponentDescription of all the
     * agents that own a df_service. Given the IDFComponentDescription, the name
     * of the agent is known using getName()
     * 
     * @param target_service
     *            The name of the df_service
     * @return Its agent IDFComponentDescription
     */
    protected IDFComponentDescription[] getReceivers(String target_service) {
        // Search for X_Service
        // Create a service description to search for.
        IDF df = (IDF) SServiceProvider.getService(getServiceContainer(),
                IDF.class, RequiredServiceInfo.SCOPE_PLATFORM).get(this);
        IDFServiceDescription sd = df.createDFServiceDescription(
                target_service, null, null);
        IDFComponentDescription dfadesc = df.createDFComponentDescription(null,
                sd);

        ISearchConstraints constraints = df.createSearchConstraints(-1, 0);

        // Use a subgoal to search
        IGoal ft = createGoal("dfcap.df_search");
        ft.getParameter("description").setValue(dfadesc);
        ft.getParameter("constraints").setValue(constraints);
        ft.getParameter("leasetime").setValue(new Long(LEASE_TIME));

        dispatchSubgoalAndWait(ft);

        return (IDFComponentDescription[]) ft.getParameterSet("result")
                .getValues();
    }

}
