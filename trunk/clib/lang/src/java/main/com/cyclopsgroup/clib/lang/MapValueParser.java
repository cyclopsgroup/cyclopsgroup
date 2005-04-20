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
package com.cyclopsgroup.clib.lang;

import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

/**
 * Simple implementation of map value parser
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class MapValueParser extends ValueParser
{
    private Map content;

    /**
     * Constructor for class MapValueParser
     *
     * @param content Map content
     */
    public MapValueParser(Map content)
    {
        this.content = content;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.ValueParser#add(java.lang.String, java.lang.String)
     */
    public void add(String name, String value)
    {
        content.put(name, value);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.ValueParser#doGetValue(java.lang.String)
     */
    protected String doGetValue(String name) throws Exception
    {
        return (String) content.get(name);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.ValueParser#doGetValues(java.lang.String)
     */
    protected String[] doGetValues(String name) throws Exception
    {
        String value = doGetValue(name);
        return value == null ? ArrayUtils.EMPTY_STRING_ARRAY
                : new String[] { value };
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.ValueParser#remove(java.lang.String)
     */
    public void remove(String name)
    {
        content.remove(name);
    }
}
