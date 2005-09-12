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

import java.util.Hashtable;
import java.util.Map;

import com.cyclopsgroup.waterview.spi.CacheManager;

/**
 * Hashtable implemented cache manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class HashtableCacheManager
    extends AbstractMapCacheManager
    implements CacheManager
{
    private Hashtable content = new Hashtable();

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.core.AbstractMapCacheManager#getContent()
     */
    protected Map getContent()
    {
        return content;
    }
}
