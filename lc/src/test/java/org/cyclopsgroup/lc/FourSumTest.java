package org.cyclopsgroup.lc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class FourSumTest
{
    @Test
    public void testGiven()
    {
        ArrayList<ArrayList<Integer>> result = new FourSum().fourSum( new int[] { 1, 0, -1, 0, -2, 2 }, 0 );
        assertEquals( 3, result.size() );
        assertTrue( result.contains( Arrays.asList( -1, 0, 0, 1 ) ) );
        assertTrue( result.contains( Arrays.asList( -2, -1, 1, 2 ) ) );
        assertTrue( result.contains( Arrays.asList( -2, 0, 0, 2 ) ) );
    }
}
