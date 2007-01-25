package com.cyclopsgroup.waterview.spi;

public class PackageNotDefinedException
    extends Exception
{
    public PackageNotDefinedException( String packageAlias )
    {
        super( "Package alias [" + packageAlias + "] is not defined" );
    }
}
