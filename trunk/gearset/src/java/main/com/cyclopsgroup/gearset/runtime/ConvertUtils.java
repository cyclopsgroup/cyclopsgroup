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
package com.cyclopsgroup.gearset.runtime;

import java.util.HashSet;

/**
 * Utility to convert primitive value
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public final class ConvertUtils
{

    private static HashSet booleanFalseValues;

    private static HashSet booleanTrueValues;

    /** Default boolean value */
    public static final boolean DEFAULT_BOOLEAN = false;

    /** Default double value */
    public static final double DEFAULT_DOUBLE = 0.0;

    /** Default float value */
    public static final float DEFAULT_FLOAT = 0.0F;

    /** Default int value */
    public static final int DEFAULT_INT = 0;

    /** Default long value */
    public static final long DEFAULT_LONG = 0L;

    /** Default short value */
    public static final short DEFAULT_SHORT = (short) 0;
    static
    {
        booleanTrueValues = new HashSet();
        booleanTrueValues.add("true");
        booleanTrueValues.add("t");
        booleanTrueValues.add("yes");
        booleanTrueValues.add("y");
        booleanTrueValues.add("1");

        booleanFalseValues = new HashSet();
        booleanFalseValues.add("false");
        booleanFalseValues.add("f");
        booleanFalseValues.add("no");
        booleanFalseValues.add("n");
        booleanFalseValues.add("0");
    }

    /**
     * Convert string to boolean value
     * 
     * @param value String value
     * @return Convert result. false will be returned if conversion fails.
     */
    public static final boolean convert2Boolean(String value)
    {
        return convert2Boolean(value, DEFAULT_BOOLEAN);
    }

    /**
     * Convert string to boolean value
     * 
     * @param value String value
     * @param defaultValue Default boolean value
     * @return Convert result
     */
    public static final boolean convert2Boolean(String value,
            boolean defaultValue)
    {
        try
        {
            if (booleanTrueValues.contains(value.toLowerCase()))
            {
                return true;
            }
            else if (booleanFalseValues.contains(value.toLowerCase()))
            {
                return false;
            }
            else
            {
                throw new Exception();
            }
        }
        catch (Exception swallowed)
        {
            return defaultValue;
        }
    }

    /**
     * Convert string to double value
     * 
     * @param value String value
     * @return Convert result. 0.0 will be returned if conversion fails.
     */
    public static final double convert2Double(String value)
    {
        return convert2Double(value, DEFAULT_DOUBLE);
    }

    /**
     * Convert string to double value
     * 
     * @param value String value
     * @param defaultValue Default double value
     * @return Convert result
     */
    public static final double convert2Double(String value, double defaultValue)
    {
        try
        {
            return Double.parseDouble(value);
        }
        catch (Exception swallowed)
        {
            return defaultValue;
        }
    }

    /**
     * Convert string to float value
     * 
     * @param value String value
     * @return Convert result. 0.0F will be returned if conversion fails.
     */
    public static final float convert2Float(String value)
    {
        return convert2Float(value, DEFAULT_FLOAT);
    }

    /**
     * Convert string to float value
     * 
     * @param value String value
     * @param defaultValue Default float value
     * @return Convert result
     */
    public static final float convert2Float(String value, float defaultValue)
    {
        try
        {
            return Float.parseFloat(value);
        }
        catch (Exception swallowed)
        {
            return defaultValue;
        }
    }

    /**
     * Convert string to int value
     * 
     * @param value String value
     * @return Convert result. 0 will be returned if conversion fails.
     */
    public static final int convert2Int(String value)
    {
        return convert2Int(value, DEFAULT_INT);
    }

    /**
     * Convert string to int value
     * 
     * @param value String value
     * @param defaultValue Default int value
     * @return Convert result
     */
    public static final int convert2Int(String value, int defaultValue)
    {
        try
        {
            return Integer.parseInt(value);
        }
        catch (Exception swallowed)
        {
            return defaultValue;
        }
    }

    /**
     * Convert string to long value
     * 
     * @param value String value
     * @return Convert result. 0L will be returned if conversion fails.
     */
    public static final long convert2Long(String value)
    {
        return convert2Long(value, DEFAULT_LONG);
    }

    /**
     * Convert string to long value
     * 
     * @param value String value
     * @param defaultValue Default long value
     * @return Convert result
     */
    public static final long convert2Long(String value, long defaultValue)
    {
        try
        {
            return Long.parseLong(value);
        }
        catch (Exception swallowed)
        {
            return defaultValue;
        }
    }

    /**
     * Convert string to short value
     * 
     * @param value String value
     * @return Convert result. (short) 0 will be returned if conversion fails.
     */
    public static final short convert2Short(String value)
    {
        return convert2Short(value, DEFAULT_SHORT);
    }

    /**
     * Convert string to short value
     * 
     * @param value String value
     * @param defaultValue Default short value
     * @return Convert result
     */
    public static final short convert2Short(String value, short defaultValue)
    {
        try
        {
            return Short.parseShort(value);
        }
        catch (Exception swallowed)
        {
            return defaultValue;
        }
    }
}