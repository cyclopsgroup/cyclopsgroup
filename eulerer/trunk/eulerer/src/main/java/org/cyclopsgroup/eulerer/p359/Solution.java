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
    private static final BigInteger TWO = BigInteger.valueOf( 2 );

    private static final BigInteger THREE = BigInteger.valueOf( 3 );

    private static final BigInteger FOUR = BigInteger.valueOf( 4 );

    static BigInteger p( long row, long column )
    {
        Validate.isTrue( row > 0, "Invalid row  " + row );
        Validate.isTrue( column > 0, "Invalid column  " + column );
        BigInteger start;
        if ( row == 1 )
        {
            start = BigInteger.ONE;
        }
        else
        {
            BigInteger r = BigInteger.valueOf( row );
            start = r.add( BigInteger.ONE ).divide( TWO ).multiply( r.divide( TWO ).multiply( TWO ) );
        }

        if ( column == 1 )
        {
            return start;
        }

        BigInteger firstGap, secondGap;
        if ( row == 1 )
        {
            firstGap = TWO;
            secondGap = THREE;
        }
        else if ( row % 2 == 0 )
        {
            firstGap = BigInteger.valueOf( row ).divide( TWO ).multiply( FOUR ).add( BigInteger.ONE );
            secondGap = TWO;
        }
        else
        {
            firstGap = BigInteger.ONE;
            secondGap = BigInteger.valueOf( row ).divide( TWO ).multiply( FOUR ).add( TWO );
        }
        BigInteger gap = firstGap.add( secondGap );

        boolean perfect = column % 2 == 1;
        if ( !perfect )
        {
            column--;
        }
        BigInteger chunks = BigInteger.valueOf( ( column - 1 ) / 2 );
        BigInteger value =
            chunks.multiply( gap ).add( start ).add( chunks.multiply( chunks.add( BigInteger.ONE.negate() ) ).multiply( TWO ) );
        if ( perfect )
        {
            return value;
        }
        return value.add( firstGap ).add( chunks.multiply( TWO ) );
        // return nextInRow( value );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void run()
    {
        BoundInteger result = new BoundInteger( 0, 8 );
        for ( int i = 0; i <= 27; i++ )
        {
            BigInteger left = TWO.pow( i );
            BigInteger right = TWO.pow( 27 - i );
            for ( int j = 0; j <= 12; j++ )
            {
                BigInteger row = left.multiply( THREE.pow( j ) );
                BigInteger column = right.multiply( THREE.pow( 12 - j ) );
                BigInteger value = p( row.longValue(), column.longValue() );
                result = result.add( new BoundInteger( value, 8 ) );
            }
        }
        System.out.println( result );
    }
}
