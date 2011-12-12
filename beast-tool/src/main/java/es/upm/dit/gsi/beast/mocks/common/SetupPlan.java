package es.upm.dit.gsi.beast.mocks.common;

import jadex.base.fipa.DFServiceDescription;
import jadex.base.fipa.IDFComponentDescription;
import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IGoal;
import jadex.bdi.runtime.Plan;
import jadex.standalone.service.DirectoryFacilitatorService;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bridge.service.IServiceProvider;

/**
 * Plan to change the name of each mock agent and
 * to save its behaviour for future messages
 * 
 * @author Jorge Solitario
 */
public class SetupPlan extends Plan {

	 /** Serial version UID of the serializable class SetupPlan. */
	private static final long serialVersionUID = 112334545L;

	/**
	 * The plan body.
	 */
	public void body() {
		
		MockConfiguration configuration = (MockConfiguration) ((IMessageEvent)getReason()).getParameter(SFipa.CONTENT).getValue();
		getBeliefbase().getBelief("agent_behaviour").setFact(configuration.getBehaviour());
		
		// CREATING NEW DF_SERVICE NAME
		IServiceProvider iserviceprovider = getScope().getServiceContainer();
		DirectoryFacilitatorService idfservice = new DirectoryFacilitatorService(iserviceprovider);
		DFServiceDescription dfaux = (DFServiceDescription)idfservice
				.createDFServiceDescription(configuration.getDFservice(), configuration.getDFservice(),
						"Universidad Polit�cnica de Madrid");

		IDFComponentDescription df_description= idfservice.createDFComponentDescription(null, dfaux);
		waitFor(1000);
		
		IGoal keep = createGoal("dfcap.df_keep_registered");
		keep.getParameter("description").setValue(df_description);
		keep.getParameter("leasetime").setValue(new Long(60000));
		dispatchSubgoalAndWait(keep);
	}

}
