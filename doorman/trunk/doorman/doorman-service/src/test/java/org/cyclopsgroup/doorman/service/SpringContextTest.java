package org.cyclopsgroup.doorman.service;

import static org.junit.Assert.assertNotNull;

import org.cyclopsgroup.doorman.api.SessionService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextTest
{
    @Test
    public void testContext()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext( "unit-test-context.xml" );
        SessionService service = (SessionService) context.getBean( SessionService.class.getName() );
        assertNotNull( service );
    }
}
