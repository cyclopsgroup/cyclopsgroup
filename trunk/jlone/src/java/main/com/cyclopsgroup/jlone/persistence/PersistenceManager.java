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
package com.cyclopsgroup.jlone.persistence;

/**
 * Persistence manager interface
 * 
 * @author <a href="mailto:jiiiaqi@yahoo.com">Jiaqi Guo </a>
 * 
 * Developed with eclipse 3.0
 */
public interface PersistenceManager
{
    /**
     * Get root data document
     *
     * @return Data document object
     */
    DataDocument getDataDocument();

    /**
     * Get data document with given unique id
     *
     * @param id Unique id
     * @return Data document object
     */
    DataDocument load(long id);

    /**
     * Get data document with given path
     *
     * @param path Full path of content
     * @return Data document object
     */
    DataDocument load(String path);
}