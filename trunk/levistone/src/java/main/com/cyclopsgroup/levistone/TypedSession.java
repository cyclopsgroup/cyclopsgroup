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
 * Session for a certain type object entity
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface TypedSession
{
    /**
     * Create new entity object
     *
     * @return New created entity object
     * @throws PersistenceException Persistence related exception
     */
    Object create() throws PersistenceException;

    /**
     * Create new entity with given id
     *
     * @param id Given id
     * @return New created entity object
     * @throws PersistenceException Persistence related exception
     */
    Object create(Object id) throws PersistenceException;

    /**
     * Delete entity from persistence manager
     *
     * @param entity Entity object
     * @throws PersistenceException Persistence related exception
     */
    void delete(Object entity) throws PersistenceException;

    /**
     * Delete entity with given id
     *
     * @param id ID of entity
     * @throws PersistenceException Persistence related exception
     */
    void deleteById(Object id) throws PersistenceException;

    /**
     * Release an entity from cache
     *
     * @param entity Entity object
     */
    void evict(Object entity);

    /**
     * Release an entity from cache
     *
     * @param id ID of entity
     */
    void evictById(Object id);

    /**
     * Check out if an entity exists
     *
     * @param id Id of entity
     * @return If such an entity exists
     * @throws PersistenceException Persistence related exception
     */
    boolean exists(Object id) throws PersistenceException;

    /**
     * Get primary key of given entity object
     *
     * @param entity Entity object
     * @return Primary key
     */
    Object getId(Object entity);

    /**
     * Get current persistence manager
     *
     * @return PersistenceManager object
     */
    PersistenceManager getPersistenceManager();

    /**
     * Get current session
     *
     * @return Current session
     */
    Session getSession();

    /**
     * Get entity type
     *
     * @return Entity type
     */
    Class getType();

    /**
     * Lookup object with given id
     *
     * @param id Id of object
     * @return Entity object or null if not found
     * @throws PersistenceException Persistence related exception
     */
    Object lookup(Object id) throws PersistenceException;

    /**
     * Save created or changed object into persistence
     *
     * @param entity Entity object
     * @throws PersistenceException Persistence related exception
     */
    void save(Object entity) throws PersistenceException;
}