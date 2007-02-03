package com.cyclopsgroup.laputa.identity.db;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cache.HashtableCacheProvider;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.dialect.HSQLDialect;
import org.hsqldb.jdbcDriver;

import com.cyclopsgroup.laputa.identity.Identity;
import com.cyclopsgroup.laputa.identity.NoSuchUserException;
import com.cyclopsgroup.laputa.identity.IdentityService.AuthenticationResult;

public class DBIdentityServiceTest
    extends TestCase
{
    private DBIdentityService identityService;

    @Override
    protected void setUp()
        throws Exception
    {
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.configure( getClass().getResource( "hibernate.xml" ) );
        cfg.setProperty( "hibernate.dialect", HSQLDialect.class.getName() );
        cfg.setProperty( "hibernate.connection.driver_class", jdbcDriver.class.getName() );
        cfg.setProperty( "hibernate.connection.url", "jdbc:hsqldb:mem:test" );
        cfg.setProperty( "hibernate.connection.username", "sa" );
        cfg.setProperty( "hibernate.connection.pool_size", "1" );
        cfg.setProperty( "hibernate.connection.autocommit", "true" );
        cfg.setProperty( "hibernate.cache.provider_class", HashtableCacheProvider.class.getName() );
        cfg.setProperty( "hibernate.hbm2ddl.auto", "create-drop" );
        cfg.setProperty( "hibernate.show_sql", "true" );
        SessionFactory sessionFactory = cfg.buildSessionFactory();

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