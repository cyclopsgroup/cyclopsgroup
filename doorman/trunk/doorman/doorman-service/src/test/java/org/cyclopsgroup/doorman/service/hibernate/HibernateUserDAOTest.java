package org.cyclopsgroup.doorman.service.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.UUID;

import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.cyclopsgroup.doorman.service.storage.StoredUserSignUpRequest;
import org.cyclopsgroup.doorman.service.storage.StoredUserState;
import org.hibernate.SessionFactory;
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
        SessionFactory sf = (SessionFactory) applicationContext.getBean( "org.cyclopsgroup.doorman.SessionFactory" );
        dao = new HibernateUserDAO( sf );
    }

    /**
     * Verify create user works
     */
    @Test
    public void testCreateUser()
    {
        String id = UUID.randomUUID().toString();
        StoredUserSignUpRequest request = new StoredUserSignUpRequest();
        request.setDisplayName( "haha" );
        request.setEmailAddress( id + "@cyclopsgroup.org" );
        request.setPassword( "pass" );
        request.setUserName( id + "@cyclopsgroup.org" );
        request.setRequestDate( new Date() );

        request.setRequestId( id );
        request.setRequestToken( id );

        dao.getHibernateTemplate().save( request );
        dao.createUser( id, null );

        StoredUser user = dao.findByName( id + "@cyclopsgroup.org" );
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

        StoredUser user = dao.findByName( id + "@cyclopsgroup.org" );
        assertNull( user );

        user = new StoredUser();
        user.setDisplayName( "haha" );
        user.setEmailAddress( id + "@cyclopsgroup.org" );
        user.setLastModified( new Date() );
        user.setPassword( "pass" );
        user.setUserId( UUID.randomUUID().toString() );
        user.setUserName( id + "@cyclopsgroup.org" );
        user.setUserState( StoredUserState.ACTIVE );

        dao.getHibernateTemplate().save( user );
        dao.getHibernateTemplate().flush();

        user = dao.findByName( id + "@cyclopsgroup.org" );
        assertNotNull( user );
        assertEquals( "pass", user.getPassword() );
    }
}
