package com.cyclopsgroup.arapaho.hibernate.avalon;

import java.util.Properties;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.context.Context;
import org.apache.avalon.framework.context.ContextException;
import org.apache.avalon.framework.context.Contextualizable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cyclopsgroup.arapaho.avalon.AvalonUtils;
import com.cyclopsgroup.arapaho.hibernate.HibernateProvider;
import com.cyclopsgroup.arapaho.hibernate.SimpleHibernateProvider;

public class AvalonHibernateProvider
    extends AbstractLogEnabled
    implements HibernateProvider, Configurable, Initializable, Contextualizable
{
    private Properties initialProperties;

    private String metaFile;

    private Properties properties;

    private HibernateProvider proxy;

    public void configure( org.apache.avalon.framework.configuration.Configuration config )
        throws ConfigurationException
    {
        metaFile = config.getChild( "meta" ).getValue( SimpleHibernateProvider.DEFAULT_CONFIG_PATH );
        properties = AvalonUtils.getProperties( config.getChild( "properties" ) );
    }

    public void contextualize( Context context )
        throws ContextException
    {
        initialProperties = AvalonUtils.getInitialProperties( context );
    }

    public SessionFactory createSessionFactory()
    {
        return proxy.createSessionFactory();
    }

    public Configuration getConfiguration()
    {
        return proxy.getConfiguration();
    }

    public void initialize()
        throws Exception
    {
        SimpleHibernateProvider h = new SimpleHibernateProvider( metaFile );
        h.setProperties( properties );
        h.setProperties( initialProperties );
        proxy = h;
    }
}
