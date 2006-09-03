package com.cyclopsgroup.whisperer.smtpproxy;

import org.apache.james.services.DNSServer;
import org.codehaus.plexus.PlexusTestCase;

public class EnvironmentTest
    extends PlexusTestCase
{
    public void testDependencies()
        throws Exception
    {
        assertNotNull( lookup( DNSServer.ROLE ) );
    }
}
