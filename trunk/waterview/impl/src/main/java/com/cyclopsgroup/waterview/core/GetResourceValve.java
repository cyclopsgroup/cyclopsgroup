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

import java.net.URL;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.IOUtil;

import com.cyclopsgroup.waterview.Link;
import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.spi.ModuleService;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Get resource directly
 */
public class GetResourceValve
    implements Valve, Serviceable
{
    private static final String PATH_PREFIX = "/resource";

    private static final String PREFIX = "/" + Link.GET_INSTRUCTOR;

    private ModuleService modules;

    /**
     * Overwrite or implement method invoke()
     *
     * @see com.cyclopsgroup.waterview.spi.Valve#invoke(com.cyclopsgroup.waterview.RunData, com.cyclopsgroup.waterview.spi.PipelineContext)
     */
    public void invoke( RunDataSpi data, PipelineContext context )
        throws Exception
    {
        String requestPath = data.getRequestPath();
        if ( !requestPath.startsWith( PREFIX ) )
        {
            throw new IllegalStateException( "Make sure the configuration for " + PREFIX + " is correct" );
        }
        String path = requestPath.substring( PREFIX.length() );
        Path p = modules.parsePath( path );
        String mimeType = data.getMimeType( path );
        data.setOutputContentType( mimeType );

        String resourcePath = PATH_PREFIX + p.getPath();
        if ( StringUtils.isNotEmpty( p.getPackage() ) )
        {
            resourcePath = p.getPackage().replace( '.', '/' ) + resourcePath;
        }
        URL resource = getClass().getClassLoader().getResource( resourcePath );
        if ( resource != null )
        {
            IOUtil.copy( resource.openStream(), data.getOutputStream() );
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
        modules = (ModuleService) serviceManager.lookup( ModuleService.ROLE );
    }
}
