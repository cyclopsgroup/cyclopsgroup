/* ==========================================================================
 * Copyright 2002-2006 Cyclops Group Software Foundation
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

import java.util.Set;

import com.cyclopsgroup.tornado.sql.DataSourceManager;

/**
 * Hibernate facade
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public interface HibernateManager
{
    public static final String DEFAULT_HIBERNATE = DataSourceManager.DEFAULT_DATA_SOURCE;

    /**
     * Role name of the component
     */
    String ROLE = HibernateManager.class.getName();

    public void closeSessions()
        throws Exception;

    public void commitTransaction()
        throws Exception;

    public HibernateService getDefaultHibernateService()
        throws NoSuchHibernateConfiguredException;

    /**
     * Get hibernate service with given name
     *
     * @param name Hibernate name
     * @return HibernateService instance
     * @throws NoSuchHibernateConfiguredException
     */
    public HibernateService getHibernateService( String name )
        throws NoSuchHibernateConfiguredException;

    /**
     * Get name of hibernate services
     *
     * @return Array of string names
     */
    public Set<String> getServiceNames();

    public void rollbackTransaction()
        throws Exception;
}
