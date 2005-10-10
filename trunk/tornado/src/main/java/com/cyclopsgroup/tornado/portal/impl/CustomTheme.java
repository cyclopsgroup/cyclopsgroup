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
package com.cyclopsgroup.tornado.portal.impl;

import com.cyclopsgroup.tornado.portal.UserPreference;
import com.cyclopsgroup.waterview.spi.BaseTheme;
import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.Resource;
import com.cyclopsgroup.waterview.spi.Theme;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * User custom theme
 */
public class CustomTheme
    extends BaseTheme
{
    /**
     * Name of custom theme
     */
    public static final String NAME = "custom";

    private Theme defaultTheme;

    private CustomResource iconSet;

    private CustomResource styleSheet;

    /**
     * Constructor for class CustomTheme
     *
     * @param defaultTheme Default proxy theme
     */
    public CustomTheme( Theme defaultTheme )
    {
        super( NAME );
        this.defaultTheme = defaultTheme;
        setDescription( "Customized Theme" );
        iconSet = new CustomResource()
        {
            protected Resource doGetResource( LookAndFeelService laf, UserPreference pref )
                throws Exception
            {
                return laf.getIconSet( pref.getIconset() );
            }
        };

        styleSheet = new CustomResource()
        {
            protected Resource doGetResource( LookAndFeelService laf, UserPreference pref )
                throws Exception
            {
                return laf.getStyleSheet( pref.getStylesheet() );
            }
        };
    }

    /**
     * Overwrite or implement method getIconSet()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getIconSet()
     */
    public Resource getIconSet()
    {
        return iconSet;
    }

    /**
     * Overwrite or implement method getIconSetName()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getIconSetName()
     */
    public String getIconSetName()
    {
        return NAME;
    }

    /**
     * Overwrite or implement method getLayout()
     *
     * @see com.cyclopsgroup.waterview.spi.BaseTheme#getLayout(java.lang.String)
     */
    public Layout getLayout( String layoutName )
    {
        return defaultTheme.getLayout( layoutName );
    }

    /**
     * Overwrite or implement method getStyleSheet()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getStyleSheet()
     */
    public Resource getStyleSheet()
    {
        return styleSheet;
    }

    /**
     * Overwrite or implement method getStyleSheetName()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getStyleSheetName()
     */
    public String getStyleSheetName()
    {
        return NAME;
    }
}
