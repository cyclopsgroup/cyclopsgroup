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
package com.cyclopsgroup.waterview.velocity;

import org.apache.velocity.context.Context;

import com.cyclopsgroup.waterview.UIContext;

/**
 * Adapter for velocity
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
class VelocityContextAdapter implements Context
{
    private UIContext uiContext;

    /**
     * Constructor of VelocityContextAdapter
     * 
     * @param context UIContext object
     */
    VelocityContextAdapter(UIContext context)
    {
        uiContext = context;
    }

    /**
     * Override method containsKey in super class of VelocityContextAdapter
     * 
     * @see org.apache.velocity.context.Context#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object key)
    {
        if (key == null)
        {
            return false;
        }
        return uiContext.containsKey(key.toString());
    }

    /**
     * Override method get in super class of VelocityContextAdapter
     * 
     * @see org.apache.velocity.context.Context#get(java.lang.String)
     */
    public Object get(String key)
    {
        return uiContext.get(key);
    }

    /**
     * Override method getKeys in super class of VelocityContextAdapter
     * 
     * @see org.apache.velocity.context.Context#getKeys()
     */
    public Object[] getKeys()
    {
        return uiContext.getKeys();
    }

    /**
     * Override method put in super class of VelocityContextAdapter
     * 
     * @see org.apache.velocity.context.Context#put(java.lang.String, java.lang.Object)
     */
    public Object put(String key, Object value)
    {
        uiContext.put(key, value);
        return value;
    }

    /**
     * Override method remove in super class of VelocityContextAdapter
     * 
     * @see org.apache.velocity.context.Context#remove(java.lang.Object)
     */
    public Object remove(Object key)
    {
        if (key == null)
        {
            return null;
        }
        Object ret = uiContext.get(key.toString());
        uiContext.remove(key.toString());
        return ret;
    }

}