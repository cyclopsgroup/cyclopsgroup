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
package com.cyclopsgroup.tornado.components.hibernate;

import java.net.URL;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

import org.apache.avalon.framework.activity.Disposable;
import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cyclopsgroup.clib.site.db.DataSourceFactory;

/**
 * Default implementation of session provider
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ThreadBasedHibernateFactory extends AbstractLogEnabled implements
        HibernateFactory, Disposable, Configurable, Initializable, Serviceable
{
    private String dataSourceRole;

    private ThreadLocal localConnection = new ThreadLocal();

    private ThreadLocal localSession = new ThreadLocal();

    private Properties properties = new Properties();

    private ServiceManager serviceManager;

    private SessionFactory sessionFactory;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.hibernate.HibernateFactory#closeCurrentSession()
     */
    public void closeCurrentSession() throws Exception
    {
        Session session = (Session) localSession.get();
        if (session != null)
        {
            session.close();
            localSession.set(null);
        }
        Connection dbcon = (Connection) localConnection.get();
        if (dbcon != null && !dbcon.isClosed())
        {
            dbcon.close();
        }
        localConnection.set(null);
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
        Configuration[] props = conf.getChild("properties").getChildren(
                "property");
        for (int i = 0; i < props.length; i++)
        {
            Configuration prop = props[i];
            String propName = prop.getAttribute("name");
            String value = prop.getValue(StringUtils.EMPTY);
            setProperty(propName, value);
        }
        dataSourceRole = conf.getChild("data-source")
                .getAttribute("role", null);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Disposable#dispose()
     */
    public synchronized void dispose()
    {
        if (sessionFactory != null)
        {
            sessionFactory.close();
            sessionFactory = null;
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.site.hibernate.HibernateFactory#getCurrentSession()
     */
    public synchronized Session getCurrentSession() throws Exception
    {
        Session session = (Session) localSession.get();
        if (session != null && !session.isOpen())
        {
            session = null;
        }
        if (session == null)
        {
            Connection dbcon = null;
            if (StringUtils.isNotEmpty(dataSourceRole))
            {
                DataSourceFactory dsp = (DataSourceFactory) serviceManager
                        .lookup(dataSourceRole);
                dbcon = dsp.getDataSource().getConnection();
                localConnection.set(dbcon);
            }
            session = dbcon == null ? getSessionFactory().openSession()
                    : getSessionFactory().openSession(dbcon);
            localSession.set(session);
        }
        return session;
    }

    /**
     * Get hibernate properties
     *
     * @return Hibernate properties
     */
    public Properties getProperties()
    {
        return properties;
    }

    /**
     * Overwrite or implement method getSessionFactory()
     * @see com.cyclopsgroup.tornado.components.hibernate.HibernateFactory#getSessionFactory()
     */
    public SessionFactory getSessionFactory() throws Exception
    {
        return sessionFactory;
    }

    /**
     * Overwrite or implement method hasCurrentSession()
     * @see com.cyclopsgroup.tornado.components.hibernate.HibernateFactory#hasCurrentSession()
     */
    public boolean hasCurrentSession()
    {
        return localSession.get() != null;
    }

    /**
     * Overwrite or implement method initialize()
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        Enumeration enu = getClass().getClassLoader().getResources(
                "META-INF/cyclopsgroup/hibernate-entities.properties");
        Properties entityProps = new Properties();

        while (enu.hasMoreElements())
        {
            URL resource = (URL) enu.nextElement();
            entityProps.load(resource.openStream());
        }
        org.hibernate.cfg.Configuration c = new org.hibernate.cfg.Configuration();
        c.setProperties(properties);
        for (Iterator i = entityProps.keySet().iterator(); i.hasNext();)
        {
            String key = (String) i.next();
            String value = entityProps.getProperty(key);
            try
            {
                if (StringUtils.equals("class", value))
                {
                    Class entityClass = Class.forName(key);
                    c.addClass(entityClass);
                }
                else if (StringUtils.equals("file", value))
                {
                    URL mappingFile = getClass().getClassLoader().getResource(
                            key);
                    if (mappingFile == null)
                    {
                        getLogger().warn(
                                "Mapping file " + key
                                        + " doesn't exist in classpath");
                    }
                    else
                    {
                        c.addURL(mappingFile);
                    }
                }
            }
            catch (Exception e)
            {
                getLogger().warn(
                        "Entity definition " + key + "=" + value
                                + " is invalid", e);
            }
        }
        sessionFactory = c.buildSessionFactory();
    }

    /**
     * Overwrite or implement method service()
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        this.serviceManager = serviceManager;
    }

    /**
     * Set hibernate property
     *
     * @param propertyName Name of property
     * @param propertyValue Property value
     */
    public void setProperty(String propertyName, String propertyValue)
    {
        properties.setProperty(propertyName, propertyValue);
    }
}
