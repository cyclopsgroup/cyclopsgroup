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
package com.cyclopsgroup.lloyd.spi;

import java.util.Iterator;

import org.apache.commons.collections.IteratorUtils;

import com.cyclopsgroup.cyclib.Context;

/**
 * TODO Add javadoc for this class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DummyContext implements Context
{
    private Context parent;

    /**
     * Constructor for class DummyContext
     *
     * @param parent Parent context
     */
    public DummyContext(Context parent)
    {
        this.parent = parent;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.Context#get(java.lang.String)
     */
    public Object get(String name)
    {
        return parent == null ? null : parent.get(name);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.Context#keys()
     */
    public Iterator keys()
    {
        return parent == null ? IteratorUtils.EMPTY_ITERATOR : parent.keys();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.Context#put(java.lang.String, java.lang.Object)
     */
    public void put(String name, Object value)
    {
        throw new IllegalStateException("Context for anonymous is readonly");
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.Context#remove(java.lang.String)
     */
    public void remove(String name)
    {
        throw new IllegalStateException("Context for anonymous is readonly");
    }
}
