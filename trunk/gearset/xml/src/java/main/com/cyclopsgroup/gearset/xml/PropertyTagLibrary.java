/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
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
package com.cyclopsgroup.gearset.xml;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.jelly.TagLibrary;
import org.apache.commons.lang.NotImplementedException;

/**
 * Jelly tag library which find tag definitions from a given properties file
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public abstract class PropertyTagLibrary extends TagLibrary
{
    /**
     * Constructor of PropertyTagLibrary Tag classes are registered here
     * 
     * @throws IOException Possible resource accessing exception
     * @throws ClassNotFoundException The class specified in definition could not be found
     */
    public PropertyTagLibrary() throws IOException, ClassNotFoundException
    {
        Properties tagDefinition = getLibraryProperties();
        for (Iterator i = tagDefinition.keySet().iterator(); i.hasNext();)
        {
            String tagName = (String) i.next();
            Class tagClass = Thread.currentThread().getContextClassLoader()
                    .loadClass(tagDefinition.getProperty(tagName));
            registerTag(tagName, tagClass);
        }
    }

    /**
     * Load tag definition properties
     * 
     * @return Property object which contains tag-class mapping
     * @throws IOException Possible resource accessing exception
     */
    protected Properties getLibraryProperties() throws IOException
    {
        Properties tagDefinition = new Properties();
        tagDefinition.load(getLibraryResource().openStream());
        return tagDefinition;
    }

    /**
     * Specify the resource of properties file
     * 
     * @return Resource of properties file
     */
    protected URL getLibraryResource()
    {
        throw new NotImplementedException(getClass()
                + ".getTagDefinitionResource()");
    }
}