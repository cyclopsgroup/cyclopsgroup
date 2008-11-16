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
package com.cyclopsgroup.waterview.core;

import java.util.Map;

import com.cyclopsgroup.waterview.spi.CacheService;

/**
 * Hashtable implemented cache manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class AbstractMapCacheService
    implements CacheService
{

    /**
     * Clear all content
     */
    public void clear()
    {
        getContent().clear();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.CacheService#contains(java.lang.Object, java.lang.String)
     */
    public boolean contains( Object host, String key )
    {
        String name = key + '@' + host.getClass().getName() + '/' + host.hashCode();
        return getContent().containsKey( name );
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.CacheService#get(java.lang.Object, java.lang.String)
     */
    public Object get( Object host, String key )
    {
        String name = key + '@' + host.getClass().getName() + '/' + host.hashCode();
        return getContent().get( name );
    }

    /**
     * @return Cache map
     */
    protected abstract Map getContent();

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.CacheService#put(java.lang.Object, java.lang.String, java.lang.Object)
     */
    public void put( Object host, String key, Object value )
    {
        String name = key + '@' + host.getClass().getName() + '/' + host.hashCode();
        getContent().put( name, value );
    }
}
