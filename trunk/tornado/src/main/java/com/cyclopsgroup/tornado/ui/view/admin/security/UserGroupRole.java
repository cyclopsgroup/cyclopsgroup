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
package com.cyclopsgroup.tornado.ui.view.admin.security;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.cyclopsgroup.tornado.hibernate.HibernateService;
import com.cyclopsgroup.tornado.security.entity.Group;
import com.cyclopsgroup.tornado.security.entity.User;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Module for group/role
 */
public class UserGroupRole
    extends BaseServiceable
    implements Module
{
    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Module#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.Context)
     */
    public void execute( RuntimeData data, Context context )
        throws Exception
    {
        HibernateService hib = (HibernateService) lookupComponent( HibernateService.ROLE );
        Session s = hib.getSession();
        String userId = data.getParams().getString( "user_id" );
        User user = (User) s.load( User.class, userId );
        context.put( "userObject", user );
        Set userGroups = user.getGroups();
        context.put( "userGroups", userGroups );

        Set userGroupIds = new HashSet();
        for ( Iterator i = userGroups.iterator(); i.hasNext(); )
        {
            Group group = (Group) i.next();
            userGroupIds.add( group.getId() );
        }
        List restGroups = s.createCriteria( Group.class ).add( Expression.eq( "isDisabled", Boolean.FALSE ) ).list();
        for ( Iterator i = restGroups.iterator(); i.hasNext(); )
        {
            Group group = (Group) i.next();
            if ( userGroupIds.contains( group.getId() ) )
            {
                i.remove();
                //restGroups.remove( group );
            }
        }
        context.put( "restGroups", restGroups );
    }
}
