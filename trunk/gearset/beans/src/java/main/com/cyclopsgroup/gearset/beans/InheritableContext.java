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
package com.cyclopsgroup.gearset.beans;

import java.util.HashSet;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * TODO Add javadoc for class
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class InheritableContext extends MapContext
{
    private Context parent;

    /**
     * Constructor of InheritableContext
     * 
     * @param parentContext
     */
    public InheritableContext(Context parentContext)
    {
        super();
        parent = parentContext;
    }

    /**
     * Constructor of InheritableContext
     * 
     * @param map
     * @param parentContext
     */
    public InheritableContext(Map map, Context parentContext)
    {
        super(map);
        parent = parentContext;
    }

    /**
     * Override method get in super class of InheritableContext
     * 
     * @see com.cyclopsgroup.gearset.beans.Context#get(java.lang.String)
     */
    public Object get(String name)
    {
        Object ret = super.get(name);
        if (parent != null && ret == null)
        {
            ret = parent.get(name);
        }
        return ret;
    }

    /**
     * Override method getNames in super class of InheritableContext
     * 
     * @see com.cyclopsgroup.gearset.beans.Context#getNames()
     */
    public String[] getNames()
    {
        if (parent == null)
        {
            return super.getNames();
        }
        else
        {
            HashSet names = new HashSet();
            CollectionUtils.addAll(names, parent.getNames());
            names.addAll(getMap().keySet());
            return (String[]) names.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        }
    }

    /**
     * Method getParent() in class InheritableContext
     * 
     * @return
     */
    public Context getParent()
    {
        return parent;
    }
}