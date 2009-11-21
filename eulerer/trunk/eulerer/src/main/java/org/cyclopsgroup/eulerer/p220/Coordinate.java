package org.cyclopsgroup.eulerer.p220;

/**
 * Immutable two dimension coordinate
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class Coordinate
    extends org.cyclopsgroup.eulerer.math.Coordinate
{
    /**
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Coordinate( int x, int y )
    {
        super( x, y );
    }

    /**
     * @param delta Delta to minus
     * @return New cooridate
     */
    public Coordinate minus( Coordinate delta )
    {
        return new Coordinate( x - delta.x, y - delta.y );
    }

    /**
     * Rotate coordination and return new coordination
     *
     * @param rotate Number of clock wise turns
     * @return Offset considering source direction
     */
    public Coordinate rotate( int rotate )
    {
        switch ( rotate )
        {
            case 0:
                return new Coordinate( x, y );
            case 1:
                return new Coordinate( y, -x );
            case 2:
                return new Coordinate( -x, -y );
            case 3:
                return new Coordinate( -y, x );
            default:
                throw new AssertionError( "Unexpected rotation " + rotate );
        }
    }
}
