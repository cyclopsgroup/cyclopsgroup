package com.cyclopsgroup.arapaho.hibernate;

import java.util.Properties;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * Base test case with in-memory hsql support
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com>jiaqi</a>
 *
 */
public class HsqlHibernateTestCase
    extends TestCase
{
    protected SessionFactory sessionFactory;

    /**
     * Create hibernate config
     * Add your hibernate cfg files by overriding this method
     * 
     * @return Hibernate configuration object
     * @throws Exception
     */
    protected Configuration createHibernateConfiguration()
        throws Exception
    {
        Properties props = new Properties();
        props.load( HsqlHibernateTestCase.class.getResourceAsStream( "test-hsql-hibernate.properties" ) );
        AnnotationConfiguration conf = new AnnotationConfiguration();
        conf.setProperties( props );
        return conf;
    }

    /**
     * Create new hibernate SessionFactory object
     * 
     * @return Session factory object
     * @throws Exception
     */
    protected SessionFactory createSessionFactory()
        throws Exception
    {
        return createHibernateConfiguration().buildSessionFactory();
    }

    @Override
    protected void setUp()
        throws Exception
    {
        sessionFactory = createSessionFactory();
    }

    @Override
    protected void tearDown()
        throws Exception
    {
        sessionFactory.close();
    }
}
