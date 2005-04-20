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
package com.cyclopsgroup.clib.site.db.dbcp;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp.BasicDataSource;

import com.cyclopsgroup.clib.site.db.DataSourceFactory;

/**
 * Data source provider implemented by DBCP
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DBCPDataSourceFactory implements DataSourceFactory,
        Configurable, Initializable
{

    private BasicDataSource dataSource = new BasicDataSource();

    private Properties properties = new Properties();

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        Configuration[] confs = conf.getChildren("property");
        for (int i = 0; i < confs.length; i++)
        {
            Configuration prop = confs[i];
            String name = prop.getAttribute("name");
            String value = prop.getValue();
            properties.setProperty(name, value);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.db.DataSourceFactory#getDataSource()
     */
    public DataSource getDataSource()
    {
        return dataSource;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        BeanUtils.populate(dataSource, properties);
    }

    /**
     * Directly set a property value
     *
     * @param propertyName Property name
     * @param propertyValue Property value
     * @throws Exception Throw it out
     */
    public void setProperty(String propertyName, String propertyValue)
            throws Exception
    {
        BeanUtils.setProperty(dataSource, propertyName, propertyValue);
    }
}
