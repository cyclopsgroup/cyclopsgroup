/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
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
package com.cyclopsgroup.clib.site.user.hibernate;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

import com.cyclopsgroup.clib.site.avalon.LoadOnStart;
import com.cyclopsgroup.clib.site.hibernate.HibernateFactory;
import com.cyclopsgroup.clib.site.hibernate.HibernateServiceManager;
import com.cyclopsgroup.clib.site.hibernate.HibernateServiceable;
import com.cyclopsgroup.clib.site.user.User;
import com.cyclopsgroup.clib.site.user.UserAuthenticator;

/**
 * Hibernate implemented user authenticator
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class HibernateUserAuthenticator implements UserAuthenticator,
        Serviceable, LoadOnStart, HibernateServiceable
{

    private HibernateFactory hibernateProvider;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.user.UserAuthenticator#authenticate(java.lang.String, java.lang.String)
     */
    public boolean authenticate(String userName, String password)
            throws Exception {
        UserEntity user = (UserEntity) getCurrentHibernateSession().get(
                UserEntity.class, userName);
        if (user == null)
        {
            return false;
        }
        return StringUtils.equals(user.getPassword(), password);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.user.UserAuthenticator#exsit(java.lang.String)
     */
    public boolean exsit(String userName) throws Exception {
        UserEntity user = (UserEntity) getCurrentHibernateSession().get(
                UserEntity.class, userName);
        return user != null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.user.UserAuthenticator#fetch(java.lang.String)
     */
    public User fetch(String userName) throws Exception {
        return (UserEntity) getCurrentHibernateSession().load(UserEntity.class,
                userName);
    }

    private Session getCurrentHibernateSession() throws Exception {
        return hibernateProvider.getCurrentSession();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException {
        hibernateProvider = (HibernateFactory) serviceManager
                .lookup(HibernateFactory.ROLE);
    }

    /**
     * Overwrite or implement method serviceHibernate()
     * @see com.cyclopsgroup.clib.site.hibernate.HibernateServiceable#serviceHibernate(com.cyclopsgroup.clib.site.hibernate.HibernateServiceManager)
     */
    public void serviceHibernate(HibernateServiceManager serviceManager) {
        serviceManager.registerHibernateEntity(UserEntity.class);
    }
}
