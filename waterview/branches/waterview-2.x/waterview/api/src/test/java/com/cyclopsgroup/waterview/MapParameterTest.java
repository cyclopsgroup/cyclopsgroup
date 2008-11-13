package com.cyclopsgroup.waterview;

import junit.framework.TestCase;

public class MapParameterTest
    extends TestCase
{
    public void testGetValues()
    {
        MapParameters mp = new MapParameters();
        mp.set( "a", "12" );
        mp.set( "a", "100" );
        mp.set( "b", "yes" );

        assertEquals( 100, mp.getInt( "a" ) );
        assertTrue( mp.getBoolean( "b" ) );
    }
}
