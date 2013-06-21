package es.upm.dit.gsi.beast.mock.jade.common;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.mock.jade.common.AgentRegistration.java
 * 
 * Class to register the agent in the DF Service.
 * 
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * @author Alberto Mardomingo
 * @version 0.1
 * 
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
    public static void registerAgent(Agent agent, String serviceName, String serviceType) throws FIPAException{
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        
        sd.setType(serviceType);
        sd.setName(serviceName);
        
        //NOTE El serviceType es un string que define el tipo de servicio publicado en el DF por el Agente X. 
        //     He escogido crear nombres en clave en jade.common.Definitions para este campo. 
        //NOTE El serviceName es el nombre efectivo del servicio. 
        //     Esto es lo que el usuario va a definir en MockConfiguration.DFNameService y no el tipo como estaba puesto. 
        //        sd.setType(agentType);
        //        sd.setName(agent.getLocalName());
        
        //Add services??
        
        // Sets the agent description
        dfd.setName(agent.getAID());
        dfd.addServices(sd);
        
        // Register the agent
        DFService.register(agent, dfd);
    }

}
