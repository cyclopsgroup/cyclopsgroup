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
package com.cyclopsgroup.levistone.spi;

import com.cyclopsgroup.levistone.QueryResult;

/**
 * Base query result implementation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class AbstractQueryResult implements QueryResult
{
    private int limit = -1;

    private int offset = 0;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.QueryResult#getLimit()
     */
    public int getLimit()
    {
        return limit;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.QueryResult#getOffset()
     */
    public int getOffset()
    {
        return offset;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.QueryResult#setLimit(int)
     */
    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.QueryResult#setOffset(int)
     */
    public void setOffset(int offset)
    {
        this.offset = offset;
    }
}