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

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Value parser object
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public abstract class ValueParser
{
    /**
     * Method to override which provide the true value
     * 
     * @param valueName
     *                   Name of the value
     * @return String value
     * @throws Exception
     *                    Just throw it out
     */
    protected abstract String doGetValue(String valueName) throws Exception;

    /**
     * Method to override which provide the true value array
     * 
     * @param valueName
     *                   Name of the value
     * @return String value
     * @throws Exception
     *                    Just throw it out
     */
    protected abstract String[] doGetValues(String valueName) throws Exception;

    /**
     * Simple get method
     * 
     * @param valueName
     *                   Name of the value
     * @return String value
     * @throws Exception
     *                    Just throw it out
     */
    public final String get(String valueName) throws Exception
    {
        return doGetValue(valueName);
    }

    /**
     * Method getInt() in class ValueParser
     * 
     * @param name
     * @return
     */
    public final int getInt(String name)
    {
        return getInt(name, ConvertUtils.DEFAULT_INT);
    }

    /**
     * Method getInt() in class ValueParser
     * 
     * @param name
     * @param defaultValue
     * @return
     */
    public final int getInt(String name, int defaultValue)
    {
        try
        {
            return ConvertUtils.convert2Int(doGetValue(name), defaultValue);
        }
        catch (Exception swallowed)
        {
            return defaultValue;
        }
    }

    /**
     * Get string value
     * 
     * @param name
     *                   Name
     * @return String value. StringUtils.EMPTY will return if can't get the value
     */
    public final String getString(String name)
    {
        return getString(name, StringUtils.EMPTY);
    }

    /**
     * Get string value
     * 
     * @param name
     *                   Name
     * @param defaultValue
     *                   Defualt value
     * @return String value
     */
    public final String getString(String name, String defaultValue)
    {
        try
        {
            return doGetValue(name);
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    public final String[] getStrings(String name)
    {
        return getStrings(name, ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public final String[] getStrings(String name, String[] defaultValue)
    {
        try
        {
            return doGetValues(name);
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }
}