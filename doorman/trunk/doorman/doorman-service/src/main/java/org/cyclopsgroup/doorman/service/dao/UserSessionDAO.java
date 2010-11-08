package org.cyclopsgroup.doorman.service.dao;

import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.cyclopsgroup.doorman.service.storage.StoredUserSession;

public interface UserSessionDAO
{
    void createNew( StoredUserSession session );

    StoredUserSession findById( String sessionId );

    void updateUser( String sessionId, StoredUser user );
}
