package org.cyclopsgroup.lc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ThreeClosestTest
{
    @Test
    public void testGiven()
    {
        assertEquals( 2, new ThreeClosest().threeSumClosest( new int[] { -1, 2, 1, -4 }, 1 ) );
    }
}
