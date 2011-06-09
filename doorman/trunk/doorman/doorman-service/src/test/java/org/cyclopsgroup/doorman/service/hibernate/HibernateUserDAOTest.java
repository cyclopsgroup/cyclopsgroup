package org.cyclopsgroup.doorman.service.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.cyclopsgroup.caff.util.UUIDUtils;
import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.cyclopsgroup.doorman.service.storage.StoredUserSignUpRequest;
import org.cyclopsgroup.service.security.PasswordStrategy;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Test of {@link HibernateUserDAO}
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@ContextConfiguration( locations = { "classpath:unit-test-context.xml" } )
public class HibernateUserDAOTest
    extends AbstractJUnit4SpringContextTests
{
    private HibernateUserDAO dao;

    /**
     * Prepare testing version of DAO
     */
    @Before
    public void setUpDao()
    {
        SessionFactory sf =
            (SessionFactory) applicationContext.getBeansOfType( SessionFactory.class ).values().iterator().next();
        dao = new HibernateUserDAO( sf );
    }

    /**
     * Verify create user works
     */
    @Test
    public void testCreateUser()
    {
        String id = UUIDUtils.randomStringId();
        StoredUserSignUpRequest request = new StoredUserSignUpRequest();
        request.setDisplayName( "haha" );
        request.setEmailAddress( id + "@cyclopsgroup.org" );
        request.setPassword( "pass" );
        request.setPasswordStrategy( PasswordStrategy.PLAIN );
        request.setUserName( id + "@cyclopsgroup.org" );
        request.setRequestDate( new DateTime() );
        request.setDomainName( "cyclopsgroup.org" );
        request.setRequestId( id );
        request.setRequestToken( id );

        dao.getHibernateTemplate().save( request );
        dao.createUser( id );

        StoredUser user = dao.findByNameOrId( id + "@cyclopsgroup.org" );
        assertNotNull( user );
        assertEquals( "pass", user.getPassword() );
    }

    /**
     * Insert a record and find it by name
     */
    @Test
    public void testFindByName()
    {
        String id = UUID.randomUUID().toString();

        StoredUser user = dao.findByNameOrId( id + "@cyclopsgroup.org" );
        assertNull( user );

        user = Utils.createStoredUser( id );
        dao.getHibernateTemplate().save( user );
        dao.getHibernateTemplate().flush();

        user = dao.findByNameOrId( id + "@cyclopsgroup.org" );
        assertNotNull( user );
        assertEquals( "pass", user.getPassword() );
    }
}
