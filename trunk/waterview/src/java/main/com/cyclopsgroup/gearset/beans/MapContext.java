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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

/**
 * Map implemented context
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class MapContext implements Context
{

    private Map map;

    /**
     * Constructor of MapContext
     */
    public MapContext()
    {
        this(new HashMap());
    }

    /**
     * Constructor of MapContext
     * 
     * @param initialMap
     *                   Initial map object
     */
    public MapContext(Map initialMap)
    {
        map = initialMap;
    }

    /**
     * Override method get in super class of MapContext
     * 
     * @see com.cyclopsgroup.gearset.beans.Context#get(java.lang.String)
     */
    public Object get(String name)
    {
        if (map == null)
        {
            return null;
        }
        else
        {
            return map.get(name);
        }
    }

    /**
     * Get map object
     * 
     * @return Map instance
     */
    public Map getMap()
    {
        return map;
    }

    /**
     * Override method getNames in super class of MapContext
     * 
     * @see com.cyclopsgroup.gearset.beans.Context#getNames()
     */
    public String[] getNames()
    {
        if (map == null)
        {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        else
        {
            return (String[]) map.keySet().toArray(
                    ArrayUtils.EMPTY_STRING_ARRAY);
        }
    }

    /**
     * Override method put in super class of MapContext
     * 
     * @see com.cyclopsgroup.gearset.beans.Context#put(java.lang.String, java.lang.Object)
     */
    public void put(String name, Object object)
    {
        if (map == null)
        {
            return;
        }
        if (object == null)
        {
            map.remove(name);
        }
        else
        {
            map.put(name, object);
        }
    }
}