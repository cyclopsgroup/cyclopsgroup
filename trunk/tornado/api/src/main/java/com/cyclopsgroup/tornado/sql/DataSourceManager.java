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

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Manager of data sources
 */
public class DataSourceManager
    extends AbstractLogEnabled
    implements Configurable, Initializable, Serviceable
{
    /** Default name of data source */
    public static final String DEFAULT_DATA_SOURCE = "default";

    /** Role name of service */
    public static final String ROLE = DataSourceManager.class.getName();

    private Map dataSourceServices;

    private Map dataSourceRoles;

    private ServiceManager serviceManager;

    /**
     * Override method DataSourceManager in supper class
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration conf )
        throws ConfigurationException
    {
        dataSourceRoles = new HashMap();
        Configuration[] confs = conf.getChildren( "data-source" );
        for ( int i = 0; i < confs.length; i++ )
        {
            Configuration dsconf = confs[i];
            String name = dsconf.getAttribute( "name" );
            String role = dsconf.getAttribute( "role" );
            dataSourceRoles.put( name, role );
        }
    }

    /**
     * Get default data source
     *
     * @return Data source instance
     * @throws NoSuchDataSourceException If default data source is not defined
     */
    public DataSource getDataSource()
        throws NoSuchDataSourceException
    {
        return getDataSource( DEFAULT_DATA_SOURCE );
    }

    /**
     * Get data source
     *
     * @param name Data source name
     * @return Data source instance or null
     * @throws NoSuchDataSourceException If data source is not defined
     */
    public DataSource getDataSource( String name )
        throws NoSuchDataSourceException
    {
        return getDataSourceHome( name ).getDataSource();
    }

    /**
     * Get data source factory
     *
     * @param name Data source name
     * @return Data source factory instance or null
     */
    public DataSourceService getDataSourceHome( String name )
        throws NoSuchDataSourceException
    {
        DataSourceService service = (DataSourceService) dataSourceServices.get( name );
        if ( service == null )
        {
            throw new NoSuchDataSourceException( name );
        }
        return service;
    }

    /**
     * Override method DataSourceManager in supper class
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        dataSourceServices = new Hashtable();
        for ( Iterator i = dataSourceRoles.keySet().iterator(); i.hasNext(); )
        {
            String name = (String) i.next();
            String role = (String) dataSourceRoles.get( name );
            DataSourceService dsf = (DataSourceService) serviceManager.lookup( role );
            dataSourceServices.put( name, dsf );
        }
    }

    /**
     * Override method DataSourceManager in supper class
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        this.serviceManager = serviceManager;
    }
}
