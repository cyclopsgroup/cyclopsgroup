package org.cyclopsgroup.eulerer.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test of rational number
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class RationalNumberTest
{
    /**
     * Verify plus operation
     */
    @Test
    public void testPlus()
    {
        assertEquals( new RationalNumber( 5, 6 ), new RationalNumber( 1, 2 ).add( new RationalNumber( 1, 3 ) ) );
        assertEquals( new RationalNumber( 1, 6 ), new RationalNumber( 1, 2 ).add( new RationalNumber( -1, 3 ) ) );
        assertEquals( RationalNumber.ONE, new RationalNumber( 1, 3 ).add( new RationalNumber( 2, 3 ) ) );
    }

    /**
     * Verify multiply operation
     */
    @Test
    public void testMultiply()
    {
        assertEquals( new RationalNumber( 1, 6 ), new RationalNumber( 1, 2 ).multiply( new RationalNumber( 1, 3 ) ) );
        assertEquals( new RationalNumber( -3, 10 ), new RationalNumber( 1, 2 ).multiply( new RationalNumber( 3, -5 ) ) );
    }
}
