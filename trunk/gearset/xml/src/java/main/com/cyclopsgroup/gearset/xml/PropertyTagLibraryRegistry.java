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

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.TagLibrary;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.gearset.beans.SimpleLogEnabled;

/**
 * Base tag library registry class which takes a given properties file as registry
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public abstract class PropertyTagLibraryRegistry extends SimpleLogEnabled
{

    /** Empty url array */
    public static final URL[] EMPTY_URL_ARRAY = new URL[0];

    private HashMap tagLibraries = new HashMap();

    /**
     * Constructor of PropertyTagLibraryRegistry
     */
    public PropertyTagLibraryRegistry()
    {
        try
        {
            URL[] resources = getRegistryResources();
            for (int i = 0; i < resources.length; i++)
            {
                URL resource = resources[i];
                Properties props = new Properties();
                try
                {
                    props.load(resource.openStream());
                    for (Iterator j = props.keySet().iterator(); j.hasNext();)
                    {
                        String key = (String) j.next();
                        if (key.endsWith(".url"))
                        {
                            String url = props.getProperty(key);
                            String name = StringUtils.chomp(key, ".url");
                            String libraryName = props.getProperty(name
                                    + ".library");
                            if (StringUtils.isEmpty(libraryName))
                            {
                                continue;
                            }
                            TagLibrary library = (TagLibrary) Thread
                                    .currentThread().getContextClassLoader()
                                    .loadClass(libraryName).newInstance();
                            tagLibraries.put(url, library);
                        }
                    }
                }
                catch (Exception e)
                {
                    getLogger().error(
                            "Error occurred when loading properties "
                                    + resource, e);
                }
            }
        }
        catch (Exception e)
        {
            getLogger().fatal("Can't initialize tag library registry", e);
        }
    }

    /**
     * Specify the resource of properties file
     * 
     * @return Resource of properties file
     * @throws Exception Throw it out
     */
    protected abstract URL[] getRegistryResources() throws Exception;

    /**
     * Register all tag libraries in this registry to a given jelly context
     * 
     * @param jellyContext JellyContext object
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