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
package com.cyclopsgroup.gearset.jelly;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.jelly.TagLibrary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyclopsgroup.gearset.bean.LogEnabled;

/**
 * Jelly tag library which find tag definitions from a given properties file
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public abstract class PropertyTagLibrary extends TagLibrary implements
        LogEnabled
{

    private Log logger;

    /**
     * Constructor of PropertyTagLibrary Tag classes are registered here
     */
    public PropertyTagLibrary()
    {
        try
        {
            Properties tagDefinition = getLibraryProperties();
            for (Iterator i = tagDefinition.keySet().iterator(); i.hasNext();)
            {
                String tagName = (String) i.next();
                try
                {
                    Class tagClass = Thread.currentThread()
                            .getContextClassLoader().loadClass(
                                    tagDefinition.getProperty(tagName));
                    registerTag(tagName, tagClass);
                }
                catch (Exception e)
                {
                    getLogger().error(
                            "Can't load tag class "
                                    + tagDefinition.getProperty(tagName)
                                    + " for tag " + tagName, e);
                }
            }
        }
        catch (Exception e)
        {
            getLogger().fatal("Can't initialize tag library", e);
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
    protected abstract URL getLibraryResource();

    /**
     * Override method getLogger in super class of PropertyTagLibrary
     * 
     * @see com.cyclopsgroup.gearset.bean.LogEnabled#getLogger()
     */
    public Log getLogger()
    {
        synchronized (this)
        {
            if (logger == null)
            {
                logger = LogFactory.getLog(getClass());
            }
        }
        return logger;
    }
}