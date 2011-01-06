package org.cyclopsgroup.doorman.service.core;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.cyclopsgroup.doorman.api.User;
import org.cyclopsgroup.doorman.api.UserOperationResult;
import org.cyclopsgroup.doorman.api.UserService;
import org.cyclopsgroup.doorman.service.dao.DAOFactory;
import org.cyclopsgroup.doorman.service.dao.UserDAO;
import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserOperationResult authenticate( String userName, String secureCredential )
    {
        StoredUser u = userDao.findByName( userName );
        if ( u == null )
        {
            throw new WebApplicationException( Response.status( Status.NOT_FOUND ).entity(
                                                                                           "User " + userName
                                                                                               + " not found" ).build() );
        }
        String password = u.getPassword();


        return UserOperationResult.SUCCESSFUL;
    }

    /**
     * @inheritDoc
     */
    @Override
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

}
