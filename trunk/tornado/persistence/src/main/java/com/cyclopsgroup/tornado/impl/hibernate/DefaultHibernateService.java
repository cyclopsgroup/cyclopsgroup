/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the Open Software License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://opensource.org/licenses/osl-2.1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.tornado.impl.hibernate;

import java.net.URL;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import org.apache.avalon.framework.activity.Disposable;
import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cyclopsgroup.tornado.hibernate.HibernateService;
import com.cyclopsgroup.tornado.hibernate.NoSuchHibernateConfiguredException;
import com.cyclopsgroup.tornado.sql.DataSourceService;
import com.cyclopsgroup.tornado.utils.ConfigurationUtils;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Default implementation of hibernate factory
 */
public class DefaultHibernateService
    extends AbstractLogEnabled
    implements HibernateService, Configurable, Disposable, Initializable, Serviceable
{
    private DataSourceService dataSourceService;

    private String dataSourceServiceRole;

    private Vector<Class<Object>> entityClasses = new Vector<Class<Object>>();

    private org.hibernate.cfg.Configuration hibernateConfiguration;

    private Properties hibernateProperties = new Properties();

    private ThreadLocal<Session> localSession = new ThreadLocal<Session>();

    private ThreadLocal<Transaction> localTransaction = new ThreadLocal<Transaction>();

    private String name;

    private ServiceManager serviceManager;

    private SessionFactory sessionFactory;

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#closeSession()
     */
    public synchronized void closeSession()
    {
        Session session = localSession.get();
        if ( session != null )
        {
            if ( session.isOpen() )
            {
                session.flush();
                session.close();
            }
            localSession.set( null );
        }
    }

    /**
     * Overwrite or implement method commitTransaction()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#commitTransaction(java.lang.String)
     */
    public void commitTransaction()
        throws Exception
    {
        Transaction transaction = localTransaction.get();
        if ( transaction != null )
        {
            Session session = getSession();
            if ( session != null )
            {
                session.flush();
            }
            if ( transaction != null )
            {
                transaction.commit();
            }
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( org.apache.avalon.framework.configuration.Configuration conf )
        throws ConfigurationException
    {
        name = conf.getChild( "name" ).getValue();
        dataSourceServiceRole = conf.getChild( "data-source" ).getAttribute( "role" );
        hibernateProperties = ConfigurationUtils.getProperties( conf.getChild( "properties" ) );
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Disposable#dispose()
     */
    public void dispose()
    {
        closeSession();
    }

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#getConnection()
     */
    public Connection getConnection()
        throws Exception
    {
        return getSession().connection();
    }

    public DataSourceService getDataSourceService()
    {
        return dataSourceService;
    }

    /**
     * Override method getEntityClasses in class DefaultHibernateHome
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#getEntityClasses(java.lang.String)
     */
    public Class[] getEntityClasses()
    {
        return entityClasses.toArray( ArrayUtils.EMPTY_CLASS_ARRAY );
    }

    /**
     * Override method getHibernateConfiguration in class DefaultHibernateHome
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#getHibernateConfiguration(java.lang.String)
     */
    public org.hibernate.cfg.Configuration getHibernateConfiguration()
    {
        return hibernateConfiguration;
    }

    public String getName()
    {
        return name;
    }

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#getSession()
     */
    public Session getSession()
        throws Exception
    {
        return getSession( true );
    }

    /**
     * Overwrite or implement method getSession()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#getSession(java.lang.String, boolean)
     */
    public synchronized Session getSession( boolean withTransaction )
        throws Exception
    {
        Session session = localSession.get();
        if ( session == null )
        {
            SessionFactory sf = getSessionFactory();
            if ( dataSourceService == null )
            {
                session = sf.openSession();
            }
            else
            {
                Connection dbcon = dataSourceService.getLocalConnection();
                session = sf.openSession( dbcon );
            }
            localSession.set( session );
        }
        if ( withTransaction )
        {
            Transaction transaction = session.beginTransaction();
            localTransaction.set( transaction );
        }
        return session;
    }

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#getSessionFactory()
     */
    public SessionFactory getSessionFactory()
        throws NoSuchHibernateConfiguredException
    {
        return sessionFactory;
    }

    /**
     * Override method DefaultHibernateFactory in supper class
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> enu = cl.getResources( "META-INF/cyclopsgroup/hibernate." + getName() + ".cfg.xml" );

        hibernateConfiguration = new org.hibernate.cfg.Configuration();
        hibernateConfiguration.setProperties( hibernateProperties );

        while ( enu.hasMoreElements() )
        {
            URL resource = enu.nextElement();
            getLogger().info( "Configure hibernate service [" + name + "] with " + resource );
            hibernateConfiguration.configure( resource );
        }

        sessionFactory = hibernateConfiguration.buildSessionFactory();

        if ( StringUtils.isNotEmpty( dataSourceServiceRole ) )
        {
            dataSourceService = (DataSourceService) serviceManager.lookup( dataSourceServiceRole );
        }
    }

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#rollbackTransaction()
     */
    public synchronized void rollbackTransaction()
        throws Exception
    {
        Transaction transaction = localTransaction.get();
        if ( transaction != null && transaction.isActive() )
        {
            transaction.rollback();
        }
    }

    /**
     * Override method DefaultHibernateFactory in supper class
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        this.serviceManager = serviceManager;
    }
}
