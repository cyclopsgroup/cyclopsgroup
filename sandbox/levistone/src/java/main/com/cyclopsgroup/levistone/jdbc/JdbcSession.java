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

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cyclopsgroup.levistone.PersistenceException;
import com.cyclopsgroup.levistone.QueryException;
import com.cyclopsgroup.levistone.QueryResult;
import com.cyclopsgroup.levistone.query.NamedQuery;
import com.cyclopsgroup.levistone.query.Query;
import com.cyclopsgroup.levistone.spi.AbstractConnectionSession;

/**
 * JDBC implemented db session
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JdbcSession extends AbstractConnectionSession
{
    private boolean closed = false;

    private List queryResults = new ArrayList();

    /**
     * Constructor of JdbcSession
     * 
     * @param persistenceManager JDBC persistence manager
     * @param name
     * @param id Session id
     * @param dbcon DB connection
     */
    JdbcSession(JdbcPersistenceManager persistenceManager, String name,
            String id, Connection dbcon)
    {
        super(persistenceManager, name, id, dbcon);
    }

    private void closeQueryResults()
    {
        for (Iterator i = queryResults.iterator(); i.hasNext();)
        {
            QueryResult queryResult = (QueryResult) i.next();
            queryResult.close();
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#create(java.lang.Class)
     */
    public Object create(Class type) throws PersistenceException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#create(java.lang.Class, java.lang.Object)
     */
    public Object create(Class type, Object id) throws PersistenceException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#delete(java.lang.Class, java.lang.Object)
     */
    public void delete(Class type, Object entity) throws PersistenceException
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#deleteById(java.lang.Class, java.lang.Object)
     */
    public void deleteById(Class type, Object id) throws PersistenceException
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#evict(java.lang.Class, java.lang.Object)
     */
    public void evict(Class type, Object entity)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#evictById(java.lang.Class, java.lang.Object)
     */
    public void evictById(Class type, Object id)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override method executeQuery in super class of JdbcSession
     * 
     * @see com.cyclopsgroup.levistone.Session#executeQuery(com.cyclopsgroup.levistone.query.NamedQuery, java.util.Map)
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
     * Override method executeQuery in super class of JdbcSession
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
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.Session#getId(java.lang.Class, java.lang.Object)
     */
    public Object getId(Class type, Object entity)
    {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub

    }
}
