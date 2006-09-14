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
package com.cyclopsgroup.waterview;

import java.util.Iterator;

import com.cyclopsgroup.waterview.utils.FixedSizeIterator;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Iterator implemented large list
 */
public class IteratorLargeList
    implements LargeList
{
    private boolean disposed = false;

    private Iterator iterator;

    /**
     * Constructor for class IteratorLargeList
     *
     * @param iterator Iterator object
     */
    public IteratorLargeList( Iterator iterator )
    {
        this.iterator = iterator;
    }

    /**
     * Overwrite or implement method getSize()
     *
     * @see com.cyclopsgroup.waterview.LargeList#getSize()
     */
    public int getSize()
        throws Exception
    {
        return UNKNOWN_SIZE;
    }

    /**
     * Overwrite or implement method iterate()
     *
     * @see com.cyclopsgroup.waterview.LargeList#iterate(int, int, com.cyclopsgroup.waterview.LargeList.Sorting[])
     */
    public Iterator iterate( int startPosition, int maxAmount, Sorting[] sortings )
        throws Exception
    {
        //Make sure this object can only be used for one time
        if ( disposed )
        {
            throw new IllegalStateException( "It's already iterated" );
        }
        disposed = true;

        if ( startPosition > 0 )
        {
            for ( int i = 0; i < startPosition; i++ )
            {
                iterator.next();
            }
        }
        if ( maxAmount == UNLIMITED_MAX_AMOUNT )
        {
            return iterator;
        }
        return new FixedSizeIterator( iterator, maxAmount );
    }
}