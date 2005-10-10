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
package com.cyclopsgroup.laputa.core;

/**
 * Component to manage generic entity
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface EntityRepository
{
    /** Role name of this component */
    String ROLE = EntityRepository.class.getName();

    /**
     * Delete object from db
     *
     * @param entity Object to delete
     * @throws Exception Throw it out
     */
    void delete(Object entity) throws Exception;

    /**
     * Find object with string id
     *
     * @param type Object type
     * @param id String id
     * @return Entity object
     * @throws Exception Throw it out
     */
    Object lookup(Class type, String id) throws Exception;

    /**
     * Save changed object
     *
     * @param entity Entity object
     * @throws Exception Throw it out
     */
    void save(Object entity) throws Exception;
}
