package com.cyclopsgroup.laputa.identity.db;

import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cyclopsgroup.laputa.identity.Identity;
import com.cyclopsgroup.laputa.identity.NoSuchUserException;
import com.cyclopsgroup.laputa.identity.spi.AbstractVolatileIdentityService;
import com.cyclopsgroup.laputa.identity.spi.SimpleIdentity;

public class DBIdentityService
    extends AbstractVolatileIdentityService
{
    private SessionFactory sessionFactory;

    public DBIdentityService( SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }

    public AuthenticationResult authenticate( String userName, String password )
    {
        User user = findUser( userName );
        if ( user == null )
        {
            return AuthenticationResult.NO_SUCH_USER;
        }
        //TODO compare password
        if ( user.isDisabled() )
        {
            return AuthenticationResult.DISABLED_USER;
        }
        return AuthenticationResult.SUCCESSFUL;
    }

    @Override
    protected Identity createNewIdentity( String userName )
        throws NoSuchUserException
    {
        User user = findUser( userName );
        if ( user == null )
        {
            throw new NoSuchUserException( userName );
        }
        SimpleIdentity si = new SimpleIdentity( userName );
        si.setLocale( new Locale( user.getLanguage(), user.getCountry() ) );
        si.setTimeZone( TimeZone.getTimeZone( user.getTimeZone() ) );
        return si;
    }

    private User findUser( String userName )
    {
        User user = null;
        Session session = sessionFactory.openSession();
        try
        {
            Query query = session.getNamedQuery( User.class.getName() + ".findByName" );
            query.setString( "userName", userName );
            Iterator i = query.iterate();
            if ( i.hasNext() )
            {
                user = (User) i.next();
            }
            return user;
        }
        finally
        {
            session.close();
        }
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}