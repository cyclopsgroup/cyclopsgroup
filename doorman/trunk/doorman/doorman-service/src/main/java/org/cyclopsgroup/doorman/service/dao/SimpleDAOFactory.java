package org.cyclopsgroup.doorman.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Factory of all DAOs
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@Service
public class SimpleDAOFactory
    implements DAOFactory
{
    private UserDAO userDao;

    private UserSessionDAO userSessionDao;

    /**
     * @inheritDoc
     */
    @Override
    public UserDAO createUserDAO()
    {
        return userDao;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UserSessionDAO createUserSessionDAO()
    {
        return userSessionDao;
    }

    /**
     * @param userDao Value {@link #createUserDAO()} returns
     */
    @Autowired
    public final void setUserDao( UserDAO userDao )
    {
        this.userDao = userDao;
    }

    /**
     * @param userSessionDao Value {@link #createUserSessionDAO()} returns
     */
    @Autowired
    public final void setUserSessionDao( UserSessionDAO userSessionDao )
    {
        this.userSessionDao = userSessionDao;
    }
}
