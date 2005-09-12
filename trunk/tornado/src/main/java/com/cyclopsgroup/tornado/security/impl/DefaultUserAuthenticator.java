/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.tornado.security.impl;

import java.util.List;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.cyclopsgroup.tornado.hibernate.HibernateService;
import com.cyclopsgroup.tornado.security.User;
import com.cyclopsgroup.tornado.security.UserAuthenticationResult;
import com.cyclopsgroup.tornado.security.UserAuthenticator;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class DefaultUserAuthenticator
    extends AbstractLogEnabled
    implements UserAuthenticator, Serviceable
{
    private HibernateService hibernate;

    /**
     * Override method service in class DefaultUserAuthenticator
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        hibernate = (HibernateService) serviceManager.lookup( HibernateService.ROLE );
    }

    /**
     * Override method authenticate in class DefaultUserAuthenticator
     *
     * @see com.cyclopsgroup.tornado.security.UserAuthenticator#authenticate(java.lang.String, java.lang.String)
     */
    public UserAuthenticationResult authenticate( String userName, String password )
    {
        try
        {
            Session s = hibernate.getSession();
            List users = s.createCriteria( User.class ).add( Expression.eq( "isDisabled", Boolean.FALSE ) )
                .add( Expression.eq( "name", userName ) ).setMaxResults( 1 ).list();
            if ( users.isEmpty() )
            {
                return UserAuthenticationResult.NO_SUCH_USER;
            }
            User user = (User) users.get( 0 );
            if ( !StringUtils.equals( user.getPrivatePassword(), password ) )
            {
                return UserAuthenticationResult.WRONG_PASSWORD;
            }
            return UserAuthenticationResult.SUCCESS;
        }
        catch ( Exception e )
        {
            getLogger().error( "User authentication error", e );
            return UserAuthenticationResult.ERROR;
        }
    }
}
