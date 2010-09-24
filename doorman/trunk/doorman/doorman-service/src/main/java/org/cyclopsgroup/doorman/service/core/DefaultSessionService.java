package org.cyclopsgroup.doorman.service.core;

import org.cyclopsgroup.doorman.api.SessionService;
import org.cyclopsgroup.doorman.api.User;
import org.cyclopsgroup.doorman.api.UserSession;
import org.cyclopsgroup.doorman.api.UserSessionAttributes;

public class DefaultSessionService
    implements SessionService
{
    @Override
    public UserSession getSession( String sessionId )
    {
        UserSession session = new UserSession();
        return session;
    }

    @Override
    public void signIn( String sessionId, String user, String password )
    {
    }

    @Override
    public void signOut( String sessionId )
    {
    }

    @Override
    public void signUp( String sessionId, User user )
    {
    }

    @Override
    public UserSession startSession( String sessionId, UserSessionAttributes attributes )
    {
        UserSession session = new UserSession();

        return session;
    }

    @Override
    public void confirmSignUp( String sessionId, String token )
    {
        // TODO Auto-generated method stub

    }
}
