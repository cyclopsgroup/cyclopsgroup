package com.cyclopsgroup.arapaho.test;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;
import org.hibernate.cache.HashtableCacheProvider;
import org.hibernate.dialect.HSQLDialect;
import org.hsqldb.jdbcDriver;

import com.cyclopsgroup.arapaho.hibernate.HibernateProvider;
import com.cyclopsgroup.arapaho.hibernate.SimpleHibernateProvider;

/**
 * Base test case with in-memory hsql support
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com>jiaqi</a>
 *
 */
public class HsqlHibernateTestCase
    extends TestCase
{
    private HibernateProvider hibernateProvider;

    protected SessionFactory sessionFactory;

    /**
     * Overridable method to create testing hibernate service
     * 
     * @return SimpleHibernateProvider instance
     * @throws Exception
     */
    protected HibernateProvider createHibernateProvider()
        throws Exception
    {
        SimpleHibernateProvider shp = new SimpleHibernateProvider();
        shp.setProperty( "hibernate.dialect", HSQLDialect.class.getName() );
        shp.setProperty( "hibernate.connection.driver_class", jdbcDriver.class.getName() );
        shp.setProperty( "hibernate.connection.url", "jdbc:hsqldb:mem:test" );
        shp.setProperty( "hibernate.connection.username", "sa" );
        shp.setProperty( "hibernate.connection.pool_size", "1" );
        shp.setProperty( "hibernate.connection.autocommit", "true" );
        shp.setProperty( "hibernate.cache.provider_class", HashtableCacheProvider.class.getName() );
        shp.setProperty( "hibernate.hbm2ddl.auto", "create-drop" );
        shp.setProperty( "hibernate.show_sql", "true" );
        return shp;
    }

    @Override
    protected void setUp()
        throws Exception
    {
        hibernateProvider = createHibernateProvider();
        sessionFactory = hibernateProvider.createSessionFactory();
    }

    @Override
    protected void tearDown()
        throws Exception
    {
        sessionFactory.close();
    }
}
