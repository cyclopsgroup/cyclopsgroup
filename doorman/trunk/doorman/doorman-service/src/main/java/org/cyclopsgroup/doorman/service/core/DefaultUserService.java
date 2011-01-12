package org.cyclopsgroup.doorman.service.core;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;
import org.cyclopsgroup.doorman.api.User;
import org.cyclopsgroup.doorman.api.UserOperationResult;
import org.cyclopsgroup.doorman.api.UserService;
import org.cyclopsgroup.doorman.service.dao.DAOFactory;
import org.cyclopsgroup.doorman.service.dao.UserDAO;
import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Server side implementation of user service
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@Service
public class DefaultUserService
    implements UserService
{
    private final UserDAO userDao;

    /**
     * @param daoFactory DAO factory that creates DAOs
     */
    @Autowired
    public DefaultUserService( DAOFactory daoFactory )
    {
        userDao = daoFactory.createUserDAO();
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional( isolation = Isolation.READ_COMMITTED, readOnly = true )
    public UserOperationResult authenticate( String userName, String secureCredential )
    {
        StoredUser u = userDao.findByName( userName );
        if ( u == null )
        {
            return UserOperationResult.NO_SUCH_IDENTITY;
        }
        String password = u.getPassword();
        if ( StringUtils.equals( password, secureCredential ) )
        {
            return UserOperationResult.SUCCESSFUL;
        }
        return UserOperationResult.AUTHENTICATION_FAILURE;
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional( isolation = Isolation.READ_COMMITTED, readOnly = true )
    public User getUser( String userName )
    {
        StoredUser u = userDao.findByName( userName );
        if ( u == null )
        {
            throw new WebApplicationException( Response.status( Status.NOT_FOUND ).entity(
                                                                                           "User " + userName
                                                                                               + " not found" ).build() );
        }
        User user = new User();
        user.setDisplayName( u.getDisplayName() );
        user.setDomainName( u.getDomainName() );
        user.setEmailAddress( u.getEmailAddress() );
        user.setUserId( u.getUserId() );
        user.setUserName( u.getUserName() );
        return user;
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional( isolation = Isolation.READ_COMMITTED, readOnly = true )
    public UserOperationResult ping( String userName )
    {
        StoredUser u = userDao.findByName( userName );
        if ( u == null )
        {
            return UserOperationResult.NO_SUCH_IDENTITY;
        }
        return UserOperationResult.SUCCESSFUL;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void update( String userName, User user )
    {
        StoredUser u = userDao.findByName( userName );
        if ( u == null )
        {
            throw new WebApplicationException( Response.status( Status.NOT_FOUND ).entity(
                                                                                           "User " + userName
                                                                                               + " not found" ).build() );
        }
        u.setDisplayName( user.getDisplayName() );
        u.setEmailAddress( user.getEmailAddress() );
        u.setLastModified( new DateTime().toDateTime( DateTimeZone.UTC ).toDate() );
        u.setTimeZoneId( user.getTimeZoneId() );
        userDao.saveUser( u );
    }
}
