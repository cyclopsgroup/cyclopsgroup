package com.cyclopsgroup.arapaho.hibernate;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public abstract class HsqlHibernateTestCase
    extends TestCase
{

    protected Session session;

    protected SessionFactory sessionFactory;

    protected AnnotationConfiguration createHibernateConfiguration()
    {
        AnnotationConfiguration c = new AnnotationConfiguration();
        HsqlUtils.setHsqlProperties( c );
        return c;
    }

    @Override
    protected void setUp()
    {
        sessionFactory = createHibernateConfiguration().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    @Override
    protected void tearDown()
    {
        session.close();
        sessionFactory.close();
    }
}
