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

import com.cyclopsgroup.tornado.portal.PortalService;
import com.cyclopsgroup.tornado.portal.UserPreference;
import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.waterview.Resource;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Custom resource
 */
public abstract class CustomResource
    extends Resource
{
    /**
     * Constructor for class CustomResource
     */
    public CustomResource()
    {
        super( Resource.INTERNAL, null );
    }

    protected abstract Resource doGetResource( LookAndFeelService laf, UserPreference pref )
        throws Exception;

    /**
     * Overwrite or implement method toURL()
     *
     * @see com.cyclopsgroup.waterview.Resource#toURL(com.cyclopsgroup.waterview.RuntimeData)
     */
    public String toURL( RuntimeData data )
        throws Exception
    {
        RuntimeUser user = RuntimeUser.getInstance( data );
        PortalService portal = (PortalService) data.getServiceManager().lookup( PortalService.ROLE );
        UserPreference up = portal.findUserPreference( user.getId() );
        if ( up == null )
        {
            throw new IllegalStateException( "Custom style must be selected when user preference is created" );
        }
        LookAndFeelService laf = (LookAndFeelService) data.getServiceManager().lookup( LookAndFeelService.ROLE );
        Resource resource = doGetResource( laf, up );
        return resource.toURL( data );
    }
}
