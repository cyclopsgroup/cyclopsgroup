package com.cyclopsgroup.laputa.identity.db;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.cyclopsgroup.laputa.identity.Identity;
import com.cyclopsgroup.laputa.identity.NoSuchUserException;
import com.cyclopsgroup.laputa.identity.IdentityService.AuthenticationResult;

public class DBIdentityServiceTest
    extends TestCase
{
    private DBIdentityService identityService;

    private SessionFactory sessionFactory;

    @Override
    protected void setUp()
        throws Exception
    {
        //TODO Decouple this logic to somewhere else
        AnnotationConfiguration conf = new AnnotationConfiguration();
        conf.setProperty( "hibernate.dialect", "org.hibernate.dialect.HSQLDialect" );
        conf.setProperty( "hibernate.connection.driver_class", "org.hsqldb.jdbcDriver" );
        conf.setProperty( "hibernate.connection.url", "jdbc:hsqldb:mem:test" );
        conf.setProperty( "hibernate.connection.username", "sa" );
        conf.setProperty( "hibernate.connection.password", "" );
        conf.setProperty( "hibernate.connection.pool_size", "1" );
        conf.setProperty( "hibernate.connection.autocommit", "true" );
        conf.setProperty( "hibernate.cache.provider_class", "org.hibernate.cache.HashtableCacheProvider" );
        conf.setProperty( "hibernate.hbm2ddl.auto", "create-drop" );
        conf.setProperty( "hibernate.show_sql", "true" );
        conf.addAnnotatedClass( User.class );
        sessionFactory = conf.buildSessionFactory();

        User user = new User();
        user.setUserName( "john" );
        Session s = sessionFactory.openSession();
        s.save( user );
        s.close();
        identityService = new DBIdentityService( sessionFactory );
    }

    @Override
    protected void tearDown()
        throws Exception
    {
        sessionFactory.close();
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
