package org.cyclopsgroup.eulerer.p048;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

/**
 * Test of {@link Solver}
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class SolverTest
{
    /**
     * Verify when length is 10
     */
    @Test
    public void testWith10()
    {
        assertEquals( BigInteger.valueOf( 405071317L ), Solver.solve( 10, 10 ) );
    }

    /**
     * Verify when length is 3
     */
    @Test
    public void testWith3()
    {
        assertEquals( BigInteger.valueOf( 32 ), Solver.solve( 3, 10 ) );
    }
}
