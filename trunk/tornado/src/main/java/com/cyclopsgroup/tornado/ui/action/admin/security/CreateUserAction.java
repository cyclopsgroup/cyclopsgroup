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

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.cyclopsgroup.tornado.hibernate.HibernateService;
import com.cyclopsgroup.tornado.security.entity.User;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.ValueParser;
import com.cyclopsgroup.waterview.utils.TypeUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class CreateUserAction
    extends BaseServiceable
    implements Action
{

    /**
     * Override method execute in class CreateUserAction
     *
     * @see com.cyclopsgroup.waterview.Action#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.ActionContext)
     */
    public void execute( RuntimeData data, ActionContext context )
        throws Exception
    {
        HibernateService hibernate = (HibernateService) lookupComponent( HibernateService.ROLE );
        ValueParser params = data.getParams();
        String name = params.getString( "name" );
        boolean failed = false;
        Session s = hibernate.getSession();
        List existingUsers = s.createCriteria( User.class ).add( Expression.eq( "isDisabled", Boolean.FALSE ) )
            .add( Expression.eq( "name", name ) ).setMaxResults( 1 ).list();
        if ( !existingUsers.isEmpty() )
        {
            context.error( "user", "User [" + name + " already exists, try another one" );
            failed = true;
        }

        String password = params.getString( "password" );
        if ( !password.equals( params.getString( "confirmed_password" ) ) )
        {
            context.error( "confirmed_password", "Two passwords are not the same" );
            failed = true;
        }

        if ( failed )
        {
            return;
        }

        User user = new User();
        TypeUtils.getBeanUtils().copyProperties( user, params.toProperties() );
        s.save( user );

        context.addMessage( "User " + name + " is created" );
    }
}
