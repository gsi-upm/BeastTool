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

        boolean finish = false;
        IGoal[]  value = null;
        while (!finish) {
            value = introspector.getAgentGoals("Tester", connector);
             if (value.length>0) {
                 finish = true;
             }
        }
        Assert.assertTrue(value instanceof IGoal[]);
        Assert.assertNotNull(value[0]);
        Assert.assertEquals(value[0].getType(), "live");
    }
    
    
    @Test
    public void getPlansFromAgent() {

        Logger logger = Logger.getLogger(JadexPlatformBeastTest.class.getName());
        JadexConnector connector = (JadexConnector) PlatformSelector.getConnector("jadex", logger);
        JadexAgentIntrospector introspector = (JadexAgentIntrospector) PlatformSelector.getAgentIntrospector("jadex");
        connector.launchPlatform();
        connector.createAgent("Tester", "es/upm/dit/gsi/beast/test/agent/jadex/TesterAgent.agent.xml");

        boolean finish = false;
        IPlan[] value = null;
        while (!finish) {
             value = introspector.getAgentPlans("Tester", connector);
             if (value.length>0) {
                 finish = true;
             }
        }
        Assert.assertTrue(value instanceof IPlan[]);
        Assert.assertTrue(value.length>0);
        Assert.assertEquals(value[0].getType(), "survive");
    }

    
    
}
