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
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * Valve to find page object
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DeterminePageValve
    extends AbstractLogEnabled
    implements Valve, Serviceable
{
    private JellyEngine jelly;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Valve#invoke(com.cyclopsgroup.waterview.RunData, com.cyclopsgroup.waterview.spi.PipelineContext)
     */
    public void invoke( RunDataSpi data, PipelineContext context )
        throws Exception
    {
        Path pagePath = data.getPage();
        if ( pagePath == null )
        {
            throw new NullPointerException( "Path is not ready yet" );
        }
        synchronized ( this )
        {
            String fullPath = "/page" + pagePath.getPath();

            Script pageScript = jelly.getScript( pagePath.getPackage(), fullPath, null );
            if ( pageScript != null )
            {
                populatePage( data, pageScript );
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
                    populatePage( data, pageScript );
                    break;
                }
            }
        }
        context.invokeNextValve( data );
    }

    private void populatePage( RunDataSpi data, Script script )
        throws JellyTagException
    {
        JellyContext jc = new JellyContext( jelly.getGlobalContext() );
        script.run( jc, XMLOutput.createDummyXMLOutput() );
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
