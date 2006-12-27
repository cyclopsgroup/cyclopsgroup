package com.cyclopsgroup.laputa.identity.db;

import org.hibernate.Session;

import com.cyclopsgroup.arapaho.hibernate.HsqlHibernateTestCase;
import com.cyclopsgroup.laputa.identity.Identity;
import com.cyclopsgroup.laputa.identity.NoSuchUserException;
import com.cyclopsgroup.laputa.identity.IdentityService.AuthenticationResult;

public class DBIdentityServiceTest
    extends HsqlHibernateTestCase
{
    private DBIdentityService identityService;

    @Override
    protected void setUp()
        throws Exception
    {
        super.setUp();
        User user = new User();
        user.setUserName( "john" );
        Session s = sessionFactory.openSession();
        s.save( user );
        s.close();
        identityService = new DBIdentityService( sessionFactory );
    }

    public void testAuthenticate()
    {
        assertEquals( AuthenticationResult.SUCCESSFUL, identityService.authenticate( "john", "" ) );
        assertEquals( AuthenticationResult.NO_SUCH_USER, identityService.authenticate( "syz", "" ) );
    }

    public void testJohn()
        throws NoSuchUserException
    {
        String ticket = identityService.signIn( "john" );
        Identity id = identityService.getIdentity( ticket );
        assertNotNull( id );
        assertEquals( "john", id.getUserName() );
        identityService.signOut( ticket );
        assertNull( identityService.getIdentity( ticket ) );
    }

    public void testUnknownUser()
    {
        try
        {
            identityService.signIn( "unknownUser" );
            fail();
        }
        catch ( NoSuchUserException e )
        {
            assertEquals( "unknownUser", e.getUserName() );
        }
    }
}