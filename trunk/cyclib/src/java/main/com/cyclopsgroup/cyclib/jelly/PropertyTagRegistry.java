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

import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.jelly.TagLibrary;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Property file defined tag registry
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class PropertyTagRegistry extends TagRegistry
{
    private Log logger = LogFactory.getLog(getClass());

    /**
     * Constructor for class PropertiesTagLibrary
     * 
     * @param libraryDefinitionPath tag definition path
     */
    public PropertyTagRegistry(String libraryDefinitionPath)
    {
        Properties props = null;
        try
        {
            props = PropertyLoader.loadAll(libraryDefinitionPath);
        }
        catch (Exception e)
        {
            logger.error("Can not load tag library definition", e);
        }
        for (Iterator i = props.keySet().iterator(); i.hasNext();)
        {
            String key = (String) i.next();
            if (!key.endsWith(".url"))
            {
                continue;
            }
            String url = props.getProperty(key);
            String libraryName = StringUtils.chomp(url, ".url");
            if (props.containsKey(libraryName + ".class"))
            {
                String libraryClass = props.getProperty(libraryName + ".class");
                try
                {
                    TagLibrary library = (TagLibrary) Class.forName(
                            libraryClass).newInstance();
                    registerTagLibrary(url, library);
                }
                catch (Exception e)
                {
                    logger.error("Can not register tag library [" + url + "]",
                            e);
                }
            }
            else if (props.containsKey(libraryName + ".tags"))
            {
                String tagDefinition = props.getProperty(libraryName + ".tags");
                PropertyTagLibrary library = new PropertyTagLibrary(
                        tagDefinition);
                registerTagLibrary(url, library);
            }
            else
            {
                logger.error("Invalid tag library definition [" + url + "]");
            }
        }
    }
}