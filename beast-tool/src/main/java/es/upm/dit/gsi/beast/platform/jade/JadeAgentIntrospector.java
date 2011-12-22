package es.upm.dit.gsi.beast.platform.jade;

import jade.core.Agent;

import java.util.HashMap;

import es.upm.dit.gsi.beast.platform.AgentIntrospector;
import es.upm.dit.gsi.beast.platform.Connector;

public class JadeAgentIntrospector implements AgentIntrospector {

    private static HashMap<String, HashMap<String, Object>> dataToTest;

    private static JadeAgentIntrospector INSTANCE = new JadeAgentIntrospector();

    private JadeAgentIntrospector() {
        dataToTest = new HashMap<String, HashMap<String, Object>>();
    }

    public static JadeAgentIntrospector getInstance() {
        return INSTANCE;
    }

    public static JadeAgentIntrospector getMyInstance(Agent agent) {
        HashMap<String, Object> believes = new HashMap<String, Object>();
        JadeAgentIntrospector.getInstance().getDataToTest().put(agent.getLocalName(), believes);
        return INSTANCE;
    }
    
    public HashMap<String, HashMap<String, Object>> getDataToTest() {
        return dataToTest;
    }

    @Override
    public Object getBeliefValue(String agent_name, String belief_name,
            Connector connector) {
        return JadeAgentIntrospector.dataToTest.get(agent_name).get(belief_name);
    }

    @Override
    public void setBeliefValue(String agent_name, String belief_name,
            Object new_value, Connector connector) {
        JadeAgentIntrospector.dataToTest.get(agent_name).put(belief_name, new_value);
    }

    public HashMap<String, Object> retrieveBelievesValue(Agent agent) {
        return JadeAgentIntrospector.dataToTest.get(agent.getLocalName()); 
    }

    public void storeBeliefValue(Agent agent, String belief_name,
            Object new_value) {
        JadeAgentIntrospector.dataToTest.get(agent.getLocalName()).put(belief_name, new_value);

    }

    @Override
    public Object[] getAgentPlans(String agent_name, Connector connector) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] getAgentGoals(String agent_name, Connector connector) {
        // TODO Auto-generated method stub
        return null;
    }

}
