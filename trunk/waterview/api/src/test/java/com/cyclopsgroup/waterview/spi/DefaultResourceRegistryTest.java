package com.cyclopsgroup.waterview.spi;

import junit.framework.TestCase;

import com.cyclopsgroup.waterview.spi.PackageNotDefinedException;

public class DefaultResourceRegistryTest
    extends TestCase
{
    private DefaultResourceRegistry drr;

    @Override
    protected void setUp()
    {
        drr = new DefaultResourceRegistry();
        drr.addPackage( "a", "a.a.a" );
        drr.addPackage( "b", "b.b.b" );
    }

    public void testGetFullClassName()
        throws Exception
    {
        assertEquals( "a.a.a.Xyz", drr.getFullClassName( "/a/Xyz.vm" ) );
        assertEquals( "b.b.b.b.Xyz", drr.getFullClassName( "b/b/Xyz.mmld" ) );
        assertEquals( "a.a.a.a.Xyz", drr.getFullClassName( "a/a/Xyz" ) );

        try
        {
            drr.getFullClassName( "c/X.vm" );
            fail( "PackageNotDefinedException is supposed to be thrown" );
        }
        catch ( PackageNotDefinedException e )
        {

        }

        drr.setDefaultPackageAlias( "a" );
        assertEquals( "a.a.a.c.X", drr.getFullClassName( "c/X.vm" ) );
    }

    public void testGetFullResourcePath()
        throws Exception
    {
        assertEquals( "a/a/a/Xyz.vm", drr.getFullResourcePath( "/a/Xyz.vm" ) );
        assertEquals( "b/b/b/b/Xyz.mmld", drr.getFullResourcePath( "b/b/Xyz.mmld" ) );
    }
}
