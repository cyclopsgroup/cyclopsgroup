package org.cyclopsgroup.doorman.service.dao;

import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.cyclopsgroup.doorman.service.storage.StoredUserSignupRequest;

public interface UserDAO
{
    StoredUser createUser( String requestToken, String domainName );

    StoredUser findByName( String userName );

    void saveSignupRequest( StoredUserSignupRequest request );
}
