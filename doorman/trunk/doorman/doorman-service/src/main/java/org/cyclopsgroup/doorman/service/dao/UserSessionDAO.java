package org.cyclopsgroup.doorman.service.dao;

import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.cyclopsgroup.doorman.service.storage.StoredUserSession;

/**
 * Data access object of {@link StoredUserSession}
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public interface UserSessionDAO
{
    /**
     * Create new user session
     *
     * @param session New user session to create
     */
    void createNew( StoredUserSession session );

    /**
     * Find existing user session based on ID
     *
     * @param sessionId Id of user session to find
     * @return User session with given ID or NULL if it's not found
     */
    StoredUserSession findById( String sessionId );

    /**
     * @param sessionId ID of session to ping
     * @return The user session object it pings
     */
    StoredUserSession pingSession( String sessionId );

    /**
     * Update user session with given user
     *
     * @param sessionId User session ID
     * @param user User to update, which can be NULL
     */
    void updateUser( String sessionId, StoredUser user );
}
