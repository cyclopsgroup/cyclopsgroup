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
package com.cyclopsgroup.levistone.torque;

import java.sql.Connection;
import java.util.Hashtable;

import javax.sql.DataSource;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.torque.Torque;
import org.apache.torque.TorqueException;

import com.cyclopsgroup.levistone.DataSourceManager;
import com.cyclopsgroup.levistone.Session;
import com.cyclopsgroup.levistone.spi.AbstractPersistenceManager;

/**
 * Torque implemented persistence manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class TorquePersistenceManager extends AbstractPersistenceManager
        implements Serviceable, Configurable, Initializable
{

    /**
     * 
     * @uml.property name="dataSourceManager" 
     */
    private DataSourceManager dataSourceManager;

    private Hashtable peerAdapters = new Hashtable();

    private String torqueProperties;

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        torqueProperties = conf.getChild("properties").getValue();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.spi.AbstractPersistenceManager#doOpenSession(java.lang.String, java.lang.String)
     */
    protected Session doOpenSession(String persistenceName, String sessionId)
            throws Exception
    {
        Connection dbcon = null;
        if (dataSourceManager != null)
        {
            DataSource dataSource = dataSourceManager
                    .getDataSource(persistenceName);
            dbcon = dataSource.getConnection();
        }
        else
        {
            dbcon = Torque.getConnection(persistenceName);
        }
        TorqueSession session = new TorqueSession(this, persistenceName,
                sessionId, dbcon);
        return session;
    }

    /**
     * Get peer adapter associated with a entity class
     *
     * @param type Entity class
     * @return Peer adapter
     * @throws Exception Throw it out if type mismatch
     */
    public synchronized TorquePeerAdapter getPeerAdapter(Class type)
            throws Exception
    {
        if (peerAdapters.containsKey(type))
        {
            return (TorquePeerAdapter) peerAdapters.get(type);
        }
        Class peerType = Class.forName(type.getName() + "Peer");
        TorquePeerAdapter peerAdapter = new TorquePeerAdapter(type, peerType);
        peerAdapters.put(type, peerAdapter);
        return peerAdapter;
    }

    /**
     * Init torque component
     *
     * @param props Torque property file
     * @throws TorqueException Throw it out
     */
    public void init(String props) throws TorqueException
    {
        if (!Torque.isInit())
        {
            Torque.init(props);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        init(torqueProperties);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        setDataSourceManager((DataSourceManager) serviceManager
                .lookup(DataSourceManager.ROLE));
    }

    /**
     * Set data source manager
     * 
     * @param dataSourceManager Data source manager
     * 
     * @uml.property name="dataSourceManager"
     */
    void setDataSourceManager(DataSourceManager dataSourceManager)
    {
        this.dataSourceManager = dataSourceManager;
    }

}