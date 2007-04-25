package com.cyclopsgroup.arapaho.spring;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.FactoryBean;

import com.cyclopsgroup.arapaho.hibernate.HsqlUtils;

public class HsqlHibernateSessionFactoryBean
    implements FactoryBean
{
    private Configuration config;

    public HsqlHibernateSessionFactoryBean( Configuration config )
    {
        this.config = config;
    }

    public Object getObject()
        throws Exception
    {
        HsqlUtils.setHsqlProperties( config );
        return config.buildSessionFactory();
    }

    public Class<SessionFactory> getObjectType()
    {
        return SessionFactory.class;
    }

    public boolean isSingleton()
    {
        return true;
    }

}
