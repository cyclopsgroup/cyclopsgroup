package org.cyclopsgroup.doorman.service.hibernate;

import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.UUID;

import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.cyclopsgroup.doorman.service.storage.StoredUserSession;
import org.cyclopsgroup.doorman.service.storage.StoredUserState;
import org.cyclopsgroup.doorman.service.storage.StoredUserType;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Test cases for {@link hibernateUserSessionDAO}
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@ContextConfiguration( locations = { "classpath:unit-test-context.xml" } )
public class HibernateUserSessionDAOTest
    extends AbstractTransactionalJUnit4SpringContextTests
{
    private HibernateUserSessionDAO dao;

    /**
     * Prepare testing DAO
     */
    @Before
    public void setUpDao()
    {
        SessionFactory sf =
            (SessionFactory) applicationContext.getBeansOfType( SessionFactory.class ).values().iterator().next();
        dao = new HibernateUserSessionDAO( sf );
        dao.getHibernateTemplate().setAllowCreate( true );
    }

    /**
     * Test update session
     */
    @Test
    public void testUpdateSession()
    {
        StoredUserSession session = new StoredUserSession();
        session.setAcceptLanguage( "us" );
        session.setCreationDate( new Date() );
        session.setIpAddress( "127.0.0.1" );
        session.setLastModified( new Date() );
        session.setLastVerification( new Date() );
        session.setSessionId( UUID.randomUUID().toString() );
        session.setUserAgent( "ua" );
        dao.getHibernateTemplate().save( session );
        dao.getHibernateTemplate().flush();

        StoredUser user = new StoredUser();
        user.setDisplayName( "haha" );
        user.setEmailAddress( "x@cyclopsgroup.org" );
        user.setLastModified( new Date() );
        user.setPassword( "pass" );

        String id = UUID.randomUUID().toString();
        user.setUserId( id );
        user.setUserName( id + "@cyclopsgroup.org" );
        user.setUserState( StoredUserState.ACTIVE );
        user.setCreationDate( new Date() );
        user.setDomainName( "cyclopsgroup.org" );
        user.setUserType( StoredUserType.LOCAL );
        dao.getHibernateTemplate().save( user );
        dao.getHibernateTemplate().flush();

        assertNull( session.getUser() );

        dao.getHibernateTemplate().flush();

        user = (StoredUser) dao.getHibernateTemplate().load( StoredUser.class, user.getUserId() );
        dao.updateUser( session.getSessionId(), user );
        dao.getHibernateTemplate().flush();
    }
}
