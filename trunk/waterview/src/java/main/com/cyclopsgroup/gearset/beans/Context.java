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
package com.cyclopsgroup.gearset.beans;

/**
 * Context interface
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public interface Context
{

    /**
     * Get object from context
     * 
     * @param name
     *                   Name of the variable
     * @return Object in context
     */
    Object get(String name);

    /**
     * Get array of names in this context
     * 
     * @return Name array
     */
    String[] getNames();

    /**
     * Put new value in context
     * 
     * @param name
     *                   Name of the variable
     * @param object
     *                   Value of the variable
     */
    void put(String name, Object object);
}