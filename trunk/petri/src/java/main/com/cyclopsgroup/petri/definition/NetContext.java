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
package com.cyclopsgroup.petri.definition;

/**
 * Flow specific context interface
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public interface NetContext
{

    /**
     * Get object from context
     *
     * @param variableName Name of variable
     * @return Variable object or null if not found
     */
    Object get(String variableName);

    /**
     * Get name array
     *
     * @return Name array
     */
    String[] getNames();

    /**
     * Put object into context
     *
     * @param variableName Name of variable
     * @param variable Variable object
     */
    void put(String variableName, Object variable);
}