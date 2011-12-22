package es.upm.dit.gsi.beast.platform.jade;

import java.util.HashMap;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Test;

import es.upm.dit.gsi.beast.platform.PlatformSelector;

public class JadeIntrospectorTest {

    @Test
    public void setBeliefStringWithoutDownCasting() {
        //Setup
        Logger logger = Logger.getLogger(JadeIntrospectorTest.class.getName());
        JadeConnector connector = (JadeConnector) PlatformSelector.getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector.getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent", new HashMap<String, Object>());//The same that JadeAgentIntrospector.getMyInstance((Agent) this);
        
        // Set
        introspector.setBeliefValue("myAgent", "test", "true", connector);
        // Get
        Object value = introspector.getBeliefValue("myAgent", "test", connector);
        
        // Assert
        Assert.assertEquals("true", value);
    }
    
    @Test
    public void setBeliefStringWithDownCasting() {
        //Setup
        Logger logger = Logger.getLogger(JadeIntrospectorTest.class.getName());
        JadeConnector connector = (JadeConnector) PlatformSelector.getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector.getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent", new HashMap<String, Object>());//The same that JadeAgentIntrospector.getMyInstance((Agent) this);
        
        // Set
        introspector.setBeliefValue("myAgent", "test", "true", connector);
        // Get
        String value = (String) introspector.getBeliefValue("myAgent", "test", connector);
        
        // Assert
        Assert.assertEquals("true", value);
    }
    
    @Test
    public void setBeliefBooleanWithoutDownCasting() {
        //Setup
        Logger logger = Logger.getLogger(JadeIntrospectorTest.class.getName());
        JadeConnector connector = (JadeConnector) PlatformSelector.getConnector("jade", logger);
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector.getAgentIntrospector("jade");
        introspector.getDataToTest().put("myAgent", new HashMap<String, Object>());//The same that JadeAgentIntrospector.getMyInstance((Agent) this);
        
        // Set
        introspector.setBeliefValue("myAgent", "test", true, connector);
        // Get
        Object value = introspector.getBeliefValue("myAgent", "test", connector);
        
        // Assert
        Assert.assertEquals("true", value);
        
        //FIXME este test no tira
    }
    
}
