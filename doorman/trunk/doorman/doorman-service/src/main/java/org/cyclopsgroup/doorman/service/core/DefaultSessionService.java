package org.cyclopsgroup.doorman.service.core;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.doorman.api.SessionService;
import org.cyclopsgroup.doorman.api.User;
import org.cyclopsgroup.doorman.api.UserOperationResult;
import org.cyclopsgroup.doorman.api.UserSession;
import org.cyclopsgroup.doorman.api.UserSessionAttributes;
import org.cyclopsgroup.doorman.service.dao.DAOFactory;
import org.cyclopsgroup.doorman.service.dao.UserDAO;
import org.cyclopsgroup.doorman.service.dao.UserSessionDAO;
import org.cyclopsgroup.doorman.service.hibernate.DataOperationException;
import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.cyclopsgroup.doorman.service.storage.StoredUserSession;
import org.cyclopsgroup.doorman.service.storage.StoredUserSignupRequest;
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
    private static final Log LOG = LogFactory.getLog( DefaultSessionService.class );

    static final String SERVICE_NAME = "org.cyclopsgroup.doorman.api.SessionService";

    private final UserDAO userDao;

    private final UserSessionDAO userSessionDao;

    /**
     * @param daoFactory Factory instance that creates necessary DAOs
     */
    @Autowired
    public DefaultSessionService( DAOFactory daoFactory )
    {
        this.userSessionDao = daoFactory.createUserSessionDAO();
        this.userDao = daoFactory.createUserDAO();
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional( isolation = Isolation.SERIALIZABLE )
    public UserOperationResult confirmSignUp( String sessionId, String token )
    {
        StoredUser user;
        try
        {
            user = userDao.createUser( token, null );
        }
        catch ( DataOperationException e )
        {
            LOG.error( "Can't create user with token " + token, e );
            return e.getOperationResult();
        }
        userSessionDao.updateUser( sessionId, user );
        return UserOperationResult.SUCCESSFUL;
    }

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

        // Set attributes
        UserSessionAttributes attributes = new UserSessionAttributes();
        attributes.setAcceptLanguage( s.getAcceptLanguage() );
        attributes.setIpAddress( s.getIpAddress() );
        attributes.setUserAgent( s.getUserAgent() );
        session.setAttributes( attributes );

        // Set user
        StoredUser u = s.getUser();
        if ( u != null )
        {
            User user = new User();
            user.setDisplayName( u.getDisplayName() );
            user.setDomainName( u.getDomainName() );
            user.setEmailAddress( u.getEmailAddress() );
            user.setUserId( u.getUserId() );
            user.setUserName( u.getUserName() );
            session.setUser( user );
        }
        return session;
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional( isolation = Isolation.SERIALIZABLE )
    public UserOperationResult signIn( String sessionId, String userName, String password )
    {
        StoredUser u = userDao.findByName( userName );
        if ( u == null )
        {
            return UserOperationResult.NO_SUCH_IDENTITY;
        }
        if ( !StringUtils.equals( password, u.getPassword() ) )
        {
            return UserOperationResult.AUTHENTICATION_FAILURE;
        }
        userSessionDao.updateUser( sessionId, u );
        return UserOperationResult.SUCCESSFUL;
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional( isolation = Isolation.READ_COMMITTED )
    public UserOperationResult signOut( String sessionId )
    {
        userSessionDao.updateUser( sessionId, null );
        return UserOperationResult.SUCCESSFUL;
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional( isolation = Isolation.SERIALIZABLE )
    public UserOperationResult signUp( String sessionId, User user )
    {
        StoredUser existingUser = userDao.findByName( user.getUserName() );
        if ( existingUser != null )
        {
            return UserOperationResult.IDENTITY_EXISTED;
        }
        StoredUserSignupRequest request = new StoredUserSignupRequest();
        request.setDisplayName( user.getDisplayName() );
        request.setEmailAddress( user.getEmailAddress() );
        request.setPassword( user.getPassword() );
        request.setRequestDate( new Date() );
        String id = UUID.randomUUID().toString();
        request.setRequestId( id );
        request.setRequestToken( id );
        request.setUserName( user.getUserName() );
        userDao.saveSignupRequest( request );

        LOG.info( "Sign up request " + id + " is saved" );
        return UserOperationResult.SUCCESSFUL;
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional( isolation = Isolation.SERIALIZABLE )
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
}
