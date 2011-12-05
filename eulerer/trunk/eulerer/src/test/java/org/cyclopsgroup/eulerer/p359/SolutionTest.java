package org.cyclopsgroup.eulerer.p359;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Test of p359 solution
 *
 * @author Jiaqi Guo
 */
public class SolutionTest
{
    private static class Tail
    {
        private BigInteger value;

        private long length;

        private void append( BigInteger newValue )
        {
            length++;
            value = newValue;
        }

        private Tail( BigInteger initValue )
        {
            length = 1;
            value = initValue;
        }
    }

    /**
     * Verify getValue returns the same correct value that computed in brute force
     */
    @Test
    public void testPWithCalculatedGrid()
    {
        int maxNumber = 10000;
        List<Tail> tails = new ArrayList<Tail>();
        for ( int i = 1; i <= maxNumber; i++ )
        {
            BigInteger bi = BigInteger.valueOf( i );
            boolean merged = false;
            int row = 1;
            for ( int j = 0; j < tails.size(); j++ )
            {
                Tail tail = tails.get( j );
                long root = (long) Math.sqrt( tail.value.doubleValue() + i );

                if ( tail.value.add( bi ).equals( BigInteger.valueOf( root ).pow( 2 ) ) )
                {
                    tail.append( BigInteger.valueOf( i ) );
                    assertEquals( "Position row=" + row + ", column=" + tail.length + " unexpected", bi,
                                  Solution.p( row, tail.length ) );
                    merged = true;
                    break;
                }
                row++;
            }
            if ( !merged )
            {
                tails.add( new Tail( bi ) );
                assertEquals( BigInteger.valueOf( i ), Solution.p( tails.size(), 1 ) );
            }
        }
    }

    /**
     * Verify with given known values
     */
    @Test
    public void testPWithKnownValues()
    {
        assertEquals( BigInteger.valueOf( 1 ), Solution.p( 1, 1 ) );
        assertEquals( BigInteger.valueOf( 3 ), Solution.p( 1, 2 ) );
        assertEquals( BigInteger.valueOf( 2 ), Solution.p( 2, 1 ) );
        assertEquals( BigInteger.valueOf( 440 ), Solution.p( 10, 20 ) );
        assertEquals( BigInteger.valueOf( 4863 ), Solution.p( 25, 75 ) );
        assertEquals( BigInteger.valueOf( 19454 ), Solution.p( 99, 100 ) );
    }
}
