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
package com.cyclopsgroup.waterview.jelly.deftaglib;

import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.LookAndFeelService.PredefinedLayout;

class PredefinedLayoutProxy
    implements PredefinedLayout
{
    private LookAndFeelService laf;

    private String layoutName;

    private PredefinedLayout layout;

    private synchronized PredefinedLayout getProxy()
    {
        if ( layout == null )
        {
            layout = laf.getLayout( layoutName );
            if ( layout == null )
            {
                throw new RuntimeException( "Layout " + layoutName + " doesn't exsit" );
            }
        }
        return layout;
    }

    /**
     * Constructor for class PredefinedLayoutProxy
     *
     * @param laf Look and feel service
     * @param layoutName Layout name
     */
    PredefinedLayoutProxy( LookAndFeelService laf, String layoutName )
    {
        this.laf = laf;
        this.layoutName = layoutName;
    }

    /**
     * Overwrite or implement method getDescription()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService.PredefinedLayout#getDescription()
     */
    public String getDescription()
    {
        return getProxy().getDescription();
    }

    /**
     * Overwrite or implement method getName()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService.PredefinedLayout#getName()
     */
    public String getName()
    {
        return getProxy().getName();
    }

    /**
     * Overwrite or implement method getTitle()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService.PredefinedLayout#getTitle()
     */
    public String getTitle()
    {
        return getProxy().getTitle();
    }

    /**
     * Overwrite or implement method render()
     *
     * @see com.cyclopsgroup.waterview.spi.Layout#render(com.cyclopsgroup.waterview.RunData, com.cyclopsgroup.waterview.spi.Page)
     */
    public void render( RunDataSpi data, Page page )
        throws Exception
    {
        getProxy().render( data, page );
    }
}
