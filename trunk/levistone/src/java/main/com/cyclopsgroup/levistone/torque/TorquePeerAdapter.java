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
package com.cyclopsgroup.levistone.torque;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.torque.util.Criteria;

/**
 * Torque peer adapter
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class TorquePeerAdapter
{
    private static final Class[] CRITERIA_CONNECTION_TYPES = new Class[] {
            Criteria.class, Connection.class };

    private Class[] entityConnectionTypes;

    private Class entityType;

    private Class peerType;

    /**
     * Constructor for class TorquePeerAdapter
     *
     * @param entityType
     * @param peerType Entity type
     * @throws Exception
     */
    public TorquePeerAdapter(Class entityType, Class peerType) throws Exception
    {
        this.entityType = entityType;
        this.peerType = peerType;
        entityConnectionTypes = new Class[] { entityType, Connection.class };
    }

    /**
     * Call do delete method
     *
     * @param entity Entity to delete
     * @param dbcon Database connection
     * @throws Exception Throw it out
     */
    public void doDelete(Object entity, Connection dbcon) throws Exception
    {
        Method method = MethodUtils.getAccessibleMethod(peerType, "doDelete",
                entityConnectionTypes);
        method.invoke(null, new Object[] { entity, dbcon });
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param entity
     * @param dbcon
     * @throws Exception
     */
    public void doInsert(Object entity, Connection dbcon) throws Exception
    {
        Method method = MethodUtils.getAccessibleMethod(peerType, "doInsert",
                entityConnectionTypes);
        method.invoke(null, new Object[] { entity, dbcon });
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param criteria
     * @param dbcon
     * @return
     * @throws Exception
     */
    public List doSelect(Criteria criteria, Connection dbcon) throws Exception
    {
        Method method = MethodUtils.getAccessibleMethod(peerType, "doSelect",
                CRITERIA_CONNECTION_TYPES);
        return (List) method.invoke(null, new Object[] { criteria, dbcon });
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param entity
     * @param dbcon
     * @throws Exception
     */
    public void doUpdate(Object entity, Connection dbcon) throws Exception
    {
        Method method = MethodUtils.getAccessibleMethod(peerType, "doUpdate",
                entityConnectionTypes);
        method.invoke(null, new Object[] { entity, dbcon });
    }
}