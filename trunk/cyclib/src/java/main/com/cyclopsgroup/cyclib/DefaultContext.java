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
package com.cyclopsgroup.cyclib;

import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * Default implementation of context
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class DefaultContext implements Context
{
    private HashMap content = new HashMap();

    private Context parent;

    /**
     * Constructor for class DefaultContext
     */
    public DefaultContext()
    {
        this(null);
    }

    /**
     * Constructor for class DefaultContext
     *
     * @param parent Parent context
     */
    public DefaultContext(Context parent)
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
        Object ret = content.get(name);
        if (ret == null && parent != null)
        {
            ret = parent.get(name);
        }
        return ret;
    }

    /**
     * TODO Add javadoc for this method
     *
     * @return HashMap content
     */
    public HashMap getContent()
    {
        return content;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.Context#getNames()
     */
    public String[] getNames()
    {
        if (parent == null)
        {
            return (String[]) content.keySet().toArray(
                    ArrayUtils.EMPTY_STRING_ARRAY);
        }
        HashSet names = new HashSet(content.keySet());
        CollectionUtils.addAll(names, parent.getNames());
        return (String[]) names.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.Context#put(java.lang.String, java.lang.Object)
     */
    public void put(String name, Object variable)
    {
        content.put(name, variable);
    }
}