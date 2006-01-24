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
package com.cyclopsgroup.tornado.sql;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp.BasicDataSource;

import com.cyclopsgroup.tornado.utils.ConfigurationUtils;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * DBCP implemented data source factory
 */
public class DBCPDataSourceService
    extends AbstractLogEnabled
    implements DataSourceService, Configurable, Initializable
{
    private DataSource dataSource;

    private Properties properties;

    /**
     * Override method DBCPDataSourceFactory in supper class
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration conf )
        throws ConfigurationException
    {
        properties = ConfigurationUtils.getProperties( conf );
    }

    /**
     * Overwrite or implement method getDataSource()
     *
     * @see com.cyclopsgroup.tornado.sql.DataSourceService#getDataSource()
     */
    public DataSource getDataSource()
    {
        return dataSource;
    }

    /**
     * Override method DBCPDataSourceFactory in supper class
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        BasicDataSource bds = new BasicDataSource();
        BeanUtils.populate( bds, properties );
        dataSource = bds;
    }
}
