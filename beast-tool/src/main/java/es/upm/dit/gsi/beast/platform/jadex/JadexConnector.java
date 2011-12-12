package es.upm.dit.gsi.beast.platform.jadex;

import java.util.HashMap;

import es.upm.dit.gsi.beast.platform.Connector;
import jadex.base.Starter;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.IComponentManagementService;
import jadex.bridge.IExternalAccess;
import jadex.bridge.IMessageService;
import jadex.bridge.service.IServiceProvider;
import jadex.bridge.service.SServiceProvider;
import jadex.commons.future.ThreadSuspendable;

/**
 * To create our jadex Platform and create its agents, allowing the comunnicaction between them
 * 
 * @author Jorge Solitario
 */
public class JadexConnector extends Connector{

	private IExternalAccess platform;
	private IComponentManagementService cmsService = null;
	private IMessageService messageService = null;
	
	private HashMap<String,IComponentIdentifier> createdAgents;

	/**
	 * Platform is launched and the external access of the message service and
	 * the component management service (CMS) are saved
	 * 
	 * @return the external access of the platform
	 */
	public void launchPlatform() {

		platform = (IExternalAccess) Starter.createPlatform(null).get(new ThreadSuspendable());
		IServiceProvider container = (IServiceProvider) platform.getServiceProvider();
		cmsService = SServiceProvider.getService(container,
				IComponentManagementService.class).get(new ThreadSuspendable());
		messageService = SServiceProvider.getService(container, IMessageService.class)
				.get(new ThreadSuspendable());
		createdAgents = new HashMap<String,IComponentIdentifier>();
	}


	/**
	 * This method gets the external access of the platform.
	 * 
	 * @return The external access of the platform
	 */
	public IExternalAccess getPlatform() {
		return platform;
	}
	
	
	/**
	 * This method gets the CMS for creating agents into the platform.
	 * 
	 * @return the Component Management Service
	 */
	public IComponentManagementService getCmsService() {
		return cmsService;
	}

	
	/**
	 * This method gets the service for sendind messages to the platform.
	 * 
	 * @return the messageService
	 */
	public IMessageService getMessageService() {
		return messageService;
	}
	
	
	/**
	 * Creates a real agent in the platform
	 * 
	 * @param name The name that the agent is gonna have in the platform
	 * @param path The path of the description (xml) of the agent 
	 */
	public void createAgent(String agent_name, String path) {
		IComponentIdentifier agent = cmsService.createComponent(agent_name, path, null, null).get(new ThreadSuspendable());
		createdAgents.put(agent_name, agent);
	}
	
	/**
	 * This method searches in createdAgents so given 
	 * an agent name returns its IComponentIdentifier
	 * 
	 * @param agent_name The name of the agent in the platform
	 * @return The IComponentIdentifier of the agent in the platform
	 */
	public IComponentIdentifier getAgentID(String agent_name) {
		
		return createdAgents.get(agent_name);
	}
	
	
	/**
	 * This method searches in the Component Management Servive,
	 * so given an agent name returns its IExternalAccess
	 * 
	 * @param agent_name The name of the agent in the platform
	 * @return The IComponentIdentifier of the agent in the platform
	 */
	protected IExternalAccess getAgentsExternalAccess(String agent_name) {
		
		return cmsService.getExternalAccess(getAgentID(agent_name)).get(new ThreadSuspendable());
	}
}
