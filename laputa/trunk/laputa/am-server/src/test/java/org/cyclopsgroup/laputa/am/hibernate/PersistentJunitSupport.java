package org.cyclopsgroup.laputa.am.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;

/**
 * A base class for unit test
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class PersistentJunitSupport
{
    private Session aSession;

    private SessionFactory sessionFactory;

    /**
     * @return Value of field aSession
     */
    public final Session aSession()
    {
        return aSession;
    }

    /**
     * @return Value of field sessionFactory
     */
    public final SessionFactory sessionFactory()
    {
        return sessionFactory;
    }

    /**
     * Prepare hibernate SessionFactory for testing
     */
    @Before
    public void setUpSessionFactory()
    {
        AnnotationConfiguration config = new AnnotationConfiguration();
        config.setProperty( "hibernate.dialect", "org.hibernate.dialect.HSQLDialect" );
        config.setProperty( "hibernate.connection.driver_class", "org.hsqldb.jdbcDriver" );
        config.setProperty( "hibernate.connection.url", "jdbc:hsqldb:mem:cyclopsgroup" );
        config.setProperty( "hibernate.connection.username", "sa" );
        config.setProperty( "hibernate.connection.password", "" );
        config.setProperty( "hibernate.connection.pool_size", "5" );
        config.setProperty( "hibernate.connection.autocommit", "true" );
        config.setProperty( "hibernate.cache.provider_class", "org.hibernate.cache.HashtableCacheProvider" );
        config.setProperty( "hibernate.hbm2ddl.auto", "create-drop" );
        config.setProperty( "hibernate.show_sql", "true" );
        AccountManagerConfigUtils.addEntities( config );
        sessionFactory = config.buildSessionFactory();
        aSession = sessionFactory.openSession();
    }

    /**
     * Close hibernate SessionFactory for testing
     */
    @After
    public void tearDownSessionFactory()
    {
        aSession.close();
        sessionFactory.close();
    }
}
