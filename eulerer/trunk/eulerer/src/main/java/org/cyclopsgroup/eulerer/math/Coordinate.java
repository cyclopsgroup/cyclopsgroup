package org.cyclopsgroup.eulerer.math;

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
}
