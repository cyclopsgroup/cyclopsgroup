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
 * User manager interface
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface UserPersistenceManager
{
    /**
     * Add new group
     *
     * @param groupName
     * @return Group object
     */
    Group addGroup(String groupName) throws Exception;

    /**
     * Add new user
     *
     * @param userName
     * @param password
     * @return User object
     */
    User addUser(String userName, String password) throws Exception;

    /**
     * TODO Add javadoc for this method
     *
     * @param groupName
     * @return Group object
     */
    Group getEnabledGroup(String groupName) throws Exception;

    /**
     * Get active user by user name
     *
     * @param userName
     * @return User object
     */
    User getEnabledUser(String userName) throws Exception;

    /**
     * TODO Add javadoc for this method
     *
     * @param groupId
     * @return Group object
     */
    Group getGroup(long groupId) throws Exception;

    /**
     * Get the groups this user belongs to
     *
     * @param user User object
     * @return Group array
     */
    Group[] getGroups(User user) throws Exception;

    /**
     * TODO Add javadoc for this method
     *
     * @param userId
     * @return User object
     */
    User getUser(long userId) throws Exception;

    /**
     * TODO Add javadoc for this method
     *
     * @param user
     * @param group
     */
    void joinGroup(User user, Group group) throws Exception;

    /**
     * TODO Add javadoc for this method
     *
     * @param user
     * @param group
     */
    void leaveGroup(User user, Group group) throws Exception;

    /**
     * TODO Add javadoc for this method
     *
     * @param user
     */
    void saveUser(User user) throws Exception;
}