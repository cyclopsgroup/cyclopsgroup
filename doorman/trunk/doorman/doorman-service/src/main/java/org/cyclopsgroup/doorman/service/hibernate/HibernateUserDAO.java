package org.cyclopsgroup.doorman.service.hibernate;

import java.util.Date;
import java.util.List;

import org.cyclopsgroup.doorman.api.UserOperationResult;
import org.cyclopsgroup.doorman.service.dao.DataOperationException;
import org.cyclopsgroup.doorman.service.dao.UserDAO;
import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.cyclopsgroup.doorman.service.storage.StoredUserSignUpRequest;
import org.cyclopsgroup.doorman.service.storage.StoredUserState;
import org.cyclopsgroup.doorman.service.storage.StoredUserType;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Hibernate based implementation of user DAO
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
class HibernateUserDAO
    extends HibernateDaoSupport
    implements UserDAO
{
    /**
     * @param sessionFactory Hibernate session factory
     */
    HibernateUserDAO( SessionFactory sessionFactory )
    {
        setSessionFactory( sessionFactory );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void createUser( StoredUser user )
    {
        Date now = new DateTime().withZone( DateTimeZone.UTC ).toDate();
        user.setLastModified( now );
        user.setCreationDate( now );
        user.setUserType( StoredUserType.LOCAL );
        getHibernateTemplate().save( user );
    }

    /**
     * @inheritDoc
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public StoredUser createUser( String requestToken )
    {
        List<StoredUserSignUpRequest> requests =
            getHibernateTemplate().findByNamedQuery( StoredUserSignUpRequest.QUERY_BY_TOKEN, requestToken );
        if ( requests.isEmpty() )
        {
            throw new DataOperationException( UserOperationResult.NO_SUCH_IDENTITY, "Token " + requestToken
                + " doesn't exist" );
        }
        StoredUserSignUpRequest request = requests.get( 0 );
        List<StoredUser> users =
            getHibernateTemplate().findByNamedQuery( StoredUser.QUERY_BY_NAME_OR_ID,
                                                     new Object[] { request.getUserName(), null } );
        if ( !users.isEmpty() )
        {
            throw new DataOperationException( UserOperationResult.IDENTITY_EXISTED, "User " + request.getUserName()
                + " already exists" );
        }
        StoredUser user = new StoredUser();
        user.setDisplayName( request.getDisplayName() );
        user.setDomainName( request.getDomainName() );
        user.setEmailAddress( request.getEmailAddress() );
        user.setLastModified( new Date() );
        user.setPassword( request.getPassword() );
        user.setUserId( request.getRequestId() );
        user.setUserName( request.getUserName() );
        user.setUserState( StoredUserState.ACTIVE );
        user.setUserType( StoredUserType.LOCAL );
        user.setCreationDate( new DateTime().withZone( DateTimeZone.UTC ).toDate() );
        getHibernateTemplate().save( user );
        getHibernateTemplate().deleteAll( requests );
        return user;
    }

    /**
     * @inheritDoc
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public StoredUser findByNameOrId( String nameOrId )
    {
        List<StoredUser> users =
            getHibernateTemplate().findByNamedQuery( StoredUser.QUERY_BY_NAME_OR_ID,
                                                     new Object[] { nameOrId, nameOrId } );
        return users.isEmpty() ? null : users.get( 0 );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void saveSignupRequest( StoredUserSignUpRequest request )
    {
        request.setRequestDate( new DateTime().withZone( DateTimeZone.UTC ).toDate() );
        getHibernateTemplate().save( request );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void saveUser( StoredUser user )
    {
        getHibernateTemplate().update( user );
    }
}
