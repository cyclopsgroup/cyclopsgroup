package com.cyclopsgroup.waterview.spi;

import java.util.List;

public interface ResourceRegistry
{
    String getFullClassName( String shortPath );

    String getFullResourcePath( String shortPath );

    List<String> getPackageAliases();

    String getPackageName( String packageAlias );
}
