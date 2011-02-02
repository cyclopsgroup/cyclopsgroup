package org.cyclopsgroup.doorman.service.core;

import org.cyclopsgroup.doorman.api.User;
import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

class ServiceUtils
{
    static void copyUser( User from, StoredUser to )
    {
        to.setCountryCode( from.getCountryCode() );
        to.setDisplayName( from.getDisplayName() );
        to.setDomainName( from.getDomainName() );
        to.setEmailAddress( from.getEmailAddress() );
        to.setLanguageCode( from.getLanguageCode() );
        to.setTimeZoneId( from.getTimeZoneId() );
        to.setUserName( from.getUserName() );
    }

    static User createUser( StoredUser u )
    {
        User user = new User();
        user.setCountryCode( u.getCountryCode() );
        user.setCreationDate( new DateTime( u.getCreationDate(), DateTimeZone.UTC ) );
        user.setDisplayName( u.getDisplayName() );
        user.setDomainName( u.getDomainName() );
        user.setEmailAddress( u.getEmailAddress() );
        user.setLanguageCode( u.getLanguageCode() );
        user.setLastVisit( new DateTime( u.getLastVisit(), DateTimeZone.UTC ) );
        user.setTimeZoneId( u.getTimeZoneId() );
        user.setUserId( u.getUserId() );
        user.setUserName( u.getUserName() );
        return user;
    }
}
