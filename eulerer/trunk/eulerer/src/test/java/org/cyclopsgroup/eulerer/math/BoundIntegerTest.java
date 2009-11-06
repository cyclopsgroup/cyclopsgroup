package org.cyclopsgroup.eulerer.math;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

/**
 * Test of {@link BoundInteger}
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class BoundIntegerTest
{
    /**
     * Verify power method
     */
    @Test
    public void testPower()
    {
        assertEquals( BigInteger.valueOf( 8 ), new BoundInteger( 2, 5 ).power( 3 ).toBigInteger() );
    }
}
