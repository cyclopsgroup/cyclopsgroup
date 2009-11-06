package org.cyclopsgroup.eulerer.p220;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Path segment relative to given coordinate and direction
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class Path
{
    /**
     * Offset after path
     */
    final Coordinate offset;

    /**
     * Number of steps in this path
     */
    final long steps;

    /**
     * Number of turns in this path
     */
    final int turns;

    /**
     * @param offset Absolute location offset
     * @param turns Number of turns it takes
     * @param steps Number of steps it takes
     */
    Path( Coordinate offset, int turns, long steps )
    {
        this.offset = offset;
        this.steps = steps;
        this.turns = turns;
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
        return ReflectionToStringBuilder.toString( this );
    }
}
