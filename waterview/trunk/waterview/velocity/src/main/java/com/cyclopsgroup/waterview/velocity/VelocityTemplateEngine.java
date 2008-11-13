package com.cyclopsgroup.waterview.velocity;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.spi.PackageNotDefinedException;
import com.cyclopsgroup.waterview.spi.RenderTemplateException;
import com.cyclopsgroup.waterview.spi.ResourceRegistry;
import com.cyclopsgroup.waterview.spi.TemplateEngine;
import com.cyclopsgroup.waterview.spi.TemplateNotFoundException;

public class VelocityTemplateEngine
    implements TemplateEngine
{
    private static final String INTERNAL_RESOURCE_LOADER = "__registry__";

    private VelocityEngine velocityEngine;

    public VelocityTemplateEngine( Properties velocityProperties, ResourceRegistry resourceRegistry )
        throws PackageNotDefinedException
    {
        velocityEngine = new VelocityEngine();
        ExtendedProperties props = ExtendedProperties.convertProperties( velocityProperties );
        addInternalResourceLoaderConfig( props, resourceRegistry );
        velocityEngine.setExtendedProperties( props );
    }

    private void addInternalResourceLoaderConfig( ExtendedProperties props, ResourceRegistry resourceRegistry )
        throws PackageNotDefinedException
    {
        props.addProperty( "resource.loader", INTERNAL_RESOURCE_LOADER );
        String[] aliases = resourceRegistry.getPackageAliases().toArray( ArrayUtils.EMPTY_STRING_ARRAY );
        props.addProperty( INTERNAL_RESOURCE_LOADER + ".resource.loader.class", RegisteredResourceLoader.class
            .getName() );
        props.addProperty( INTERNAL_RESOURCE_LOADER + ".resource.loader.aliases", StringUtils.join( aliases, ',' ) );
        for ( String alias : aliases )
        {
            props.addProperty( INTERNAL_RESOURCE_LOADER + ".resource.loader.package." + alias, resourceRegistry
                .getPackageName( alias ) );
        }
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
        throws TemplateNotFoundException, RenderTemplateException, IOException
    {
        VelocityContextAdapter velocityContext = new VelocityContextAdapter( context );
        try
        {
            velocityEngine.mergeTemplate( templatePath, velocityContext, output );
        }
        catch ( ResourceNotFoundException e )
        {
            throw new TemplateNotFoundException( templatePath );
        }
        catch ( IOException e )
        {
            throw e;
        }
        catch ( Exception e )
        {
            throw new RenderTemplateException( e );
        }
    }

    public boolean templateExists( String templatePath )
    {
        return velocityEngine.templateExists( templatePath );
    }
}