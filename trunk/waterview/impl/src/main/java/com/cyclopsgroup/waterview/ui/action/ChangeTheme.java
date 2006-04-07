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

import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.NoSuchLookAndFeelException;
import com.cyclopsgroup.waterview.spi.Theme;
import com.cyclopsgroup.waterview.spi.LookAndFeelService.Style;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Action to change theme
 */
public class ChangeTheme
    extends BaseServiceable
    implements Action
{
    private static final String CUSTOM_THEME = "custom";

    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Action#execute(com.cyclopsgroup.waterview.RunData, com.cyclopsgroup.waterview.ActionContext)
     */
    public void execute( RunData data, ActionContext context )
        throws Exception
    {
        String themeName = data.getParameters().getString( "theme_name" );
        final LookAndFeelService laf = (LookAndFeelService) lookup( LookAndFeelService.ROLE );
        Theme theme = null;
        if ( themeName.equals( CUSTOM_THEME ) )
        {
            final String styleName = data.getParameters().getString( "style_name" );
            //final String iconSetName = data.getParameters().getString(
            //       "iconset_name");

            theme = new ThemeProxy( laf.getDefaultTheme() )
            {
                /**
                 * Overwrite or implement method getName()
                 *
                 * @see com.cyclopsgroup.waterview.ui.action.ThemeProxy#getName()
                 */
                public String getName()
                {
                    return CUSTOM_THEME;
                }

                /**
                 * Overwrite or implement method getStyle()
                 *
                 * @see com.cyclopsgroup.waterview.spi.Theme#getStyle()
                 */
                public Style getStyle()
                    throws NoSuchLookAndFeelException
                {
                    return laf.getStyle( styleName );
                }

                /**
                 * Overwrite or implement method getStyleSheetName()
                 *
                 * @see com.cyclopsgroup.waterview.ui.action.ThemeProxy#getStyleName()
                 */
                public String getStyleName()
                {
                    return styleName;
                }
            };
        }
        else
        {
            theme = laf.getTheme( themeName );
        }
        laf.setRuntimeTheme( data, theme );

        context.addMessage( "Theme for current session is changed" );
    }
}
