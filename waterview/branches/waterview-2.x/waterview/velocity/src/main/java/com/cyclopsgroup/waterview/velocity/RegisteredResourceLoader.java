package com.cyclopsgroup.waterview.velocity;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.cyclopsgroup.waterview.spi.MultiPackageResourceRegistry;
import com.cyclopsgroup.waterview.spi.PackageNotDefinedException;

public class RegisteredResourceLoader
    extends ClasspathResourceLoader
{
    private Map<String, String> packageMap = new ConcurrentHashMap<String, String>();

    @Override
    public synchronized InputStream getResourceStream( String name )
        throws ResourceNotFoundException
    {
        try
        {
            return super.getResourceStream( MultiPackageResourceRegistry.getFullResourcePath( packageMap, name, null ) );
        }
        catch ( PackageNotDefinedException e )
        {
            throw new ResourceNotFoundException( "Resource " + name + " is not found" );
        }
    }

    @Override
    public void init( ExtendedProperties configuration )
    {
        super.init( configuration );
        for ( String alias : configuration.getStringArray( "aliases" ) )
        {
            String packageName = configuration.getString( "package." + alias );
            packageMap.put( alias, packageName.replace( '.', '/' ) );
        }
    }
}