package org.cyclopsgroup.doorman.service.hibernate;

import java.util.Date;
import java.util.List;

import org.cyclopsgroup.doorman.api.UserOperationResult;
import org.cyclopsgroup.doorman.service.dao.UserDAO;
import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.cyclopsgroup.doorman.service.storage.StoredUserSignupRequest;
import org.cyclopsgroup.doorman.service.storage.StoredUserState;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

@Service
public class HibernateUserDAO
    extends HibernateDaoSupport
    implements UserDAO
{
    @Autowired
    public HibernateUserDAO( SessionFactory sessionFactory )
    {
        setSessionFactory( sessionFactory );
    }

    /**
     * @inheritDoc
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public StoredUser createUser( String requestToken, String domainName )
    {
        List<StoredUserSignupRequest> requests =
            getHibernateTemplate().findByNamedQuery( StoredUserSignupRequest.QUERY_BY_TOKEN,
                                                     new Object[] { requestToken } );
        if ( requests.isEmpty() )
        {
            throw new DataOperationException( UserOperationResult.NO_SUCH_IDENTITY, "Token " + requestToken
                + " doesn't exist" );
        }
        StoredUserSignupRequest request = requests.get( 0 );
        List<StoredUser> users =
            getHibernateTemplate().findByNamedQuery( StoredUser.QUERY_BY_NAME, new Object[] { request.getUserName() } );
        if ( !users.isEmpty() )
        {
            throw new DataOperationException( UserOperationResult.IDENTITY_EXISTED, "User " + request.getUserName()
                + " already exists" );
        }
        StoredUser user = new StoredUser();
        user.setDisplayName( request.getDisplayName() );
        user.setDomainName( domainName );
        user.setEmailAddress( request.getEmailAddress() );
        user.setLastModified( new Date() );
        user.setPassword( request.getPassword() );
        user.setUserId( request.getRequestId() );
        user.setUserName( request.getUserName() );
        user.setUserState( StoredUserState.ACTIVE );
        getHibernateTemplate().save( user );
        getHibernateTemplate().deleteAll( requests );
        return user;
    }

    /**
     * @inheritDoc
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public StoredUser findByName( String userName )
    {
        List<StoredUser> users = getHibernateTemplate().findByNamedQuery( StoredUser.QUERY_BY_NAME, userName );
        return users.isEmpty() ? null : users.get( 0 );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void saveSignupRequest( StoredUserSignupRequest request )
    {
        getHibernateTemplate().save( request );
    }
}
