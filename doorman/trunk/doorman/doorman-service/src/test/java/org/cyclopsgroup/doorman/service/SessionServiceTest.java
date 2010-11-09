package org.cyclopsgroup.doorman.service;

import static org.junit.Assert.assertEquals;

import org.cyclopsgroup.doorman.api.SessionService;
import org.cyclopsgroup.doorman.api.User;
import org.cyclopsgroup.doorman.api.UserSession;
import org.cyclopsgroup.doorman.api.UserSessionAttributes;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class SessionServiceTest
{
    private SessionService service;

    /**
     * Prepare the testing service implementation
     */
    @Before
    public void setUpService()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext( "unit-test-context.xml" );
        service = (SessionService) context.getBean( SessionService.class.getName() );
    }

    /**
     * Start a session and get it
     */
    @Test
    public void testStartAndGet()
    {
        UserSessionAttributes attributes = new UserSessionAttributes();
        attributes.setAcceptLanguage( "en_US" );
        attributes.setUserAgent( "test" );
        service.startSession( "111", attributes );

        UserSession session = service.getSession( "111" );
        assertEquals( "111", session.getSessionId() );
        assertEquals( "en_US", session.getAttributes().getAcceptLanguage() );
        assertEquals( "test", session.getAttributes().getUserAgent() );
    }

    /**
     * Verify sign up process
     */
    @Test
    public void testSignup()
    {
        User user = new User();
        user.setDisplayName( "Jiaqi" );
        user.setEmailAddress( "jiaqi@amazon.com" );
        user.setPassword( "password" );
        user.setUserName( "jiaqi@amazon.com" );
        service.signUp( "test-session", user );
    }
}
