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

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.CacheService;
import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.Page;

/**
 * Script layout which doesn't initial script object initially
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class JellyLayoutProxy
    implements Layout
{
    private String layoutScript;

    /**
     * Constructor for class ShellScriptLayout
     *
     * @param layoutScript Layout script path
     */
    public JellyLayoutProxy( String layoutScript )
    {
        this.layoutScript = layoutScript;
    }

    private synchronized JellyLayout getLayout( RuntimeData data )
        throws Exception
    {
        CacheService cm = (CacheService) data.getServiceManager().lookup( CacheService.ROLE );
        JellyLayout layout = (JellyLayout) cm.get( this, layoutScript );
        if ( layout == null )
        {
            JellyEngine je = (JellyEngine) data.getServiceManager().lookup( JellyEngine.ROLE );
            layout = new JellyLayout( je.getScript( layoutScript ), layoutScript );
            cm.put( this, layoutScript, layout );
        }
        return layout;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Layout#render(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.Page)
     */
    public void render( RuntimeData runtime, Page page )
        throws Exception
    {
        getLayout( runtime ).render( runtime, page );
    }
}
