package es.upm.dit.gsi.beast.mock.jadex.common;

import jadex.base.fipa.DFServiceDescription;
import jadex.base.fipa.IDFComponentDescription;
import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IGoal;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;
import jadex.bridge.service.IServiceProvider;
import jadex.standalone.service.DirectoryFacilitatorService;

import java.util.logging.Logger;

import es.upm.dit.gsi.beast.mock.common.MockConfiguration;

/**
 * Plan to change the name of each mock agent and to save its behaviour for
 * future messages
 * 
 * @author Jorge Solitario
 */
public class SetupPlan extends Plan {

    /** Serial version UID of the serializable class SetupPlan. */
    private static final long serialVersionUID = 112334545L;

    private static final int LEASE_TIME = 60000;
    private static final int WAIT_FOR_TIME = 1000;
    /**
     * The plan body.
     */
    public void body() {

        MockConfiguration configuration = (MockConfiguration) ((IMessageEvent) getReason())
                .getParameter(SFipa.CONTENT).getValue();
        getBeliefbase().getBelief("agent_behaviour").setFact(
                configuration.getBehaviour());

        // CREATING NEW DF_SERVICE NAME
        IServiceProvider iserviceprovider = getScope().getServiceContainer();
        DirectoryFacilitatorService idfservice = new DirectoryFacilitatorService(
                iserviceprovider);
        DFServiceDescription dfaux = (DFServiceDescription) idfservice
                .createDFServiceDescription(configuration.getDFservice(),
                        configuration.getDFservice(),
                        "Universidad Politécnica de Madrid");

        IDFComponentDescription df_description = idfservice
                .createDFComponentDescription(null, dfaux);
        waitFor(WAIT_FOR_TIME);

        IGoal keep = createGoal("dfcap.df_keep_registered");
        keep.getParameter("description").setValue(df_description);
        keep.getParameter("leasetime").setValue(new Long(LEASE_TIME));
        dispatchSubgoalAndWait(keep);

        Logger logger = Logger.getLogger(this.getClass().toString());
        logger.info(configuration.getDFservice() + " registered");
    }

}
