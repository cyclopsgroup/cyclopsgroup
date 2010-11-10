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
        StoredUserSignUpRequest request = new StoredUserSignUpRequest();
        request.setDisplayName( "haha" );
        request.setEmailAddress( "y@cyclopsgroup.org" );
        request.setPassword( "pass" );
        request.setUserName( "y@cyclopsgroup.org" );
        request.setRequestDate( new Date() );

        String token = UUID.randomUUID().toString();
        request.setRequestId( token );
        request.setRequestToken( token );

        dao.getHibernateTemplate().save( request );
        dao.createUser( token, null );

        StoredUser user = dao.findByName( "y@cyclopsgroup.org" );
        assertNotNull( user );
        assertEquals( "pass", user.getPassword() );
    }

    /**
     * Insert a record and find it by name
     */
    @Test
    public void testFindByName()
    {
        StoredUser user = dao.findByName( "x@cyclopsgroup.org" );
        assertNull( user );

        user = new StoredUser();
        user.setDisplayName( "haha" );
        user.setEmailAddress( "x@cyclopsgroup.org" );
        user.setLastModified( new Date() );
        user.setPassword( "pass" );
        user.setUserId( UUID.randomUUID().toString() );
        user.setUserName( "x@cyclopsgroup.org" );
        user.setUserState( StoredUserState.ACTIVE );

        dao.getHibernateTemplate().save( user );
        dao.getHibernateTemplate().flush();

        user = dao.findByName( "x@cyclopsgroup.org" );
        assertNotNull( user );
        assertEquals( "pass", user.getPassword() );
    }
}
