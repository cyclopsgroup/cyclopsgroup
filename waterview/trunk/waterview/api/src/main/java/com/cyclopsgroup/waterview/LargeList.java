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
import java.util.List;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Large list that can be accessed partially
 */
public interface LargeList<T>
{
    /** Sorting option */
    public interface Sorting
    {
        /**
         * Get sorting name
         *
         * @return Name of sorted field
         */
        String getName();

        /**
         * Is descending sorting?
         *
         * @return True if it's descending
         */
        boolean isDescending();
    }

    /** Unknown page size */
    int UNKNOWN_SIZE = -1;

    /** Unlimited length*/
    int UNLIMITED_MAX_AMOUNT = -1;

    /**
     * Get the size of complete list
     *
     * @return Int size or UNKNOWN_SIZE if it's unknown
     * @throws Exception Throw it out
     */
    int getSize()
        throws Exception;

    /**
     * Partially iterate this list
     *
     * @param startPosition Start position, usually it's 0
     * @param maxAmount If it's UNLIMITED_MAX_AMOUNT, result will be unlimited
     * @param sortings Sorted fields
     * @return Iterator of result
     * @throws Exception Throw it out
     */
    Iterator<T> iterate( int startPosition, int maxAmount, List<Sorting> sortings )
        throws Exception;
}