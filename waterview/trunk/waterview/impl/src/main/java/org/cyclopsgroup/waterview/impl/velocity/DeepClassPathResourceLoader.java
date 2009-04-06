package org.cyclopsgroup.waterview.impl.velocity;

import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private static final Log LOG = LogFactory.getLog(DeepClassPathResourceLoader.class);
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
        if ( StringUtils.isEmpty(pathPrefix) )
        {
            LOG.warn( "prefix property is empty" );
            pathPrefix = StringUtils.EMPTY;
        }
        log.info( "Velocity resource loader path prefix is set to " + pathPrefix );
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean resourceExists( String path )
    {
        return super.resourceExists( pathPrefix + path );
    }
}
