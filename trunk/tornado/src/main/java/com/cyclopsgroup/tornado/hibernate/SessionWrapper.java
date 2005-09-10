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
package com.cyclopsgroup.tornado.hibernate;

import java.sql.Connection;
import java.util.HashMap;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Wrapper for sessions
 */
class SessionWrapper
{
    private HashMap connections = new HashMap();

    private HashMap sessions = new HashMap();

    private HashMap transactions = new HashMap();

    /**
     * Enable transaction
     *
     * @param dataSourceName Data source name
     */
    public void enableTransaction( String dataSourceName )
    {
        if ( transactions.containsKey( dataSourceName ) )
        {
            return;
        }
        if ( !sessions.containsKey( dataSourceName ) )
        {
            return;
        }
        Session session = getSession( dataSourceName );
        Transaction transaction = session.beginTransaction();
        transactions.put( dataSourceName, transaction );
    }

    /**
     * Get connection
     *
     * @param dataSourceName Data source name
     * @return Connection object
     */
    Connection getConnection( String dataSourceName )
    {
        return (Connection) connections.get( dataSourceName );
    }

    String[] getDataSourceNames()
    {
        return (String[]) sessions.keySet().toArray( ArrayUtils.EMPTY_STRING_ARRAY );
    }

    Session getSession( String dataSourceName )
    {
        return (Session) sessions.get( dataSourceName );
    }

    Transaction getTransaction( String dataSourceName )
    {
        return (Transaction) transactions.get( dataSourceName );
    }

    void remove( String dataSourceName )
    {
        sessions.remove( dataSourceName );
        connections.remove( dataSourceName );
        transactions.remove( dataSourceName );
    }

    void setConnection( String dataSourceName, Connection dbcon )
    {
        connections.put( dataSourceName, dbcon );
    }

    void setSession( String dataSourceName, Session session )
    {
        sessions.put( dataSourceName, session );
    }
}
