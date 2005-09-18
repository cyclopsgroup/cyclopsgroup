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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.cyclopsgroup.waterview.utils.TypeUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Map based value parser
 */
public class MapValueParser
    extends ValueParser
{
    private Map map;

    /**
     * Constructor for class MapValueParser
     *
     * @param map Given map content
     */
    public MapValueParser( Map map )
    {
        this.map = map;
    }

    /**
     * Overwrite or implement method in MapValueParser
     *
     * @see com.cyclopsgroup.waterview.ValueParser#add(java.lang.String, java.lang.String)
     */
    public void add( String name, String value )
    {
        getMap().put( name, value );
    }

    /**
     * Overwrite or implement method in MapValueParser
     *
     * @see com.cyclopsgroup.waterview.ValueParser#doGetValue(java.lang.String)
     */
    protected String doGetValue( String name )
        throws Exception
    {
        Object object = getMap().get( name );
        if ( object == null )
        {
            return null;
        }
        if ( !TypeUtils.isIteratable( object ) )
        {
            return TypeUtils.toString( object );
        }
        Iterator it = TypeUtils.iterate( object );
        if ( it.hasNext() )
        {
            return TypeUtils.toString( it.next() );
        }
        return null;
    }

    /**
     * Overwrite or implement method in MapValueParser
     *
     * @see com.cyclopsgroup.waterview.ValueParser#doGetValues(java.lang.String)
     */
    protected String[] doGetValues( String name )
        throws Exception
    {
        Object object = getMap().get( name );
        if ( object == null )
        {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        ArrayList values = new ArrayList();
        CollectionUtils.addAll( values, TypeUtils.iterate( object ) );
        String[] ret = new String[values.size()];
        for ( int i = 0; i < ret.length; i++ )
        {
            Object value = values.get( i );
            ret[i] = TypeUtils.toString( value );
        }
        return ret;
    }

    /**
     * Get map object
     *
     * @return Map object
     */
    public Map getMap()
    {
        return map;
    }

    /**
     * Overwrite or implement method in MapValueParser
     *
     * @see com.cyclopsgroup.waterview.ValueParser#remove(java.lang.String)
     */
    public void remove( String name )
    {
        getMap().remove( name );
    }

    /**
     * Override method doGetAttributeNames in class MapValueParser
     *
     * @see com.cyclopsgroup.waterview.ValueParser#doGetAttributeNames()
     */
    protected String[] doGetAttributeNames()
    {
        return (String[]) getMap().keySet().toArray( ArrayUtils.EMPTY_STRING_ARRAY );
    }
}
