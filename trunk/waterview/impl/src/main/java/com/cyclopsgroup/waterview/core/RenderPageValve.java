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
package com.cyclopsgroup.waterview.core;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.ModuleService;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * Valve to render page
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class RenderPageValve
    extends AbstractLogEnabled
    implements Valve, Serviceable
{
    private ModuleService moduleService;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Valve#invoke(com.cyclopsgroup.waterview.RunData, com.cyclopsgroup.waterview.spi.PipelineContext)
     */
    public void invoke( RunDataSpi data, PipelineContext context )
        throws Exception
    {
        moduleService.runModule( '/' + data.getPage().getPackageAlias() + "/page" + data.getPage().getPath(), data,
                                 data.getRequestContext() );
        if ( data.isStopped() )
        {
            return;
        }

        Page page = data.getPageObject();

        data.setOutputContentType( "text/html; charset=UTF-8" );
        Layout layout = page.getLayout();
        String pageLayoutId = data.getParameters().getString( "page_layout_id" );
        if ( StringUtils.isNotEmpty( pageLayoutId ) )
        {
            layout = moduleService.getLayout( pageLayoutId );
        }
        if ( layout == null )
        {
            layout = moduleService.getLayout( ModuleService.DEFAULT_LAYOUT_NAME );
        }
        page.setLayout( layout );
        layout.render( data, page );
        context.invokeNextValve( data );
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
        moduleService = (ModuleService) serviceManager.lookup( ModuleService.ROLE );
    }
}
