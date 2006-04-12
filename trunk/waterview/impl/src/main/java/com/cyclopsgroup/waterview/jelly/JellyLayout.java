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

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.BaseModuleRunnable;
import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.RunDataSpi;

/**
 * Layout with a script support
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JellyLayout
    extends BaseModuleRunnable
    implements Layout
{
    private Script script;

    /**
     * Constructor for class JellyScriptLayout
     *
     * @param script Jelly script object
     * @param modulePath Path of module
     */
    public JellyLayout( Script script, String modulePath )
    {
        super( modulePath );
        this.script = script;
        if ( script == null )
        {
            throw new NullPointerException( "Script can not be null" );
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Layout#render(com.cyclopsgroup.waterview.RunData, com.cyclopsgroup.waterview.spi.Page)
     */
    public synchronized void render( RunDataSpi data, Page page )
        throws Exception
    {
        data.getRequestContext().put( NAME, this );

        runModule( data, data.getRequestContext() );

        JellyEngine je = (JellyEngine) data.getServiceManager().lookup( JellyEngine.ROLE );
        JellyContext jellyContext = je.createJellyContext( data.getRequestContext() );
        XMLOutput output = XMLOutput.createXMLOutput( data.getOutput() );
        script.run( jellyContext, output );
        data.getRequestContext().put( NAME, null );
    }
}
