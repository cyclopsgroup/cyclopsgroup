package org.cyclopsgroup.doorman.service.core;

import org.apache.commons.lang.Validate;
import org.cyclopsgroup.doorman.api.SessionService;
import org.cyclopsgroup.doorman.api.User;
import org.cyclopsgroup.doorman.api.UserSession;
import org.cyclopsgroup.doorman.api.UserSessionAttributes;
import org.cyclopsgroup.doorman.service.dao.DAOFactory;
import org.cyclopsgroup.doorman.service.dao.UserSessionDAO;
import org.cyclopsgroup.doorman.service.storage.StoredUserSession;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of session service
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@Service( DefaultSessionService.SERVICE_NAME )
public class DefaultSessionService
    implements SessionService
{
    private static final String SERVICE_NAME = "org.cyclopsgroup.doorman.api.SessionService";

    /**
     * @param daoFactory Factory instance that creates necessary DAOs
     */
    @Autowired
    public DefaultSessionService( DAOFactory daoFactory )
    {
        this.userSessionDao = daoFactory.createUserSessionDAO();
    }

    private final UserSessionDAO userSessionDao;

    /**
     * @inheritDoc
     */
    @Override
    @Transactional( isolation = Isolation.READ_COMMITTED, readOnly = true )
    public UserSession getSession( String sessionId )
    {
        StoredUserSession s = userSessionDao.findById( sessionId );
        if ( s == null )
        {
            return null;
        }
        UserSession session = new UserSession();
        session.setCreationDate( new DateTime( s.getCreationDate(), DateTimeZone.UTC ) );
        session.setLastActivity( new DateTime( s.getLastModified(), DateTimeZone.UTC ) );
        session.setSessionId( sessionId );
        UserSessionAttributes attributes = new UserSessionAttributes();
        attributes.setAcceptLanguage( s.getAcceptLanguage() );
        attributes.setIpAddress( s.getIpAddress() );
        attributes.setUserAgent( s.getUserAgent() );
        session.setAttributes( attributes );
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

    /**
     * @inheritDoc
     */
    @Override
    @Transactional( isolation = Isolation.READ_COMMITTED )
    public UserSession startSession( String sessionId, UserSessionAttributes attributes )
    {
        Validate.notNull( sessionId, "Session ID can't be NULL" );

        DateTime now = new DateTime( DateTimeZone.UTC );
        StoredUserSession s = new StoredUserSession();
        s.setSessionId( sessionId );
        s.setCreationDate( now.toDate() );
        s.setLastModified( now.toDate() );
        if ( attributes != null )
        {
            s.setAcceptLanguage( attributes.getAcceptLanguage() );
            s.setIpAddress( attributes.getIpAddress() );
            s.setUserAgent( attributes.getUserAgent() );
        }
        userSessionDao.createNew( s );

        UserSession session = new UserSession();
        session.setAttributes( attributes );
        session.setSessionId( sessionId );
        session.setCreationDate( now );
        session.setLastActivity( now );
        return session;
    }

    @Override
    public void confirmSignUp( String sessionId, String token )
    {
        // TODO Auto-generated method stub

    }
}
