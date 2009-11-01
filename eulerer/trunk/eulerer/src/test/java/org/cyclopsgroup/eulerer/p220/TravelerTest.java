package org.cyclopsgroup.eulerer.p220;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class TravelerTest
{
    /**
     * Verify {@link Traveler#pathFrom(Traveler)} is accurate
     */
    @Test
    public void testPathFrom()
    {
        for ( int i = 0; i < 4; i++ )
        {
            Traveler t = new Traveler();
            t.stepForward();
            for ( int j = 0; i < i; j++ )
            {
                t.turnLeft();
            }

            Traveler from = t.clone();
            t.turnRight();
            t.stepForward();
            t.stepForward();
            t.turnRight();
            t.stepForward();

            assertEquals( new Path( new Coordinate( 2, -1 ), 2, 3 ), t.pathFrom( from ) );
        }
    }

    /**
     * Verify {@link Traveler#walk(Path)}
     */
    @Test
    public void testWalk()
    {
        Traveler t = new Traveler();
        t.stepForward();
        t.turnRight();

        t.walk( new Path( new Coordinate( 2, -1 ), 2, 3 ) );

        assertEquals( new Coordinate( -1, -1 ), t.getCoordinate() );
        assertEquals( Direction.LEFT, t.getDirection() );
        assertEquals( 4L, t.getSteps() );
    }
}
