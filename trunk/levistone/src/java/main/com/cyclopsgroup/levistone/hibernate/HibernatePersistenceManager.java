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
package com.cyclopsgroup.levistone.hibernate;

import net.sf.hibernate.SessionFactory;

import com.cyclopsgroup.levistone.NamedQuery;
import com.cyclopsgroup.levistone.Session;
import com.cyclopsgroup.levistone.base.BasePersistenceManager;

/**
 * TODO Add javadoc for class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class HibernatePersistenceManager extends BasePersistenceManager
{
    private SessionFactory sessionFactory;

    /**
     * Override method createQuery in super class of HibernatePersistenceManager
     * 
     * @see com.cyclopsgroup.levistone.PersistenceManager#createQuery(java.lang.String)
     */
    public NamedQuery createQuery(String name)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override method doCancelSession in super class of HibernatePersistenceManager
     * 
     * @see com.cyclopsgroup.levistone.base.BasePersistenceManager#doCancelSession(com.cyclopsgroup.levistone.Session)
     */
    protected void doCancelSession(Session session) throws Exception
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override method doCloseSession in super class of HibernatePersistenceManager
     * 
     * @see com.cyclopsgroup.levistone.base.BasePersistenceManager#doCloseSession(com.cyclopsgroup.levistone.Session)
     */
    protected void doCloseSession(Session session) throws Exception
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override method doOpenSession in super class of HibernatePersistenceManager
     * 
     * @see com.cyclopsgroup.levistone.base.BasePersistenceManager#doOpenSession(java.lang.String)
     */
    protected Session doOpenSession(String persistenceName) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }
}