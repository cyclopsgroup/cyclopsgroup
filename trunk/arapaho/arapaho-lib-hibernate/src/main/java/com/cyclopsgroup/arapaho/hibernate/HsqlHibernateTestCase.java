package com.cyclopsgroup.arapaho.hibernate;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;

/**
 * Base test case with in-memory hsql support
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com>jiaqi</a>
 *
 */
public class HsqlHibernateTestCase
    extends TestCase
{
    private HibernateProvider hibernateInstance;

    protected SessionFactory sessionFactory;

    protected HibernateProvider createHibernateService()
        throws Exception
    {
        SimpleHibernateProvider dhs = new SimpleHibernateProvider();
        dhs.setProperty( "hibernate.dialect", "org.hibernate.dialect.HSQLDialect" );
        dhs.setProperty( "hibernate.connection.driver_class", "org.hsqldb.jdbcDriver" );
        dhs.setProperty( "hibernate.connection.url", "jdbc:hsqldb:mem:test" );
        dhs.setProperty( "hibernate.connection.username", "sa" );
        dhs.setProperty( "hibernate.connection.pool_size", "1" );
        dhs.setProperty( "hibernate.connection.autocommit", "true" );
        dhs.setProperty( "hibernate.cache.provider_class", "org.hibernate.cache.HashtableCacheProvider" );
        dhs.setProperty( "hibernate.hbm2ddl.auto", "create-drop" );
        dhs.setProperty( "hibernate.show_sql", "true" );
        return dhs;
    }

    @Override
    protected void setUp()
        throws Exception
    {
        hibernateInstance = createHibernateService();
        sessionFactory = hibernateInstance.createSessionFactory();
    }

    @Override
    protected void tearDown()
        throws Exception
    {
        sessionFactory.close();
    }
}
