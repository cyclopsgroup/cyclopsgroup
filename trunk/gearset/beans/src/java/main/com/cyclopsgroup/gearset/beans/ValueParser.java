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
     * Get boolean value of a variable
     * 
     * @param name
     *                   Name of the variable
     * @return Variable value as int
     */
    public final boolean getBoolean(String name)
    {
        return getBoolean(name, ConvertUtils.DEFAULT_BOOLEAN);
    }

    /**
     * Get int value of a variable
     * 
     * @param name
     *                   Name of the variable
     * @param defaultValue
     *                   Default boolean value
     * @return Variable value as boolean, defaultValue will be returned if any exception happens
     */
    public final boolean getBoolean(String name, boolean defaultValue)
    {
        try
        {
            return ConvertUtils.convert2Boolean(doGetValue(name), defaultValue);
        }
        catch (Exception swallowed)
        {
            return defaultValue;
        }
    }

    /**
     * Get double value of a variable
     * 
     * @param name
     *                   Name of the variable
     * @return Variable value as int
     */
    public final double getDouble(String name)
    {
        return getDouble(name, ConvertUtils.DEFAULT_DOUBLE);
    }

    /**
     * Get int value of a variable
     * 
     * @param name
     *                   Name of the variable
     * @param defaultValue
     *                   Default double value
     * @return Variable value as double, defaultValue will be returned if any exception happens
     */
    public final double getDouble(String name, double defaultValue)
    {
        try
        {
            return ConvertUtils.convert2Double(doGetValue(name), defaultValue);
        }
        catch (Exception swallowed)
        {
            return defaultValue;
        }
    }

    /**
     * Get float value of a variable
     * 
     * @param name
     *                   Name of the variable
     * @return Variable value as int
     */
    public final float getFloat(String name)
    {
        return getFloat(name, ConvertUtils.DEFAULT_FLOAT);
    }

    /**
     * Get int value of a variable
     * 
     * @param name
     *                   Name of the variable
     * @param defaultValue
     *                   Default float value
     * @return Variable value as float, defaultValue will be returned if any exception happens
     */
    public final float getFloat(String name, float defaultValue)
    {
        try
        {
            return ConvertUtils.convert2Float(doGetValue(name), defaultValue);
        }
        catch (Exception swallowed)
        {
            return defaultValue;
        }
    }

    /**
     * Get int value of a variable
     * 
     * @param name
     *                   Name of the variable
     * @return Variable value as int
     */
    public final int getInt(String name)
    {
        return getInt(name, ConvertUtils.DEFAULT_INT);
    }

    /**
     * Get int value of a variable
     * 
     * @param name
     *                   Name of the variable
     * @param defaultValue
     *                   Default int value
     * @return Variable value as int, defaultValue will be returned if any exception happens
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
     * Get int array variable
     * 
     * @param name
     *                   Variable name
     * @return Array of int
     */
    public final int[] getInts(String name)
    {
        return getInts(name, ArrayUtils.EMPTY_INT_ARRAY);
    }

    /**
     * Get int array variable
     * 
     * @param name
     *                   Variable name
     * @param defaultValue
     *                   Default int array value
     * @return Int array, defaultValue will be returned if any exception occurs
     */
    public final int[] getInts(String name, int[] defaultValue)
    {
        try
        {
            String[] values = doGetValues(name);
            int[] ret = new int[values.length];
            for (int i = 0; i < values.length; i++)
            {
                String value = values[i];
                ret[i] = ConvertUtils.convert2Int(value);
            }
            return ret;
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Get long value of a variable
     * 
     * @param name
     *                   Name of the variable
     * @return Variable value as int
     */
    public final long getLong(String name)
    {
        return getLong(name, ConvertUtils.DEFAULT_LONG);
    }

    /**
     * Get int value of a variable
     * 
     * @param name
     *                   Name of the variable
     * @param defaultValue
     *                   Default long value
     * @return Variable value as long, defaultValue will be returned if any exception happens
     */
    public final long getLong(String name, long defaultValue)
    {
        try
        {
            return ConvertUtils.convert2Long(doGetValue(name), defaultValue);
        }
        catch (Exception swallowed)
        {
            return defaultValue;
        }
    }

    /**
     * Get long array variable
     * 
     * @param name
     *                   Variable name
     * @return Array of long
     */
    public final long[] getLongs(String name)
    {
        return getLongs(name, ArrayUtils.EMPTY_LONG_ARRAY);
    }

    /**
     * Get long array variable
     * 
     * @param name
     *                   Variable name
     * @param defaultValue
     *                   Default int array value
     * @return Long array, defaultValue will be returned if any exception occurs
     */
    public final long[] getLongs(String name, long[] defaultValue)
    {
        try
        {
            String[] values = doGetValues(name);
            long[] ret = new long[values.length];
            for (int i = 0; i < values.length; i++)
            {
                String value = values[i];
                ret[i] = ConvertUtils.convert2Long(value);
            }
            return ret;
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Get short value of a variable
     * 
     * @param name
     *                   Name of the variable
     * @return Variable value as int
     */
    public final short getShort(String name)
    {
        return getShort(name, ConvertUtils.DEFAULT_SHORT);
    }

    /**
     * Get int value of a variable
     * 
     * @param name
     *                   Name of the variable
     * @param defaultValue
     *                   Default short value
     * @return Variable value as short, defaultValue will be returned if any exception happens
     */
    public final short getShort(String name, short defaultValue)
    {
        try
        {
            return ConvertUtils.convert2Short(doGetValue(name), defaultValue);
        }
        catch (Exception swallowed)
        {
            return defaultValue;
        }
    }

    /**
     * Get short array variable
     * 
     * @param name
     *                   Variable name
     * @return Array of short
     */
    public final short[] getShorts(String name)
    {
        return getShorts(name, ArrayUtils.EMPTY_SHORT_ARRAY);
    }

    /**
     * Get short array variable
     * 
     * @param name
     *                   Variable name
     * @param defaultValue
     *                   Default int array value
     * @return Short array, defaultValue will be returned if any exception occurs
     */
    public final short[] getShorts(String name, short[] defaultValue)
    {
        try
        {
            String[] values = doGetValues(name);
            short[] ret = new short[values.length];
            for (int i = 0; i < values.length; i++)
            {
                String value = values[i];
                ret[i] = ConvertUtils.convert2Short(value);
            }
            return ret;
        }
        catch (Exception e)
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

    /**
     * Get string array
     * 
     * @param name
     *                   Variable name
     * @return Array of string
     */
    public final String[] getStrings(String name)
    {
        return getStrings(name, ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Get string array with given default value
     * 
     * @param name
     *                   Name of variable
     * @param defaultValue
     *                   String array value
     * @return String array
     */
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