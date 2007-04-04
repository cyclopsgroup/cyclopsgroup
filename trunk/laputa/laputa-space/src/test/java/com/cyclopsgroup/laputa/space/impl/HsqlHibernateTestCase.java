package com.cyclopsgroup.laputa.space.impl;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cyclopsgroup.arapaho.hibernate.HibernateUtils;

public class HsqlHibernateTestCase
    extends TestCase
{

    protected Session session;

    protected SessionFactory sessionFactory;

    @Override
    protected void setUp()
    {
        HibernateConfiguration c = new HibernateConfiguration();
        HibernateUtils.makeHsqlConfiguration( c );
        sessionFactory = c.buildSessionFactory();
        session = c.buildSessionFactory().openSession();
    }

    @Override
    protected void tearDown()
    {
        session.close();
        sessionFactory.close();
    }
}
