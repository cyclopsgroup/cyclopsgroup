package com.cyclopsgroup.arapaho.hibernate;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class SimpleHibernateProvider
    implements HibernateProvider
{
    public static final String DEFAULT_CONFIG_PATH = "META-INF/cyclopsgroup/hibernate.properties";

    private Configuration config = new AnnotationConfiguration();

    private Log logger = LogFactory.getLog( getClass() );

    public SimpleHibernateProvider()
        throws IOException
    {
        this( DEFAULT_CONFIG_PATH );
    }

    public SimpleHibernateProvider( ExtendedProperties props )
        throws IOException
    {
        populateExtendedProperties( props, new HashSet<String>() );
    }

    public SimpleHibernateProvider( String props )
        throws IOException
    {
        Enumeration<URL> propsResources = Thread.currentThread().getContextClassLoader().getResources( props );
        HashSet<String> populatedFiles = new HashSet<String>();
        while ( propsResources.hasMoreElements() )
        {
            ExtendedProperties ep = new ExtendedProperties();
            URL propsResource = propsResources.nextElement();
            logger.info( "Reading meta file " + propsResource );
            ep.load( propsResource.openStream() );
            populateExtendedProperties( ep, populatedFiles );
        }
    }

    public SimpleHibernateProvider( URL props )
        throws IOException
    {
        ExtendedProperties ep = new ExtendedProperties();
        ep.load( props.openStream() );
        populateExtendedProperties( ep, new HashSet<String>() );
    }

    public void configure( URL configFile )
    {
        config.configure( configFile );
    }

    /**
     * @see com.cyclopsgroup.arapaho.hibernate.HibernateProvider#createSessionFactory()
     */
    public SessionFactory createSessionFactory()
    {
        return config.buildSessionFactory();
    }

    /**
     * @see com.cyclopsgroup.arapaho.hibernate.HibernateProvider#getConfiguration()
     */
    public Configuration getConfiguration()
    {
        return config;
    }

    private void populateExtendedProperties( ExtendedProperties props, HashSet<String> populatedFiles )
        throws IOException
    {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        String[] hibernateConfigs = props.getStringArray( "arapaho.hibernate.config" );
        for ( String hibernateConfig : hibernateConfigs )
        {
            URL configResource = cl.getResource( hibernateConfig );
            if ( cl == null )
            {
                logger.warn( "Hibernate config " + hibernateConfig + " doesn't exist in classpath" );
            }
            else
            {
                configure( configResource );
            }
        }

        String[] hibernateProperties = props.getStringArray( "arapaho.hibernate.include" );
        for ( String hibernatePropertyFile : hibernateProperties )
        {
            if ( populatedFiles.contains( hibernatePropertyFile ) )
            {
                continue;
            }
            populatedFiles.add( hibernatePropertyFile );
            URL configResource = cl.getResource( hibernatePropertyFile );
            if ( cl == null )
            {
                logger.warn( "Hibernate properties file " + hibernatePropertyFile + " doesn't exist in classpath" );
            }
            else
            {
                logger.debug( "Configuring hibernate with " + configResource );

                ExtendedProperties ep = new ExtendedProperties();
                ep.load( configResource.openStream() );
                configResource = null;
                populateExtendedProperties( ep, populatedFiles );
            }
        }

        for ( Iterator<String> i = props.getKeys(); i.hasNext(); )
        {
            String key = i.next();
            setProperty( key, props.getString( key ) );
        }
    }

    public void setProperties( Properties props )
    {
        if ( props != null )
        {
            for ( Object name : props.keySet() )
            {
                config.setProperty( name.toString(), props.getProperty( name.toString() ) );
            }
        }
    }

    public void setProperties( URL props )
        throws IOException
    {
        Properties p = new Properties();
        p.load( props.openStream() );
        setProperties( p );
    }

    public void setPropertiesFile( String path )
        throws MalformedURLException, IOException
    {
        File file = new File( path );
        setProperties( file.toURL() );
    }

    public void setProperty( String name, String value )
    {
        config.setProperty( name, value );
    }
}