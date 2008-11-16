package org.cyclopsgroup.waterview.impl.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.waterview.WebModule;

/**
 * Module resolver based on a list of packages
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class PackageListModuleResolver
    implements ModuleResolver
{
    private final List<String> resourcePaths;

    private final ClassLoader classLoader;

    /**
     * @param packageNames List of package names
     */
    public PackageListModuleResolver( List<String> packageNames )
    {
        this( packageNames, PackageListModuleResolver.class.getClassLoader() );
    }

    /**
     * @param packageNames Name of package names
     * @param classLoader Class loader that loads resources
     */
    public PackageListModuleResolver( List<String> packageNames, ClassLoader classLoader )
    {
        Validate.notNull( classLoader, "Class loader can't be NULL" );
        Validate.notNull( packageNames, "Package names can't be NULL" );

        this.classLoader = classLoader;
        ArrayList<String> resourcePaths = new ArrayList<String>( packageNames.size() );
        for ( String packageName : packageNames )
        {
            String packagePath = packageName.replace( '.', '/' );
            if ( packagePath.endsWith( "/" ) )
            {
                packagePath = packagePath.substring( 0, packagePath.length() - 1 );
            }
            resourcePaths.add( packagePath );
        }
        this.resourcePaths = Collections.unmodifiableList( resourcePaths );
    }

    /**
     * @inheritDoc
     */
    @Override
    public WebModule findModule( String name )
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String findResource( String path )
    {
        Validate.notNull( path, "Path can't be NULL" );
        for ( String resourcePath : resourcePaths )
        {
            String fullPath;
            if ( path.startsWith( "/" ) )
            {
                fullPath = resourcePath + path;
            }
            else
            {
                fullPath = resourcePath + "/" + path;
            }
            if ( classLoader.getResource( fullPath ) != null )
            {
                return fullPath;
            }
        }
        return null;
    }

}
