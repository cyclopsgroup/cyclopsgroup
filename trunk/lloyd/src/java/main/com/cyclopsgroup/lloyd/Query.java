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

/**
 * Query model
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class Query
{
    private Criteria criteria = new Criteria();

    private Class objectClass;

    /**
     * Constructor of Query
     * 
     * @param objectClass Object class of result
     */
    public Query(Class objectClass)
    {
        this.objectClass = objectClass;
    }

    /**
     * Get criteria object
     * 
     * @return Criteria object
     */
    public Criteria getCriteria()
    {
        return criteria;
    }

    /**
     * Get class of result object
     * 
     * @return Class of result object
     */
    public Class getObjectClass()
    {
        return objectClass;
    }
}