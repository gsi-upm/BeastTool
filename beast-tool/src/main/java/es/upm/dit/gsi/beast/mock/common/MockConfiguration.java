package es.upm.dit.gsi.beast.mock.common;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.mock.common.MockConfiguration.java
 * 
 * MockConfiguration, where it is saved the behaviour of the mock agents
 * dfServiceName is the name that the mock must publish in the DF behaviour is
 * where the information for answering messages is saved
 * 
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * @author Jorge Solitario
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @version 0.1
 * 
 */
public class MockConfiguration {

    
    //TODO Service_Type could be a custom field of MockCondiguration.
    
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