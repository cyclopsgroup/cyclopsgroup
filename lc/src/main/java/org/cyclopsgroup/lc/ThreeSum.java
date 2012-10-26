package org.cyclopsgroup.lc;

import java.util.ArrayList;
import java.util.Arrays;

public class ThreeSum
    extends SumSearchSupport
{
    /**
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets
     * in the array which gives the sum of zero.
     *
     * @param num Array of input integers
     * @return Array of triplets
     */
    public ArrayList<ArrayList<Integer>> threeSum( int[] num )
    {
        Arrays.sort( num );
        return findSum( num, 0, num.length, 3, 0 );
    }
}
