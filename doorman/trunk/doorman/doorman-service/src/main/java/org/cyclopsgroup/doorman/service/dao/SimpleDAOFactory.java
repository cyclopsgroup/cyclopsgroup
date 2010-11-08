package org.cyclopsgroup.doorman.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public final void setUserDao( UserDAO userDao )
    {
        this.userDao = userDao;
    }

    @Autowired
    public final void setUserSessionDao( UserSessionDAO userSessionDao )
    {
        this.userSessionDao = userSessionDao;
    }
}
