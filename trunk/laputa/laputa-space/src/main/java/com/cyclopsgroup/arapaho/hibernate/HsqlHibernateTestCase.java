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
        c.setProperty( "hibernate.dialect", "org.hibernate.dialect.HSQLDialect" );
        c.setProperty( "hibernate.connection.driver_class", "org.hsqldb.jdbcDriver" );
        c.setProperty( "hibernate.connection.url", "jdbc:hsqldb:mem:test" );
        c.setProperty( "hibernate.connection.username", "sa" );
        c.setProperty( "hibernate.connection.pool_size", "1" );
        c.setProperty( "hibernate.connection.autocommit", "true" );
        c.setProperty( "hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider" );
        c.setProperty( "hibernate.hbm2ddl.auto", "create-drop" );
        c.setProperty( "hibernate.show_sql", "true" );
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
