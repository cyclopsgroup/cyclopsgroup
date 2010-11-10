package org.cyclopsgroup.doorman.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * A dummy test that verifies configuration xml file
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@ContextConfiguration( locations = { "classpath:unit-test-context.xml" } )
public class SpringContextTest
    extends AbstractJUnit4SpringContextTests
{
    /**
     * A dummy test that verifies context is created successfully
     */
    @Test
    public void testContext()
    {
        assertNotNull( applicationContext );
    }
}
