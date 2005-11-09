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

import java.util.Hashtable;

import org.apache.commons.lang.ArrayUtils;

/**
 * A package of tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class TagPackage
{
    /** Empty array */
    public static final TagPackage[] EMPTY_ARRAY = new TagPackage[0];

    private Hashtable tagClasses = new Hashtable();

    /**
     * Add a tag class into repository
     *
     * @param name Tag name
     * @param tagClass Tag class
     */
    public void addTag( String name, Class tagClass )
    {
        tagClasses.put( name, tagClass );
    }

    /**
     * Get tag classes
     *
     * @param name Tag name
     * @return Tag class
     */
    public Class getTagClass( String name )
    {
        return (Class) tagClasses.get( name );
    }

    /**
     * Get name of tags
     *
     * @return Tag name array
     */
    public String[] getTagNames()
    {
        return (String[]) tagClasses.keySet().toArray( ArrayUtils.EMPTY_STRING_ARRAY );
    }
}
