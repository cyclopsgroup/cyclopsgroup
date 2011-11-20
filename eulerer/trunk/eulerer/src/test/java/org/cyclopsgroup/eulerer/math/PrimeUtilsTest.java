package org.cyclopsgroup.eulerer.math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test of prime utils
 *
 * @author Jiaqi Guo
 */
public class PrimeUtilsTest
{
    /**
     * Test a few numbers
     */
    @Test
    public void verifyIsPrime()
    {
        assertTrue( PrimeUtils.isPrime( 2 ) );
        assertTrue( PrimeUtils.isPrime( 5 ) );
        assertTrue( PrimeUtils.isPrime( 19 ) );
        assertTrue( PrimeUtils.isPrime( 37 ) );
        assertTrue( PrimeUtils.isPrime( 1299709L ) );
        assertTrue( PrimeUtils.isPrime( 15485863L ) );

        assertFalse( PrimeUtils.isPrime( 4 ) );
        assertFalse( PrimeUtils.isPrime( 169 ) );
        assertFalse( PrimeUtils.isPrime( 999999L ) );
        assertFalse( PrimeUtils.isPrime( 62773913L ) );
    }
}
