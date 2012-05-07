package es.upm.dit.gsi.beast.mock;

import jadex.base.fipa.SFipa;
import es.upm.dit.gsi.beast.mock.common.MockConfiguration;
import es.upm.dit.gsi.beast.story.BeastTestCase;

/**
 * This class creates mock agents on the Scenario
 * 
 * @author Álvaro Carrera Barroso
 */
public class MockManager {

    /**
     * This method is used to launch mock agents. First it creates them, with
     * the generic df_service_name \"mock_agent\", and then the method sends to
     * the agent a message with the new df_service_name and its behaviour.
     * 
     * @param agent_name
     *            The name of the mock agent
     * @param agent_path
     *            The path of the agent, described in
     *            mocks/jadex/common/Definitions file
     * @param configuration
     *            Where the new df_service_name and the agents behaviour is
     *            saved
     * @param scenario
     *            The Scenario of the Test
     */
    public static void startMockJadexAgent(String agent_name,
            String agent_path, MockConfiguration configuration,
            BeastTestCase story) {

        story.startAgent(agent_name, agent_path);
        story.sendMessageToAgent(agent_name, SFipa.INFORM, configuration);
    }

    
    public static void startMockJadeAgent(String agent_name, String agent_path, 
                                            MockConfiguration configuration, 
                                            BeastTestCase story) {

//        scenario.startAgent(agent_name, agent_path);
        Object [] arguments = new Object[1];
        arguments[0] = configuration;
        story.startAgent(agent_name, agent_path, "MockContainer", arguments);
//        scenario.sendMessageToAgent(agent_name, SFipa.INFORM, configuration);
    }
}
