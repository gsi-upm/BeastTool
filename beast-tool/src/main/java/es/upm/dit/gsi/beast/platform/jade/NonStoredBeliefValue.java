package es.upm.dit.gsi.beast.platform.jade;


public class NonStoredBeliefValue extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -5039955261109681904L;

    
    public NonStoredBeliefValue(String agent, String belief_name) {
        super("The agent belief " + belief_name + " for agent " + agent + " was not previously stored in the AgentInstrospector");
    }
}
