/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.utils;

import java.util.Iterator;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Iterator with given max size
 */
public class PartialIterator
    implements Iterator
{
    private int index = 0;

    private Iterator iterator;

    private int maxSize;

    private int offset = 0;

    /**
     * Constructor for class LimitedIterator
     *
     * @param iterator Iterator
     * @param size Limited size of it
     * @param from From given position
     */
    public PartialIterator( Iterator iterator, int size, int from )
    {
        this.iterator = iterator;
        maxSize = size;
        if ( size < 0 )
        {
            throw new IllegalArgumentException( "Size of iterator can not be so small" );
        }
        this.offset = from;
    }

    /**
     * Constructor for class PartialIterator
     *
     * @param iterator Iterator object
     * @param size Size of it
     */
    public PartialIterator( Iterator iterator, int size )
    {
        this( iterator, size, 0 );
    }

    /**
     * Overwrite or implement method hasNext()
     *
     * @see java.util.Iterator#hasNext()
     */
    public boolean hasNext()
    {
        return index < maxSize && iterator.hasNext();
    }

    /**
     * Overwrite or implement method next()
     *
     * @see java.util.Iterator#next()
     */
    public Object next()
    {
        index++;
        return iterator.next();
    }

    /**
     * Overwrite or implement method remove()
     *
     * @see java.util.Iterator#remove()
     */
    public void remove()
    {
        iterator.remove();
    }
}
