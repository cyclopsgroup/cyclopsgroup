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
import java.util.Map;

import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.Persistent;

import com.cyclopsgroup.levistone.NamedQuery;
import com.cyclopsgroup.levistone.PersistenceException;
import com.cyclopsgroup.levistone.PersistenceManager;
import com.cyclopsgroup.levistone.QueryException;
import com.cyclopsgroup.levistone.QueryResult;
import com.cyclopsgroup.levistone.query.Query;
import com.cyclopsgroup.levistone.spi.AbstractConnectionSession;

/**
 * Torque implemented session
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class TorqueSession extends AbstractConnectionSession
{

    /**
     * If this session is closed
     */
    private boolean closed = false;

    /**
     * Constructor for class TorqueSession
     *
     * @param persistenceManager
     * @param name
     * @param id Id of session
     * @param dbcon
     */
    public TorqueSession(PersistenceManager persistenceManager, String name,
            String id, Connection dbcon)
    {
        super(persistenceManager, name, id, dbcon);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#create(java.lang.Class)
     */
    public Object create(Class type) throws PersistenceException
    {
        try
        {
            return type.newInstance();
        }
        catch (Exception e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#create(java.lang.Class, java.lang.Object)
     */
    public Object create(Class type, Object id) throws PersistenceException
    {
        try
        {
            Persistent entity = (Persistent) create(type);
            ObjectKey key = TorqueUtils.object2TorqueKey(id);
            entity.setPrimaryKey(key);
            return entity;
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

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#delete(java.lang.Class, java.lang.Object)
     */
    public void delete(Class type, Object entity) throws PersistenceException
    {
        try
        {
            getPeerAdapter(type).doDelete(entity, getConnection());
        }
        catch (Exception e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#deleteById(java.lang.Class, java.lang.Object)
     */
    public void deleteById(Class type, Object id) throws PersistenceException
    {
        Object entity = lookup(type, id);
        if (entity != null)
        {
            delete(type, entity);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#evict(java.lang.Class, java.lang.Object)
     */
    public void evict(Class type, Object entity)
    {
        //do nothing
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#evictById(java.lang.Class, java.lang.Object)
     */
    public void evictById(Class type, Object id)
    {
        //do nothing
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#executeQuery(com.cyclopsgroup.levistone.NamedQuery, java.util.Map)
     */
    public QueryResult executeQuery(NamedQuery query, Map attributes)
            throws QueryException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#executeQuery(com.cyclopsgroup.levistone.query.Query)
     */
    public QueryResult executeQuery(Query query) throws QueryException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#executeQuery(java.lang.String)
     */
    public QueryResult executeQuery(String sqlQuery) throws QueryException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#exists(java.lang.Class, java.lang.Object)
     */
    public boolean exists(Class type, Object id) throws PersistenceException
    {
        Object entity = lookup(type, id);
        return entity != null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#getId(java.lang.Class, java.lang.Object)
     */
    public Object getId(Class type, Object entity)
    {
        Persistent p = (Persistent) entity;
        if (p.isNew())
        {
            return null;
        }
        ObjectKey ok = p.getPrimaryKey();
        return TorqueUtils.torqueKey2Object(ok);
    }

    private TorquePeerAdapter getPeerAdapter(Class type) throws Exception
    {
        TorquePersistenceManager tpm = (TorquePersistenceManager) getPersistenceManager();
        return tpm.getPeerAdapter(type);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#isClosed()
     */
    public boolean isClosed()
    {
        return closed;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#lookup(java.lang.Class, java.lang.Object)
     */
    public Object lookup(Class type, Object id) throws PersistenceException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#save(java.lang.Class, java.lang.Object)
     */
    public void save(Class type, Object entity) throws PersistenceException
    {
        try
        {
            Persistent p = (Persistent) entity;
            p.save(getConnection());
        }
        catch (Exception e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
     * Setter method for closed
     * 
     * @param closed The closed to set.
     * 
     * @uml.property name="closed"
     */
    void setClosed(boolean closed)
    {
        this.closed = closed;
    }

}