/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
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
package com.cyclopsgroup.waterview.velocity;

import java.util.HashSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.jelly.JellyContext;
import org.apache.velocity.context.Context;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Adapter for velocity context with a Jelly context
 */
public class VelocityJellyContextAdapter implements Context
{
    private JellyContext context;

    /**
     * Constructor for class VelocityJellyContextAdapter
     *
     * @param context Jelly context
     */
    public VelocityJellyContextAdapter(JellyContext context)
    {
        this.context = context;
    }

    /**
     * Overwrite or implement method containsKey()
     *
     * @see org.apache.velocity.context.Context#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object key)
    {
        return get(key.toString()) != null;
    }

    /**
     * Overwrite or implement method get()
     *
     * @see org.apache.velocity.context.Context#get(java.lang.String)
     */
    public Object get(String name)
    {
        return context.getVariable(name);
    }

    /**
     * Overwrite or implement method getKeys()
     *
     * @see org.apache.velocity.context.Context#getKeys()
     */
    public Object[] getKeys()
    {
        HashSet keys = new HashSet();
        CollectionUtils.addAll(keys, context.getVariableNames());
        return keys.toArray();
    }

    /**
     * Overwrite or implement method put()
     *
     * @see org.apache.velocity.context.Context#put(java.lang.String, java.lang.Object)
     */
    public Object put(String name, Object value)
    {
        Object ret = get(name);
        context.setVariable(name, value);
        return ret;
    }

    /**
     * Overwrite or implement method remove()
     *
     * @see org.apache.velocity.context.Context#remove(java.lang.Object)
     */
    public Object remove(Object key)
    {
        Object ret = get(key.toString());
        context.removeVariable(key.toString());
        return ret;
    }

}
