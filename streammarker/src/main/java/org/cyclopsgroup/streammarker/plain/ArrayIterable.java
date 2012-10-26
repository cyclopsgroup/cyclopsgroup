package org.cyclopsgroup.streammarker.plain;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterable<T>
    implements Iterable<T>
{
    private class It
        implements Iterator<T>
    {
        private int index = 0;

        @Override
        public boolean hasNext()
        {
            return index < array.length;
        }

        @Override
        public T next()
        {
            if ( !hasNext() )
            {
                throw new NoSuchElementException( "The " + index + "th element does not exist in array with "
                    + array.length + " elements" );
            }
            return array[index++];
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException( "Array iterable is read only" );
        }
    }

    private final T[] array;

    public ArrayIterable( T[] array )
    {
        this.array = array;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Iterator<T> iterator()
    {
        return new It();
    }
}
