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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.lang.ArrayUtils;

/**
 * java.util.Map implemented value parser
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class MapValueParser extends ValueParser
{
    private Map map;

    /**
     * Constructor of MapValueParser
     */
    public MapValueParser()
    {
        this(new MultiHashMap());
    }

    /**
     * Constructor of MapValueParser
     * 
     * @param content Content map object
     */
    public MapValueParser(Map content)
    {
        map = content;
    }

    /**
     * Override method doGetValue in super class of MapValueParser
     * 
     * @see com.cyclopsgroup.gearset.beans.ValueParser#doGetValue(java.lang.String)
     */
    protected String doGetValue(String valueName) throws Exception
    {
        Object object = map.get(valueName).toString();
        Object ret = object;
        if (object instanceof Collection)
        {
            ret = ((Collection) object).toArray()[0];
        }
        else if (object instanceof Object[])
        {
            ret = ((Object[]) object)[0];
        }
        return ret.toString();
    }

    /**
     * Override method doGetValues in super class of MapValueParser
     * 
     * @see com.cyclopsgroup.gearset.beans.ValueParser#doGetValues(java.lang.String)
     */
    protected String[] doGetValues(String valueName) throws Exception
    {
        ArrayList values = new ArrayList();
        Object object = map.get(valueName);
        if (object instanceof Collection)
        {
            for (Iterator i = ((Collection) object).iterator(); i.hasNext();)
            {
                Object next = i.next();
                if (next != null)
                {
                    values.add(next.toString());
                }
            }
        }
        else if (object instanceof Object[])
        {
            Object[] array = (Object[]) object;
            for (int i = 0; i < array.length; i++)
            {
                if (array[i] != null)
                {
                    values.add(array[i].toString());
                }
            }
        }
        else
        {
            values.add(object.toString());
        }
        return (String[]) values.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Get map content
     * 
     * @return Map object
     */
    public Map getMap()
    {
        return map;
    }
}