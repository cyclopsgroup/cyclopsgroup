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
package com.cyclopsgroup.lloyd;

import javax.sql.DataSource;

/**
 * Facade persistence manager interface
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface PersistenceManager
{

    /** Default respository name */
    String DEFAULT_REPOSITORY = "default";

    /** Role in container */
    String ROLE = PersistenceManager.class.getName();

    /**
     * Create new query object
     * 
     * @param objectClass Class of queried object
     * @return New query object
     */
    Query createQuery(Class objectClass);

    /**
     * Get named query object
     * 
     * @param objectClass Object class
     * @param queryName Unique name of query
     * @return Query object
     */
    Query createQuery(Class objectClass, String queryName);

    /**
     * Method getDataSource() in class PersistenceManager
     * 
     * @param repoName
     * @return
     */
    DataSource getDataSource(String repoName);

    /**
     * Method startSession() in class PersistenceManager
     * 
     * @return A new persistence session
     */
    PersistenceSession startSession();

    /**
     * Method startSession() in class PersistenceManager
     * 
     * @param repoName Given respository name
     * @return A new session
     */
    PersistenceSession startSession(String repoName);
}