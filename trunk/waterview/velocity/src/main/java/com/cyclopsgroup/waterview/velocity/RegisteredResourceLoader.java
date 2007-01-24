package com.cyclopsgroup.waterview.velocity;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class RegisteredResourceLoader
    extends ClasspathResourceLoader
{
    private Map<String, String> packageMap = new ConcurrentHashMap<String, String>();

    @Override
    public synchronized InputStream getResourceStream( String name )
        throws ResourceNotFoundException
    {
        for ( String alias : packageMap.keySet() )
        {
            if ( name.startsWith( alias + '/' ) || name.startsWith( '/' + alias + '/' ) )
            {

            }
        }
        return super.getResourceStream( name );
    }

    @Override
    public void init( ExtendedProperties configuration )
    {
        super.init( configuration );
        for ( String alias : configuration.getStringArray( "alias" ) )
        {
            String packageName = configuration.getString( alias );
            packageMap.put( alias, packageName.replace( '.', '/' ) );
        }
    }
}