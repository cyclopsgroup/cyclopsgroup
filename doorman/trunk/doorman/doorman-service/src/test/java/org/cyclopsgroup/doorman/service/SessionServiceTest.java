package org.cyclopsgroup.doorman.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.cyclopsgroup.doorman.api.SessionService;
import org.cyclopsgroup.doorman.api.User;
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
        service = (SessionService) applicationContext.getBean( SessionService.class.getName() );
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
        service.startSession( "111", newAttributes() );

        UserSession session = service.getSession( "111" );
        assertEquals( "111", session.getSessionId() );
        assertEquals( "en_US", session.getAttributes().getAcceptLanguage() );
        assertEquals( "test", session.getAttributes().getUserAgent() );
    }

    /**
     * Verify sign up process
     */
    @Test
    public void testSignUp()
    {
        service.startSession( "test-session", newAttributes() );

        User user = new User();
        user.setDisplayName( "Jiaqi" );
        user.setEmailAddress( "jiaqi@amazon.com" );
        user.setPassword( "password" );
        user.setUserName( "jiaqi@amazon.com" );
        String token = service.signUp( "test-session", user ).getToken();

        UserSession s = service.getSession( "test-session" );
        assertNull( s.getUser() );

        service.confirmSignUp( "test-session", token );
        s = service.getSession( "test-session" );
        user = s.getUser();
        assertNotNull( user );
        assertEquals( "Jiaqi", user.getDisplayName() );
    }
}
