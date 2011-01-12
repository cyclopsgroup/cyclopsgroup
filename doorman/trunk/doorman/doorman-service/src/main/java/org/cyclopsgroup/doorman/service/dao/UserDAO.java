package org.cyclopsgroup.doorman.service.dao;

import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.cyclopsgroup.doorman.service.storage.StoredUserSignUpRequest;

/**
 * DAO of {@link StoredUser} and {@link StoredUserSignUpRequest}
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public interface UserDAO
{
    /**
     * Create new user object directly
     *
     * @param user User object to create
     * @return Created user
     */
    void createUser( StoredUser user );

    /**
     * Finish signup and create user with given token
     *
     * @param requestToken Secret token to finish signup process
     * @return User model that is created
     * @throws DataOperationException When common exception happens and specific result is expected to return
     */
    StoredUser createUser( String requestToken )
        throws DataOperationException;

    /**
     * Find user with given user name or unique ID
     *
     * @param nameOrId Given user name or ID
     * @return User model or NULL if no such user is found
     */
    StoredUser findByNameOrId( String nameOrId );

    /**
     * Create and save new signup request
     *
     * @param request Sign up request
     * @throws DataOperationException When common exception happens and specific result is expected to return
     */
    void saveSignupRequest( StoredUserSignUpRequest request )
        throws DataOperationException;

    /**
     * @param user User to update
     */
    void saveUser( StoredUser user );
}
