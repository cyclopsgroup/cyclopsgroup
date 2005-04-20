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
package com.cyclopsgroup.clib.site.velocity;

import java.util.Properties;

import org.apache.avalon.framework.activity.Disposable;
import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;

/**
 * Default velocity provider implementation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultVelocityFactory extends AbstractLogEnabled implements
        Configurable, Initializable, VelocityFactory, Disposable
{

    private Properties properties = new Properties();

    private VelocityEngine velocityEngine;

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        Configuration[] props = conf.getChild("properties").getChildren(
                "property");
        for (int i = 0; i < props.length; i++)
        {
            Configuration prop = props[i];
            String propName = prop.getAttribute("name");
            String value = prop.getValue(StringUtils.EMPTY);
            setProperty(propName, value);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Disposable#dispose()
     */
    public void dispose()
    {
        velocityEngine = null;
    }

    /**
     * Get all properties
     *
     * @return Properties
     */
    public Properties getProperties()
    {
        return properties;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.velocity.VelocityFactory#getVelocityEngine()
     */
    public VelocityEngine getVelocityEngine()
    {
        return velocityEngine;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("basevelocity.properties"));
        props.putAll(properties);

        velocityEngine = new VelocityEngine();
        velocityEngine.init(props);
    }

    /**
     * Explicitely set a property
     *
     * @param propertyName Property name
     * @param propertyValue Property value
     */
    public void setProperty(String propertyName, String propertyValue)
    {
        properties.setProperty(propertyName, propertyValue);
    }
}
