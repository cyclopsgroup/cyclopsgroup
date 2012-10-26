package org.cyclopsgroup.eulerer.p393;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="http://projecteuler.net/problem=393">Problem 393</a>
 */
public class Solution
    implements Runnable
{
    private final Map<Long, Long> knownResults = new HashMap<Long, Long>();

    long count( int x, int y )
    {
        long key = x + ( (long) y ) << 32;
        if ( knownResults.containsKey( key ) )
        {
            return knownResults.get( key );
        }

        long result = doCount( x, y );
        knownResults.put( key, result );
        knownResults.put( ( (long) x ) << 32 + y, result );
        return result;
    }

    private long doCount( int x, int y )
    {
        if ( x % 2 == 1 && y % 2 == 1 )
        {
            throw new IllegalArgumentException( "x and y can't both be odd" );
        }
        if ( x < y )
        {
            throw new IllegalArgumentException( "Y can't be smaller than x" );
        }
        if ( y == 2 )
        {
            if ( x == 2 )
            {
                return 2;
            }
            if ( x == 3 )
            {
                return 2;
            }

            if ( x % 2 == 1 )
            {
                int result = 0;
                for ( int i = 2; i <= x - 2; i++ )
                {
                    result += count( x, 2 ) * 2;
                }
                return result + 2;
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void run()
    {
        // TODO Auto-generated method stub

    }
}
