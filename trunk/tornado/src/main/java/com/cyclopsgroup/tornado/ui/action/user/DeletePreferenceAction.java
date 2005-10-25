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
import com.cyclopsgroup.tornado.portal.UserPreference;
import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.tornado.security.SecurityService;
import com.cyclopsgroup.tornado.security.entity.User;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class DeletePreferenceAction
    extends BaseServiceable
    implements Action
{

    /**
     * Override method execute in class DeletePreferenceAction
     *
     * @see com.cyclopsgroup.waterview.Action#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.ActionContext)
     */
    public void execute( RuntimeData data, ActionContext context )
        throws Exception
    {
        String prefId = data.getParameters().getString( "preference_id" );
        PersistenceManager persist = (PersistenceManager) lookupComponent( PersistenceManager.ROLE );
        UserPreference up = (UserPreference) persist.load( UserPreference.class, prefId );
        persist.delete( up );
        User user = (User) persist.load( User.class, up.getUserId() );
        SecurityService security = (SecurityService) lookupComponent( SecurityService.ROLE );
        security.refreshUser( user.getName() );
        security.refreshUser( RuntimeUser.getInstance( data ).getName() );
        context.addMessage( "User setting is deleted, default setting is applied" );
    }
}
