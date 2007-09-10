package com.cyclopsgroup.nimbus;

import junit.framework.TestCase;

/**
 * @author jiaqi
 * 
 * @version $Id:$
 */
public class HelloTest
    extends TestCase
{
    public void testRun()
    {
        ClientContext ctx = new SingleServiceMockClientContext( null );
        ctx.registerService( new HelloClient() );
        Hello h = (Hello) ctx.createClientProxy( Hello.SERVICE_CLIENT_ID, Hello.class );
        assertEquals( "Hello, Bender", h.sayHello( "Bender" ) );
    }
}
