package com.cyclopsgroup.laputa.identity.ws.client;

import junit.framework.TestCase;

public class IdentityServiceWebServiceClientTest
    extends TestCase
{
    public void testDummy()
        throws Exception
    {
        IdentityServiceWebServiceClient client = new IdentityServiceWebServiceClient( "local://localhost:8080" );
        client.authenticate( "test", "test" );
    }
}
