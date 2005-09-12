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

import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.utils.TypeUtils;

/**
 * Typed value parser
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class ValueParser
{

    private static HashSet booleanFalseValues = new HashSet();

    private static HashSet booleanTrueValues = new HashSet();

    static
    {
        booleanTrueValues.add( "true" );
        booleanTrueValues.add( "yes" );
        booleanTrueValues.add( "1" );
        booleanTrueValues.add( "t" );
        booleanTrueValues.add( "y" );

        booleanFalseValues.add( "false" );
        booleanFalseValues.add( "no" );
        booleanFalseValues.add( "0" );
        booleanFalseValues.add( "f" );
        booleanFalseValues.add( "n" );
    }

    /** Default boolean value */
    public boolean defaultBooleanValue = false;

    /** Default double value */
    public double defaultDoubleValue = 0.0;

    /** Default float value */
    public float defaultFloatValue = 0.0f;

    /** default int value */
    public int defaultIntValue = 0;

    /** Default long value */
    public long defaultLongValue = 0l;

    /** Default string value */
    public String defaultStringValue = StringUtils.EMPTY;

    /**
     * Add an attribute
     *
     * @param name Name of the attribute
     * @param value
     */
    public abstract void add( String name, String value );

    /**
     * Internally get string value
     *
     * @param name Name of attribute
     * @return String value of attribute
     * @throws Exception Throw it out
     */
    protected abstract String doGetValue( String name )
        throws Exception;

    /**
     * Internally get string array value
     *
     * @param name Name of the attribute
     * @return String array value of attribute
     * @throws Exception Throw it out
     */
    protected abstract String[] doGetValues( String name )
        throws Exception;

    /**
     * Simple get method
     *
     * @param name Name of the attribute
     * @return Attribute value or attribute values
     * @throws Exception Throw it out
     */
    public Object get( String name )
        throws Exception
    {
        String[] values = doGetValues( name );
        if ( values == null || values.length == 0 )
        {
            return null;
        }
        if ( values.length == 1 )
        {
            return values[0];
        }
        return values;
    }

    /**
     * Get boolean value of attribute
     *
     * @param name Name of attribute
     * @return Boolean value of attribute
     */
    public boolean getBoolean( String name )
    {
        return getBoolean( name, defaultBooleanValue );
    }

    /**
     * Get boolean value of attribute
     *
     * @param name Attribute name
     * @param defaultValue Default value
     * @return Boolean value of attribute
     */
    public boolean getBoolean( String name, boolean defaultValue )
    {
        try
        {
            String ret = doGetValue( name );
            if ( StringUtils.isEmpty( ret ) )
            {
                return defaultValue;
            }
            if ( booleanTrueValues.contains( ret ) )
            {
                return true;
            }
            if ( booleanFalseValues.contains( ret ) )
            {
                return false;
            }
            return defaultValue;
        }
        catch ( Exception e )
        {
            return defaultValue;
        }
    }

    /**
     * Get date value
     *
     * @param name Attribute name
     * @return Date or null
     */
    public Date getDate( String name )
    {
        return getDate( name, (Date) null );
    }

    /**
     * Get date with default value
     *
     * @param name Attribute name
     * @param defaultValue Default date value
     * @return Date object
     */
    public Date getDate( String name, Date defaultValue )
    {
        try
        {
            Date date = (Date) TypeUtils.convert( doGetValue( name ), Date.class );
            if ( date == null )
            {
                date = defaultValue;
            }
            return date;
        }
        catch ( Exception e )
        {
            return defaultValue;
        }
    }

    /**
     * Get date value with DateFormat
     *
     * @param name Attribute name
     * @param defaultValue Default date value
     * @param format DateFormat
     * @return Date object
     */
    public Date getDate( String name, Date defaultValue, DateFormat format )
    {
        try
        {
            return format.parse( doGetValue( name ) );
        }
        catch ( Exception e )
        {
            return defaultValue;
        }
    }

    /**
     * Get date value with DateFormat
     *
     * @param name Attribute name
     * @param format Date format
     * @return Date object
     */
    public Date getDate( String name, DateFormat format )
    {
        return getDate( name, null, format );
    }

    /**
     * Get double value of attribute
     *
     * @param name Name of the attribute
     * @return Double value of attribute
     */
    public double getDouble( String name )
    {
        return getDouble( name, defaultDoubleValue );
    }

    /**
     * Get Float value of attribute
     *
     * @param name Name of the attribute
     * @param defaultValue Default value of attribute
     * @return String value of attribute
     */
    public double getDouble( String name, double defaultValue )
    {
        try
        {
            return Double.parseDouble( doGetValue( name ) );
        }
        catch ( Exception ignored )
        {
            return defaultValue;
        }
    }

    /**
     * Get float value of attribute
     *
     * @param name Name of the attribute
     * @return Float value of attribute
     */
    public float getFloat( String name )
    {
        return getFloat( name, defaultFloatValue );
    }

    /**
     * Get Float value of attribute
     *
     * @param name Name of the attribute
     * @param defaultValue Default value of attribute
     * @return String value of attribute
     */
    public float getFloat( String name, float defaultValue )
    {
        try
        {
            return Float.parseFloat( doGetValue( name ) );
        }
        catch ( Exception ignored )
        {
            return defaultValue;
        }
    }

    /**
     * Get int value of attribute
     *
     * @param name Name of the attribute
     * @return Int value of attribute
     */
    public int getInt( String name )
    {
        return getInt( name, defaultIntValue );
    }

    /**
     * Get int value of attribute
     *
     * @param name Name of the attribute
     * @param defaultValue Default value of attribute
     * @return Int value of attribute
     */
    public int getInt( String name, int defaultValue )
    {
        try
        {
            return Integer.parseInt( doGetValue( name ) );
        }
        catch ( Exception ignored )
        {
            return defaultValue;
        }
    }

    /**
     * Get int value array
     *
     * @param name Name of the attribute
     * @return Int value array
     */
    public int[] getInts( String name )
    {
        return getInts( name, defaultIntValue );
    }

    /**
     * Get int values
     *
     * @param name Attribute name
     * @param defaultValue Default int value
     * @return Int array
     */
    public int[] getInts( String name, int defaultValue )
    {
        try
        {
            String[] values = doGetValues( name );
            int[] ret = new int[values.length];
            for ( int i = 0; i < values.length; i++ )
            {
                String value = values[i];
                try
                {
                    ret[i] = Integer.parseInt( value );
                }
                catch ( Exception ignored )
                {
                    ret[i] = defaultValue;
                }
            }
            return ret;
        }
        catch ( Exception e )
        {
            return ArrayUtils.EMPTY_INT_ARRAY;
        }
    }

    /**
     * Get long value of attribute
     *
     * @param name Name of the attribute
     * @return Long value of attribute
     */
    public long getLong( String name )
    {
        return getLong( name, defaultLongValue );
    }

    /**
     * Get long value of attribute
     *
     * @param name Name of the attribute
     * @param defaultValue Default value of attribute
     * @return Int value of attribute
     */
    public long getLong( String name, long defaultValue )
    {
        try
        {
            return Long.parseLong( doGetValue( name ) );
        }
        catch ( Exception ignored )
        {
            return defaultValue;
        }
    }

    /**
     * Get long array value of attribute
     *
     * @param name Name of the attribute
     * @return Long value
     */
    public long[] getLongs( String name )
    {
        return getLongs( name, defaultLongValue );
    }

    /**
     * Get long array value of attribute
     *
     * @param name Name of the attribute
     * @param defaultValue Default long value
     * @return Long array
     */
    public long[] getLongs( String name, long defaultValue )
    {
        try
        {
            String[] values = doGetValues( name );
            long[] ret = new long[values.length];
            for ( int i = 0; i < values.length; i++ )
            {
                String value = values[i];
                try
                {
                    ret[i] = Long.parseLong( value );
                }
                catch ( Exception e )
                {
                    ret[i] = defaultValue;
                }
            }
            return ret;
        }
        catch ( Exception e )
        {
            return ArrayUtils.EMPTY_LONG_ARRAY;
        }
    }

    /**
     * Get String value of attribute
     *
     * @param name Name of the attribute
     * @return String value of attribute
     */
    public String getString( String name )
    {
        return getString( name, defaultStringValue );
    }

    /**
     * Get String value of attribute
     *
     * @param name Name of the attribute
     * @param defaultValue Default value of attribute
     * @return String value of attribute
     */
    public String getString( String name, String defaultValue )
    {
        try
        {
            String ret = doGetValue( name );
            return ret == null ? defaultValue : ret;
        }
        catch ( Exception ignored )
        {
            return defaultValue;
        }
    }

    /**
     * Get string array value of an attribute
     *
     * @param name Attribute name
     * @return String array
     */
    public String[] getStrings( String name )
    {
        return getStrings( name, defaultStringValue );
    }

    /**
     * Get string array
     *
     * @param name Name of the attribute
     * @param defaultValue Default string value
     * @return String array
     */
    public String[] getStrings( String name, String defaultValue )
    {
        try
        {
            String[] values = doGetValues( name );
            for ( int i = 0; i < values.length; i++ )
            {
                String value = values[i];
                if ( value == null )
                {
                    values[i] = defaultValue;
                }
            }
            return values;
        }
        catch ( Exception e )
        {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
    }

    /**
     * Remove an attribute
     *
     * @param name Name of the attribute
     */
    public abstract void remove( String name );

    /**
     * Set a value
     *
     * @param name Name of attribute
     * @param value Value of attribute
     */
    public void set( String name, String value )
    {
        remove( name );
        if ( value != null )
        {
            add( name, value );
        }
    }
}