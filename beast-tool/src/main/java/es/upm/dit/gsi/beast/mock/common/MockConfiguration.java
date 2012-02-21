package es.upm.dit.gsi.beast.mock.common;


/**
 * MockConfiguration, where it is saved the behaviour of the mock agents
 * dfServiceName is the name that the mock must publish in the DF behaviour is
 * where the information for answering messages is saved
 * 
 * @author Jorge Solitario
 */
public class MockConfiguration {

    private String dfServiceName;
    private AgentBehaviour behaviour;

    public MockConfiguration(String newDFServiceName,
            AgentBehaviour newBehaviour) {
        this.dfServiceName = newDFServiceName;
        this.behaviour = newBehaviour;
    }

    public MockConfiguration() {
    }

    /**
     * @return dfServiceName
     */
    public String getDFservice() {
        return this.dfServiceName;
    }

    /**
     * @return behaviour
     */
    public AgentBehaviour getBehaviour() {
        return this.behaviour;
    }

    /**
     * Saves the new name of agents df_service
     * 
     * @param dfService
     */
    public void setDFServiceName(String dfService) {
        this.dfServiceName = dfService;
    }

    /**
     * Saves the new name of agents behaviour
     * 
     * @param behaviour
     */
    public void setBehaviour(AgentBehaviour behaviour) {
        this.behaviour = behaviour;
    }
}