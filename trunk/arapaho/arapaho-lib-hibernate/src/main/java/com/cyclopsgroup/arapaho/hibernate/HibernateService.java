package com.cyclopsgroup.arapaho.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Hibernate service facade
 *
 * @author <a href="mailto:jiaqi@amazon.com>jiaqi</a>
 *
 */
public interface HibernateService
{
    String DEFAULT_HIBERNATE = "default";

    String ROLE = HibernateService.class.getName();

    String SESSION_ID_KEY = HibernateService.class.getName() + "/sessionId";

    void closeSession( long sessionId );

    HibernateProvider getHibernateProvider( String hibernateName );

    Session getSession( long sessionId );

    SessionFactory getSessionFactory();

    SessionFactory getSessionFactory( String hibernateName );

    long openSession( String hibernateName );
}