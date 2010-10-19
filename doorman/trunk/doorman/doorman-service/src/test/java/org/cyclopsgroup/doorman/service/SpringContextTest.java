package org.cyclopsgroup.doorman.service;

import org.cyclopsgroup.doorman.api.SessionService;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringContextTest
{
    @Test
    public void testContext()
    {
        FileSystemXmlApplicationContext context =
            new FileSystemXmlApplicationContext( "src/test/config/unit-test-context.xml" );
        SessionService service = (SessionService) context.getBean( SessionService.class.getName() );
        service.getSession( "111" );
    }
}
