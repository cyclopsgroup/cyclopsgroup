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
package com.cyclopsgroup.tornado.components.security.impl;

import org.apache.avalon.framework.logger.AbstractLogEnabled;

import com.cyclopsgroup.clib.site.hibernate.HibernateServiceManager;
import com.cyclopsgroup.clib.site.hibernate.HibernateServiceable;
import com.cyclopsgroup.tornado.components.security.entity.TornadoUser;
import com.cyclopsgroup.tornado.core.security.UserAuthenticator;

public class DefaultUserAuthenticator extends AbstractLogEnabled implements
        UserAuthenticator, HibernateServiceable
{
    //private HibernateFactory hibernate;

    /**
     * Overwrite or implement method checkUser()
     * @see com.cyclopsgroup.tornado.core.security.UserAuthenticator#checkUser(java.lang.String, java.lang.String)
     */
    public int checkUser(String userName, String password)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Overwrite or implement method serviceHibernate()
     * @see com.cyclopsgroup.clib.site.hibernate.HibernateServiceable#serviceHibernate(com.cyclopsgroup.clib.site.hibernate.HibernateServiceManager)
     */
    public void serviceHibernate(HibernateServiceManager serviceManager)
    {
        serviceManager.registerHibernateEntity(TornadoUser.class);
    }
}
