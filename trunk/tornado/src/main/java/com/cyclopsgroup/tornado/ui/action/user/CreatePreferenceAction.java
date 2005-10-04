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
package com.cyclopsgroup.tornado.ui.action.user;

import com.cyclopsgroup.tornado.persist.PersistenceManager;
import com.cyclopsgroup.tornado.portal.PortalService;
import com.cyclopsgroup.tornado.portal.UserPreference;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.Theme;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class CreatePreferenceAction
    extends BaseServiceable
    implements Action
{
    /**
     * Override method execute in class CreatePreferenceAction
     *
     * @see com.cyclopsgroup.waterview.Action#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.ActionContext)
     */
    public void execute( RuntimeData data, ActionContext context )
        throws Exception
    {
        PersistenceManager persist = (PersistenceManager) lookupComponent( PersistenceManager.ROLE );
        String userId = data.getParams().getString( "user_id" );
        UserPreference up = (UserPreference) persist.create( UserPreference.class );
        up.setUserId( userId );
        up.setThemeName( PortalService.UNSET_THEME_NAME );

        LookAndFeelService laf = (LookAndFeelService) lookupComponent( LookAndFeelService.ROLE );
        Theme theme = laf.getTheme( data.getThemeName() );
        up.setIconset( theme.getIconSetName() );
        up.setStylesheet( theme.getStyleSheetName() );

        persist.saveNew( up );
        context.addMessage( "User setting is created" );
    }
}
