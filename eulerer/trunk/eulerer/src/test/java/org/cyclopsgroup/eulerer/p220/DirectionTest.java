package org.cyclopsgroup.eulerer.p220;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Verify operations in direction
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class DirectionTest
{
    /**
     * Verify the turn fuction
     */
    @Test
    public void testTurn()
    {
        assertEquals( Direction.UP, Direction.UP.add( 0 ) );
        assertEquals( Direction.RIGHT, Direction.UP.add( 1 ) );
        assertEquals( Direction.DOWN, Direction.UP.add( 2 ) );
        assertEquals( Direction.LEFT, Direction.UP.add( 3 ) );
        assertEquals( Direction.UP, Direction.UP.add( 4 ) );
        assertEquals( Direction.RIGHT, Direction.UP.add( 5 ) );
        assertEquals( Direction.LEFT, Direction.UP.add( -1 ) );
        assertEquals( Direction.DOWN, Direction.UP.add( -2 ) );
        assertEquals( Direction.RIGHT, Direction.UP.add( -3 ) );

        assertEquals( Direction.DOWN, Direction.RIGHT.add( 1 ) );
        assertEquals( Direction.LEFT, Direction.DOWN.add( 1 ) );
        assertEquals( Direction.UP, Direction.LEFT.add( 1 ) );

        assertEquals( Direction.UP, Direction.RIGHT.add( -1 ) );
        assertEquals( Direction.RIGHT, Direction.DOWN.add( -1 ) );
        assertEquals( Direction.DOWN, Direction.LEFT.add( -1 ) );
    }
}
