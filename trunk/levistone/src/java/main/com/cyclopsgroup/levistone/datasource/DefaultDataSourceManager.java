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
package com.cyclopsgroup.levistone.datasource;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.levistone.DataSourceManager;

/**
 * Default implementation of data source manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultDataSourceManager extends AbstractLogEnabled implements
        Serviceable, Configurable, Initializable, DataSourceManager
{

    private Properties dataSourceRoles = new Properties();

    private Hashtable dataSources = new Hashtable();

    private ServiceManager serviceManager;

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        Configuration[] defs = conf.getChildren("datasource");
        for (int i = 0; i < defs.length; i++)
        {
            Configuration def = defs[i];
            String name = def.getAttribute("name", DEFAULT_NAME);
            String role = def.getAttribute("role");
            dataSourceRoles.setProperty(name, role);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.DataSourceManager#getDataSource(java.lang.String)
     */
    public DataSource getDataSource(String name)
    {
        return (DataSource) dataSources.get(name);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        for (Iterator i = dataSourceRoles.keySet().iterator(); i.hasNext();)
        {
            String name = (String) i.next();
            String role = dataSourceRoles.getProperty(name);
            try
            {
                DataSource dataSource = (DataSource) serviceManager
                        .lookup(role);
                dataSources.put(name, dataSource);
            }
            catch (Exception e)
            {
                getLogger().error("Setup data source [" + name + "] error", e);
            }
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        this.serviceManager = serviceManager;
    }
}