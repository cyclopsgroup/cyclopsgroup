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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.TagLibrary;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;

/**
 * Base tag library registry class which takes a given properties file as registry
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public abstract class PropertyTagLibraryRegistry
{
    private HashMap tagLibraries = new HashMap();

    public PropertyTagLibraryRegistry() throws Exception
    {
        Properties props = getRegistryProperties();
        for (Iterator i = props.keySet().iterator(); i.hasNext();)
        {
            String key = (String) i.next();
            if (key.endsWith(".url"))
            {
                String url = props.getProperty(key);
                String name = StringUtils.chomp(key, ".url");
                String libraryName = props.getProperty(name + ".library");
                if (StringUtils.isEmpty(libraryName))
                {
                    continue;
                }
                TagLibrary library = (TagLibrary) Thread.currentThread()
                        .getContextClassLoader().loadClass(libraryName)
                        .newInstance();
                tagLibraries.put(url, library);
            }
        }
    }

    /**
     * Load registry properties
     * 
     * @return Property object which contains tag-class mapping
     * @throws IOException
     *                    Possible resource accessing exception
     */
    protected Properties getRegistryProperties() throws IOException
    {
        Properties tagDefinition = new Properties();
        tagDefinition.load(getRegistryResource().openStream());
        return tagDefinition;
    }

    /**
     * Specify the resource of properties file
     * 
     * @return Resource of properties file
     */
    protected URL getRegistryResource()
    {
        throw new NotImplementedException(getClass()
                + ".getTagDefinitionResource()");
    }

    /**
     * Register all tag libraries in this registry to a given jelly context
     * 
     * @param jellyContext
     *                   JellyContext object
     */
    public synchronized void registerTagLibraries(JellyContext jellyContext)
    {
        for (Iterator i = tagLibraries.keySet().iterator(); i.hasNext();)
        {
            String url = (String) i.next();
            TagLibrary library = (TagLibrary) tagLibraries.get(url);
            jellyContext.registerTagLibrary(url, library);
        }
    }
}