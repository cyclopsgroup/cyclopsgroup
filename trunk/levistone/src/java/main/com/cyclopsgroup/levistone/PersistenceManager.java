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
package com.cyclopsgroup.levistone;

/**
 * Facade component interface
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface PersistenceManager
{
    /** Role name in container */
    String ROLE = PersistenceManager.class.getName();

    /**
     * Create empty query with given type
     * 
     * @param entityType Type of entity class
     * @return New query object
     */
    Query createQuery(Class entityType);

    /**
     * Create named query with given name
     * 
     * @param name Queyr name
     * @return Named query object
     */
    NamedQuery createQuery(String name);

    /**
     * Get current active sessions
     * 
     * @return Active session array
     */
    Session[] getActiveSessions();

    /**
     * Start new persistence session
     * 
     * @return New session instance
     * @throws PersistenceException Persistence exception
     */
    Session openSession() throws PersistenceException;

    /**
     * Open new session with given persistence name
     * 
     * @param persistenceName
     * @return
     * @throws PersistenceException
     */
    Session openSession(String persistenceName) throws PersistenceException;
}