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

import java.util.Date;

import junit.framework.TestCase;

import com.cyclopsgroup.levistone.Session;

/**
 * Test case for persistence manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class TorquePersistenceManagerTest extends TestCase
{
    private TorquePersistenceManager persistenceManager;

    /**
     * Override or implement method of parent class or interface
     *
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        persistenceManager = new TorquePersistenceManager();
        persistenceManager.init("src/java/test/Torque.properties");
    }

    /**
     * Testing for object creation and deletion
     *
     * @throws Exception Throw it out
     */
    public void testSession() throws Exception
    {
        User user = new User();
        Date birthday = new Date();
        user.setBirthday(birthday);
        user.setEmail("abd@aaa.com");
        user.setUserName("joe");
        Session session = persistenceManager.openSession();
        try
        {
            session.save(User.class, user);
            long id = user.getId();
            User sameUser = (User) session.lookup(User.class, new Long(id));
            System.out.println(sameUser);
            assertNotNull(sameUser);
            assertEquals("joe", sameUser.getUserName());
            session.delete(User.class, sameUser);
            try
            {
                sameUser = (User) session.lookup(User.class, new Long(id));
                fail();
            }
            catch (Exception e)
            {
                //This is correct
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            persistenceManager.closeSession(session);
        }
    }
}