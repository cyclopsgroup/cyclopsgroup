package org.cyclopsgroup.lc;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SumSearchSupportTest
{
    @Test
    public void testFindSumForSingleNumberWithEmpty()
    {
        assertTrue( SumSearchSupport.findSum( new int[] { 1, 2, 3, 4 }, 0, 4, 1, 5 ).isEmpty() );
    }
}
