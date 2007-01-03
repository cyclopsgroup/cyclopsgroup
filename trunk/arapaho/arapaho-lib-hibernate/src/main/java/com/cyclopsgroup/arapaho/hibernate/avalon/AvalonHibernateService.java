package com.cyclopsgroup.arapaho.hibernate.avalon;

import java.util.HashMap;
import java.util.Map;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cyclopsgroup.arapaho.hibernate.HibernateProvider;
import com.cyclopsgroup.arapaho.hibernate.HibernateService;
import com.cyclopsgroup.arapaho.hibernate.SimpleHibernateService;

public class AvalonHibernateService
    extends AbstractLogEnabled
    implements Configurable, Serviceable, HibernateService, Initializable
{

    private Map<String, String> instanceRoles = new HashMap<String, String>();

    private HibernateService proxy;

    private ServiceManager serviceManager;

    public void closeSession( long sessionId )
    {
        proxy.closeSession( sessionId );
    }

    public void configure( Configuration config )
        throws ConfigurationException
    {
        for ( Configuration inst : config.getChildren( "instance" ) )
        {
            instanceRoles.put( inst.getAttribute( "name" ), inst.getAttribute( "role" ) );
        }
    }

    public HibernateProvider getHibernateProvider( String hibernateName )
    {
        return proxy.getHibernateProvider( hibernateName );
    }

    public Session getSession( long sessionId )
    {
        return proxy.getSession( sessionId );
    }

    public SessionFactory getSessionFactory()
    {
        return proxy.getSessionFactory();
    }

    public SessionFactory getSessionFactory( String hibernateName )
    {
        return proxy.getSessionFactory( hibernateName );
    }

    public void initialize()
        throws Exception
    {
        Map<String, HibernateProvider> instances = new HashMap<String, HibernateProvider>();
        for ( String name : instanceRoles.keySet() )
        {
            String role = instanceRoles.get( name );
            HibernateProvider inst = (HibernateProvider) serviceManager.lookup( role );
            instances.put( name, inst );
        }
        proxy = new SimpleHibernateService( instances );
    }

    public long openSession( String hibernateName )
    {
        return proxy.openSession( hibernateName );
    }

    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        this.serviceManager = serviceManager;
    }
}
