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

import java.util.Hashtable;
import java.util.Map;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.collections.map.ListOrderedMap;

import com.cyclopsgroup.levistone.NamedQuery;
import com.cyclopsgroup.levistone.PersistenceException;
import com.cyclopsgroup.levistone.PersistenceManager;
import com.cyclopsgroup.levistone.Session;
import com.cyclopsgroup.levistone.query.Query;

/**
 * Base implementation of persistence manager
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class AbstractPersistenceManager extends AbstractLogEnabled
        implements PersistenceManager
{
    private Map activeSessions = ListOrderedMap.decorate(new Hashtable());

    private long uniqueId = 0L;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.PersistenceManager#closeSession(com.cyclopsgroup.levistone.Session)
     */
    public void closeSession(Session session) throws PersistenceException
    {
        session.close();
        activeSessions.remove(session.getId());
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.PersistenceManager#createQuery(java.lang.Class)
     */
    public Query createQuery(Class entityType)
    {
        return new Query(entityType);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.PersistenceManager#createQuery(java.lang.String)
     */
    public NamedQuery createQuery(String name)
    {
        return null;
    }

    /**
     * Method doOpenSession() in class BasePersistenceManager
     *
     * @param persistenceName
     * @param sessionId       session id
     * @return
     * @throws Exception
     */
    protected abstract Session doOpenSession(String persistenceName,
            String sessionId) throws Exception;

    /**
     * Override method getActiveSessions in super class of BasePersistenceManager
     *
     * @see com.cyclopsgroup.levistone.PersistenceManager#getActiveSessions()
     */
    public Session[] getActiveSessions()
    {
        return (Session[]) activeSessions.values().toArray(Session.EMPTY_ARRAY);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.PersistenceManager#openSession()
     */
    public Session openSession() throws PersistenceException
    {
        return openSession(Session.DEFAULT_NAME);
    }

    /**
     * Override method openSession in super class of BasePersistenceManager
     *
     * @see com.cyclopsgroup.levistone.PersistenceManager#openSession(java.lang.String)
     */
    public synchronized Session openSession(String persistenceName)
            throws PersistenceException
    {
        try
        {
            String id = String.valueOf(uniqueId++);

            Session session = doOpenSession(persistenceName, id);
            activeSessions.put(session.getId(), session);
            return session;
        }
        catch (PersistenceException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new PersistenceException(e);
        }
    }
}