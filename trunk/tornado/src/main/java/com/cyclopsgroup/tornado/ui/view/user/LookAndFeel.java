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
package com.cyclopsgroup.tornado.ui.view.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.tornado.portal.PortalService;
import com.cyclopsgroup.tornado.portal.UserPreference;
import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.DefaultSelectOption;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.Theme;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class LookAndFeel
    extends BaseServiceable
    implements Module
{
    /**
     * Override method execute in class LookAndFeel
     *
     * @see com.cyclopsgroup.waterview.Module#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.Context)
     */
    public void execute( RuntimeData data, Context context )
        throws Exception
    {
        String userId = (String) context.get( "userId" );
        if ( StringUtils.isEmpty( userId ) )
        {
            userId = RuntimeUser.getInstance( data ).getId();
        }

        context.put( "userId", userId );

        PortalService portal = (PortalService) lookup( PortalService.ROLE );
        UserPreference up = portal.findUserPreference( userId );
        context.put( "noPreference", new Boolean( up == null ) );
        if ( up == null )
        {
            return;
        }
        context.put( "pref", up );

        LookAndFeelService laf = (LookAndFeelService) lookup( LookAndFeelService.ROLE );
        List themeSelection = new ArrayList();
        String[] themeNames = laf.getThemeNames();
        for ( int i = 0; i < themeNames.length; i++ )
        {
            String themeName = themeNames[i];
            Theme theme = laf.getTheme( themeName );
            DefaultSelectOption dso = new DefaultSelectOption( themeName, theme.getDescription() );
            themeSelection.add( dso );
        }
        context.put( "themes", themeSelection );

        String[] iconSetNames = laf.getIconSetNames();
        List iconSets = new ArrayList();
        for ( int i = 0; i < iconSetNames.length; i++ )
        {
            String name = iconSetNames[i];
            DefaultSelectOption dso = new DefaultSelectOption( name, laf.getIconSet( name ).getDescription() );
            iconSets.add( dso );
        }
        context.put( "iconSets", iconSets );

        String[] styleSheetNames = laf.getStyleSheetNames();
        List styleSheets = new ArrayList();
        for ( int i = 0; i < styleSheetNames.length; i++ )
        {
            String name = styleSheetNames[i];
            DefaultSelectOption dso = new DefaultSelectOption( name, laf.getStyleSheet( name ).getDescription() );
            styleSheets.add( dso );
        }
        context.put( "styleSheets", styleSheets );
    }
}