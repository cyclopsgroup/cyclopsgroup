package com.cyclopsgroup.arapaho.spring;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cyclopsgroup.arapaho.hibernate.HsqlHibernateTestCase;

public abstract class ApplicationContextTestCase
    extends HsqlHibernateTestCase
{
    public static final String SESSION_INTERCEPTOR = "com.cyclopsgroup.HibernateSessionInterceptor";

    public static final String SESSION_FACTORY = SessionFactory.class.getName();

    protected ConfigurableApplicationContext springContext;

    protected ConfigurableApplicationContext createApplicationContext()
    {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext( new String[] { "spring-beans.xml" },
                                                                                 false );
        ctx.addBeanFactoryPostProcessor( new BeanFactoryPostProcessor()
        {
            public void postProcessBeanFactory( ConfigurableListableBeanFactory beanFactory )
                throws BeansException
            {
                prepareApplicationContext( beanFactory );
            }
        } );
        ctx.refresh();
        System.out.println( ctx.getBean( SESSION_INTERCEPTOR ) );
        return ctx;
    }

    protected void prepareApplicationContext( ConfigurableListableBeanFactory beanFactory )
    {
        beanFactory.registerSingleton( SESSION_FACTORY, sessionFactory );
        beanFactory.registerSingleton( SESSION_INTERCEPTOR, new SingleHibernateSessionInterceptor( session ) );
    }

    @Override
    protected void setUp()
    {
        super.setUp();
        springContext = createApplicationContext();
    }

    @Override
    protected void tearDown()
    {
        springContext.close();
        super.tearDown();
    }
}
