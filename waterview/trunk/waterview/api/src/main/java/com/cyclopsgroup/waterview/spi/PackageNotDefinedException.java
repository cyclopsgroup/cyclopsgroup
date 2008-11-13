package com.cyclopsgroup.waterview.spi;

public class PackageNotDefinedException
    extends Exception
{
    private static final long serialVersionUID = 1L;

    public PackageNotDefinedException( String packageAlias )
    {
        super( "Package alias [" + packageAlias + "] is not defined" );
    }
}
