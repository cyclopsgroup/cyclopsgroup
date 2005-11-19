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
package com.cyclopsgroup.waterview.ui.action;

import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.NoSuchLookAndFeelException;
import com.cyclopsgroup.waterview.spi.Theme;
import com.cyclopsgroup.waterview.spi.LookAndFeelService.IconSet;
import com.cyclopsgroup.waterview.spi.LookAndFeelService.Style;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Theme proxy
 */
class ThemeProxy
    implements Theme
{
    private Theme proxy;

    ThemeProxy( Theme proxy )
    {
        this.proxy = proxy;
    }

    /**
     * Overwrite or implement method getDescription()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getDescription()
     */
    public String getDescription()
    {
        return proxy.getDescription();
    }

    /**
     * Overwrite or implement method getIconSet()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getIconSet()
     */
    public IconSet getIconSet()
        throws NoSuchLookAndFeelException
    {
        return proxy.getIconSet();
    }

    /**
     * Overwrite or implement method getIconSetName()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getIconSetName()
     */
    public String getIconSetName()
    {
        return proxy.getIconSetName();
    }

    /**
     * Overwrite or implement method getLayout()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getLayout(java.lang.String)
     */
    public Layout getLayout( String layoutName )
    {
        return proxy.getLayout( layoutName );
    }

    /**
     * Overwrite or implement method getName()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getName()
     */
    public String getName()
    {
        return proxy.getName();
    }

    /**
     * Overwrite or implement method getStyleSheet()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getStyle()
     */
    public Style getStyle()
        throws NoSuchLookAndFeelException
    {
        return proxy.getStyle();
    }

    /**
     * Overwrite or implement method getStyleSheetName()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getStyleName()
     */
    public String getStyleName()
    {
        return proxy.getStyleName();
    }

    /**
     * Overwrite or implement method getTitle()
     *
     * @see com.cyclopsgroup.waterview.spi.SelectableItem#getTitle()
     */
    public String getTitle()
    {
        return proxy.getTitle();
    }
}
