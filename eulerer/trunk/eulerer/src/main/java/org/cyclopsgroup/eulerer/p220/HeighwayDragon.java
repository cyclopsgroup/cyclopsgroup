package org.cyclopsgroup.eulerer.p220;

public class HeighwayDragon
{
    private Coordinate coordinate = new Coordinate();

    private Direction direction = Direction.UP;

    private final int maxLevels;

    private long step;

    public HeighwayDragon( int maxLevels )
    {
        if ( maxLevels < 0 )
        {
            throw new IllegalArgumentException( "Invalid input " + maxLevels + " level" );
        }
        this.maxLevels = maxLevels;
        reset();
    }

    private void reset()
    {
        direction = Direction.UP;
        coordinate = new Coordinate();
        step = 0;
    }

    public void traverseFor( long steps )
    {
        reset();
        traverseFor( steps, 0, "Fa" );
    }

    private void traverseFor( long steps, int level, String plan )
    {
        if ( steps <= 0 )
        {
            throw new IllegalArgumentException( "Invalid steps " + steps );
        }
        for ( int i = 0; i < plan.length(); i++ )
        {
            char ch = plan.charAt( i );
            switch ( ch )
            {
                case 'F':
                    direction.stepForward( coordinate );
                    step++;
                    if ( step == steps )
                    {
                        return;
                    }
                    break;
                case 'L':
                    direction = direction.turnLeft();
                    break;
                case 'R':
                    direction = direction.turnRight();
                    break;
                case 'a':
                    if ( level < maxLevels  )
                    {
                        traverseFor( steps, level + 1, "aRbFR" );
                    }
                    break;
                case 'b':
                    if ( level < maxLevels  )
                    {
                        traverseFor( steps, level + 1, "LFaLb" );
                    }
                    break;
                default:
                    throw new AssertionError( "Unknown plan " + ch );
            }
        }
    }

    public Coordinate position()
    {
        return coordinate;
    }
}
