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
package com.cyclopsgroup.waterview.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.LRUMap;

/**
 * Utils for map
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public final class MapUtils
{
    /** Static StiffMap instance */
    public static final Map STIFF_MAP = new Map()
    {
        /**
         * Override or implement method of parent class or interface
         *
         * @see java.util.Map#clear()
         */
        public void clear()
        {
        }

        /**
         * Override or implement method of parent class or interface
         *
         * @see java.util.Map#containsKey(java.lang.Object)
         */
        public boolean containsKey(Object key)
        {
            return false;
        }

        /**
         * Override or implement method of parent class or interface
         *
         * @see java.util.Map#containsValue(java.lang.Object)
         */
        public boolean containsValue(Object value)
        {
            return false;
        }

        /**
         * Override or implement method of parent class or interface
         *
         * @see java.util.Map#entrySet()
         */
        public Set entrySet()
        {
            return Collections.EMPTY_SET;
        }

        /**
         * Override or implement method of parent class or interface
         *
         * @see java.util.Map#get(java.lang.Object)
         */
        public Object get(Object key)
        {
            return null;
        }

        /**
         * Override or implement method of parent class or interface
         *
         * @see java.util.Map#isEmpty()
         */
        public boolean isEmpty()
        {
            return true;
        }

        /**
         * Override or implement method of parent class or interface
         *
         * @see java.util.Map#keySet()
         */
        public Set keySet()
        {
            return Collections.EMPTY_SET;
        }

        /**
         * Override or implement method of parent class or interface
         *
         * @see java.util.Map#put(java.lang.Object, java.lang.Object)
         */
        public Object put(Object key, Object value)
        {
            return value;
        }

        /**
         * Override or implement method of parent class or interface
         *
         * @see java.util.Map#putAll(java.util.Map)
         */
        public void putAll(Map t)
        {
        }

        /**
         * Override or implement method of parent class or interface
         *
         * @see java.util.Map#remove(java.lang.Object)
         */
        public Object remove(Object key)
        {
            return null;
        }

        /**
         * Override or implement method of parent class or interface
         *
         * @see java.util.Map#size()
         */
        public int size()
        {
            return 0;
        }

        /**
         * Override or implement method of parent class or interface
         *
         * @see java.util.Map#values()
         */
        public Collection values()
        {
            return Collections.EMPTY_SET;
        }
    };

    /**
     * Create map cache based on size
     *
     * @param size Size of cache
     * @return Map object
     */
    public static Map createCache(int size)
    {
        if (size == 0)
        {
            return STIFF_MAP;
        }
        else if (size > 0)
        {
            return new LRUMap(size);
        }
        else
        {
            return new Hashtable();
        }
    }
}
