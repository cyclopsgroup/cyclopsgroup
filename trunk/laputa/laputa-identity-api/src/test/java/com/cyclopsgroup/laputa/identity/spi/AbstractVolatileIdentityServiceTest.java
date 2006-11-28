package com.cyclopsgroup.laputa.identity.spi;

import junit.framework.TestCase;

import com.cyclopsgroup.laputa.identity.Identity;
import com.cyclopsgroup.laputa.identity.NoSuchUserException;

public class AbstractVolatileIdentityServiceTest
    extends TestCase
{
    private AbstractVolatileIdentityService identityService;

    @Override
    protected void setUp()
        throws Exception
    {
        identityService = new AbstractVolatileIdentityService()
        {
            public AuthenticationResult authenticate( String userName, String password )
            {
                return AuthenticationResult.SUCCESSFUL;
            }

            @Override
            protected Identity createNewIdentity( String userName )
                throws NoSuchUserException
            {
                return new SimpleIdentity( userName );
            }
        };
    }

    @Override
    protected void tearDown()
        throws Exception
    {
        identityService.close();
    }

    public void testUsage()
        throws NoSuchUserException
    {
        String ticket = identityService.signIn( "test" );

        Identity id = identityService.getIdentity( ticket );
        assertNotNull( id );
        assertEquals( "test", id.getUserName() );

        identityService.signOut( ticket );
        assertNull( identityService.getIdentity( ticket ) );
    }

    public void testTimeout()
        throws NoSuchUserException, InterruptedException
    {
        identityService.setTimeout( 500L );
        identityService.setCheckInterval( 200L );
        String ticket = identityService.signIn( "test" );
        Thread.sleep( 300L );
        assertNotNull( identityService.getIdentity( ticket ) );
        Thread.sleep( 600L );
        assertNull( identityService.getIdentity( ticket ) );
    }
}
