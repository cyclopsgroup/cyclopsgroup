/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 *
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.tornado.persist;

import java.io.Serializable;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Generic persistence layer API
 */
public interface PersistenceManager
{
    /** Role of persistence manager */
    String ROLE = PersistenceManager.class.getName();

    /**
     * Create new entity instance
     *
     * @param type Type of entity
     * @return Entity instance
     * @throws Exception Throw it out
     */
    Object create( Class type )
        throws Exception;

    /**
     * Create new entity instance of given ID
     *
     * @param type Type of entity
     * @param id Given id
     * @return New object
     * @throws Exception Throw it out
     */
    Object create( Class type, Serializable id )
        throws Exception;

    /**
     * Delete the given entity
     *
     * @param entity Given entity
     * @throws Exception Throw it out
     */
    void delete( Object entity )
        throws Exception;

    /**
     * Delete object by id
     *
     * @param type Entity type
     * @param id Id of entity
     * @throws Exception THrow it out
     */
    void delete( Class type, Serializable id )
        throws Exception;

    /**
     * Find object by id
     *
     * @param id Id of object
     * @param type Entity type
     * @return Entity instance or null if not found
     * @throws Exception Throw it out
     */
    Object find( Class type, Serializable id )
        throws Exception;

    /**
     * Load object by id
     *
     * @param type Entity type
     * @param id Entity id
     * @return Entity instance
     * @throws Exception Thrown if no record is found
     * @throws Exception Throw it out
     */
    Object load( Class type, Serializable id )
        throws Exception;

    /**
     * Save entity as new or old
     *
     * @param entity Entity to save
     * @throws Exception Throw it out
     */
    void save( Object entity )
        throws Exception;

    /**
     * Save entity as new
     *
     * @param entity Entity to save
     * @throws Exception Throw it out
     */
    void saveNew( Object entity )
        throws Exception;

    /**
     * Save object as old
     *
     * @param entity Entity to save
     * @throws Exception throw it out
     */
    void update( Object entity )
        throws Exception;
}
