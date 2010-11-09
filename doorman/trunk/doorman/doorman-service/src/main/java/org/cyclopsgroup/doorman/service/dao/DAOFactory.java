package org.cyclopsgroup.doorman.service.dao;

import org.cyclopsgroup.doorman.service.storage.StoredUser;
import org.cyclopsgroup.doorman.service.storage.StoredUserSession;
import org.cyclopsgroup.doorman.service.storage.StoredUserSignUpRequest;

/**
 * Facade of all DAOs
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public interface DAOFactory
{
    /**
     * @return Instance of DAO for {@link StoredUserSession}
     */
    UserSessionDAO createUserSessionDAO();

    /**
     * @return Instance of DAO for {@link StoredUser} and {@link StoredUserSignUpRequest}
     */
    UserDAO createUserDAO();
}
