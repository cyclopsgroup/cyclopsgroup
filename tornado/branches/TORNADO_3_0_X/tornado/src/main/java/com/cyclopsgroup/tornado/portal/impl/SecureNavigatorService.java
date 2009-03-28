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

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.tornado.security.SecurityListener;
import com.cyclopsgroup.tornado.security.SecurityService;
import com.cyclopsgroup.tornado.security.UserChangedEvent;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.navigator.impl.DefaultNavigatorService;
import com.cyclopsgroup.waterview.web.RuntimeTreeNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Secure navigator service
 */
public class SecureNavigatorService
    extends DefaultNavigatorService
    implements SecurityListener, Serviceable
{
    /**
     * Overwrite or implement method doCreateRuntimeRoot()
     *
     * @see com.cyclopsgroup.waterview.navigator.impl.DefaultNavigatorService#doCreateRuntimeRoot(com.cyclopsgroup.waterview.RuntimeData)
     */
    protected RuntimeTreeNode doCreateRuntimeRoot( RuntimeData data )
    {
        return new SecureRuntimeNavigatorNode( null, getRootNode() );
    }

    /**
     * Overwrite or implement method performAction()
     *
     * @see com.cyclopsgroup.tornado.security.SecurityListener#performAction(java.lang.Object)
     */
    public void performAction( Object event )
        throws Exception
    {
        if ( event instanceof UserChangedEvent )
        {
            refresh( ( (UserChangedEvent) event ).getRuntimeData() );
        }
    }

    /**
     * Overwrite or implement method service()
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        SecurityService security = (SecurityService) serviceManager.lookup( SecurityService.ROLE );
        security.addListener( this );
    }
}
