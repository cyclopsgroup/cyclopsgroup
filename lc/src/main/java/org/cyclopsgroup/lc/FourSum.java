package org.cyclopsgroup.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FourSum
    extends SumSearchSupport
{
    private int[] listToArray( List<Integer> list )
    {
        int[] result = new int[list.size()];
        int i = 0;
        for ( int n : list )
        {
            result[i++] = n;
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> fourSum( int[] num, int target )
    {
        Arrays.sort( num );
        int pivot = target / 4;
        List<Integer> lowList = new ArrayList<Integer>();
        List<Integer> highList = new ArrayList<Integer>();

        int seq = 0;
        for ( int n : num )
        {
            if ( n > pivot )
            {
                highList.add( n );
            }
            else if ( n < pivot )
            {
                lowList.add( n );
            }
            else
            {
                if ( seq++ % 2 == 0 )
                {
                    highList.add( n );
                }
                else
                {
                    lowList.add( n );
                }
            }
        }
        int[] lows = listToArray( lowList );
        int[] highs = listToArray( highList );

        Set<ArrayList<Integer>> result = new HashSet<ArrayList<Integer>>();
        for ( int low : lows )
        {
            for ( ArrayList<Integer> s : findSum( highs, 0, highs.length, 3, target - low ) )
            {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add( low );
                list.addAll( s );
                result.add( list );
            }
        }
        for ( int high : highs )
        {
            for ( ArrayList<Integer> s : findSum( lows, 0, lows.length, 3, target - high ) )
            {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.addAll( s );
                list.add( high );
                result.add( list );
            }
        }

        for ( int i = 0; i < lows.length - 1; i++ )
        {
            int low1 = lows[i];
            for ( int j = i + 1; j < lows.length; j++ )
            {
                int low2 = lows[j];
                for ( ArrayList<Integer> s : findSum( highs, 0, highs.length, 2, target - low1 - low2 ) )
                {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add( low1 );
                    list.add( low2 );
                    list.addAll( s );
                    result.add( list );
                }
            }
        }
        return new ArrayList<ArrayList<Integer>>( result );
    }
}
