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
package com.cyclopsgroup.cyclib.jelly;

import java.util.Hashtable;
import java.util.Iterator;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.TagLibrary;

/**
 * Tag registry
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class TagRegistry
{
    private Hashtable tagLibraries = new Hashtable();

    /**
     * Create empty context with registered tag libraries
     *
     * @return JellyContext object
     */
    public JellyContext createContext()
    {
        JellyContext jc = new JellyContext();
        registerContext(jc);
        return jc;
    }

    /**
     * Register all registered tag libraries to given jelly context
     *
     * @param context Jelly context
     */
    public void registerContext(JellyContext context)
    {
        for (Iterator i = tagLibraries.keySet().iterator(); i.hasNext();)
        {
            String url = (String) i.next();
            TagLibrary library = (TagLibrary) tagLibraries.get(url);
            context.registerTagLibrary(url, library);
        }
    }

    /**
     * Register a tag library
     *
     * @param url URL of tag library
     * @param tagLibrary TagLibrary to register
     */
    public void registerTagLibrary(String url, TagLibrary tagLibrary)
    {
        tagLibraries.put(url, tagLibrary);
    }

    /**
     * Remove a registered tag library
     *
     * @param url URL of tag library
     */
    public void unregisterTagLibrary(String url)
    {
        tagLibraries.remove(url);
    }
}