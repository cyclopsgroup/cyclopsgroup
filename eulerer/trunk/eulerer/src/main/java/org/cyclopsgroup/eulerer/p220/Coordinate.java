package org.cyclopsgroup.eulerer.p220;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Immutable two dimension coordinate
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class Coordinate
{
    /**
     * Public X field
     */
    public final int x;

    /**
     * Public Y field
     */
    public final int y;

    /**
     * @param x Value of X
     * @param y Value of Y
     */
    public Coordinate( int x, int y )
    {
        this.x = x;
        this.y = y;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals( Object obj )
    {
        return EqualsBuilder.reflectionEquals( this, obj );
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode( this );
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

    /**
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return x + "," + y;
    }
}
