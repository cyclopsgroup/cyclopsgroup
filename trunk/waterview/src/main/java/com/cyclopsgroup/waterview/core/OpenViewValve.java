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
package com.cyclopsgroup.waterview.core;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.ModuleService;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.Theme;
import com.cyclopsgroup.waterview.spi.Valve;
import com.cyclopsgroup.waterview.spi.View;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Valve to open view
 */
public class OpenViewValve
    implements Valve, Serviceable
{
    private LookAndFeelService laf;

    private ModuleService modules;

    /**
     * Overwrite or implement method invoke()
     *
     * @see com.cyclopsgroup.waterview.spi.Valve#invoke(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.PipelineContext)
     */
    public void invoke( RuntimeData data, PipelineContext context )
        throws Exception
    {
        data.setOutputContentType( "text/html" );
        Theme theme = laf.getTheme( data.getThemeName() );
        if ( theme == null )
        {
            data.setThemeName( laf.getDefaultTheme().getName() );
            theme = laf.getDefaultTheme();
        }
        RuntimeTheme rt = new RuntimeTheme( theme, data );
        data.getRequestContext().put( RuntimeTheme.NAME, rt );
        data.getRequestContext().put( "themeBase", rt.getIconSetUrl() );

        String viewPath = (String) data.getRequestContext().get( RuntimeData.OPEN_VIEW_NAME );
        if ( StringUtils.isEmpty( viewPath ) )
        {
            return;
        }
        View view = modules.createDynaView( viewPath );
        if ( view == null )
        {
            return;
        }
        view.render( data, data.getRequestContext() );
        data.getOutput().flush();
    }

    /**
     * Overwrite or implement method service()
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        modules = (ModuleService) serviceManager.lookup( ModuleService.ROLE );
        laf = (LookAndFeelService) serviceManager.lookup( LookAndFeelService.ROLE );
    }
}
