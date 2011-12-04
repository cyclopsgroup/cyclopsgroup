package org.cyclopsgroup.eulerer.p359;

import java.math.BigInteger;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.eulerer.math.BoundInteger;

/**
 * Problem of 259, Hilbert's hotel
 *
 * @author Jiaqi Guo
 */
public class Solution
    implements Runnable
{
    public static void main( String[] args )
    {
        new Solution().run();
    }

    private static BigInteger nextInRow( BigInteger from )
    {
        long root = (long) Math.sqrt( from.floatValue() * 2 ) + 1;
        return BigInteger.valueOf( root ).pow( 2 ).add( from.negate() );
    }

    static BigInteger p( long row, long column )
    {
        Validate.isTrue( row > 0, "Invalid row  " + row );
        Validate.isTrue( column > 0, "Invalid column  " + column );
        long s;
        if ( row == 1 )
        {
            s = 1;
        }
        else
        {
            s = ( row + 1 ) / 2 * ( row / 2 ) * 2;
        }
        BigInteger start = BigInteger.valueOf( s );
        if ( column == 1 )
        {
            return start;
        }
        if ( column == 1 )
        {
            return start;
        }
        BigInteger second = nextInRow( start );
        if ( column == 2 )
        {
            return second;
        }
        BigInteger third = nextInRow( second );
        if ( column == 3 )
        {
            return third;
        }
        BigInteger gap = third.add( start.negate() );

        boolean perfect = column % 2 == 1;
        if ( !perfect )
        {
            column--;
        }
        BigInteger chunks = BigInteger.valueOf( ( column - 1 ) / 2 );
        BigInteger value =
            chunks.multiply( gap ).add( start ).add( chunks.multiply( chunks.add( BigInteger.ONE.negate() ) ).multiply( BigInteger.valueOf( 2 ) ) );
        if ( perfect )
        {
            return value;
        }
        return nextInRow( value );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void run()
    {
        BoundInteger result = new BoundInteger( 0, 8 );
        BigInteger two = BigInteger.valueOf( 2 );
        BigInteger three = BigInteger.valueOf( 3 );
        for ( int i = 0; i <= 27; i++ )
        {
            BigInteger left = two.pow( i );
            BigInteger right = two.pow( 27 - i );
            for ( int j = 0; j <= 12; j++ )
            {
                BigInteger row = left.multiply( three.pow( j ) );
                BigInteger column = right.multiply( three.pow( 12 - j ) );
                BigInteger value = p( row.longValue(), column.longValue() );
                System.out.println( "P(" + row + ", " + column + ") = " + value );
                result = result.add( new BoundInteger( value, 8 ) );
            }
        }
        System.out.println( result );
    }
}
