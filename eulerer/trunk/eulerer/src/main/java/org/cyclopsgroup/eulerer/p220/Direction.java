package org.cyclopsgroup.eulerer.p220;

public enum Direction
{
    DOWN, LEFT, RIGHT, UP;

    /**
     * @param cord Change coordinate by stepping forward
     */
    public void stepForward( Coordinate cord )
    {
        switch ( this )
        {
            case UP:
                cord.y++;
                break;
            case RIGHT:
                cord.x++;
                break;
            case DOWN:
                cord.y--;
                break;
            case LEFT:
                cord.x--;
                break;
            default:
                throw new AssertionError( "Unexpected direction " + this );
        }
    }

    /**
     * @return New direction after turning left
     */
    public Direction turnLeft()
    {
        switch ( this )
        {
            case UP:
                return LEFT;
            case RIGHT:
                return UP;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            default:
                throw new AssertionError( "Unexpected direction " + this );
        }
    }

    /**
     * @return New direction after turning right
     */
    public Direction turnRight()
    {
        switch ( this )
        {
            case UP:
                return RIGHT;
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            default:
                throw new AssertionError( "Unexpected direction " + this );
        }
    }
}
