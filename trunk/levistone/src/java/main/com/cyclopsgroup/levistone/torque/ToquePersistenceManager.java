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

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.levistone.DataSourceManager;
import com.cyclopsgroup.levistone.Session;
import com.cyclopsgroup.levistone.spi.AbstractPersistenceManager;

/**
 * Torque implemented persistence manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ToquePersistenceManager extends AbstractPersistenceManager implements
        Serviceable
{
    private DataSourceManager dataSourceManager;

    private Hashtable peerAdapters = new Hashtable();

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.spi.AbstractPersistenceManager#doCancelSession(com.cyclopsgroup.levistone.Session)
     */
    protected void doCancelSession(Session session) throws Exception
    {
        TorqueSession ses = (TorqueSession) session;
        ses.setClosed(true);
        Connection dbcon = ses.getConnection();
        dbcon.rollback();
        dbcon.close();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.spi.AbstractPersistenceManager#doCloseSession(com.cyclopsgroup.levistone.Session)
     */
    protected void doCloseSession(Session session) throws Exception
    {
        TorqueSession ses = (TorqueSession) session;
        ses.setClosed(true);
        Connection dbcon = ses.getConnection();
        dbcon.commit();
        dbcon.close();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.spi.AbstractPersistenceManager#doOpenSession(java.lang.String, java.lang.String)
     */
    protected Session doOpenSession(String persistenceName, String sessionId)
            throws Exception
    {
        DataSource dataSource = dataSourceManager
                .getDataSource(persistenceName);
        Connection dbcon = dataSource.getConnection();
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
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        dataSourceManager = (DataSourceManager) serviceManager
                .lookup(DataSourceManager.ROLE);
    }
}