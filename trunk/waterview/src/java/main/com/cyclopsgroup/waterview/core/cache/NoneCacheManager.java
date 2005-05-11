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
package com.cyclopsgroup.waterview.core.cache;

import com.cyclopsgroup.waterview.CacheManager;

/**
 * Always load new cache manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class NoneCacheManager implements CacheManager
{

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.CacheManager#contains(java.lang.Object, java.lang.String)
     */
    public boolean contains(Object host, String key)
    {
        return false;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.CacheManager#get(java.lang.Object, java.lang.String)
     */
    public Object get(Object host, String key)
    {
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.CacheManager#put(java.lang.Object, java.lang.String, java.lang.Object)
     */
    public void put(Object host, String key, Object value)
    {
        //do nothing
    }

}
