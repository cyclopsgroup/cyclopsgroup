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

import net.sf.hibernate.Session;

import com.cyclopsgroup.lloyd.Group;
import com.cyclopsgroup.lloyd.User;
import com.cyclopsgroup.lloyd.UserPersistenceManager;

/**
 * TODO Add javadoc for this class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class HibernateUserPersistenceManager implements UserPersistenceManager
{
    private Session session;

    /**
     * Constructor for class HibernateUserPersistenceManager
     *
     * @param state
     */
    public HibernateUserPersistenceManager(HibernatePersistenceState state)
    {
        session = state.getSession();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.UserPersistenceManager#addGroup(java.lang.String)
     */
    public Group addGroup(String groupName)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.UserPersistenceManager#addUser(java.lang.String, java.lang.String)
     */
    public User addUser(String userName, String password) throws Exception
    {
        HibernateUser hu = new HibernateUser();
        hu.setName(userName);
        hu.setPassword(password);
        hu.setDisabled(false);
        getSession().save(hu);
        return hu;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.UserPersistenceManager#getEnabledGroup(java.lang.String)
     */
    public Group getEnabledGroup(String groupName)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.UserPersistenceManager#getEnabledUser(java.lang.String)
     */
    public User getEnabledUser(String userName)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.UserPersistenceManager#getGroup(long)
     */
    public Group getGroup(long groupId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.UserPersistenceManager#getGroups(com.cyclopsgroup.lloyd.User)
     */
    public Group[] getGroups(User user)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Session getSession()
    {
        return session;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.UserPersistenceManager#getUser(long)
     */
    public User getUser(long userId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.UserPersistenceManager#joinGroup(com.cyclopsgroup.lloyd.User, com.cyclopsgroup.lloyd.Group)
     */
    public void joinGroup(User user, Group group)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.UserPersistenceManager#leaveGroup(com.cyclopsgroup.lloyd.User, com.cyclopsgroup.lloyd.Group)
     */
    public void leaveGroup(User user, Group group)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.lloyd.UserPersistenceManager#saveUser(com.cyclopsgroup.lloyd.User)
     */
    public void saveUser(User user)
    {
        // TODO Auto-generated method stub

    }

}
