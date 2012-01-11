package es.upm.dit.gsi.beast.platform.jadex;

import jadex.bdi.runtime.IGoal;
import jadex.bdi.runtime.IPlan;

import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.Test;
import es.upm.dit.gsi.beast.platform.PlatformSelector;

/**
 * Test class to test JadexAgentIntrospector
 * 
 * @author jjsolitario
 * @version 1.0
 *
 */

public class JadexPlatformBeastTest {
        
    @Test
    public void getGoalsFromAgent() {

        Logger logger = Logger.getLogger(JadexPlatformBeastTest.class.getName());
        JadexConnector connector = (JadexConnector) PlatformSelector.getConnector("jadex", logger);
        JadexAgentIntrospector introspector = (JadexAgentIntrospector) PlatformSelector.getAgentIntrospector("jadex");
        connector.launchPlatform();
        connector.createAgent("Tester", "es/upm/dit/gsi/beast/test/agent/jadex/TesterAgent.agent.xml");

        IGoal[] value = introspector.getAgentGoals("Tester", connector);
        Assert.assertEquals(value[0].getType(), "live");
    }
    
    
    @Test
    public void getPlansFromAgent() {

        Logger logger = Logger.getLogger(JadexPlatformBeastTest.class.getName());
        JadexConnector connector = (JadexConnector) PlatformSelector.getConnector("jadex", logger);
        JadexAgentIntrospector introspector = (JadexAgentIntrospector) PlatformSelector.getAgentIntrospector("jadex");
        connector.launchPlatform();
        connector.createAgent("Tester", "es/upm/dit/gsi/beast/test/agent/jadex/TesterAgent.agent.xml");

        IPlan[] value = introspector.getAgentPlans("Tester", connector);
        Assert.assertEquals(value[0].getType(), "survive");
    }

    
    
}
