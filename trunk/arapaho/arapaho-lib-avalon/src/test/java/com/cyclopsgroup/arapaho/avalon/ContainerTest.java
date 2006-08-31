package com.cyclopsgroup.arapaho.avalon;

import org.codehaus.plexus.PlexusTestCase;

public class ContainerTest
    extends PlexusTestCase
{
    public void testComponent()
        throws Exception
    {
        Component c = (Component) lookup( Component.ROLE );
        assertNotNull( c );
        assertTrue( c.isInitialized() );
        assertEquals( "abc", c.getTestValue() );
    }
}
