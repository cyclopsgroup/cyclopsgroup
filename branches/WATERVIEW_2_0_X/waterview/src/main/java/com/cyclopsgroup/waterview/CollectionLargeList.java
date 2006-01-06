/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
 *
 * Licensed under the Open Software License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://opensource.org/licenses/osl-2.1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import org.apache.commons.collections.comparators.ComparatorChain;

import com.cyclopsgroup.waterview.utils.BeanPropertyComparator;
import com.cyclopsgroup.waterview.utils.FixedSizeIterator;
import com.cyclopsgroup.waterview.utils.HashCodeComparator;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Collection implemented large list
 */
public class CollectionLargeList implements LargeList
{
    private Collection collection;

    /**
     * Constructor for type CollectionTableData
     *
     * @param collection Collecton of data
     */
    public CollectionLargeList(Collection collection)
    {
        this.collection = collection;
    }

    /**
     * Overwrite or implement method getSize()
     *
     * @see com.cyclopsgroup.waterview.LargeList#getSize()
     */
    public int getSize()
    {
        return collection.size();
    }

    /**
     * Overwrite or implement method iterate()
     *
     * @see com.cyclopsgroup.waterview.IteratorLargeList#iterate(int, int, com.cyclopsgroup.waterview.LargeList.Sorting[])
     */
    public Iterator iterate(int startPosition, int maxAmount, Sorting[] sortings)
            throws Exception
    {
        Collection sortedResult = collection;
        if (sortings != null && sortings.length > 0)
        {
            ComparatorChain chain = new ComparatorChain();
            for (int i = 0; i < sortings.length; i++)
            {
                Sorting sorting = sortings[i];
                if (sorting.isDescending())
                {
                    chain.addComparator(new BeanPropertyComparator(sorting
                            .getName()), true);
                }
                else
                {
                    chain.addComparator(new BeanPropertyComparator(sorting
                            .getName()));
                }
            }
            chain.addComparator(HashCodeComparator.INSTANCE);
            sortedResult = new TreeSet(chain);
            sortedResult.addAll(collection);
        }
        Iterator it = sortedResult.iterator();
        if (startPosition > 0)
        {
            for (int i = 0; i < startPosition; i++)
            {
                it.next();
            }
        }
        if (maxAmount == UNLIMITED_MAX_AMOUNT)
        {
            return it;
        }
        return new FixedSizeIterator(it, maxAmount);
    }
}
