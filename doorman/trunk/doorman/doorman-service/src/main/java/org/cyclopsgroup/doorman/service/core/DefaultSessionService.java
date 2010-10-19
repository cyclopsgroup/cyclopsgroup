package org.cyclopsgroup.doorman.service.core;

import org.cyclopsgroup.doorman.api.SessionService;
import org.cyclopsgroup.doorman.api.User;
import org.cyclopsgroup.doorman.api.UserSession;
import org.cyclopsgroup.doorman.api.UserSessionAttributes;
import org.cyclopsgroup.doorman.service.dao.DAOFactory;
import org.cyclopsgroup.doorman.service.dao.UserSessionDAO;

/**
 * Default implementation of session service
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class DefaultSessionService
    implements SessionService
{
    /**
     * @param daoFactory Factory instance that creates necessary DAOs
     */
    public DefaultSessionService( DAOFactory daoFactory )
    {
        this.userSessionDao = daoFactory.createUserSessionDAO();
    }

    private final UserSessionDAO userSessionDao;

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
