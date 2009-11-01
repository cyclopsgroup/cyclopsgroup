package org.cyclopsgroup.eulerer.p220;

/**
 * Enumerate four directions
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public enum Direction
{
    /**
     * Y--
     */
    DOWN( 2 ),
    /**
     * X--
     */
    LEFT( 3 ),
    /**
     * X++
     */
    RIGHT( 1 ),
    /**
     * Y++
     */
    UP( 0 );

    private static final Direction[] VALUE_INDEX = new Direction[] { UP, RIGHT, DOWN, LEFT };

    private final int value;

    private Direction( int value )
    {
        this.value = value;
    }

    /**
     * Turn direction clock wise for given number of times
     *
     * @param v Number of turns
     * @return New direction after turning
     */
    public Direction add( int v )
    {
        if ( v == 0 )
        {
            return this;
        }
        int index = ( v + value ) % 4;
        if ( index < 0 )
        {
            index += 4;
        }
        return VALUE_INDEX[index];
    }

    /**
     * @param dir New direction
     * @return To get to the new direction, number of clock wise turns to take
     */
    public int minus( Direction dir )
    {
        int result = value - dir.value;
        if ( result < 0 )
        {
            result += 4;
        }
        return result;
    }
}
