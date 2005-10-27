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

import com.cyclopsgroup.tornado.persist.PersistenceManager;
import com.cyclopsgroup.tornado.security.entity.SecurityEntityManager;
import com.cyclopsgroup.tornado.security.entity.User;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.Parameters;
import com.cyclopsgroup.waterview.RuntimeData;
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

        Parameters params = data.getParameters();
        String name = params.getString( "name" );
        boolean failed = false;
        SecurityEntityManager sem = (SecurityEntityManager) lookup( SecurityEntityManager.ROLE );
        if ( sem.findUserByName( name ) != null )
        {
            context.error( "user", "User [" + name + " already exists, try another one" );
            failed = true;
        }

        String password = params.getString( "privatePassword" );
        if ( !password.equals( params.getString( "confirmedPassword" ) ) )
        {
            context.error( "confirmedPassword", "Two passwords are not the same" );
            failed = true;
        }

        if ( failed )
        {
            return;
        }

        PersistenceManager persist = (PersistenceManager) lookup( PersistenceManager.ROLE );
        User user = (User) persist.create( User.class );
        TypeUtils.getBeanUtils().copyProperties( user, params.toProperties() );
        user.setCountry( data.getLocale().getCountry() );
        user.setLanguage( data.getLocale().getLanguage() );
        persist.saveNew( user );

        context.addMessage( "User " + user.getDisplayName() + " is created" );
    }
}
