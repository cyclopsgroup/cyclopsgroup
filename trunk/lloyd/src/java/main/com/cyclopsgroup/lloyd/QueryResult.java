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
package com.cyclopsgroup.lloyd;

import java.util.Iterator;
import java.util.List;

/**
 * Query result
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface QueryResult
{
    /**
     * Get limit of result
     * 
     * @return Max record count
     */
    int getLimit();

    /**
     * Set max record number
     * 
     * @return Max record count
     */
    int getOffset();

    /**
     * Get result iterator
     * 
     * @return Iterator object
     */
    Iterator iterator();

    /**
     * Get result as list
     * 
     * @return
     */
    List list();

    /**
     * Set max record count
     * 
     * @param limit Limit of a query
     */
    void setLimit(int limit);

    /**
     * Set offset
     * 
     * @param offset Offset value
     */
    void setOffset(int offset);
}