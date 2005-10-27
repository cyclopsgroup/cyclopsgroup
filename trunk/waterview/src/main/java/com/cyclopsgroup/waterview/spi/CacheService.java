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
package com.cyclopsgroup.waterview.spi;

/**
 * Interface to handle all cache
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface CacheService
{
    /** Role name of CacheManager */
    String ROLE = CacheService.class.getName();

    /**
     * Check if an object exists in the cache
     *
     * @param host Host
     * @param key Object key
     * @return True if it exist
     */
    boolean contains( Object host, String key );

    /**
     * Get object from cache
     *
     * @param host Host
     * @param key Object key
     * @return Object, null if it doesn't exist
     */
    Object get( Object host, String key );

    /**
     * Put object into cache
     *
     * @param host Host
     * @param key Object key
     * @param value Object value
     */
    void put( Object host, String key, Object value );
}
