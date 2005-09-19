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
package com.cyclopsgroup.tornado.portal;

import java.util.List;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.cyclopsgroup.tornado.hibernate.HibernateService;
import com.cyclopsgroup.tornado.security.CreateUserEvent;
import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.tornado.security.SecurityListener;
import com.cyclopsgroup.tornado.security.SecurityService;
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
     * Overwrite or implement method initialize()
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        security.addListener( this );
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
            Criteria criteria = hibernate.getSession().createCriteria( UserPreference.class );
            List prefs = criteria.add( Expression.eq( "userId", user.getId() ) ).setMaxResults( 1 ).list();
            String themeName = laf.getDefaultTheme().getName();
            if ( !prefs.isEmpty() )
            {
                UserPreference up = (UserPreference) prefs.get( 0 );
                if ( !up.getThemeName().equals( PortalService.UNSET_THEME_NAME ) )
                {
                    themeName = up.getThemeName();
                }
            }
            user.getAttributes().set( PortalService.USER_THEME_NAME, themeName );
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
    }
}