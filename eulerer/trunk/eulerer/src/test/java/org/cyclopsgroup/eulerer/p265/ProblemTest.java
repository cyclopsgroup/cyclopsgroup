package org.cyclopsgroup.eulerer.p265;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

public class ProblemTest
{
    /**
     * Verify with know result of three digit problem
     */
    @Test
    public void testWithThree()
    {
        Set<Long> result = new Problem( 3 ).solve();
        assertEquals( 2, result.size() );
        assertTrue( result.contains( 23L ) );
        assertTrue( result.contains( 29L ) );
    }
}
