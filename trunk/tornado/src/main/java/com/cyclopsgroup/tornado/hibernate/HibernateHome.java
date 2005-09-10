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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cyclopsgroup.tornado.sql.DataSourceManager;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Hibernate facade
 */
public interface HibernateHome
{
    /** Default data source name */
    String DEFAULT_DATASOURCE = DataSourceManager.DEFAULT_DATA_SOURCE;

    /** Role name of this component */
    String ROLE = HibernateHome.class.getName();

    /**
     * Close default session
     */
    void closeSession();

    /**
     * Close given session
     *
     * @param dataSourceName Data source name
     */
    void closeSession(String dataSourceName);

    /**
     * Close sessions attached to current thread
     */
    void closeSessions();

    /**
     * Commit default transaction
     *
     * @throws Exception Throw it out
     */
    void commitTransaction() throws Exception;

    /**
     * Close given transaction
     *
     * @param dataSourceName Data source name
     * @throws Exception Throw it out
     */
    void commitTransaction(String dataSourceName) throws Exception;

    /**
     * Commit all transactions
     *
     * @throws Exception Throw it out
     */
    void commitTransactions() throws Exception;

    /**
     * Get all entity classes
     *
     * @param dataSourceName Data source name
     * @return Entity classes
     */
    Class[] getEntityClasses(String dataSourceName);

    /**
     * Get hibernate configuration
     * 
     * @param dataSourceName Data source name
     * @return Configuraton object
     */
    Configuration getHibernateConfiguration(String dataSourceName);

    /**
     * Get current session with transaction support
     *
     * @return Session object
     * @throws Exception Throw it out if can not open session
     */
    Session getSession() throws Exception;

    /**
     * Get hibernate session attached to current thread
     *
     * @param withTransaction With transaction beginned or not
     * @return Session object. It's always returned
     * @throws Exception Throw it out
     */
    Session getSession(boolean withTransaction) throws Exception;

    /**
     * Get given session
     *
     * @param dataSourceName Data source name
     * @return Hibernate session object
     * @throws Exception Throw it out
     */
    Session getSession(String dataSourceName) throws Exception;

    /**
     * @param dataSourceName Data source name
     * @return SQL Connection
     * @throws Exception Throw it out
     */
    Connection getConnection(String dataSourceName) throws Exception;

    /**
     * Get session with transaction or not
     *
     * @param dataSourceName Data source name
     * @param withTransaction With transaction or not
     * @return Hibernate session object
     * @throws Exception Throw it out
     */
    Session getSession(String dataSourceName, boolean withTransaction)
            throws Exception;

    /**
     * Get session factory
     *
     * @param dataSourceName Data source name
     * @return Hibernate session factory
     * @throws NoSuchHibernateConfiguredException Throw it out
     */
    SessionFactory getSessionFactory(String dataSourceName)
            throws NoSuchHibernateConfiguredException;

    /**
     * Roll back current transaction
     *
     * @throws Exception Throw it out
     */
    void rollbackTransaction() throws Exception;

    /**
     * Roll back given transaction
     *
     * @param dataSourceName Data source name
     * @throws Exception Throw it out
     */
    void rollbackTransaction(String dataSourceName) throws Exception;

    /**
     * Rollback all transactions
     *
     * @throws Exception Throw itout
     */
    void rollbackTransactions() throws Exception;
}
