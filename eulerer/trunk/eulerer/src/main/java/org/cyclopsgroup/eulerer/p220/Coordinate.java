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
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return x + "," + y;
    }

    /**
     * @param direction Direction of source
     * @param offset Offset disregarding direction of source
     * @return Offset considering source direction
     */
    static Coordinate rotateBy( Direction direction, Coordinate offset )
    {
        switch ( direction )
        {
            case UP:
                return new Coordinate( offset.x, offset.y );
            case RIGHT:
                return new Coordinate( offset.y, -offset.x );
            case DOWN:
                return new Coordinate( -offset.x, -offset.y );
            case LEFT:
                return new Coordinate( -offset.y, offset.x );
            default:
                throw new AssertionError( "Unexpected direction " + direction );
        }
    }

    /**
     * @param direction Direction of source
     * @param offset Offset considering direction of source
     * @return Offset disregarding source direction
     */
    static Coordinate rotateFrom( Direction direction, Coordinate offset )
    {
        switch ( direction )
        {
            case UP:
            case DOWN:
                return rotateBy( direction, offset );
            case LEFT:
                return rotateBy( Direction.RIGHT, offset );
            case RIGHT:
                return rotateBy( Direction.LEFT, offset );
            default:
                throw new AssertionError( "Unexpected direction " + direction );
        }
    }
}
