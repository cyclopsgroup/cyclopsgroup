package com.cyclopsgroup.waterview.velocity;

import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.cyclopsgroup.waterview.impl.velocity.DeepClassPathResourceLoader;
import org.cyclopsgroup.waterview.impl.velocity.VelocityEngineBuilder;
import org.junit.Test;

public class VelocityEngineBuilderTest
{
    /**
     * @throws Exception
     */
    @Test
    public void testLoadDefaultLayout() throws Exception
    {
        Properties p = new Properties();
        p.setProperty( "class.resource.loader.class", DeepClassPathResourceLoader.class.getName() );
        p.setProperty( "class.resource.loader.prefix", "waterview/template/" );
        p.setProperty( "resource.loader", "class" );
        VelocityEngine engine =
            new VelocityEngineBuilder().setProperties( p ).newEngine();
        Template t = engine.getTemplate( "_layout_/default_layout.vm" );
        assertNotNull( t );
    }
}
