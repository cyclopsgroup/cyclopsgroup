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
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.jelly.TagLibrary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO Add javadoc for this class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class PropertyTagRegistry extends TagRegistry
{

    /**
     * Load tag definition from given resource
     *
     * @param resource Given resource
     * @return Properties object
     * @throws Exception Throw it out
     */
    protected final static Properties load(URL resource) throws Exception
    {
        return PropertyLoader.load(resource);
    }

    /**
     * Convenient method to load all properties definition
     *
     * @param path Given path
     * @return Properties object
     * @throws Exception Throw it out
     */
    protected final static Properties loadAll(String path) throws Exception
    {
        return PropertyLoader.loadAll(path);
    }

    private Log logger = LogFactory.getLog(getClass());

    /**
     * Constructor for class PropertiesTagLibrary
     */
    public PropertyTagRegistry()
    {
        Properties props = null;
        try
        {
            props = loadProperties();
        }
        catch (Exception e)
        {
            logger.error("Can not load tag library definition", e);
        }
        for (Iterator i = props.keySet().iterator(); i.hasNext();)
        {
            String url = (String) i.next();

            try
            {
                TagLibrary library = (TagLibrary) Class.forName(
                        props.getProperty(url)).newInstance();
                registerTagLibrary(url, library);
            }
            catch (Exception e)
            {
                logger.warn("Can not register tag library [" + url + "]", e);
            }
        }
    }

    /**
     * Derived class has to implement this method to provide tag definition
     *
     * @return Tag definition properties
     * @throws Exception Throw it out
     */
    protected abstract Properties loadProperties() throws Exception;
}