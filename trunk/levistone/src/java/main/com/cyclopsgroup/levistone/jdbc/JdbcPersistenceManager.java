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
package com.cyclopsgroup.levistone.jdbc;

import com.cyclopsgroup.levistone.NamedQuery;
import com.cyclopsgroup.levistone.PersistenceException;
import com.cyclopsgroup.levistone.Session;
import com.cyclopsgroup.levistone.spi.BasePersistenceManager;

/**
 * Jdbc implemented persistence manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JdbcPersistenceManager extends BasePersistenceManager
{

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.PersistenceManager#cancelSession(com.cyclopsgroup.levistone.Session)
     */
    public void cancelSession(Session session)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.PersistenceManager#closeSession(com.cyclopsgroup.levistone.Session)
     */
    public void closeSession(Session session)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.PersistenceManager#createQuery(java.lang.String)
     */
    public NamedQuery createQuery(String name)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.PersistenceManager#getActiveSessions()
     */
    public Session[] getActiveSessions()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.PersistenceManager#openSession(java.lang.String)
     */
    public Session openSession(String persistenceName)
            throws PersistenceException
    {
        // TODO Auto-generated method stub
        return null;
    }
}