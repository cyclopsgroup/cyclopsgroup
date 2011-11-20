package org.cyclopsgroup.eulerer.math;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Unitily around prime numbers
 *
 * @author Jiaqi Guo
 */
public class PrimeUtils
{
    /**
     * A reusable context for performance gain
     *
     * @author Jiaqi Guo
     */
    public static class Context
    {
        private final SortedSet<Long> knownPrimes = new TreeSet<Long>( Arrays.asList( 2L, 3L, 5L, 7L, 11L, 13L, 17L,
                                                                                      19L ) );

        private long range = 19L;
    }

    /**
     * Check if a number if prime or not
     *
     * @param number Number to check
     * @return True if number is prime
     */
    public static boolean isPrime( long number )
    {
        return isPrime( number, new Context() );
    }

    /**
     * Check if number is prime with given context object
     *
     * @param number Number to check
     * @param context A reusable context for performance gain
     * @return True if number is prime
     */
    public static boolean isPrime( long number, Context context )
    {
        if ( number == 1 || context.knownPrimes.contains( number ) )
        {
            return true;
        }
        long sqrt = (long) Math.sqrt( number );
        while ( context.range < sqrt )
        {
            context.range += 2;
            if ( isPrime( context.range ) )
            {
                context.knownPrimes.add( context.range );
            }
        }
        for ( long knownPrime : context.knownPrimes )
        {
            if ( knownPrime > sqrt )
            {
                return true;
            }
            if ( number % knownPrime == 0 )
            {
                return false;
            }
        }
        return true;
    }
}
