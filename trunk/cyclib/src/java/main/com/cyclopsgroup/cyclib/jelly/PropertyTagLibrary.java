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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Tag library defined with a given properties file
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class PropertyTagLibrary extends TagLibrary
{
    private Log logger = LogFactory.getLog(getClass());

    /**
     * Constructor for class PropertiesTagLibrary
     * @param tagDefinitionPath Path of tag definition properties file
     */
    public PropertyTagLibrary(String tagDefinitionPath)
    {
        Properties props = null;
        try
        {
            props = PropertyLoader.loadAll(tagDefinitionPath);
        }
        catch (Exception e)
        {
            logger.error("Can not load tag library definition", e);
        }
        for (Iterator i = props.keySet().iterator(); i.hasNext();)
        {
            String tagName = (String) i.next();
            String tagClass = props.getProperty(tagName);
            try
            {
                registerTag(tagName, Class.forName(tagClass));
            }
            catch (Exception e)
            {
                logger.warn("Can not register tag [" + tagName
                        + "] with class [" + tagClass + "]", e);
            }
        }
    }
}