package com.cyclopsgroup.waterview.spi;

import java.util.List;

public interface ResourceRegistry
{
    String getDefaultPackageAlias();

    String getFullClassName( String shortPath )
        throws PackageNotDefinedException;

    String getFullResourcePath( String shortPath )
        throws PackageNotDefinedException;

    List<String> getPackageAliases();

    String getPackageName( String packageAlias )
        throws PackageNotDefinedException;
}
