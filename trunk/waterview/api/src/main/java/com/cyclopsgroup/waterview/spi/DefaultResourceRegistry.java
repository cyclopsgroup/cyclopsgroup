package com.cyclopsgroup.waterview.spi;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;

/**
 * TODO Handle empty request with leading package alias
 *
 * @author <a href="mailto:jiaqi@amazon.com>jiaqi</a>
 *
 */
public class DefaultResourceRegistry
    implements ResourceRegistry
{
    public static String getFullResourcePath( Map<String, String> packageMap, String requestPath,
                                              String defaultPackageAlias )
        throws PackageNotDefinedException
    {
        String path = requestPath;
        if ( path.charAt( 0 ) == '/' )
        {
            path = path.substring( 1 );
        }

        String packageAlias = path.indexOf( '/' ) > 0 ? path.substring( 0, path.indexOf( '/' ) ) : null;
        String packageName = packageAlias == null ? null : (String) packageMap.get( packageAlias );
        if ( packageName != null )
        {
            return packageName.replace( '.', '/' ) + path.substring( path.indexOf( '/' ) );
        }
        else if ( StringUtils.isNotEmpty( defaultPackageAlias ) )
        {
            packageName = (String) packageMap.get( defaultPackageAlias );
            if ( packageName == null )
            {
                throw new PackageNotDefinedException( packageAlias );
            }
            return packageName.replace( '.', '/' ) + '/' + path;
        }
        else
        {
            throw new PackageNotDefinedException( packageAlias );
        }
    }

    private String defaultPackageAlias;

    //Order matters in this case
    private ListOrderedMap packages = (ListOrderedMap) ListOrderedMap
        .decorate( new ConcurrentHashMap<String, String>() );

    public void addPackage( String packageAlias, String packageName )
    {
        packages.put( packageAlias, packageName );
    }

    public String getDefaultPackageAlias()
    {
        return defaultPackageAlias;
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
        String packageAlias = path.indexOf( '/' ) > 0 ? path.substring( 0, path.indexOf( '/' ) ) : null;
        String packageName = packageAlias == null ? null : (String) packages.get( packageAlias );
        if ( packageName != null )
        {
            return packageName + path.substring( path.indexOf( '/' ) ).replace( '/', '.' );
        }
        else if ( StringUtils.isNotEmpty( defaultPackageAlias ) )
        {
            packageName = (String) packages.get( defaultPackageAlias );
            if ( packageName == null )
            {
                throw new PackageNotDefinedException( packageAlias );
            }
            return packageName + '.' + path.replace( '/', '.' );
        }
        else
        {
            throw new PackageNotDefinedException( packageAlias );
        }
    }

    public String getFullResourcePath( String shortPath )
        throws PackageNotDefinedException
    {
        return getFullResourcePath( packages, shortPath, defaultPackageAlias );
    }

    @SuppressWarnings("unchecked")
    public List<String> getPackageAliases()
    {
        return packages.asList();
    }

    public String getPackageName( String packageAlias )
        throws PackageNotDefinedException
    {
        String packageName = (String) packages.get( packageAlias );
        if ( packageName == null )
        {
            throw new PackageNotDefinedException( packageAlias );
        }
        return packageName;
    }

    public void setDefaultPackageAlias( String defaultPackageAlias )
    {
        this.defaultPackageAlias = defaultPackageAlias;
    }

    public void setPackages( Map<String, String> packageMap )
    {
        packages.putAll( packageMap );
    }
}