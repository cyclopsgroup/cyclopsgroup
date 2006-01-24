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
package com.cyclopsgroup.waterview.jelly;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.CacheService;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * Valve to find page object
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DeterminePageValve
    extends AbstractLogEnabled
    implements Configurable, Valve, Initializable, Serviceable
{

    private static final Page EMPTY_PAGE = new Page();

    private Page defaultPage = EMPTY_PAGE;

    private String defaultPageScript;

    private JellyEngine jelly;

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration conf )
        throws ConfigurationException
    {
        String page = conf.getChild( "default-page" ).getValue( null );
        if ( page != null )
        {
            defaultPageScript = page;
        }
    }

    /**
     * Overwrite or implement method initialize()
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        if ( StringUtils.isNotEmpty( defaultPageScript ) )
        {
            Script script = jelly.getScript( defaultPageScript, (Script) null );
            if ( script == null )
            {
                return;
            }
            defaultPage = loadPage( script );
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Valve#invoke(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.PipelineContext)
     */
    public void invoke( RuntimeData runtime, PipelineContext context )
        throws Exception
    {
        Page page = (Page) runtime.getRequestContext().get( Page.NAME );
        if ( page != null )
        {
            context.invokeNextValve( runtime );
            return;
        }
        Path pagePath = runtime.getPage();
        if ( pagePath == null )
        {
            throw new NullPointerException( "Path is not ready yet" );
        }
        synchronized ( this )
        {
            CacheService cacheManager = (CacheService) runtime.getServiceManager().lookup( CacheService.ROLE );
            page = (Page) cacheManager.get( this, pagePath.getFullPath() );
            if ( page == null )
            {
                //ModuleChain moduleChain = new ModuleChain();
                String fullPath = "/page" + pagePath.getPath();
                //moduleChain.addModule(mm.getModule(fullPath));

                Script pageScript = jelly.getScript( pagePath.getPackage(), fullPath, null );
                if ( pageScript != null )
                {
                    page = loadPage( pageScript );
                }
                String[] parts = StringUtils.split( pagePath.getPath(), '/' );
                for ( int j = parts.length - 1; j >= 0; j-- )
                {
                    parts[j] = "Default.jelly";
                    String[] newParts = new String[j + 1];
                    System.arraycopy( parts, 0, newParts, 0, j + 1 );
                    String defaultPath = StringUtils.join( newParts, '/' );
                    fullPath = "/page/" + defaultPath;
                    pageScript = jelly.getScript( pagePath.getPackageAlias(), fullPath, null );
                    if ( pageScript != null )
                    {
                        page = loadPage( pageScript );
                        //moduleChain.addModule(mm.getModule(model.getPackage(), fullPath));
                        break;
                    }
                }
                if ( page == null )
                {
                    page = defaultPage;
                }
                //page.setModule(moduleChain);
                cacheManager.put( this, pagePath.getFullPath(), page );
            }
        }
        runtime.getRequestContext().put( Page.NAME, page );
        context.invokeNextValve( runtime );
    }

    private Page loadPage( Script script )
        throws JellyTagException
    {
        JellyContext jc = new JellyContext( jelly.getGlobalContext() );
        script.run( jc, XMLOutput.createDummyXMLOutput() );
        return (Page) jc.getVariable( Page.NAME );
    }

    /**
     * Overwrite or implement method service()
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        jelly = (JellyEngine) serviceManager.lookup( JellyEngine.ROLE );
    }
}