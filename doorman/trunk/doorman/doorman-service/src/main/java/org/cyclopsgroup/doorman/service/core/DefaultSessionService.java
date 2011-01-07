package org.cyclopsgroup.doorman.service.core;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cyclopsgroup.caff.util.UUIDUtils;
import org.cyclopsgroup.doorman.api.SessionService;
import org.cyclopsgroup.doorman.api.User;
import org.cyclopsgroup.doorman.api.UserOperationResult;
import org.cyclopsgroup.doorman.api.UserSession;
import org.cyclopsgroup.doorman.api.UserSessionAttributes;
import org.cyclopsgroup.doorman.api.UserSignUpResponse;
import org.cyclopsgroup.doorman.service.dao.DAOFactory;
import org.cyclopsgroup.doorman.service.dao.DataOperationException;
import org.cyclopsgroup.doorman.service.dao.UserDAO;
import org.cyclopsgroup.doorman.service.dao.UserSessionDAO;
import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.cyclopsgroup.doorman.service.storage.StoredUserSession;
import org.cyclopsgroup.doorman.service.storage.StoredUserSignUpRequest;
import org.cyclopsgroup.doorman.service.storage.StoredUserState;
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
@Service
public class DefaultSessionService
    implements SessionService
{
    private static final Log LOG = LogFactory.getLog( DefaultSessionService.class );

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
            user = userDao.createUser( token );
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
    public UserSignUpResponse requestSignUp( String sessionId, User user )
    {
        StoredUser existingUser = userDao.findByName( user.getUserName() );
        if ( existingUser != null )
        {
            return new UserSignUpResponse( UserOperationResult.IDENTITY_EXISTED, null );
        }
        StoredUserSignUpRequest request = new StoredUserSignUpRequest();
        request.setDisplayName( user.getDisplayName() );
        request.setEmailAddress( user.getEmailAddress() );
        request.setPassword( user.getPassword() );
        String id = UUIDUtils.randomStringId();
        request.setRequestId( id );
        request.setRequestToken( id );
        request.setUserName( user.getUserName() );
        request.setDomainName( "cyclopsgroup.org" );
        userDao.saveSignupRequest( request );

        LOG.info( "Sign up request " + id + " is saved" );
        return new UserSignUpResponse( UserOperationResult.SUCCESSFUL, id );
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

    /**
     * @inheritDoc
     */
    @Override
    @Transactional( isolation = Isolation.SERIALIZABLE )
    public UserOperationResult signUp( String sessionId, User user )
    {
        StoredUser existing = userDao.findByName( user.getUserName() );
        if ( existing != null )
        {
            return UserOperationResult.IDENTITY_EXISTED;
        }

        String uid = UUIDUtils.randomStringId();
        StoredUser u = new StoredUser();
        u.setDisplayName( user.getDisplayName() );
        u.setEmailAddress( user.getEmailAddress() );
        u.setDomainName( user.getDomainName() );
        u.setPassword( user.getPassword() );
        u.setUserId( uid );
        u.setUserName( user.getUserName() );
        u.setUserState( StoredUserState.ACTIVE );
        userDao.createUser( u );
        userSessionDao.updateUser( sessionId, u );
        return UserOperationResult.SUCCESSFUL;
    }
}
