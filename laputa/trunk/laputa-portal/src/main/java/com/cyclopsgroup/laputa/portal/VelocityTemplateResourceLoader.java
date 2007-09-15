package com.cyclopsgroup.laputa.portal;

import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VelocityTemplateResourceLoader
    extends ClasspathResourceLoader
{
    private String templatePrefix;

    public InputStream getResourceStream( String path )
        throws ResourceNotFoundException
    {
        String resourcePath;
        if ( path.charAt( 0 ) == '/' )
        {
            resourcePath = templatePrefix + path;
        }
        else
        {
            resourcePath = templatePrefix + '/' + path;
        }
        return super.getResourceStream( resourcePath );
    }

    public String getTemplatePrefix()
    {
        return templatePrefix;
    }

    @Override
    public void init( ExtendedProperties configuration )
    {
        super.init( configuration );
        templatePrefix = configuration.getString( "templatePrefix", "templates" );
        log.info( "Template path prefix is " + templatePrefix );
    }

    public void setTemplatePrefix( String templatePrefix )
    {
        this.templatePrefix = templatePrefix;
    }
}
