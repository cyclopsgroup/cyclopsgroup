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
import java.util.HashSet;

import org.apache.commons.lang.ArrayUtils;

/**
 * Default implementation of UIContext
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultUIContext implements UIContext
{
    private HashMap content = new HashMap();

    private UIContext parent;

    /**
     * Construct context without parent
     */
    public DefaultUIContext()
    {
        this(null);
    }

    /**
     * Constructor of DefaultUIContext
     * 
     * @param parent
     */
    public DefaultUIContext(UIContext parent)
    {
        this.parent = parent;
    }

    /**
     * Override method containsKey in super class of DefaultUIContext
     * 
     * @see com.cyclopsgroup.waterview.UIContext#containsKey(java.lang.String)
     */
    public boolean containsKey(String key)
    {
        boolean ret = content.containsKey(key);
        if (!ret && parent != null)
        {
            ret = parent.containsKey(key);
        }
        return ret;
    }

    /**
     * Override method get in super class of DefaultUIContext
     * 
     * @see com.cyclopsgroup.waterview.UIContext#get(java.lang.String)
     */
    public Object get(String name)
    {
        if (content.containsKey(name))
        {
            return content.get(name);
        }
        else if (parent != null)
        {
            return parent.get(name);
        }
        else
        {
            return null;
        }
    }

    /**
     * Method getContent() in class DefaultUIContext
     * 
     * @return
     */
    public HashMap getContent()
    {
        return content;
    }

    /**
     * Override method getKeys in super class of DefaultUIContext
     * 
     * @see com.cyclopsgroup.waterview.UIContext#getKeys()
     */
    public String[] getKeys()
    {
        HashSet keys = new HashSet(content.keySet());
        if (content != null)
        {
            keys.addAll(content.keySet());
        }
        return (String[]) keys.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Override method put in super class of DefaultUIContext
     * 
     * @see com.cyclopsgroup.waterview.UIContext#put(java.lang.String, java.lang.Object)
     */
    public void put(String name, Object object)
    {
        content.put(name, object);
    }

    /**
     * Override method remove in super class of DefaultUIContext
     * 
     * @see com.cyclopsgroup.waterview.UIContext#remove(java.lang.String)
     */
    public void remove(String key)
    {
        content.remove(key);
    }
}