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

import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.spi.CacheManager;
import com.cyclopsgroup.waterview.spi.DynaViewFactory;
import com.cyclopsgroup.waterview.spi.View;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Internal cached view factory
 */
class CachedViewFactory implements DynaViewFactory
{
    private CacheManager cache;

    private DynaViewFactory proxy;

    /**
     * @param proxy
     * @param cache
     */
    CachedViewFactory(DynaViewFactory proxy, CacheManager cache)
    {
        this.proxy = proxy;
        this.cache = cache;
    }

    /**
     * Overwrite or implement method createView()
     *
     * @see com.cyclopsgroup.waterview.spi.DynaViewFactory#createView(com.cyclopsgroup.waterview.Path)
     */
    public synchronized View createView(Path path) throws Exception
    {
        String key = proxy.hashCode() + '/' + path.getFullPath();
        if (cache.contains(this, key))
        {
            return (View) cache.get(this, key);
        }
        View view = proxy.createView(path);
        cache.put(this, key, view);
        return view;
    }
}