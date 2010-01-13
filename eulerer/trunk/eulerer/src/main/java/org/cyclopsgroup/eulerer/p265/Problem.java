package org.cyclopsgroup.eulerer.p265;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

class Problem
{
    private final int length;

    private final int size;

    private final int mask;

    Problem( int length )
    {
        this.length = length;
        this.size = 1 << length;
        this.mask = ( 1 << length ) - 1;
    }

    Set<Long> solve()
    {
        HashSet<Long> result = new HashSet<Long>();
        traverse( 0, length, new HashSet<Long>(), result );
        return result;
    }

    private void traverse( long number, int offset, Set<Long> visitedNumbers, Set<Long> result )
    {
        long visit = number & mask;
        if ( visitedNumbers.contains( visit ) )
        {
            return;
        }
        visitedNumbers.add( visit );
        try
        {
            if ( visitedNumbers.size() == ( size ) )
            {
                result.add( number >> ( length - 1 ) );
                return;
            }
            if ( offset >= size + length )
            {
                return;
            }
            long derived = number << 1;
            traverse( derived, offset + 1, visitedNumbers, result );
            if ( offset < size )
            {
                traverse( derived + 1, offset + 1, visitedNumbers, result );
            }
        }
        finally
        {
            visitedNumbers.remove( visit );
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
    }
}
