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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.iterators.IteratorChain;

/**
 * Default implementation of context
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class DefaultContext implements Context
{
    private Map content = new HashMap();

    private Context parent;

    /**
     * Constructor for class DefaultContext
     * 
     * @param content Content of this context
     */
    public DefaultContext(Map content)
    {
        this(content, null);
    }

    /**
     * Constructor for class DefaultContext
     *
     * @param content Content of this context
     * @param parent Parent context
     */
    public DefaultContext(Map content, Context parent)
    {
        this.content = content;
        this.parent = parent;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.Context#get(java.lang.String)
     */
    public Object get(String name)
    {
        Object ret = content.get(name);
        if (ret == null && parent != null)
        {
            ret = parent.get(name);
        }
        return ret;
    }

    /**
     * Get hash map content of this context
     *
     * @return HashMap content
     */
    public Map getContent()
    {
        return content;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.Context#keys()
     */
    public Iterator keys()
    {
        if (parent == null)
        {
            return content.keySet().iterator();
        }
        return new IteratorChain(parent.keys(), content.keySet().iterator());
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.Context#put(java.lang.String, java.lang.Object)
     */
    public void put(String name, Object variable)
    {
        if (variable == null)
        {
            content.remove(name);
        }
        else
        {
            content.put(name, variable);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.Context#remove(java.lang.String)
     */
    public void remove(String name)
    {
        content.remove(name);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer('{');
        sb.append(content.toString());
        if (parent != null)
        {
            sb.append(',').append(parent.toString());
        }
        sb.append('}');
        return sb.toString();
    }
}