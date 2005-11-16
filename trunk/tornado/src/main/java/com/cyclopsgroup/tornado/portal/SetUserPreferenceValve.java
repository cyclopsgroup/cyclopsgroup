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

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.Theme;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Valve to set user preference
 */
public class SetUserPreferenceValve
    extends AbstractLogEnabled
    implements Valve, Serviceable
{
    private LookAndFeelService laf;

    /**
     * Overwrite or implement method invoke()
     *
     * @see com.cyclopsgroup.waterview.spi.Valve#invoke(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.PipelineContext)
     */
    public void invoke( RuntimeData data, PipelineContext context )
        throws Exception
    {
        RuntimeUser user = RuntimeUser.getInstance( data );
        String themeName = user.getAttributes().getString( PortalService.THEME_ATTRIBUTE_NAME );
        if ( !StringUtils.isEmpty( themeName ) && !PortalService.UNSET_THEME_NAME.equals( themeName ) )
        {
            Theme theme = laf.getTheme( themeName );
            laf.setRuntimeTheme( data, theme );
        }
        context.invokeNextValve( data );
    }

    /**
     * Overwrite or implement method service()
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        laf = (LookAndFeelService) serviceManager.lookup( LookAndFeelService.ROLE );
    }
}
