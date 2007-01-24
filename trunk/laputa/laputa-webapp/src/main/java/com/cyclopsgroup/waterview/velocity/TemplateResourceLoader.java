package com.cyclopsgroup.waterview.velocity;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class TemplateResourceLoader
    extends ClasspathResourceLoader
{
    private String defaultPath;

    private Log log = LogFactory.getLog( getClass() );

    private Map<String, String> mappings = new Hashtable<String, String>();

    @Override
    public synchronized InputStream getResourceStream( String name )
        throws ResourceNotFoundException
    {
        if ( StringUtils.isEmpty( name ) )
        {
            throw new ResourceNotFoundException( "Template name is not specified" );
        }
        if ( name.charAt( 0 ) == '/' )
        {
            name = name.substring( 1 );
        }
        String fullPath = null;
        for ( String pathPrefix : mappings.keySet() )
        {
            if ( name.startsWith( pathPrefix ) )
            {
                fullPath = mappings.get( pathPrefix ) + name.substring( pathPrefix.length() );
            }
        }
        if ( fullPath == null && defaultPath != null )
        {
            fullPath = defaultPath + name;
        }
        if ( fullPath == null )
        {
            fullPath = name;
        }
        return super.getResourceStream( fullPath );
    }

    @Override
    public void init( ExtendedProperties config )
    {
        super.init( config );
        for ( String mapping : config.getStringArray( "mapping" ) )
        {
            String[] parts = StringUtils.split( mapping, ':' );
            String name = parts[0].trim() + '/';
            String path = parts[1].trim() + '/';
            mappings.put( name, path );
            log.info( "Velocity path prefix \"" + name + "\" is mapped to " + path );
            if ( "default/".equals( name ) )
            {
                defaultPath = path;
                log.info( "Default velocity path prefix is mapped to " + path );
            }
        }
    }
}
