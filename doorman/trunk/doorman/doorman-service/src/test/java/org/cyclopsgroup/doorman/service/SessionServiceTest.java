package org.cyclopsgroup.doorman.service;

import static org.junit.Assert.assertEquals;

import org.cyclopsgroup.doorman.api.SessionService;
import org.cyclopsgroup.doorman.api.UserSession;
import org.cyclopsgroup.doorman.api.UserSessionAttributes;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SessionServiceTest
{
    @Test
    public void testStartAndGet()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext( "unit-test-context.xml" );
        SessionService service = (SessionService) context.getBean( SessionService.class.getName() );
        UserSessionAttributes attributes = new UserSessionAttributes();
        attributes.setAcceptLanguage( "en_US" );
        attributes.setUserAgent( "test" );
        service.startSession( "111", attributes );

        UserSession session = service.getSession( "111" );
        assertEquals( "111", session.getSessionId() );
        assertEquals( "en_US", session.getAttributes().getAcceptLanguage() );
        assertEquals( "test", session.getAttributes().getUserAgent() );
    }
}
