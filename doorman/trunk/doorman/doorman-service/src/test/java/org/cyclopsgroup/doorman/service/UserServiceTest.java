package org.cyclopsgroup.doorman.service;

import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.cyclopsgroup.doorman.api.ListUserRequest;
import org.cyclopsgroup.doorman.api.UserService;
import org.cyclopsgroup.doorman.api.Users;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Tests of {@link UserService}
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@ContextConfiguration( locations = { "classpath:unit-test-context.xml" } )
public class UserServiceTest
    extends AbstractJUnit4SpringContextTests
{
    private UserService service;

    /**
     * Prepare the user service for testing
     */
    @Before
    public void setUpService()
    {
        service = (UserService) applicationContext.getBeansOfType( UserService.class ).values().iterator().next();
    }

    /**
     * Verify empty list of result is handled
     */
    @Test
    public void testListWithEmptyResult()
    {
        Users users = service.list( new ListUserRequest( Collections.<String> emptyList() ) );
        assertTrue( users.getUsers().isEmpty() );
    }
}
