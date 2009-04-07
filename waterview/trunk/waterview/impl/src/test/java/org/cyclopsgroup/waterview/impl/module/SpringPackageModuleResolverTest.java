package org.cyclopsgroup.waterview.impl.module;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Test case of {@link SpringPackageModuleResolver}
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class SpringPackageModuleResolverTest
{
    private static final String PACKAGE = "org.cyclopsgroup.waterview.impl";

    /**
     * Load package {@value #PACKAGE} and verify beans
     */
    @Test
    public void testResolving()
    {
        ClassPathXmlApplicationContext parent = new ClassPathXmlApplicationContext( "test-beans.xml" );
        SpringPackageModuleResolver r = new SpringPackageModuleResolver( PACKAGE );
        r.setApplicationContext( parent );
        r.afterPropertiesSet();
        assertNotNull( r.findModule( "/index.vm" ) );
        assertNotNull( r.findModule( "_layout_/default_layout.vm" ) );
        assertNull( r.findModule( "/xyz.vm" ) );
    }
}
