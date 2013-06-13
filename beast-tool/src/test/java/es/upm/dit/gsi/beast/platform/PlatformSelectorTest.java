/**
 * 
 */
package es.upm.dit.gsi.beast.platform;

import junit.framework.Assert;

import org.junit.Test;

import es.upm.dit.gsi.beast.platform.jade.JadeAgentIntrospector;
import es.upm.dit.gsi.beast.platform.jade.JadeConnector;
import es.upm.dit.gsi.beast.platform.jade.JadeMessenger;
import es.upm.dit.gsi.beast.platform.jadex.JadexAgentIntrospector;
import es.upm.dit.gsi.beast.platform.jadex.JadexConnector;
import es.upm.dit.gsi.beast.platform.jadex.JadexMessenger;

/**
 * @author a.carrera
 *
 */
public class PlatformSelectorTest {

    @Test
    public void NullConnectorTest() {
        Object connector = PlatformSelector.getConnector("no", null);
        Assert.assertNull(connector);
    }
    
    @Test
    public void NullMessengerTest() {
        Object messenger = PlatformSelector.getMessenger("no");
        Assert.assertNull(messenger);
    }
    
    @Test
    public void NullIntrospectorTest() {
        Object introspector = PlatformSelector.getAgentIntrospector("no");
        Assert.assertNull(introspector);
    }
    
    @Test
    public void JadeConnectorTest() {
        JadeConnector connector = (JadeConnector) PlatformSelector.getConnector("Jade", null);
        Assert.assertNotNull(connector);
    }
    
    @Test
    public void JadeMessengerTest() {
        JadeMessenger messenger = (JadeMessenger) PlatformSelector.getMessenger("Jade");
        Assert.assertNotNull(messenger);
    }
    
    @Test
    public void JadeIntrospectorTest() {
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector.getAgentIntrospector("Jade");
        Assert.assertNotNull(introspector);
    }
    
    @Test
    public void JadeConnectorLowerTest() {
        JadeConnector connector = (JadeConnector) PlatformSelector.getConnector("jade", null);
        Assert.assertNotNull(connector);
    }
    
    @Test
    public void JadeMessengerLowerTest() {
        JadeMessenger messenger = (JadeMessenger) PlatformSelector.getMessenger("jade");
        Assert.assertNotNull(messenger);
    }
    
    @Test
    public void JadeIntrospectorLowerTest() {
        JadeAgentIntrospector introspector = (JadeAgentIntrospector) PlatformSelector.getAgentIntrospector("jade");
        Assert.assertNotNull(introspector);
    }
    
    @Test
    public void JadexConnectorTest() {
        JadexConnector connector = (JadexConnector) PlatformSelector.getConnector("Jadex", null);
        Assert.assertNotNull(connector);
    }
    
    @Test
    public void JadexMessengerTest() {
        JadexMessenger messenger = (JadexMessenger) PlatformSelector.getMessenger("Jadex");
        Assert.assertNotNull(messenger);
    }
    
    @Test
    public void JadexIntrospectorTest() {
        JadexAgentIntrospector introspector = (JadexAgentIntrospector) PlatformSelector.getAgentIntrospector("Jadex");
        Assert.assertNotNull(introspector);
    }
    
    @Test
    public void JadexConnectorLowerTest() {
        JadexConnector connector = (JadexConnector) PlatformSelector.getConnector("jadex", null);
        Assert.assertNotNull(connector);
    }
    
    @Test
    public void JadexMessengerLowerTest() {
        JadexMessenger messenger = (JadexMessenger) PlatformSelector.getMessenger("jadex");
        Assert.assertNotNull(messenger);
    }
    
    @Test
    public void JadexIntrospectorLowerTest() {
        JadexAgentIntrospector introspector = (JadexAgentIntrospector) PlatformSelector.getAgentIntrospector("jadex");
        Assert.assertNotNull(introspector);
    }
    
}
