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

import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Util to load properties
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public final class PropertyLoader
{
    private static Log logger = LogFactory.getLog(PropertyLoader.class);

    /**
     * Convenient method to load all properties definition
     *
     * @param path Given path
     * @return Properties object
     * @throws Exception Throw it out
     */
    public static Properties loadAll(String path) throws Exception
    {
        Properties props = new Properties();
        Enumeration e = Thread.currentThread().getContextClassLoader()
                .getResources(path);
        while (e.hasMoreElements())
        {
            URL resource = (URL) e.nextElement();
            try
            {
                props.load(resource.openStream());
            }
            catch (Exception ex)
            {
                logger.error(
                        "Can not load tag library from [" + resource + "]", ex);
            }
        }
        return props;
    }
}