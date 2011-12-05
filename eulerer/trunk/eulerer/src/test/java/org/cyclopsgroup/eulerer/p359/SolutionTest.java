package org.cyclopsgroup.eulerer.p359;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * Test of p359 solution
 *
 * @author Jiaqi Guo
 */
public class SolutionTest
{
    /**
     * Verify getValue returns the same correct value that computed in brute force
     */
    @Test
    public void testPWithCalculatedGrid()
    {
        int maxNumber = 10000;
        List<List<Integer>> grid = new ArrayList<List<Integer>>();
        for ( int i = 1; i <= maxNumber; i++ )
        {
            boolean merged = false;
            int row = 1;
            for ( List<Integer> chain : grid )
            {
                int lastNumber = chain.get( chain.size() - 1 );
                int root = (int) Math.sqrt( (double) lastNumber + i );

                if ( lastNumber + i == root * root )
                {
                    chain.add( i );
                    assertEquals( "Position row=" + row + ", column=" + chain.size() + " unexpected",
                                  BigInteger.valueOf( i ), Solution.p( row, chain.size() ) );
                    merged = true;
                    break;
                }
                row++;
            }
            if ( !merged )
            {
                grid.add( new ArrayList<Integer>( Collections.singletonList( i ) ) );
                assertEquals( BigInteger.valueOf( i ), Solution.p( grid.size(), 1 ) );
            }
        }

        // for ( List<Integer> row : grid )
        // {
        // System.out.println( row );
        // int previous = 0;
        // for ( int v : row )
        // {
        // if ( previous != 0 )
        // {
        // System.out.print( ( v - previous ) + ", " );
        // }
        // previous = v;
        // }
        // System.out.println();
        // }
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
