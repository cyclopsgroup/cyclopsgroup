package org.cyclopsgroup.eulerer.p048;

import java.math.BigInteger;

import org.cyclopsgroup.eulerer.math.BoundInteger;

/**
 * Solve problem 48, the last X digit of 1 + 2^2 + 33
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class Solver
{
    /**
     * @param length Number n of 1 + 2^2 + 3^3 + ... + n^n
     * @param digits Last number of digits to return
     * @return Calculation result
     */
    public static BigInteger solve( int length, int digits )
    {
        BoundInteger v = new BoundInteger( 0, digits );
        for ( int i = 1; i <= length; i++ )
        {
            v = v.add( new BoundInteger( i, digits ).power( i ) );
        }
        return v.toBigInteger();
    }
}
