package com.cyclopsgroup.waterview.spi;

import java.util.ArrayList;
import java.util.List;

public class SimpleResourceRegistry
    implements ResourceRegistry
{
    private static final String PACKAGE_ALIAS = "default";

    private List<String> packageAliases;

    private String packageName;

    public SimpleResourceRegistry( String packageName )
    {
        this.packageName = packageName;
        packageAliases = new ArrayList<String>( 1 );
        packageAliases.add( PACKAGE_ALIAS );
    }

    public String getDefaultPackageAlias()
    {
        return PACKAGE_ALIAS;
    }

    public String getFullClassName( String shortPath )
        throws PackageNotDefinedException
    {
        String path = shortPath;
        if ( shortPath.lastIndexOf( '.' ) > shortPath.lastIndexOf( '/' ) )
        {
            path = shortPath.substring( 0, shortPath.lastIndexOf( '.' ) );
        }
        if ( path.charAt( 0 ) == '/' )
        {
            path = path.substring( 1 );
        }
        return packageName + '.' + path.replace( '/', '.' );
    }

    public String getFullResourcePath( String shortPath )
        throws PackageNotDefinedException
    {
        String path = shortPath;
        if ( path.charAt( 0 ) != '/' )
        {
            path = '/' + path;
        }
        return packageName.replace( '.', '/' ) + path;
    }

    public List<String> getPackageAliases()
    {
        return packageAliases;
    }

    public String getPackageName( String packageAlias )
        throws PackageNotDefinedException
    {
        return packageName;
    }

}
