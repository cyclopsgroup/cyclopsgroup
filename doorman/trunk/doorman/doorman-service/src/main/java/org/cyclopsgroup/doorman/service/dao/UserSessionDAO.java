package org.cyclopsgroup.doorman.service.dao;

import org.cyclopsgroup.doorman.service.storage.StoredUserSession;

public interface UserSessionDAO
{
    void createNew( StoredUserSession session );

    StoredUserSession load( String sessionId );
}
