package com.cyclopsgroup.waterview.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.map.ListOrderedMap;

import com.cyclopsgroup.waterview.spi.ResourceRegistry;

public class DefaultResourceRegistry
    implements ResourceRegistry
{
    private ListOrderedMap packages = (ListOrderedMap) ListOrderedMap
        .decorate( new ConcurrentHashMap<String, String>() );

    public void addPackage( String packageAlias, String packageName )
    {
        packages.put( packageAlias, packageName );
    }

    public String getFullClassName( String shortPath )
    {
        // TODO Auto-generated method stub
        return null;
    }

    public String getFullResourcePath( String shortPath )
    {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<String> getPackageAliases()
    {
        return packages.asList();
    }

    public String getPackageName( String packageAlias )
    {
        return (String) packages.get( packageAlias );
    }

    public void setPackages( Map<String, String> packageMap )
    {
        packages.putAll( packageMap );
    }

}
