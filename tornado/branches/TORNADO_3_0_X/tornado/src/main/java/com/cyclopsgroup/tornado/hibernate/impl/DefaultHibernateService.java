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
package com.cyclopsgroup.tornado.hibernate.impl;

import java.net.URL;
import java.sql.Connection;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.avalon.framework.activity.Disposable;
import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cyclopsgroup.tornado.hibernate.HibernateService;
import com.cyclopsgroup.tornado.hibernate.NoSuchHibernateConfiguredException;
import com.cyclopsgroup.tornado.sql.DataSourceManager;
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
    private DataSourceManager dataSourceManager;

    private MultiHashMap entityClasses = new MultiHashMap();

    private Hashtable hibernateConfigurations = new Hashtable();

    private HashMap hibernateProperties = new HashMap();

    private ThreadLocal localSession = new ThreadLocal();

    private Hashtable sessionFactories = new Hashtable();

    /**
     * Overwrite or implement method closeSession()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#closeSession()
     */
    public void closeSession()
    {
        closeSession( DEFAULT_DATASOURCE );
    }

    /**
     * Overwrite or implement method closeSession()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#closeSession(java.lang.String)
     */
    public void closeSession( String dataSourceName )
    {
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        if ( wrapper != null )
        {
            try
            {
                Session session = wrapper.getSession( dataSourceName );
                if ( session != null )
                {
                    session.flush();
                    session.close();
                }
                Connection dbcon = wrapper.getConnection( dataSourceName );
                if ( dbcon != null )
                {
                    dbcon.close();
                }
                wrapper.remove( dataSourceName );
            }
            catch ( Exception e )
            {
                getLogger().warn( "Can not close hibernate session", e );
            }
        }
    }

    /**
     * Overwrite or implement method closeSessions()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#closeSessions()
     */
    public void closeSessions()
    {
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        if ( wrapper != null )
        {
            String[] names = wrapper.getDataSourceNames();
            for ( int i = 0; i < names.length; i++ )
            {
                closeSession( names[i] );
            }
        }
        localSession.set( null );
    }

    /**
     * Overwrite or implement method commitTransaction()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#commitTransaction()
     */
    public void commitTransaction()
        throws Exception
    {
        commitTransaction( DEFAULT_DATASOURCE );
    }

    /**
     * Overwrite or implement method commitTransaction()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#commitTransaction(java.lang.String)
     */
    public void commitTransaction( String dataSourceName )
        throws Exception
    {
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        if ( wrapper != null )
        {
            Session session = wrapper.getSession( dataSourceName );
            if ( session != null )
            {
                session.flush();
            }
            Transaction transaction = wrapper.getTransaction( dataSourceName );
            if ( transaction != null )
            {
                transaction.commit();
            }
            Connection dbcon = wrapper.getConnection( dataSourceName );
            dbcon.commit();
        }
    }

    /**
     * Overwrite or implement method commitTransactions()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#commitTransactions()
     */
    public void commitTransactions()
        throws Exception
    {
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        if ( wrapper != null )
        {
            String[] names = wrapper.getDataSourceNames();
            for ( int i = 0; i < names.length; i++ )
            {
                try
                {
                    commitTransaction( names[i] );
                }
                catch ( Exception e )
                {
                    getLogger().warn( "Can not close hibernate session", e );
                }
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
        Configuration[] props = conf.getChildren( "data-source" );
        for ( int i = 0; i < props.length; i++ )
        {
            Configuration prop = props[i];
            String name = prop.getAttribute( "name" );
            Properties p = ConfigurationUtils.getProperties( prop );
            hibernateProperties.put( name, p );
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Disposable#dispose()
     */
    public synchronized void dispose()
    {
        for ( Iterator i = sessionFactories.values().iterator(); i.hasNext(); )
        {
            SessionFactory sf = (SessionFactory) i.next();
            sf.close();
        }
        sessionFactories.clear();
    }

    /**
     * Override method getEntityClasses in class DefaultHibernateHome
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#getEntityClasses(java.lang.String)
     */
    public Class[] getEntityClasses( String dataSourceName )
    {
        Collection classes = (Collection) entityClasses.get( dataSourceName );
        return classes == null ? ArrayUtils.EMPTY_CLASS_ARRAY : (Class[]) classes
            .toArray( ArrayUtils.EMPTY_CLASS_ARRAY );
    }

    /**
     * Overwrite or implement method getSession()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#getSession()
     */
    public Session getSession()
        throws Exception
    {
        return getSession( DEFAULT_DATASOURCE );
    }

    /**
     * Overwrite or implement method getSession()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#getSession(boolean)
     */
    public synchronized Session getSession( boolean withTransaction )
        throws Exception
    {
        return getSession( DEFAULT_DATASOURCE, withTransaction );
    }

    /**
     * Overwrite or implement method getSession()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#getSession(java.lang.String)
     */
    public Session getSession( String dataSourceName )
        throws Exception
    {
        return getSession( dataSourceName, true );
    }

    /**
     * Override method getConnection in class DefaultHibernateHome
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#getConnection(java.lang.String)
     */
    public synchronized Connection getConnection( String dataSourceName )
        throws Exception
    {
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        if ( wrapper == null )
        {
            wrapper = new SessionWrapper();
            localSession.set( wrapper );
        }
        Connection dbcon = wrapper.getConnection( dataSourceName );
        if ( dbcon == null )
        {
            DataSource dataSource = dataSourceManager.getDataSource( dataSourceName );
            dbcon = dataSource.getConnection();
            wrapper.setConnection( dataSourceName, dbcon );
        }
        return dbcon;
    }

    /**
     * Overwrite or implement method getSession()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#getSession(java.lang.String, boolean)
     */
    public synchronized Session getSession( String dataSourceName, boolean withTransaction )
        throws Exception
    {
        Connection dbcon = getConnection( dataSourceName );
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        Session session = wrapper.getSession( dataSourceName );
        if ( session == null )
        {
            SessionFactory sf = getSessionFactory( dataSourceName );
            session = sf.openSession( dbcon );
            wrapper.setSession( dataSourceName, session );
        }
        if ( withTransaction )
        {
            wrapper.enableTransaction( dataSourceName );
        }
        return session;
    }

    /**
     * Overwrite or implement method getSessionFactory()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#getSessionFactory(java.lang.String)
     */
    public SessionFactory getSessionFactory( String dataSourceName )
        throws NoSuchHibernateConfiguredException
    {
        SessionFactory sf = (SessionFactory) sessionFactories.get( dataSourceName );
        if ( sf == null )
        {
            throw new NoSuchHibernateConfiguredException( dataSourceName );
        }
        return sf;
    }

    /**
     * Override method DefaultHibernateFactory in supper class
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        Enumeration enu = getClass().getClassLoader()
            .getResources( "META-INF/cyclopsgroup/hibernate-entities.properties" );
        ExtendedProperties props = new ExtendedProperties();
        while ( enu.hasMoreElements() )
        {
            URL resource = (URL) enu.nextElement();
            props.load( resource.openStream() );
        }

        for ( Iterator i = hibernateProperties.keySet().iterator(); i.hasNext(); )
        {
            String name = (String) i.next();

            org.hibernate.cfg.Configuration c = new org.hibernate.cfg.Configuration();
            Properties p = (Properties) hibernateProperties.get( name );
            c.setProperties( p );
            for ( Iterator j = props.getKeys(); j.hasNext(); )
            {
                String key = (String) j.next();
                String value = (String) props.getString( key );

                if ( StringUtils.equals( value, name ) )
                {
                    try
                    {
                        Class entityClass = Class.forName( key );
                        c.addClass( entityClass );
                        entityClasses.put( value, entityClass );
                    }
                    catch ( Exception e )
                    {
                        getLogger().warn( "Entity " + key + " is not loaded", e );
                    }
                }
            }
            hibernateConfigurations.put( name, c );
            SessionFactory sessionFactory = c.buildSessionFactory();
            sessionFactories.put( name, sessionFactory );
        }
    }

    /**
     * Overwrite or implement method rollbackTransaction()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#rollbackTransaction()
     */
    public void rollbackTransaction()
        throws Exception
    {
        rollbackTransaction( DEFAULT_DATASOURCE );
    }

    /**
     * Overwrite or implement method rollbackTransaction()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#rollbackTransaction(java.lang.String)
     */
    public void rollbackTransaction( String dataSourceName )
        throws Exception
    {
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        if ( wrapper != null )
        {
            Transaction transaction = wrapper.getTransaction( dataSourceName );
            if ( transaction != null )
            {
                transaction.rollback();
            }
            Connection dbcon = wrapper.getConnection( dataSourceName );
            dbcon.rollback();
        }
    }

    /**
     * Overwrite or implement method rollbackTransactions()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#rollbackTransactions()
     */
    public void rollbackTransactions()
        throws Exception
    {
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        if ( wrapper != null )
        {
            String[] names = wrapper.getDataSourceNames();
            for ( int i = 0; i < names.length; i++ )
            {
                try
                {
                    rollbackTransaction( names[i] );
                }
                catch ( Exception e )
                {
                    getLogger().warn( "Can not close hibernate session", e );
                }
            }
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
        dataSourceManager = (DataSourceManager) serviceManager.lookup( DataSourceManager.ROLE );
    }

    /**
     * Override method getHibernateConfiguration in class DefaultHibernateHome
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateService#getHibernateConfiguration(java.lang.String)
     */
    public org.hibernate.cfg.Configuration getHibernateConfiguration( String dataSourceName )
    {
        return (org.hibernate.cfg.Configuration) hibernateConfigurations.get( dataSourceName );
    }
}
