package com.cyclopsgroup.waterview.velocity;

import java.io.Writer;
import java.util.Properties;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.app.VelocityEngine;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.spi.ResourceRegistry;
import com.cyclopsgroup.waterview.spi.TemplateEngine;

public class VelocityTemplateEngine
    implements TemplateEngine
{
    private VelocityEngine velocityEngine;

    private static final String INTERNAL_RESOURCE_LOADER = "__registry__";

    public VelocityTemplateEngine( Properties velocityProperties, ResourceRegistry resourceRegistry )
    {
        velocityEngine = new VelocityEngine();
        ExtendedProperties props = ExtendedProperties.convertProperties( velocityProperties );
        addInternalResourceLoaderConfig( props, resourceRegistry );
        velocityEngine.setExtendedProperties( props );
    }

    protected void addInternalResourceLoaderConfig( ExtendedProperties props, ResourceRegistry resourceRegistry )
    {
        props.addProperty( "resource.loader", INTERNAL_RESOURCE_LOADER );
        
    }

    public VelocityEngine getVelocityEngine()
    {
        return velocityEngine;
    }

    public void init()
        throws Exception
    {
        velocityEngine.init();
    }

    public void mergeTemplate( String templatePath, Context context, Writer output )
        throws Exception
    {
        VelocityContextAdapter velocityContext = new VelocityContextAdapter( context );
        velocityEngine.mergeTemplate( templatePath, velocityContext, output );
    }
}