package com.cyclopsgroup.arapaho.plexus;

import org.codehaus.plexus.PlexusTestCase;

public class MBeanServerHomeTest
    extends PlexusTestCase
{
    public void testAvaliability()
        throws Exception
    {
        MBeanServerHome home = (MBeanServerHome) lookup( MBeanServerHome.ROLE );
        assertNotNull( home );
        assertNotNull( home.getMBeanServer() );
    }
}
