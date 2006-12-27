package com.cyclopsgroup.arapaho.hibernate;

import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.ExtendedProperties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DefaultHibernateService
    implements HibernateService
{
    private Configuration config = new Configuration();

    public DefaultHibernateService( ExtendedProperties props )
    {

    }

    public DefaultHibernateService( List<URL> configFiles, Properties props )
    {
        for ( URL configFile : configFiles )
        {
            configure( configFile );
        }
        setProperties( props );
    }

    public void configure( URL configFile )
    {
        config.configure( configFile );
    }

    /**
     * @see com.cyclopsgroup.arapaho.hibernate.HibernateService#createSessionFactory()
     */
    public SessionFactory createSessionFactory()
    {
        return config.buildSessionFactory();
    }

    /**
     * @see com.cyclopsgroup.arapaho.hibernate.HibernateService#getConfiguration()
     */
    public Configuration getConfiguration()
    {
        return config;
    }

    public void setProperties( Properties props )
    {
        config.setProperties( props );
    }

    public void setProperty( String name, String value )
    {
        config.setProperty( name, value );
    }
}