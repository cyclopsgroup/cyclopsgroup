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
package com.cyclopsgroup.waterview.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.iterators.ArrayIterator;
import org.apache.commons.collections.iterators.EnumerationIterator;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Type conversion utils
 */
public final class TypeUtils
{
    private static ConvertUtilsBean convertUtils;

    private static Map nonePrimitiveTypeMap;

    private static Map typeMap;

    /**
     * Convert string to given type
     *
     * @param expression String expression
     * @param type Given destination type
     * @return Converted object
     */
    public static Object convert( String expression, Class type )
    {
        return getConvertUtils().convert( expression, type );
    }

    /**
     * Convert string to given type
     *
     * @param expression String expression
     * @param type Type name
     * @return Converted object
     */
    public static Object convert( String expression, String type )
    {
        return convert( expression, getType( type ) );
    }

    /**
     * Create a collection of grouped elements
     *
     * @param anyCollection Any collection object
     * @param size Size of group
     * @return List of groups
     */
    public static List createGroup( Object anyCollection, int size )
    {
        if ( size < 1 )
        {
            throw new IllegalArgumentException( "Size can not be smaller than 1" );
        }
        List ret = new ArrayList();
        List group = null;
        for ( Iterator i = iterate( anyCollection ); i.hasNext(); )
        {
            Object item = i.next();
            if ( group == null )
            {
                group = new ArrayList( size );
            }
            group.add( item );
            if ( group.size() == size )
            {
                ret.add( group );
                group = null;
            }
        }
        if ( group != null )
        {
            ret.add( group );
        }
        return ret;
    }

    /**
     *Create a collection of vertical groups
     *
     * @param items Any collection objects
     * @param size Size of group
     * @return List of groups
     */
    public static List createVerticalGroup( Object items, int size )
    {
        Iterator ite = iterate( items );
        List list = new ArrayList();
        CollectionUtils.addAll( list, ite );
        int length = list.size() / size;
        if ( list.size() % size > 0 )
        {
            length++;
        }
        List[] bags = new List[length];
        for ( int i = 0; i < length; i++ )
        {
            bags[i] = new ArrayList();
        }
        for ( int i = 0; i < list.size(); i++ )
        {
            Object item = list.get( i );
            List bag = bags[i % length];
            bag.add( i / length, item );
        }
        List ret = new ArrayList();
        CollectionUtils.addAll( ret, bags );
        return ret;
    }

    /**
     * @return Convert utils
     */
    public synchronized static ConvertUtilsBean getConvertUtils()
    {
        if ( convertUtils == null )
        {
            convertUtils = new ConvertUtilsBean();
            convertUtils.register( new DateConverter(), Date.class );
            convertUtils.register( new StringConverterAdapter(), String.class );
        }

        return convertUtils;
    }

    private static BeanUtilsBean beanUtils;

    /**
     * @return Instance of bean utils
     */
    public synchronized static BeanUtilsBean getBeanUtils()
    {
        if ( beanUtils == null )
        {
            beanUtils = new BeanUtilsBean( getConvertUtils(), new PropertyUtilsBean() );
        }
        return beanUtils;
    }

    /**
     * Make sure to return none primitive type
     *
     * @param type Type
     * @return None primitive type
     */
    public static Class getNonePrimitiveType( Class type )
    {
        Class ret = (Class) getNonePrimitiveTypeMap().get( type );
        if ( ret == null )
        {
            ret = type;
        }
        return ret;
    }

    /**
     * Make sure to return none primitive type
     *
     * @param typeName Type name
     * @return None primitive type
     */
    public static Class getNonePrimitiveType( String typeName )
    {
        Class type = getType( typeName );
        return getNonePrimitiveType( type );
    }

    private static Map getNonePrimitiveTypeMap()
    {
        if ( nonePrimitiveTypeMap == null )
        {
            nonePrimitiveTypeMap = new Hashtable();
            nonePrimitiveTypeMap.put( Boolean.TYPE, Boolean.class );
            nonePrimitiveTypeMap.put( Byte.TYPE, Byte.class );
            nonePrimitiveTypeMap.put( Character.TYPE, Character.class );
            nonePrimitiveTypeMap.put( Short.TYPE, Short.class );
            nonePrimitiveTypeMap.put( Integer.TYPE, Integer.class );
            nonePrimitiveTypeMap.put( Long.TYPE, Long.class );
            nonePrimitiveTypeMap.put( Float.TYPE, Float.class );
            nonePrimitiveTypeMap.put( Double.TYPE, Double.class );
        }

        return nonePrimitiveTypeMap;
    }

    /**
     * Get type
     *
     * @param typeName Name of type
     * @return Type class
     */
    public static Class getType( String typeName )
    {
        Class type = (Class) getTypeMap().get( typeName );
        if ( type == null )
        {
            try
            {
                type = Class.forName( typeName );
            }
            catch ( ClassNotFoundException e )
            {
                throw new IllegalArgumentException( typeName + " is not a recognizable type" );
            }
        }
        return type;
    }

    private static synchronized Map getTypeMap()
    {
        if ( typeMap == null )
        {
            typeMap = new Hashtable();
            typeMap.put( "boolean", Boolean.TYPE );
            typeMap.put( "byte", Byte.TYPE );
            typeMap.put( "char", Character.TYPE );
            typeMap.put( "short", Short.TYPE );
            typeMap.put( "int", Integer.TYPE );
            typeMap.put( "long", Long.TYPE );
            typeMap.put( "float", Float.TYPE );
            typeMap.put( "double", Double.TYPE );
            typeMap.put( "string", String.class );
            typeMap.put( "date", Date.class );
        }

        return typeMap;
    }

    /**
     * Test if an object is iterateable
     *
     * @param items Object
     * @return True if it's collection related
     */
    public static boolean isIteratable( Object items )
    {
        if ( items == null )
        {
            return false;
        }
        return ( items instanceof Collection ) || ( items instanceof Iterator ) || ( items instanceof Enumeration )
            || ( items instanceof Object[] );
    }

    /**
     * Return iterator of given object
     *
     * @param items Could be any object
     * @return Iteartor object of it
     */
    public static Iterator iterate( Object items )
    {
        if ( items instanceof Collection )
        {
            return ( (Collection) items ).iterator();
        }
        else if ( items instanceof Iterator )
        {
            return (Iterator) items;
        }
        else if ( items instanceof Enumeration )
        {
            return new EnumerationIterator( (Enumeration) items );
        }
        else if ( items instanceof Object[] )
        {
            return new ArrayIterator( (Object[]) items );
        }
        else
        {
            List ret = new ArrayList( 1 );
            ret.add( items );
            return ret.iterator();
        }
    }

    /**
     * Register type name
     *
     * @param name Type name 
     * @param type Type class
     */
    public static void registerType( String name, Class type )
    {
        getTypeMap().put( name, type );
    }

    /**
     * Convert object to string
     *
     * @param value Object
     * @return String expression
     */
    public static String toString( Object value )
    {
        return getConvertUtils().convert( value );
    }
}
