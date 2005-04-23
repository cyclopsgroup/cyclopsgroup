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
package com.cyclopsgroup.clib.lang.xml;

import org.apache.commons.jelly.TagLibrary;

/**
 * Clib tag library
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ClibTagLibrary extends TagLibrary
{
    /**
     * Register a package of tags
     *
     * @param pkg Tag package
     */
    public void registerPackage(TagPackage pkg)
    {
        String[] tagNames = pkg.getTagNames();
        for (int i = 0; i < tagNames.length; i++)
        {
            String tagName = tagNames[i];
            Class tagClass = pkg.getTagClass(tagName);
            registerTag(tagName, tagClass);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.commons.jelly.TagLibrary#registerTag(java.lang.String, java.lang.Class)
     */
    public void registerTag(String name, Class type)
    {
        super.registerTag(name, type);
    }
}