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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cyclopsgroup.levistone.NamedQuery;
import com.cyclopsgroup.levistone.Query;
import com.cyclopsgroup.levistone.QueryException;
import com.cyclopsgroup.levistone.QueryResult;
import com.cyclopsgroup.levistone.Session;
import com.cyclopsgroup.levistone.TypedSession;
import com.cyclopsgroup.levistone.base.BaseSession;

/**
 * JDBC implemented db session
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JdbcSession extends BaseSession implements Session
{

    private boolean closed = false;

    private Connection dbcon;

    private List queryResults = new ArrayList();

    /**
     * Constructor of JdbcSession
     * 
     * @param persistenceManager JDBC persistence manager
     * @param name
     * @param dbcon DB connection
     */
    JdbcSession(JdbcPersistenceManager persistenceManager, String name,
            Connection dbcon)
    {
        super(persistenceManager, name);
        this.dbcon = dbcon;
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
     * TODO Add javadoc for this method
     *
     * 
     */
    public void commit()
    {
        closeQueryResults();
        closed = true;
        try
        {
            if (dbcon.isClosed())
            {
                return;
            }
            dbcon.commit();
            dbcon.close();
        }
        catch (SQLException e)
        {
            logger.warn("Can not commit transaction", e);
        }
    }

    /**
     * Override method createTypedSession in super class of JdbcSession
     * 
     * @see com.cyclopsgroup.levistone.base.BaseSession#createTypedSession(java.lang.Class)
     */
    protected TypedSession createTypedSession(Class type)
    {
        JdbcTypedSession typedSession = new JdbcTypedSession(this, type, dbcon);
        return typedSession;
    }

    /**
     * Override method executeQuery in super class of JdbcSession
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
     * Override method executeQuery in super class of JdbcSession
     * 
     * @see com.cyclopsgroup.levistone.Session#executeQuery(com.cyclopsgroup.levistone.Query)
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
     * Override method getId in super class of JdbcSession
     * 
     * @see com.cyclopsgroup.levistone.Session#getId()
     */
    public String getId()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override method isClosed in super class of JdbcSession
     * 
     * @see com.cyclopsgroup.levistone.Session#isClosed()
     */
    public boolean isClosed()
    {
        return closed;
    }

    /**
     * TODO Add javadoc for this method
     *
     * 
     */
    public void rollback()
    {
        closeQueryResults();
        closed = true;
        try
        {
            if (dbcon.isClosed())
            {
                return;
            }
            dbcon.rollback();
            dbcon.close();
        }
        catch (SQLException e)
        {
            logger.warn("Can not roll back the transaction");
        }
    }
}