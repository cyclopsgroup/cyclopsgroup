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
package com.cyclopsgroup.clib.lang;

import java.util.Iterator;

/**
 * Runtime context interface
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public interface Context
{
    /**
     * Get variable from context
     *
     * @param name Variable name
     * @return Object or null
     */
    Object get(String name);

    /**
     * Get available variable names in this context
     *
     * @return Iterator of variable names
     */
    Iterator keys();

    /**
     * Put vriable into context
     *
     * @param name Variable name
     * @param variable Variable value
     */
    void put(String name, Object variable);

    /**
     * Remove a variable
     *
     * @param name Variable name
     */
    void remove(String name);
}