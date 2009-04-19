package org.cyclopsgroup.waterview.impl.pipeline;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Sanity check for configuration files
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class ConfigurationTest
{
    private ApplicationContext context;

    /**
     * Load default spring context initially
     */
    @Before
    public void setUpApplicationContext()
    {
        context = new ClassPathXmlApplicationContext( "org/cyclopsgroup/waterview/impl/simple-processor.xml" );
    }

    /**
     * Verify simple context processor is configured properly
     */
    @Test
    public void testSimpleProcessor()
    {
        WebContextProcessor processor = (WebContextProcessor) context.getBean( "waterview.SimpleContextProcessor" );
        assertNotNull( processor );
    }
}
