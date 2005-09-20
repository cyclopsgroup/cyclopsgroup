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
package com.cyclopsgroup.tornado.portal.impl;

import java.util.List;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.cyclopsgroup.tornado.hibernate.HibernateService;
import com.cyclopsgroup.tornado.portal.PortalService;
import com.cyclopsgroup.tornado.portal.UserPreference;
import com.cyclopsgroup.tornado.security.CreateUserEvent;
import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.tornado.security.SecurityListener;
import com.cyclopsgroup.tornado.security.SecurityService;
import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Default portal service
 */
public class DefaultPortalService
    extends AbstractLogEnabled
    implements Serviceable, Initializable, SecurityListener, PortalService
{
    private HibernateService hibernate;

    private LookAndFeelService laf;

    private SecurityService security;

    /**
     * Overwrite or implement method findUserPreference()
     *
     * @see com.cyclopsgroup.tornado.portal.PortalService#findUserPreference(java.lang.String)
     */
    public UserPreference findUserPreference( String userId )
        throws Exception
    {
        Session s = hibernate.getSession();
        List prefs = s.createCriteria( UserPreference.class ).add( Expression.eq( "userId", userId ) )
            .setMaxResults( 1 ).list();
        return prefs.isEmpty() ? null : (UserPreference) prefs.get( 0 );
    }

    /**
     * Overwrite or implement method initialize()
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        security.addListener( this );
        laf.registerTheme( new CustomTheme( laf.getDefaultTheme() ) );
    }

    /**
     * Overwrite or implement method performAction()
     *
     * @see com.cyclopsgroup.tornado.security.SecurityListener#performAction(java.lang.Object)
     */
    public void performAction( Object event )
        throws Exception
    {
        if ( event instanceof CreateUserEvent )
        {
            RuntimeUser user = (RuntimeUser) ( (CreateUserEvent) event ).getUser();

            UserPreference up = findUserPreference( user.getId() );
            if ( up == null )
            {
                return;
            }
            if ( !up.getThemeName().equals( PortalService.UNSET_THEME_NAME ) )
            {
                user.getAttributes().set( PortalService.USER_THEME_NAME, up.getThemeName() );
            }
        }
    }

    /**
     * Overwrite or implement method service()
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager services )
        throws ServiceException
    {
        hibernate = (HibernateService) services.lookup( HibernateService.ROLE );
        security = (SecurityService) services.lookup( SecurityService.ROLE );
        laf = (LookAndFeelService) services.lookup( LookAndFeelService.ROLE );
        services.lookup( JellyEngine.ROLE );
    }
}