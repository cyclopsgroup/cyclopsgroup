package org.cyclopsgroup.eulerer.p358;

import org.cyclopsgroup.eulerer.math.PrimeUtils;

/**
 * @author Jiaqi Guo
 */
public class Solution
    implements Runnable
{
    private static final int MOD = 100000;

    /**
     * @inheritDoc
     */
    @Override
    public void run()
    {
        calculate();
    }

    int calculate()
    {
        // If number of distinct digit sequences is X, then 56789 * X should end up with 43210
        // Find out possible value of last 5 digits of X
        int firstNumberTail = 56789;
        int lastNumberTail = 43210;
        int lengthTail = 0;

        // Iterate through each digit in first number tail to find out each digit and length tail
        for ( int i = 0, f = 1; i < 5; i++ )
        {
            int f10 = f * 10;
            for ( int j = 0; j < 10; j++ )
            {
                int factor = f * j + lengthTail;
                if ( ( firstNumberTail * factor ) % f10 == lastNumberTail % f10 )
                {
                    lengthTail = factor;
                    break;
                }
            }
            f = f10;
        }
        System.out.println( "Possible last five digits of digit sequence perpuation are " + lengthTail );

        long rangeStart = 99999999999L / 138;
        long rangeEnd = 99999999999L / 137;

        PrimeUtils.Context c = new PrimeUtils.Context();
        for ( long i = rangeStart; i < rangeEnd; i += MOD )
        {
            // Replace tail with previously calculated length tail
            long candidate = (int) ( i - ( i % MOD ) + lengthTail ) + 1;
            if ( !PrimeUtils.isPrime( candidate, c ) )
            {
                continue;
            }
            System.out.println( "Checking candidate: " + candidate + " with sum of digits "
                + ( ( candidate - 1 ) / 2 * 9 ) );

            long fifteenDigits = 10000000000000000L / candidate;
            System.out.println( fifteenDigits );
        }
        return 0;
    }
}
