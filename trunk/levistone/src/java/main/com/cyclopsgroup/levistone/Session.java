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
     * Return session unique id
     *
     * @return Session id
     */
    String getId();

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
     * Execute query and find out the first entity object
     *
     * @param query Query object
     * @return Entity object or null if not found
     * @throws QueryException
     */
    Object lookup(Query query) throws QueryException;

    /**
     * Get type session with given entity type
     *
     * @param entityType Class of entity
     * @return Typed session object
     */
    TypedSession type(Class entityType);
}