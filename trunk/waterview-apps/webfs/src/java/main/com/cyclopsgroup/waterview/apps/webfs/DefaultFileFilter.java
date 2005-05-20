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
package com.cyclopsgroup.waterview.apps.webfs;

import java.io.File;
import java.io.FileFilter;

/**
 * Default file filter
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultFileFilter implements FileFilter
{

    /**
     * Override or implement method of parent class or interface
     *
     * @see java.io.FileFilter#accept(java.io.File)
     */
    public boolean accept(File file)
    {
        if (file.isHidden())
        {
            return false;
        }
        if (file.getName().charAt(0) == '.')
        {
            return false;
        }
        return true;
    }
}