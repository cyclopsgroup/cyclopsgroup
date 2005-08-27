/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.tornado.components.hibernate;

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
    public void enableTransaction(String dataSourceName)
    {
        if (transactions.containsKey(dataSourceName))
        {
            return;
        }
        if (!sessions.containsKey(dataSourceName))
        {
            return;
        }
        Session session = getSession(dataSourceName);
        Transaction transaction = session.beginTransaction();
        transactions.put(dataSourceName, transaction);
    }

    /**
     * Get connection
     *
     * @param dataSourceName Data source name
     * @return Connection object
     */
    Connection getConnection(String dataSourceName)
    {
        return (Connection) connections.get(dataSourceName);
    }

    String[] getDataSourceNames()
    {
        return (String[]) sessions.keySet().toArray(
                ArrayUtils.EMPTY_STRING_ARRAY);
    }

    Session getSession(String dataSourceName)
    {
        return (Session) sessions.get(dataSourceName);
    }

    Transaction getTransaction(String dataSourceName)
    {
        return (Transaction) transactions.get(dataSourceName);
    }

    void remove(String dataSourceName)
    {
        sessions.remove(dataSourceName);
        connections.remove(dataSourceName);
        transactions.remove(dataSourceName);
    }

    void setConnection(String dataSourceName, Connection dbcon)
    {
        connections.put(dataSourceName, dbcon);
    }

    void setSession(String dataSourceName, Session session)
    {
        sessions.put(dataSourceName, session);
    }
}
