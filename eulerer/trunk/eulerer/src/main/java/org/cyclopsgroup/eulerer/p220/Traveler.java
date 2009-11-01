package org.cyclopsgroup.eulerer.p220;

/**
 * Cooridnate are two mutable integers, x and y
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class Traveler
{
    private Direction direction = Direction.UP;

    private long steps;

    /**
     * Horizontal offset
     */
    private int x;

    /**
     * Vertical offset
     */
    private int y;

    /**
     * @inheritDoc
     */
    @Override
    public Traveler clone()
    {
        Traveler clone = new Traveler();
        clone.x = x;
        clone.y = y;
        clone.direction = direction;
        clone.steps = steps;
        return clone;
    }

    /**
     * @return Current coordinate
     */
    public final Coordinate getCoordinate()
    {
        return new Coordinate( x, y );
    }

    /**
     * @return Currect direction
     */
    public final Direction getDirection()
    {
        return direction;
    }

    /**
     * @return Number of steps to take to current place
     */
    public final long getSteps()
    {
        return steps;
    }

    /**
     * Calculate relative path between given from location to current location
     *
     * @param from From location and direction
     * @return Path it takes from from to currrent location and direction
     */
    Path pathFrom( Traveler from )
    {
        int back = Direction.UP.minus( from.direction );
        return new Path( getCoordinate().rotate( back ).minus( from.getCoordinate().rotate( back ) ),
                         direction.minus( from.direction ), steps - from.steps );
    }

    /**
     * @param cord Change coordinate by stepping forward
     */
    void stepForward()
    {
        switch ( direction )
        {
            case UP:
                y++;
                break;
            case RIGHT:
                x++;
                break;
            case DOWN:
                y--;
                break;
            case LEFT:
                x--;
                break;
            default:
                throw new AssertionError( "Unexpected direction " + this );
        }
        steps++;
    }

    /**
     * Make a left turn
     */
    void turnLeft()
    {
        direction = direction.add( -1 );
    }

    /**
     * Make a right turn
     */
    void turnRight()
    {
        direction = direction.add( 1 );
    }

    /**
     * @param path Walk through given path
     */
    void walk( Path path )
    {
        steps += path.steps;
        Coordinate c = path.offset.rotate( direction.minus( Direction.UP ) );
        x += c.x;
        y += c.y;
        direction = direction.add( path.turns );
    }
}
