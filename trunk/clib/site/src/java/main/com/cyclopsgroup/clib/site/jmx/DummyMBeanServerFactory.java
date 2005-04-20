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
package com.cyclopsgroup.clib.site.jmx;

import javax.management.MBeanServer;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.lang.StringUtils;

/**
 * Dummy implementation of mbean server provider
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DummyMBeanServerFactory extends AbstractLogEnabled implements
        MBeanServerFactory, Initializable, Configurable
{
    private String domain;

    private MBeanServer mbeanServer;

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        domain = conf.getChild("domain").getValue(null);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.jmx.MBeanServerFactory#getMBeanServer()
     */
    public MBeanServer getMBeanServer()
    {
        return mbeanServer;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        if (StringUtils.isEmpty(domain))
        {
            mbeanServer = javax.management.MBeanServerFactory
                    .createMBeanServer(domain);
        }
        else
        {
            mbeanServer = javax.management.MBeanServerFactory
                    .createMBeanServer(domain);
        }
    }
}
