package org.cyclopsgroup.doorman.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleDAOFactory
    implements DAOFactory
{
    private UserSessionDAO userSessionDao;

    @Autowired
    public final void setUserSessionDao( UserSessionDAO userSessionDao )
    {
        this.userSessionDao = userSessionDao;
    }

    @Override
    public UserSessionDAO createUserSessionDAO()
    {
        return userSessionDao;
    }
}
