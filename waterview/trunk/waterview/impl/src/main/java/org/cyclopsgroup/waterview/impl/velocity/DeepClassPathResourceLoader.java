package org.cyclopsgroup.waterview.impl.velocity;

import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class DeepClassPathResourceLoader
    extends ClasspathResourceLoader
{
    private String pathPrefix;

    /**
     * @inheritDoc
     */
    @Override
    public InputStream getResourceStream( String path )
        throws ResourceNotFoundException
    {
        return super.getResourceStream( pathPrefix + path );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void init( ExtendedProperties configuration )
    {
        super.init( configuration );
        pathPrefix = configuration.getString( "prefix" );
        log.info( "Velocity resource loader path prefix is set to " + pathPrefix );
    }
}
