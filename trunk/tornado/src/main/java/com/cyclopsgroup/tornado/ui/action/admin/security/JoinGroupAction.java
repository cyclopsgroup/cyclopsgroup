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
package com.cyclopsgroup.tornado.ui.action.admin.security;

import org.hibernate.Session;

import com.cyclopsgroup.tornado.hibernate.HibernateService;
import com.cyclopsgroup.tornado.security.SecurityService;
import com.cyclopsgroup.tornado.security.entity.User;
import com.cyclopsgroup.tornado.security.entity.UserGroup;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Action for user to join a group
 */
public class JoinGroupAction
    extends BaseServiceable
    implements Action
{
    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Action#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.ActionContext)
     */
    public void execute( RuntimeData data, ActionContext context )
        throws Exception
    {
        String[] groupIds = data.getParams().getStrings( "group_to_join" );
        if ( groupIds.length == 0 )
        {
            return;
        }
        String userId = data.getParams().getString( "user_id" );

        HibernateService hib = (HibernateService) lookupComponent( HibernateService.ROLE );
        Session s = hib.getSession();
        User user = (User) s.load( User.class, userId );
        for ( int i = 0; i < groupIds.length; i++ )
        {
            String groupId = groupIds[i];
            UserGroup ug = new UserGroup();
            ug.setUserId( userId );
            ug.setGroupId( groupId );
            s.save( ug );
        }
        hib.commitTransaction();
        SecurityService security = (SecurityService) lookupComponent( SecurityService.ROLE );
        security.refreshUser( user.getName() );
        context.addMessage( "User " + user.getName() + " joined " + groupIds.length + " groups" );
    }
}