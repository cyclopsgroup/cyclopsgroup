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

import java.util.Map;

import com.cyclopsgroup.levistone.query.Query;

/**
 * Persistence session facade
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */

public interface Session
{

    /** Default session name */
    String DEFAULT_NAME = "default";

    /**
     * Empty array
     * 
     * @uml.property name="eMPTY_ARRAY"
     * @uml.associationEnd javaType="Session[]" multiplicity="(0 -1)"
     */
    Session[] EMPTY_ARRAY = new Session[0];

    /**
     * Close session
     * @throws PersistenceException throw it out
     */
    void close() throws PersistenceException;

    /**
     * Commit this session
     *
     * @throws PersistenceException Throw it out
     */
    void commit() throws PersistenceException;

    /**
     * Create new entity object
     * @param type Type of entity
     *
     * @return New created entity object
     * @throws PersistenceException Persistence related exception
     */
    Object create(Class type) throws PersistenceException;

    /**
     * Create new entity with given id
     *
     * @param id Given id
     * @param type Type of entity
     * @return New created entity object
     * @throws PersistenceException Persistence related exception
     */
    Object create(Class type, Object id) throws PersistenceException;

    /**
     * Delete entity from persistence manager
     *
     * @param entity Entity object
     * @param type Type of entity
     * @throws PersistenceException Persistence related exception
     */
    void delete(Class type, Object entity) throws PersistenceException;

    /**
     * Delete entity with given id
     *
     * @param id ID of entity
     * @param type Type of entity
     * @throws PersistenceException Persistence related exception
     */
    void deleteById(Class type, Object id) throws PersistenceException;

    /**
     * Release an entity from cache
     *
     * @param type Type of entity
     * @param entity Entity object
     */
    void evict(Class type, Object entity);

    /**
     * Release an entity from cache
     *
     * @param type Type of entity
     * @param id ID of entity
     */
    void evictById(Class type, Object id);

    /**
     * Execute named query with given attributes
     * 
     * @param query Named query object
     * @param attributes Attribute values
     * @return Query result
     * @throws QueryException
     */
    QueryResult executeQuery(NamedQuery query, Map attributes)
            throws QueryException;

    /**
     * Execute given query object
     * 
     * @param query
     * @return
     * @throws QueryException
     */
    QueryResult executeQuery(Query query) throws QueryException;

    /**
     * Execute sql string query
     * 
     * @param sqlQuery Sql string
     * @return Query result
     * @throws QueryException
     */
    QueryResult executeQuery(String sqlQuery) throws QueryException;

    /**
     * Check out if an entity exists
     *
     * @param type Type of entity
     * @param id Id of entity
     * @return If such an entity exists
     * @throws PersistenceException Persistence related exception
     */
    boolean exists(Class type, Object id) throws PersistenceException;

    /**
     * Return session unique id
     * 
     * @return Session id
     */
    String getId();

    /**
     * Get primary key of given entity object
     *
     * @param type Type of entity
     * @param entity Entity object
     * @return Primary key
     */
    Object getId(Class type, Object entity);

    /**
     * Get name of the persistence definition
     * 
     * @return Name of pm
     */
    String getName();

    /**
     * Get persistence manager hosting it
     * 
     * @return PersistenceManager object
     */
    PersistenceManager getPersistenceManager();

    /**
     * If session is closed
     * 
     * @return If session is closed
     */
    boolean isClosed();

    /**
     * Lookup object with given id
     *
     * @param type Type of entity
     * @param id Id of object
     * @return Entity object or null if not found
     * @throws PersistenceException Persistence related exception
     */
    Object lookup(Class type, Object id) throws PersistenceException;

    /**
     * Execute query and find out the first entity object
     * 
     * @param query Query object
     * @return Entity object or null if not found
     * @throws QueryException
     */
    Object lookup(Query query) throws QueryException;

    /**
     * Roll back this session
     * 
     * @throws PersistenceException Throw it out
     */
    void rollback() throws PersistenceException;

    /**
     * Save created or changed object into persistence
     *
     * @param type Type of entity
     * @param entity Entity object
     * @throws PersistenceException Persistence related exception
     */
    void save(Class type, Object entity) throws PersistenceException;
}