package org.cyclopsgroup.larc.serv.model;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Sanity check of spring configuration
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class SpringConfigurationTest
{
    /**
     * Make sure there's no horrible mistake in spring configuration and dependencies by loading it
     */
    @Test
    public void testConfifuration()
    {
        new ClassPathXmlApplicationContext( "org/cyclopsgroup/larc/serv/in-mem-beans.xml" );
    }
}
