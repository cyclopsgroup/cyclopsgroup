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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.cyclopsgroup.waterview.utils.TypeUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Map based value parser
 */
public class MapAttributes
    extends Attributes
{
    private Map<String, Object> map;

    /**
     * Constructor for class MapValueParser
     *
     * @param map Given map content
     */
    @SuppressWarnings("unchecked")
    public MapAttributes( Map map )
    {
        this.map = map;
    }

    /**
     * Overwrite or implement method in MapValueParser
     *
     * @see com.cyclopsgroup.waterview.Attributes#add(java.lang.String, java.lang.String)
     */
    @Override
    public void add( String name, String value )
    {
        getMap().put( name, value );
    }

    /**
     * Override method doGetAttributeNames in class MapValueParser
     *
     * @see com.cyclopsgroup.waterview.Attributes#doGetAttributeNames()
     */
    @Override
    protected Set<String> doGetAttributeNames()
    {
        return getMap().keySet();
    }

    /**
     * Overwrite or implement method in MapValueParser
     *
     * @see com.cyclopsgroup.waterview.Attributes#doGetValue(java.lang.String)
     */
    @Override
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
        Iterator<Object> it = TypeUtils.iterate( object );
        if ( it.hasNext() )
        {
            return TypeUtils.toString( it.next() );
        }
        return null;
    }

    /**
     * Overwrite or implement method in MapValueParser
     *
     * @see com.cyclopsgroup.waterview.Attributes#doGetValues(java.lang.String)
     */
    @Override
    protected List<String> doGetValues( String name )
        throws Exception
    {
        List<String> s = new ArrayList<String>();
        Object object = getMap().get( name );
        if ( object != null )
        {
            CollectionUtils.addAll( s, TypeUtils.iterate( object ) );
        }
        return Collections.unmodifiableList( s );
    }

    /**
     * Get map object
     *
     * @return Map object
     */
    public Map<String, Object> getMap()
    {
        return map;
    }

    /**
     * Overwrite or implement method in MapValueParser
     *
     * @see com.cyclopsgroup.waterview.Attributes#remove(java.lang.String)
     */
    @Override
    public void remove( String name )
    {
        getMap().remove( name );
    }
}
