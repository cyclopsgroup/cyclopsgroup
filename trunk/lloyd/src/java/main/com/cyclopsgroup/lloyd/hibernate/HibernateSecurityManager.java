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

import org.apache.avalon.framework.logger.AbstractLogEnabled;

import com.cyclopsgroup.lloyd.Asset;
import com.cyclopsgroup.lloyd.PermissionPersistenceManager;
import com.cyclopsgroup.lloyd.PersistenceState;
import com.cyclopsgroup.lloyd.RuntimeUser;
import com.cyclopsgroup.lloyd.SecurityManager;
import com.cyclopsgroup.lloyd.User;
import com.cyclopsgroup.lloyd.UserPersistenceManager;

/**
 * Hibernate implemented security manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class HibernateSecurityManager extends AbstractLogEnabled implements
        SecurityManager
{

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.SecurityManager#accept(com.cyclopsgroup.lloyd.RuntimeUser, com.cyclopsgroup.lloyd.Asset)
     */
    public boolean accept(RuntimeUser runtimeUser, Asset asset)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.SecurityManager#getPermissionPersistenceManager(com.cyclopsgroup.lloyd.PersistenceState)
     */
    public PermissionPersistenceManager getPermissionPersistenceManager(
            PersistenceState state)
    {
        return new HibernatePermissionPersistenceManager(
                (HibernatePersistenceState) state);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.SecurityManager#getRuntimeUser(java.lang.String)
     */
    public RuntimeUser getRuntimeUser(String runtimeId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.SecurityManager#getUserPersistenceManager(com.cyclopsgroup.lloyd.PersistenceState)
     */
    public UserPersistenceManager getUserPersistenceManager(
            PersistenceState state)
    {
        return new HibernateUserPersistenceManager(
                (HibernatePersistenceState) state);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.SecurityManager#startRuntimeUser(com.cyclopsgroup.lloyd.User, java.lang.String)
     */
    public RuntimeUser startRuntimeUser(User user, String runtimeId)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
