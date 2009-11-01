package org.cyclopsgroup.eulerer.p220;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test that verify code with several known result
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class HeighwayDragonTest
{
    /**
     * Verify given example is correct under code logic
     */
    @Test
    public void testWith10Levels()
    {
        traverseAndVerify( 10, 500, "18,16" );
    }

    /**
     * Verify a border case
     */
    @Test
    public void testWith1Level()
    {
        traverseAndVerify( 0, 1, "0,1" );
    }

    /**
     * Verify a short path
     */
    @Test
    public void testWith2Levels()
    {
        traverseAndVerify( 2, 3, "1,0" );
    }

    private void traverseAndVerify( int level, long steps, String expected )
    {
        HeighwayDragon d = new HeighwayDragon( level );
        d.traverseFor( steps );
        assertEquals( expected, d.getTraveler().getCoordinate().toString() );
    }
}
