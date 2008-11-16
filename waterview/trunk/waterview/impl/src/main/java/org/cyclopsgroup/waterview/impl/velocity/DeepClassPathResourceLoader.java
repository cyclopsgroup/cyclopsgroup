package org.cyclopsgroup.waterview.impl.velocity;

import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * ClassPath ResourceLoader that loads resource from deep java package
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
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
        if ( StringUtils.isEmpty( pathPrefix ) )
        {
            throw new IllegalArgumentException( "prefix property can't be empty" );
        }
        log.info( "Velocity resource loader path prefix is set to " + pathPrefix );
    }
}
