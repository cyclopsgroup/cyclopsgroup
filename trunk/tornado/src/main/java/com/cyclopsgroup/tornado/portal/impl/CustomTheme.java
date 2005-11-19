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
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.BaseTheme;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.LookAndFeelService.IconSet;
import com.cyclopsgroup.waterview.spi.LookAndFeelService.Style;

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

    private IconSet iconSet;

    private Style style;

    /**
     * Constructor for class CustomTheme
     *
     * @param pref User preference object
     * @param laf Look and feel service
     */
    public CustomTheme( final UserPreference pref, final LookAndFeelService laf )
    {
        super( NAME, laf );
        setDescription( "Customized theme" );
        setTitle( "Customized theme" );
        iconSet = new IconSet()
        {
            public String getUrl( RuntimeData data, String file, int size )
            {
                try
                {
                    return laf.getIconSet( pref.getIconset() ).getUrl( data, file, size );
                }
                catch ( Exception e )
                {
                    throw new RuntimeException( e );
                }
            }

            public String getDescription()
            {
                return "Customized icon set";
            }

            public String getName()
            {
                return NAME;
            }

            public String getTitle()
            {
                return "Customized icon set";
            }
        };

        style = new Style()
        {
            public String getUrl( RuntimeData data )
            {
                try
                {
                    return laf.getStyle( pref.getStylesheet() ).getUrl( data );
                }
                catch ( Exception e )
                {
                    throw new RuntimeException( e );
                }
            }

            public String getDescription()
            {
                return "Customized style";
            }

            public String getName()
            {
                return "Customized style";
            }

            public String getTitle()
            {
                return NAME;
            }
        };
    }

    /**
     * Overwrite or implement method getIconSet()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getIconSet()
     */
    public IconSet getIconSet()
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
     * Overwrite or implement method getStyleSheet()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getStyle()
     */
    public Style getStyle()
    {
        return style;
    }

    /**
     * Overwrite or implement method getStyleSheetName()
     *
     * @see com.cyclopsgroup.waterview.spi.Theme#getStyleName()
     */
    public String getStyleName()
    {
        return NAME;
    }
}
