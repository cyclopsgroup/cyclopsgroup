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

import java.util.Collection;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.lang.ArrayUtils;

/**
 * Default implementation of value parser
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultValueParser extends ValueParser
{
    private MultiHashMap values = new MultiHashMap();

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.ValueParser#add(java.lang.String, java.lang.String)
     */
    public void add(String name, String value)
    {
        if (value != null)
        {
            values.put(name, value);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.ValueParser#doGetValue(java.lang.String)
     */
    protected String doGetValue(String name) throws Exception
    {
        String[] stringValues = doGetValues(name);
        return stringValues.length == 0 ? null : stringValues[0];
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.ValueParser#doGetValues(java.lang.String)
     */
    protected String[] doGetValues(String name) throws Exception
    {
        Collection c = (Collection) values.get(name);
        return c == null ? ArrayUtils.EMPTY_STRING_ARRAY : (String[]) c
                .toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.ValueParser#remove(java.lang.String)
     */
    public void remove(String name)
    {
        values.remove(name);
    }
}