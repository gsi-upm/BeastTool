package es.upm.dit.gsi.beast.platform.jade;

import jade.core.Agent;

import java.util.HashMap;

import es.upm.dit.gsi.beast.platform.AgentIntrospector;
import es.upm.dit.gsi.beast.platform.Connector;

/**
 * @author a.carrera
 *
 */
public class JadeAgentIntrospector implements AgentIntrospector {

    private static HashMap<String, HashMap<String, Object>> dataToTest;
    private static HashMap<String, Agent> agents;

    private static JadeAgentIntrospector INSTANCE = new JadeAgentIntrospector();

    /**
     * 
     */
    private JadeAgentIntrospector() {
        agents = new HashMap<String, Agent>();
        dataToTest = new HashMap<String, HashMap<String, Object>>();
    }

    /**
     * @return
     */
    public static JadeAgentIntrospector getInstance() {
        return INSTANCE;
    }

    /**
     * @param agent
     * @return
     */
    public static JadeAgentIntrospector getMyInstance(Agent agent) {
        HashMap<String, Object> believes = new HashMap<String, Object>();
        JadeAgentIntrospector.getInstance().getDataToTest().put(agent.getLocalName(), believes);
        return INSTANCE;
    }
    
    /**
     * @return
     */
    public HashMap<String, HashMap<String, Object>> getDataToTest() {
        return dataToTest;
    }

    /* (non-Javadoc)
     * @see es.upm.dit.gsi.beast.platform.AgentIntrospector#getBeliefValue(java.lang.String, java.lang.String, es.upm.dit.gsi.beast.platform.Connector)
     */
    @Override
    public Object getBeliefValue(String agent_name, String belief_name,
            Connector connector) {
        return JadeAgentIntrospector.dataToTest.get(agent_name).get(belief_name);
    }

    /* (non-Javadoc)
     * @see es.upm.dit.gsi.beast.platform.AgentIntrospector#setBeliefValue(java.lang.String, java.lang.String, java.lang.Object, es.upm.dit.gsi.beast.platform.Connector)
     */
    @Override
    public void setBeliefValue(String agent_name, String belief_name,
            Object new_value, Connector connector) {
        JadeAgentIntrospector.dataToTest.get(agent_name).put(belief_name, new_value);
    }

    /**
     * @param agent
     * @return
     */
    public HashMap<String, Object> retrieveBelievesValue(Agent agent) {
        return JadeAgentIntrospector.dataToTest.get(agent.getLocalName()); 
    }

    /**
     * @param agent
     * @param belief_name
     * @param new_value
     */
    public void storeBeliefValue(Agent agent, String belief_name,
            Object new_value) {
        JadeAgentIntrospector.agents.put(agent.getLocalName(), agent);
        JadeAgentIntrospector.dataToTest.get(agent.getLocalName()).put(belief_name, new_value);

    }
    
    /**
     * @param agentName
     * @return
     */
    public Agent getAgent(String agentName) {
        return JadeAgentIntrospector.agents.get(agentName);
    }

    /* (non-Javadoc)
     * @see es.upm.dit.gsi.beast.platform.AgentIntrospector#getAgentPlans(java.lang.String, es.upm.dit.gsi.beast.platform.Connector)
     */
    @Override
    public Object[] getAgentPlans(String agent_name, Connector connector) {
        // Not supported in JADE
        return null;
    }

    /* (non-Javadoc)
     * @see es.upm.dit.gsi.beast.platform.AgentIntrospector#getAgentGoals(java.lang.String, es.upm.dit.gsi.beast.platform.Connector)
     */
    @Override
    public Object[] getAgentGoals(String agent_name, Connector connector) {
        // Not supported in JADE
        return null;
    }

}
