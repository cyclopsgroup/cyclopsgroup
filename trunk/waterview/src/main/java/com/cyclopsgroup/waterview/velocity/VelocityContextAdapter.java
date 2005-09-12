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
package com.cyclopsgroup.waterview.velocity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.cyclopsgroup.waterview.Context;

/**
 * Adapter for velocity context
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class VelocityContextAdapter
    implements org.apache.velocity.context.Context
{

    private Context context;

    /**
     * Constructor for class VelocityContextAdapter
     *
     * @param context Given context
     */
    public VelocityContextAdapter( Context context )
    {
        this.context = context;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.velocity.context.Context#containsKey(java.lang.Object)
     */
    public boolean containsKey( Object name )
    {
        Object value = context.get( name.toString() );
        return value != null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.velocity.context.Context#get(java.lang.String)
     */
    public Object get( String name )
    {
        return context.get( name );
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.velocity.context.Context#getKeys()
     */
    public Object[] getKeys()
    {
        List keys = new ArrayList();
        CollectionUtils.addAll( keys, context.keys() );
        return keys.toArray();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.velocity.context.Context#put(java.lang.String, java.lang.Object)
     */
    public Object put( String name, Object value )
    {
        context.put( name, value );
        return value;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.velocity.context.Context#remove(java.lang.Object)
     */
    public Object remove( Object name )
    {
        Object ret = context.get( name.toString() );
        context.put( name.toString(), null );
        return ret;
    }
}
