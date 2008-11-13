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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
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
    private static final ConvertUtilsBean convertUtils = new ConvertUtilsBean();

    private static final Map<Type, Class<? extends Object>> nonePrimitiveTypeMap = new Hashtable<Type, Class<? extends Object>>();

    private static final Map<String, Type> typeMap = new Hashtable<String, Type>();

    static
    {
        nonePrimitiveTypeMap.put( Boolean.TYPE, Boolean.class );
        nonePrimitiveTypeMap.put( Byte.TYPE, Byte.class );
        nonePrimitiveTypeMap.put( Character.TYPE, Character.class );
        nonePrimitiveTypeMap.put( Short.TYPE, Short.class );
        nonePrimitiveTypeMap.put( Integer.TYPE, Integer.class );
        nonePrimitiveTypeMap.put( Long.TYPE, Long.class );
        nonePrimitiveTypeMap.put( Float.TYPE, Float.class );
        nonePrimitiveTypeMap.put( Double.TYPE, Double.class );

        typeMap.put( "byte", Byte.TYPE );
        typeMap.put( "char", Character.TYPE );
        typeMap.put( "short", Short.TYPE );
        typeMap.put( "int", Integer.TYPE );
        typeMap.put( "long", Long.TYPE );
        typeMap.put( "float", Float.TYPE );
        typeMap.put( "double", Double.TYPE );
        typeMap.put( "string", String.class );
        typeMap.put( "date", Date.class );

        convertUtils.register( new DateConverter(), Date.class );
        convertUtils.register( new StringConverterAdapter(), String.class );
    }

    /**
     * Convert string to given type
     *
     * @param expression String expression
     * @param type Given destination type
     * @return Converted object
     */
    @SuppressWarnings("unchecked")
    public static <T> T convert( String expression, Class<T> type )
    {
        return (T) getConvertUtils().convert( expression, type );
    }

    /**
     * Convert string to given type
     *
     * @param expression String expression
     * @param type Type name
     * @return Converted object
     */
    @SuppressWarnings("unchecked")
    public static Object convert( String expression, String type )
    {
        return convert( expression, getNonePrimitiveType( getType( type ) ) );
    }

    /**
     * Create a collection of grouped elements
     *
     * @param anyCollection Any collection object
     * @param size Size of group
     * @return List of groups
     */
    public static List<List<Object>> createGroup( Object anyCollection, int size )
    {
        if ( size < 1 )
        {
            throw new IllegalArgumentException( "Size can not be smaller than 1" );
        }
        List<List<Object>> ret = new ArrayList<List<Object>>();
        List<Object> group = null;
        for ( Iterator<Object> i = iterate( anyCollection ); i.hasNext(); )
        {
            Object item = i.next();
            if ( group == null )
            {
                group = new ArrayList<Object>( size );
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
    public static List<List<Object>> createVerticalGroup( Object items, int size )
    {
        Iterator<Object> ite = iterate( items );
        List<Object> list = new ArrayList<Object>();
        CollectionUtils.addAll( list, ite );
        int length = list.size() / size;
        if ( list.size() % size > 0 )
        {
            length++;
        }
        List<List<Object>> ret = new ArrayList<List<Object>>( length );
        for ( int i = 0; i < length; i++ )
        {
            ret.add( new ArrayList<Object>() );
        }
        for ( int i = 0; i < list.size(); i++ )
        {
            Object item = list.get( i );
            List<Object> bag = ret.get( i % length );
            bag.add( i / length, item );
        }
        return ret;
    }

    /**
     * @return Convert utils
     */
    public static ConvertUtilsBean getConvertUtils()
    {
        return convertUtils;
    }

    private static BeanUtilsBean beanUtils = new LooseBeanUtilsBean( getConvertUtils() );;

    /** INternal beanutils bean */
    private static class LooseBeanUtilsBean
        extends BeanUtilsBean
    {
        private LooseBeanUtilsBean( ConvertUtilsBean convertUtils )
        {
            super( convertUtils, new PropertyUtilsBean() );
        }

        /**
         * Override method setProperty in class LooseBeanUtilsBean
         *
         * @see org.apache.commons.beanutils.BeanUtilsBean#setProperty(java.lang.Object, java.lang.String, java.lang.Object)
         */
        @Override
        public void setProperty( Object object, String name, Object value )
            throws IllegalAccessException, InvocationTargetException
        {
            try
            {
                super.setProperty( object, name, value );
            }
            catch ( Exception e )
            {
                //ignore exception
            }
        }

        /**
         * Override method copyProperty in class LooseBeanUtilsBean
         *
         * @see org.apache.commons.beanutils.BeanUtilsBean#copyProperty(java.lang.Object, java.lang.String, java.lang.Object)
         */
        @Override
        public void copyProperty( Object object, String name, Object value )
            throws IllegalAccessException, InvocationTargetException
        {
            try
            {
                super.copyProperty( object, name, value );
            }
            catch ( Exception e )
            {
                //ignored
            }
        }

    }

    /**
     * @return Instance of bean utils
     */
    public static BeanUtilsBean getBeanUtils()
    {
        return beanUtils;
    }

    /**
     * Make sure to return none primitive type
     *
     * @param type Type
     * @return None primitive type
     */
    public static Class<? extends Object> getNonePrimitiveType( Type type )
    {
        Class<? extends Object> ret = nonePrimitiveTypeMap.get( type );
        if ( ret != null )
        {
            return ret;
        }
        if ( type instanceof Class )
        {
            return (Class<? extends Object>) type;
        }
        throw new IllegalArgumentException( "Unknown type " + type );
    }

    /**
     * Make sure to return none primitive type
     *
     * @param typeName Type name
     * @return None primitive type
     */
    public static Class<? extends Object> getNonePrimitiveType( String typeName )
    {
        Type type = getType( typeName );
        return getNonePrimitiveType( type );
    }

    /**
     * Get type
     *
     * @param typeName Name of type
     * @return Type class
     */
    public static Type getType( String typeName )
    {
        Type type = typeMap.get( typeName );
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
    @SuppressWarnings("unchecked")
    public static Iterator<Object> iterate( Object items )
    {
        if ( items instanceof Collection )
        {
            return ( (Collection<Object>) items ).iterator();
        }
        else if ( items instanceof Iterator )
        {
            return (Iterator<Object>) items;
        }
        else if ( items instanceof Enumeration )
        {
            return new EnumerationIterator( (Enumeration) items );
        }
        else if ( items instanceof Object[] )
        {
            return new ArrayIterator( items );
        }
        else
        {
            List<Object> ret = new ArrayList<Object>( 1 );
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
    public static void registerType( String name, Type type )
    {
        typeMap.put( name, type );
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
