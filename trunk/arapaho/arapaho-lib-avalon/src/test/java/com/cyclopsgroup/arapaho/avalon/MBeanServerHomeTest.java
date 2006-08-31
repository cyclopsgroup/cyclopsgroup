package com.cyclopsgroup.arapaho.avalon;

import org.codehaus.plexus.PlexusTestCase;

import com.cyclopsgroup.arapaho.avalon.MBeanServerHome;

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
