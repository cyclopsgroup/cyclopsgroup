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
package com.cyclopsgroup.levistone;

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
     * Get count of result
     *
     * @return Integer count value
     */
    int count();

    /**
     * Getter method for limit
     * 
     * @return Returns the limit.
     * 
     * @uml.property name="limit"
     */
    int getLimit();

    /**
     * Getter method for offset
     * 
     * @return Returns the offset.
     * 
     * @uml.property name="offset"
     */
    int getOffset();


    /**
     * Get iterator of result
     *
     * @return Iterator of result
     */
    Iterator iterator();

    /**
     * Get list of result
     *
     * @return List of result
     */
    List list();

    /**
     * Setter method for limit
     * 
     * @param limit The limit to set.
     * 
     * @uml.property name="limit"
     */
    void setLimit(int limit);

    /**
     * Setter method for offset
     * 
     * @param offset The offset to set.
     * 
     * @uml.property name="offset"
     */
    void setOffset(int offset);

}