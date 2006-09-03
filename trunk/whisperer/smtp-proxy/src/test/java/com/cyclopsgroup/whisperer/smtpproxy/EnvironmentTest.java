package com.cyclopsgroup.whisperer.smtpproxy;

import org.apache.avalon.cornerstone.services.datasources.DataSourceSelector;
import org.apache.avalon.cornerstone.services.threads.ThreadManager;
import org.apache.james.services.DNSServer;
import org.codehaus.plexus.PlexusTestCase;

public class EnvironmentTest
    extends PlexusTestCase
{
    public void testDependencies()
        throws Exception
    {
        assertNotNull( lookup( DNSServer.ROLE ) );
        assertNotNull( lookup( ThreadManager.ROLE ) );
        assertNotNull( lookup( DataSourceSelector.ROLE ) );
    }
}
