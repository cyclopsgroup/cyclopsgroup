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

import com.cyclopsgroup.tornado.sql.DataSourceService;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Hibernate facade
 */
public interface HibernateService
{
    /**
     * Close default session
     */
    void closeSession();

    /**
     * Commit default transaction
     *
     * @throws Exception Throw it out
     */
    void commitTransaction()
        throws Exception;

    /**
     * @param dataSourceName Data source name
     * @return SQL Connection
     * @throws Exception Throw it out
     */
    Connection getConnection()
        throws Exception;

    DataSourceService getDataSourceService();

    /**
     * Get all entity classes
     *
     * @param dataSourceName Data source name
     * @return Entity classes
     */
    Class[] getEntityClasses();

    /**
     * Get hibernate configuration
     * 
     * @param dataSourceName Data source name
     * @return Configuraton object
     */
    Configuration getHibernateConfiguration();

    String getName();

    /**
     * Get current session with transaction support
     *
     * @return Session object
     * @throws Exception Throw it out if can not open session
     */
    Session getSession()
        throws Exception;

    /**
     * Get hibernate session attached to current thread
     *
     * @param withTransaction With transaction beginned or not
     * @return Session object. It's always returned
     * @throws Exception Throw it out
     */
    Session getSession( boolean withTransaction )
        throws Exception;

    /**
     * Get session factory
     *
     * @param dataSourceName Data source name
     * @return Hibernate session factory
     * @throws NoSuchHibernateConfiguredException Throw it out
     */
    SessionFactory getSessionFactory()
        throws NoSuchHibernateConfiguredException;

    /**
     * Roll back current transaction
     *
     * @throws Exception Throw it out
     */
    void rollbackTransaction()
        throws Exception;
}
