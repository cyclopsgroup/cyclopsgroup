package org.cyclopsgroup.doorman.service.hibernate;

import javax.annotation.Resource;

import org.cyclopsgroup.doorman.service.dao.DAOFactory;
import org.cyclopsgroup.doorman.service.dao.UserDAO;
import org.cyclopsgroup.doorman.service.dao.UserSessionDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

/**
 * DAO factory that creates Hibernate DAOs
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
@Service
public class HibernateDAOFactory
    implements DAOFactory
{
    private SessionFactory sessionFactory;

    /**
     * @inheritDoc
     */
    @Override
    public UserDAO createUserDAO()
    {
        return new HibernateUserDAO( sessionFactory );
    }

    /**
     * @inheritDoc
     */
    @Override
    public UserSessionDAO createUserSessionDAO()
    {
        return new HibernateUserSessionDAO( sessionFactory );
    }

    /**
     * @param sessionFactory Hibernate session factory
     */
    @Required
    @Resource( name = "org.cyclopsgroup.doorman.SessionFactory" )
    public void setSessionFactory( SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }
}