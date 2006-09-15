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

import org.apache.commons.collections.iterators.AbstractIteratorDecorator;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Iterator with a max size
 */
public class FixedSizeIterator<T>
    extends AbstractIteratorDecorator
{
    private int size;

    private int index = 0;

    /**
     * Overwrite or implement method hasNext()
     *
     * @see org.apache.commons.collections.iterators.AbstractIteratorDecorator#hasNext()
     */
    @Override
    public boolean hasNext()
    {
        return index < size && super.hasNext();
    }

    /**
     * Overwrite or implement method next()
     *
     * @see org.apache.commons.collections.iterators.AbstractIteratorDecorator#next()
     */
    @SuppressWarnings("unchecked")
    @Override
    public T next()
    {
        Object object = super.next();
        index++;
        return (T) object;
    }

    /**
     * Constructor for class FixedSizeIterator
     *
     * @param iterator Wrapped iterator
     * @param size Max size
     */
    public FixedSizeIterator( Iterator<? extends T> iterator, int size )
    {
        super( iterator );
        this.size = size;
    }
}
