/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.tornado.hibernate;

import java.net.URL;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

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
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cyclopsgroup.tornado.sql.DataSourceManager;
import com.cyclopsgroup.tornado.utils.ClassComparator;
import com.cyclopsgroup.tornado.utils.ConfigurationUtils;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Default implementation of hibernate factory
 */
public class DefaultHibernateHome extends AbstractLogEnabled implements
        HibernateHome, Configurable, Disposable, Initializable, Serviceable
{
    private DataSourceManager dataSourceManager;

    private Set entityClasses;

    private HashMap hibernateProperties = new HashMap();

    private ThreadLocal localSession = new ThreadLocal();

    private Hashtable sessionFactories = new Hashtable();

    /**
     * Overwrite or implement method closeSession()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#closeSession()
     */
    public void closeSession()
    {
        closeSession(DEFAULT_DATASOURCE);
    }

    /**
     * Overwrite or implement method closeSession()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#closeSession(java.lang.String)
     */
    public void closeSession(String dataSourceName)
    {
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        if (wrapper != null)
        {
            try
            {
                Session session = wrapper.getSession(dataSourceName);
                if (session != null)
                {
                    session.flush();
                    session.close();
                }
                Connection dbcon = wrapper.getConnection(dataSourceName);
                if (dbcon != null)
                {
                    dbcon.close();
                }
                wrapper.remove(dataSourceName);
            }
            catch (Exception e)
            {
                getLogger().warn("Can not close hibernate session", e);
            }
        }
    }

    /**
     * Overwrite or implement method closeSessions()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#closeSessions()
     */
    public void closeSessions()
    {
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        if (wrapper != null)
        {
            String[] names = wrapper.getDataSourceNames();
            for (int i = 0; i < names.length; i++)
            {
                closeSession(names[i]);
            }
        }
        localSession.set(null);
    }

    /**
     * Overwrite or implement method commitTransaction()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#commitTransaction()
     */
    public void commitTransaction() throws Exception
    {
        commitTransaction(DEFAULT_DATASOURCE);
    }

    /**
     * Overwrite or implement method commitTransaction()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#commitTransaction(java.lang.String)
     */
    public void commitTransaction(String dataSourceName) throws Exception
    {
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        if (wrapper != null)
        {
            Session session = wrapper.getSession(dataSourceName);
            if (session != null)
            {
                session.flush();
            }
            Transaction transaction = wrapper.getTransaction(dataSourceName);
            if (transaction != null)
            {
                transaction.commit();
            }
            Connection dbcon = wrapper.getConnection(dataSourceName);
            dbcon.commit();
        }
    }

    /**
     * Overwrite or implement method commitTransactions()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#commitTransactions()
     */
    public void commitTransactions() throws Exception
    {
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        if (wrapper != null)
        {
            String[] names = wrapper.getDataSourceNames();
            for (int i = 0; i < names.length; i++)
            {
                try
                {
                    commitTransaction(names[i]);
                }
                catch (Exception e)
                {
                    getLogger().warn("Can not close hibernate session", e);
                }
            }
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(
            org.apache.avalon.framework.configuration.Configuration conf)
            throws ConfigurationException
    {
        Configuration[] props = conf.getChildren("data-source");
        for (int i = 0; i < props.length; i++)
        {
            Configuration prop = props[i];
            String name = prop.getAttribute("name");
            Properties p = ConfigurationUtils.getProperties(prop);
            hibernateProperties.put(name, p);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Disposable#dispose()
     */
    public synchronized void dispose()
    {
        for (Iterator i = sessionFactories.values().iterator(); i.hasNext();)
        {
            SessionFactory sf = (SessionFactory) i.next();
            sf.close();
        }
        sessionFactories.clear();
    }

    /**
     * Overwrite or implement method getEntityClasses()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#getEntityClasses()
     */
    public Class[] getEntityClasses()
    {
        return (Class[]) entityClasses.toArray(ArrayUtils.EMPTY_CLASS_ARRAY);
    }

    /**
     * Overwrite or implement method getSession()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#getSession()
     */
    public Session getSession() throws Exception
    {
        return getSession(DEFAULT_DATASOURCE);
    }

    /**
     * Overwrite or implement method getSession()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#getSession(boolean)
     */
    public synchronized Session getSession(boolean withTransaction)
            throws Exception
    {
        return getSession(DEFAULT_DATASOURCE, withTransaction);
    }

    /**
     * Overwrite or implement method getSession()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#getSession(java.lang.String)
     */
    public Session getSession(String dataSourceName) throws Exception
    {
        return getSession(dataSourceName, true);
    }

    /**
     * Overwrite or implement method getSession()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#getSession(java.lang.String, boolean)
     */
    public Session getSession(String dataSourceName, boolean withTransaction)
            throws Exception
    {
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        if (wrapper == null)
        {
            wrapper = new SessionWrapper();
            localSession.set(wrapper);
        }
        Connection dbcon = wrapper.getConnection(dataSourceName);
        if (dbcon == null)
        {
            DataSource dataSource = dataSourceManager
                    .getDataSource(dataSourceName);
            if (dataSource == null)
            {
                return null;
            }
            dbcon = dataSource.getConnection();
            if (dbcon == null)
            {
                return null;
            }
            wrapper.setConnection(dataSourceName, dbcon);
        }
        Session session = wrapper.getSession(dataSourceName);
        if (session == null)
        {
            SessionFactory sf = getSessionFactory(dataSourceName);
            if (sf == null)
            {
                return null;
            }
            session = sf.openSession(dbcon);
            wrapper.setSession(dataSourceName, session);
        }
        if (withTransaction)
        {
            wrapper.enableTransaction(dataSourceName);
        }
        return session;
    }

    /**
     * Overwrite or implement method getSessionFactory()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#getSessionFactory(java.lang.String)
     */
    public SessionFactory getSessionFactory(String dataSourceName)
            throws Exception
    {
        return (SessionFactory) sessionFactories.get(dataSourceName);
    }

    /**
     * Override method DefaultHibernateFactory in supper class
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        Enumeration enu = getClass().getClassLoader().getResources(
                "META-INF/evavi/hibernate-entities.properties");
        ExtendedProperties props = new ExtendedProperties();
        while (enu.hasMoreElements())
        {
            URL resource = (URL) enu.nextElement();
            props.load(resource.openStream());
        }

        entityClasses = new TreeSet(new ClassComparator());

        for (Iterator i = hibernateProperties.keySet().iterator(); i.hasNext();)
        {
            String name = (String) i.next();

            org.hibernate.cfg.Configuration c = new org.hibernate.cfg.Configuration();
            Properties p = (Properties) hibernateProperties.get(name);
            c.setProperties(p);
            for (Iterator j = props.getKeys(); j.hasNext();)
            {
                String key = (String) j.next();
                String value = (String) props.getString(key);

                if (StringUtils.equals(value, name))
                {
                    try
                    {
                        Class entityClass = Class.forName(key);
                        c.addClass(entityClass);
                        entityClasses.add(entityClass);
                    }
                    catch (Exception e)
                    {
                        getLogger().warn("Entity " + key + " is not loaded", e);
                    }
                }
            }

            SessionFactory sessionFactory = c.buildSessionFactory();
            sessionFactories.put(name, sessionFactory);
        }
    }

    /**
     * Overwrite or implement method rollbackTransaction()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#rollbackTransaction()
     */
    public void rollbackTransaction() throws Exception
    {
        rollbackTransaction(DEFAULT_DATASOURCE);
    }

    /**
     * Overwrite or implement method rollbackTransaction()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#rollbackTransaction(java.lang.String)
     */
    public void rollbackTransaction(String dataSourceName) throws Exception
    {
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        if (wrapper != null)
        {
            Transaction transaction = wrapper.getTransaction(dataSourceName);
            if (transaction != null)
            {
                transaction.rollback();
            }
            Connection dbcon = wrapper.getConnection(dataSourceName);
            dbcon.rollback();
        }
    }

    /**
     * Overwrite or implement method rollbackTransactions()
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateHome#rollbackTransactions()
     */
    public void rollbackTransactions() throws Exception
    {
        SessionWrapper wrapper = (SessionWrapper) localSession.get();
        if (wrapper != null)
        {
            String[] names = wrapper.getDataSourceNames();
            for (int i = 0; i < names.length; i++)
            {
                try
                {
                    rollbackTransaction(names[i]);
                }
                catch (Exception e)
                {
                    getLogger().warn("Can not close hibernate session", e);
                }
            }
        }
    }

    /**
     * Override method DefaultHibernateFactory in supper class
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        dataSourceManager = (DataSourceManager) serviceManager
                .lookup(DataSourceManager.ROLE);
    }
}
