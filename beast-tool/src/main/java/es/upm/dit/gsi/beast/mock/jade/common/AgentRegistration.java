package es.upm.dit.gsi.beast.mock.jade.common;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * Class to register the agent in the DF Service.
 * 
 * @author Alberto Mardomingo
 */
public class AgentRegistration {

    /**
     * Register the agent in the platform
     * 
     * @param agent_name
     *          The name of the agent to be registered
     * @param agent
     *          The agent to register.
     * @throws FIPAException 
     */
    public static void registerAgent(Agent agent, String agentType) throws FIPAException{
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        
        sd.setType(agentType);
        sd.setName(agent.getLocalName());
        
        //Add services??
        
        // Sets the agent description
        dfd.setName(agent.getAID());
        dfd.addServices(sd);
        
        // Register the agent
        DFService.register(agent, dfd);
    }

}
