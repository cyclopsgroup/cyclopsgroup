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
package com.cyclopsgroup.waterview.jelly.deftaglib;

import com.cyclopsgroup.waterview.spi.BaseTheme;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.LookAndFeelService.IconSet;
import com.cyclopsgroup.waterview.spi.LookAndFeelService.Style;

class ThemeProxy
    extends BaseTheme
{
    private String iconSetName;

    private String styleName;

    ThemeProxy( String name, LookAndFeelService laf, String iconSetName, String styleSheetName )
    {
        super( name, laf );
        this.iconSetName = iconSetName;
        this.styleName = styleSheetName;
    }

    /**
     * Overwrite or implement method getIconSet()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getIconSet()
     */
    public IconSet getIconSet()
    {
        try
        {
            return getLAFService().getIconSet( iconSetName );
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e );
        }
    }

    /**
     * Overwrite or implement method getIconSetName()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getIconSetName()
     */
    public String getIconSetName()
    {
        return iconSetName;
    }

    /**
     * Overwrite or implement method getStyleSheet()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getStyle()
     */
    public Style getStyle()
    {
        try
        {
            return getLAFService().getStyle( styleName );
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e );
        }
    }

    /**
     * Overwrite or implement method getStyleSheetName()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getStyleName()
     */
    public String getStyleName()
    {
        return styleName;
    }
}