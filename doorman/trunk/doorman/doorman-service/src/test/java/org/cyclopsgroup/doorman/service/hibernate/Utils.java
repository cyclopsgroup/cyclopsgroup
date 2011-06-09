package org.cyclopsgroup.doorman.service.hibernate;

import java.util.Locale;
import java.util.TimeZone;

import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.cyclopsgroup.doorman.service.storage.StoredUserState;
import org.cyclopsgroup.doorman.service.storage.StoredUserType;
import org.cyclopsgroup.service.security.PasswordStrategy;
import org.joda.time.DateTime;

/**
 * Utils for testing purpose
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
class Utils
{
    static StoredUser createStoredUser( String id )
    {
        StoredUser user = new StoredUser();
        user.setDisplayName( "haha" );
        user.setEmailAddress( id + "@cyclopsgroup.org" );
        user.setLastModified( new DateTime() );
        user.setPassword( "pass" );
        user.setPasswordStrategy( PasswordStrategy.PLAIN );

        user.setUserId( id );
        user.setUserName( id + "@cyclopsgroup.org" );
        user.setUserState( StoredUserState.ACTIVE );
        user.setCreationDate( new DateTime() );
        user.setDomainName( "cyclopsgroup.org" );
        user.setUserType( StoredUserType.LOCAL );

        user.setTimeZoneId( TimeZone.getDefault().getID() );
        user.setCountryCode( Locale.getDefault().getCountry() );
        user.setLanguageCode( Locale.getDefault().getLanguage() );
        return user;
    }
}
