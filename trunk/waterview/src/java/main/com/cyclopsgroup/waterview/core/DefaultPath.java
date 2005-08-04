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

import com.cyclopsgroup.waterview.spi.ModuleManager.Path;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Internal implementation of path
 */
class DefaultPath implements Path
{
    private String packageName, path;

    /**
     * Constructor
     * 
     * @param packageName
     * @param path
     */
    DefaultPath(String packageName, String path)
    {
        this.packageName = packageName;
        this.path = path;
    }

    /**
     * Overwrite or implement method getPackage()
     * @see com.cyclopsgroup.waterview.spi.ModuleManager.Path#getPackage()
     */
    public String getPackage()
    {
        return packageName;
    }

    /**
     * Overwrite or implement method getPath()
     * @see com.cyclopsgroup.waterview.spi.ModuleManager.Path#getPath()
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Overwrite or implement method toString()
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return getPackage() + "|" + getPath();
    }
}