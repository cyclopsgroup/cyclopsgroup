package org.cyclopsgroup.doorman.service.hibernate;

import static org.junit.Assert.assertNull;

import org.cyclopsgroup.caff.util.UUIDUtils;
import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.cyclopsgroup.doorman.service.storage.StoredUserSession;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
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
        session.setCreationDate( new DateTime() );
        session.setIpAddress( "127.0.0.1" );
        session.setLastModified( new DateTime() );
        session.setLastVerification( new DateTime() );
        session.setSessionId( UUIDUtils.randomStringId() );
        session.setUserAgent( "ua" );
        dao.getHibernateTemplate().save( session );
        dao.getHibernateTemplate().flush();

        StoredUser user = Utils.createStoredUser( UUIDUtils.randomStringId() );
        dao.getHibernateTemplate().save( user );
        dao.getHibernateTemplate().flush();

        assertNull( session.getUser() );

        dao.getHibernateTemplate().flush();

        user = (StoredUser) dao.getHibernateTemplate().load( StoredUser.class, user.getUserId() );
        dao.updateUser( session.getSessionId(), user );
        dao.getHibernateTemplate().flush();
    }
}
