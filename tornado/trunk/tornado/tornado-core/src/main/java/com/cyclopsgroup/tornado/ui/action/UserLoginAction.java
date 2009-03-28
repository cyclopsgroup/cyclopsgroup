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
package com.cyclopsgroup.tornado.ui.action;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.tornado.security.RuntimeUser;
import com.cyclopsgroup.tornado.security.SecurityService;
import com.cyclopsgroup.tornado.security.UserAuthenticationResult;
import com.cyclopsgroup.tornado.security.UserAuthenticator;
import com.cyclopsgroup.tornado.security.UserChangedEvent;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Action to do user login
 */
public class UserLoginAction
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
        String userName = data.getParameters().getString( "username" );
        String password = data.getParameters().getString( "password" );

        UserAuthenticator auth = (UserAuthenticator) lookup( UserAuthenticator.ROLE );
        UserAuthenticationResult result = auth.authenticate( userName, password );

        if ( result == UserAuthenticationResult.NO_SUCH_USER )
        {
            context.error( "username", "No such user" );
            return;
        }

        if ( result == UserAuthenticationResult.WRONG_PASSWORD )
        {
            context.error( "password", "Wrong password" );
            return;
        }

        if ( result != UserAuthenticationResult.SUCCESS )
        {
            context.fail( "Login failed " + result );
            context.addMessage( "Login failed " + result );
            return;
        }
        int timeout = data.getParameters().getInt( "timeout", 30 );
        SecurityService security = (SecurityService) lookup( SecurityService.ROLE );
        RuntimeUser user = (RuntimeUser) security.login( userName, data.getSessionId(), timeout * 60000L );
        security.handleEvent( new UserChangedEvent( user, data ) );

        String url = data.getParameters().getString( "redirectto" );
        if ( StringUtils.isEmpty( url ) )
        {
            url = data.getRefererUrl();
        }
        context.setTargetUrl( url );
        context.addMessage( "User " + userName + " just logged in" );
    }
}
