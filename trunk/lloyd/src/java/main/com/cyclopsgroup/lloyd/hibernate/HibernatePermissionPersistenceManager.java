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
package com.cyclopsgroup.lloyd.hibernate;

import com.cyclopsgroup.lloyd.PermissionPersistenceManager;
import com.cyclopsgroup.lloyd.Role;

/**
 * TODO Add javadoc for this class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class HibernatePermissionPersistenceManager implements
        PermissionPersistenceManager
{

    public HibernatePermissionPersistenceManager(HibernatePersistenceState state)
    {

    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.PermissionPersistenceManager#addPermission2Role(java.lang.String, com.cyclopsgroup.lloyd.Role)
     */
    public void addPermission2Role(String permission, Role role)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.PermissionPersistenceManager#addRole(java.lang.String)
     */
    public Role addRole(String roleName)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
