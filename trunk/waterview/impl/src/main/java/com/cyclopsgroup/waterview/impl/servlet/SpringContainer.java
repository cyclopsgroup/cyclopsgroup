package com.cyclopsgroup.waterview.impl.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class SpringContainer
{
    private ConfigurableApplicationContext applicationContext;

    private URL config;

    private boolean initialized;

    private Properties initProperties = new Properties();

    private Map<String, Object> staticBeans = new HashMap<String, Object>();

    public SpringContainer()
    {
        this( null );
    }

    public SpringContainer( URL config )
    {
        this.config = config;
        initProperties.setProperty( "basedir", new File( "" ).getAbsolutePath() );
    }

    public void addStaticBean( String beanId, Object bean )
    {
        staticBeans.put( beanId, bean );
    }

    public synchronized void dispose()
    {
        applicationContext.stop();
        applicationContext.close();
        applicationContext = null;
        initialized = false;
    }

    public ConfigurableApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    public Object getBean( String beanId )
    {
        if ( applicationContext == null )
        {
            throw new IllegalStateException( "Spring container is not initialized yet" );
        }
        return applicationContext.getBean( beanId );
    }

    public URL getConfig()
    {
        return config;
    }

    public synchronized void initialize()
        throws IOException
    {
        if ( initialized )
        {
            return;
        }
        initialized = true;
        List<UrlResource> allConfigs = new ArrayList<UrlResource>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        for ( Enumeration<URL> en = classLoader.getResources( "META-INF/cyclopsgroup/spring-beans.xml" ); en
            .hasMoreElements(); )
        {
            URL metaConfig = en.nextElement();
            allConfigs.add( new UrlResource( metaConfig ) );
        }
        if ( config != null )
        {
            allConfigs.add( new UrlResource( config ) );
        }

        final UrlResource[] configResources = allConfigs.toArray( new UrlResource[0] );

        applicationContext = new AbstractXmlApplicationContext()
        {
            @Override
            protected Resource[] getConfigResources()
            {
                return configResources;
            }

            @Override
            protected void postProcessBeanFactory( ConfigurableListableBeanFactory beanFactory )
                throws BeansException
            {
                if ( !initProperties.isEmpty() )
                {
                    PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
                    configurer.setProperties( initProperties );
                    configurer.postProcessBeanFactory( beanFactory );
                }

                if ( !staticBeans.isEmpty() )
                {
                    for ( String beanId : staticBeans.keySet() )
                    {
                        beanFactory.registerSingleton( beanId, staticBeans.get( beanId ) );
                    }
                }
            }
        };

        applicationContext.refresh();
        applicationContext.start();
    }

    public boolean isInitialized()
    {
        return initialized;
    }

    public void setProperty( String name, String value )
    {
        initProperties.setProperty( name, value );
    }
}
