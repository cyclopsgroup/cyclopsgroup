package org.cyclopsgroup.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class SumSearchSupport
{
    static ArrayList<ArrayList<Integer>> findSum( int[] sortedNumbers, int searchStart, int searchEnd, int numNumbers,
                                                  int target )
    {
        // Find one number
        if ( numNumbers == 1 )
        {
            ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
            if ( Arrays.binarySearch( sortedNumbers, searchStart, searchEnd, target ) > 0 )
            {
                result.add( new ArrayList<Integer>( Arrays.asList( target ) ) );
            }
            return result;
        }
        Set<ArrayList<Integer>> result = new HashSet<ArrayList<Integer>>();

        for ( int i = searchStart; i < searchEnd + 1 - numNumbers; i++ )
        {
            int n = sortedNumbers[i];

            // way too big
            if ( n * numNumbers > target )
            {
                break;
            }
            for ( ArrayList<Integer> match : findSum( sortedNumbers, i + 1, sortedNumbers.length, numNumbers - 1,
                                                      target - n ) )
            {
                ArrayList<Integer> single = new ArrayList<Integer>();
                single.add( n );
                single.addAll( match );
                result.add( single );
            }
        }
        return new ArrayList<ArrayList<Integer>>( result );
    }
}
