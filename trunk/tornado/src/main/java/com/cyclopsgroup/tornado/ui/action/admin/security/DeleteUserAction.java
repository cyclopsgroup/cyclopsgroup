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
import com.cyclopsgroup.tornado.security.entity.User;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Action to disabled users
 */
public class DeleteUserAction
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
        String[] userIds = data.getParams().getStrings( "user_id" );
        HibernateService hib = (HibernateService) lookupComponent( HibernateService.ROLE );
        Session s = hib.getSession();
        int count = 0;
        for ( int i = 0; i < userIds.length; i++ )
        {
            String userId = userIds[i];
            User user = (User) s.load( User.class, userId );
            if ( user.getIsSystem() || user.getIsDisabled() )
            {
                continue;
            }
            user.setIsDisabled( true );
            s.update( user );
            count++;
        }
        context.addMessage( count + " users are disabled" );
    }
}
