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
package com.cyclopsgroup.levistone.spi;

import java.sql.Connection;
import java.sql.SQLException;

import com.cyclopsgroup.levistone.PersistenceException;
import com.cyclopsgroup.levistone.PersistenceManager;

/**
 * Base persistence session with connection
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class AbstractConnectionSession extends AbstractSession
{
    private Connection dbcon;

    /**
     * Constructor for class BaseConnectionSession
     *
     * @param persistenceManager
     * @param name
     * @param id Id of this session
     * @param dbcon Database connection
     */
    public AbstractConnectionSession(PersistenceManager persistenceManager,
            String name, String id, Connection dbcon)
    {
        super(persistenceManager, name, id);
        this.dbcon = dbcon;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#commit()
     */
    public void commit() throws PersistenceException
    {
        try
        {
            getConnection().commit();
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.spi.AbstractSession#doClose()
     */
    protected void doClose() throws Exception
    {
        if (!getConnection().isClosed())
        {
            getConnection().close();
        }
    }

    /**
     * Get associated db connection
     *
     * @return DBConnection object
     */
    public Connection getConnection()
    {
        return dbcon;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#rollback()
     */
    public void rollback() throws PersistenceException
    {
        try
        {
            getConnection().rollback();
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
    }
}