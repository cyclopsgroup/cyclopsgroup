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

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.levistone.Session;
import com.cyclopsgroup.levistone.datasource.DefaultDataSourceManager;
import com.cyclopsgroup.levistone.spi.AbstractPersistenceManager;

/**
 * Hibernate implemented persistence manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class HibernatePersistenceManager extends AbstractPersistenceManager
        implements Serviceable
{

    private DefaultDataSourceManager dataSourceManager = new DefaultDataSourceManager();

    private SessionFactory sessionFactory;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.levistone.spi.AbstractPersistenceManager#doOpenSession(java.lang.String, java.lang.String)
     */
    protected Session doOpenSession(String persistenceName, String sessionId)
            throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager arg0) throws ServiceException
    {
        // TODO Auto-generated method stub

    }
}