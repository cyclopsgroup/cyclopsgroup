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
package com.cyclopsgroup.waterview.ui.view.system.status;

import java.util.ArrayList;
import java.util.List;

import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.CollectionLargeList;
import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.SelectOption;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.Theme;
import com.cyclopsgroup.waterview.spi.LookAndFeelService.Style;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * View to set look and feel
 */
public class SetLookAndFeel
    extends BaseServiceable
    implements Module
{
    /**
     * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
     *
     * Internal fake theme item
     */
    public class Item
    {
        /**
         * Get description
         *
         * @return Item description
         */
        public String getDescription()
        {
            return "Customized theme";
        }

        /**
         * Get name
         *
         * @return Item name
         */
        public String getName()
        {
            return "custom";
        }
    }

    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Module#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.Context)
     */
    public void execute( RuntimeData data, Context context )
        throws Exception
    {
        LookAndFeelService laf = (LookAndFeelService) lookup( LookAndFeelService.ROLE );
        context.put( "defaultTheme", laf.getDefaultTheme() );

        String[] themeNames = laf.getThemeNames();
        List themes = new ArrayList();
        for ( int i = 0; i < themeNames.length; i++ )
        {
            String themeName = themeNames[i];
            themes.add( laf.getTheme( themeName ) );
        }
        themes.add( new Item() );
        context.put( "themeData", new CollectionLargeList( themes ) );
        Theme currentTheme = laf.getRuntimeTheme( data );
        context.put( "currentTheme", currentTheme );

        String[] styleNames = laf.getStyleNames();
        List styles = new ArrayList();
        for ( int i = 0; i < styleNames.length; i++ )
        {
            final String styleName = styleNames[i];
            final Style style = laf.getStyle( styleName );
            styles.add( new SelectOption()
            {

                public String getName()
                {
                    return styleName;
                }

                public String getTitle()
                {
                    return style.getDescription();
                }
            } );
        }
        context.put( "styles", styles );
    }
}
