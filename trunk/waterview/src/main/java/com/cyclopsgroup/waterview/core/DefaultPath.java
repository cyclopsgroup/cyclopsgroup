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

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.Path;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Internal implementation of path
 */
class DefaultPath
    implements Path
{
    private String extension = StringUtils.EMPTY;

    private String fullPath;

    private String packageAlias;

    private String packageName;

    private String path;

    private String pathWithoutExtension;

    /**
     * Constructor
     * 
     * @param packageName Package name
     * @param packageAlias Package alias
     * @param path Relative path without package alias
     */
    DefaultPath( String packageName, String packageAlias, String path )
    {
        if ( path.indexOf( 0 ) == '/' )
        {
            path = '/' + path;
        }
        this.packageName = packageName;
        this.packageAlias = packageAlias;
        this.path = path;

        pathWithoutExtension = path;
        int lastDot = StringUtils.lastIndexOf( path, '.' );
        if ( lastDot > -1 )
        {
            pathWithoutExtension = path.substring( 0, lastDot );
            extension = path.substring( lastDot );
        }

        fullPath = '/' + packageAlias + path;
    }

    /**
     * Overwrite or implement method getExtension()
     *
     * @see com.cyclopsgroup.waterview.Path#getExtension()
     */
    public String getExtension()
    {
        return extension;
    }

    /**
     * Overwrite or implement method getFullPath()
     *
     * @see com.cyclopsgroup.waterview.Path#getFullPath()
     */
    public String getFullPath()
    {
        return fullPath;
    }

    /**
     * Overwrite or implement method getPackage()
     * @see com.cyclopsgroup.waterview.Path#getPackage()
     */
    public String getPackage()
    {
        return packageName;
    }

    /**
     * Overwrite or implement method getPackageAlias()
     *
     * @see com.cyclopsgroup.waterview.Path#getPackageAlias()
     */
    public String getPackageAlias()
    {
        return packageAlias;
    }

    /**
     * Overwrite or implement method getPath()
     * @see com.cyclopsgroup.waterview.Path#getPath()
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Overwrite or implement method getPathWithoutExtension()
     *
     * @see com.cyclopsgroup.waterview.Path#getPathWithoutExtension()
     */
    public String getPathWithoutExtension()
    {
        return pathWithoutExtension;
    }

    /**
     * Overwrite or implement method toString()
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return getFullPath();
    }

}