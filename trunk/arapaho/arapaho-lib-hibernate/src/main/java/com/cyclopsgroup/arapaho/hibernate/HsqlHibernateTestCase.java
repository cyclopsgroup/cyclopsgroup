package com.cyclopsgroup.arapaho.hibernate;

import java.util.Properties;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HsqlHibernateTestCase
    extends TestCase
{
    protected SessionFactory sessionFactory;

    protected Configuration createHibernateConfiguration()
        throws Exception
    {
        Properties props = new Properties();
        props.load( HsqlHibernateTestCase.class.getResourceAsStream( "test-hsql-hibernate.properties" ) );
        AnnotationConfiguration conf = new AnnotationConfiguration();
        conf.setProperties( props );
        return conf;
    }

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
