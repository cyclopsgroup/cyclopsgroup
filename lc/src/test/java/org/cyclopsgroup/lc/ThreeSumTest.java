package org.cyclopsgroup.lc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class ThreeSumTest
{
    @Test
    public void testThreeSum()
    {
        ThreeSum ts = new ThreeSum();
        ArrayList<ArrayList<Integer>> result = ts.threeSum( new int[] { -1, 0, 1, 2, -1, -4 } );
        assertEquals( 2, result.size() );
        assertTrue( result.contains( new ArrayList<Integer>( Arrays.asList( -1, 0, 1 ) ) ) );
        assertTrue( result.contains( new ArrayList<Integer>( Arrays.asList( -1, -1, 2 ) ) ) );
    }

    @Test
    public void testEmpty()
    {
        ThreeSum ts = new ThreeSum();
        assertTrue( ts.threeSum( new int[] {} ).isEmpty() );
    }
}
