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

import java.util.Map;

/**
 * Session of persistence operations
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface PersistenceSession
{
    /**
     * Commit current session
     */
    void commit();

    /**
     * Create object with auto generated key
     * 
     * @param objectClass Object class
     * @return Created object
     */
    Object create(Class objectClass);

    /**
     * Create new object with given key
     * 
     * @param key Given key
     * @param objectClass Class of object
     * @return Created object
     */
    Object create(Class objectClass, Object key);

    /**
     * Method delete() in class PersistenceSession
     * 
     * @param object
     */
    void delete(Object object);

    /**
     * Execute a query
     * 
     * @param query Query object
     * @return QueryResult object
     */
    QueryResult execute(Query query);

    /**
     * Method execute() in class PersistenceSession
     * 
     * @param query
     * @param variables
     * @return
     */
    QueryResult execute(Query query, Map variables);

    /**
     * Method lookup() in class PersistenceSession
     * 
     * @param objectClass
     * @param key
     * @return
     */
    Object lookup(Class objectClass, Object key);

    /**
     * Method reload() in class PersistenceSession
     * 
     * @param object
     */
    void reload(Object object);

    /**
     * Roll back the session
     */
    void rollback();

    /**
     * Method save() in class PersistenceSession
     * 
     * @param object
     */
    void save(Object object);
}