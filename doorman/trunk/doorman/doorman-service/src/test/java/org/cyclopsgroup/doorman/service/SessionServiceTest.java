package org.cyclopsgroup.doorman.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.cyclopsgroup.caff.util.UUIDUtils;
import org.cyclopsgroup.doorman.api.SessionService;
import org.cyclopsgroup.doorman.api.User;
import org.cyclopsgroup.doorman.api.UserService;
import org.cyclopsgroup.doorman.api.UserSession;
import org.cyclopsgroup.doorman.api.UserSessionAttributes;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@ContextConfiguration( locations = { "classpath:unit-test-context.xml" } )
public class SessionServiceTest
    extends AbstractJUnit4SpringContextTests
{
    private SessionService service;

    /**
     * Prepare the testing service implementation
     */
    @Before
    public void setUpService()
    {
        service = (SessionService) applicationContext.getBeansOfType( SessionService.class ).values().iterator().next();
    }

    private static UserSessionAttributes newAttributes()
    {
        UserSessionAttributes attributes = new UserSessionAttributes();
        attributes.setAcceptLanguage( "en_US" );
        attributes.setUserAgent( "test" );
        return attributes;
    }

    /**
     * Start a session and get it
     */
    @Test
    public void testStartAndGet()
    {
        String id = UUIDUtils.randomStringId();
        service.startSession( id, newAttributes() );

        UserSession session = service.getSession( id );
        assertEquals( id, session.getSessionId() );
        assertEquals( "en_US", session.getAttributes().getAcceptLanguage() );
        assertEquals( "test", session.getAttributes().getUserAgent() );
    }

    /**
     * Verify sign up process
     */
    @Test
    public void testRequestSignUpAndConfirm()
    {
        String id = UUID.randomUUID().toString();
        service.startSession( id, newAttributes() );

        User user = new User();
        user.setUserId( UUIDUtils.randomStringId() );
        user.setDisplayName( "Jiaqi" );
        user.setEmailAddress( id + "@cyclopsgroup.org" );
        user.setPassword( "password" );
        user.setUserName( id + "@cyclopsgroup.org" );
        user.setDomainName( "cyclopsgroup.org" );
        String token = service.requestSignUp( id, user ).getToken();

        UserSession s = service.getSession( id );
        assertNull( s.getUser() );

        service.confirmSignUp( id, token );
        s = service.getSession( id );
        user = s.getUser();
        assertNotNull( user );
        assertEquals( "Jiaqi", user.getDisplayName() );
    }

    /**
     * Verify user sign up process
     */
    @Test
    public void testSignUp()
    {
        String id = UUID.randomUUID().toString();
        service.startSession( id, newAttributes() );

        User user = new User();
        user.setDisplayName( "Jiaqi" );
        user.setEmailAddress( id + "@cyclopsgroup.org" );
        user.setPassword( "password" );
        user.setUserName( id + "@cyclopsgroup.org" );
        user.setDomainName( "cyclopsgroup.org" );

        service.signUp( id, user );

        UserSession s = service.getSession( id );
        assertEquals( id + "@cyclopsgroup.org", s.getUser().getUserName() );

        UserService users =
            (UserService) applicationContext.getBeansOfType( UserService.class ).values().iterator().next();
        users.authenticate( id + "@cyclopsgroup.org", "password" );
    }
}
