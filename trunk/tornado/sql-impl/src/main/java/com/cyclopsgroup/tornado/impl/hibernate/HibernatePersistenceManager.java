/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 *
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.tornado.impl.hibernate;

import java.io.Serializable;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.hibernate.Session;

import com.cyclopsgroup.tornado.PersistenceService;
import com.cyclopsgroup.tornado.hibernate.HibernateService;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Hibernate implementation of persistence manager
 */
public class HibernatePersistenceManager
    extends AbstractLogEnabled
    implements PersistenceService, Serviceable, Configurable
{
    private String dataSource = HibernateService.DEFAULT_DATASOURCE;

    private HibernateService hibernate;

    /**
     * Overwrite or implement method configure()
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration conf )
        throws ConfigurationException
    {
        dataSource = conf.getChild( "data-source" ).getValue( dataSource );
    }

    /**
     * Overwrite or implement method create()
     *
     * @see com.cyclopsgroup.tornado.persist.PersistenceManager#create(java.lang.Class)
     */
    public Object create( Class type )
        throws Exception
    {
        return type.newInstance();
    }

    /**
     * Overwrite or implement method create()
     *
     * @see com.cyclopsgroup.tornado.persist.PersistenceManager#create(java.lang.Class, java.io.Serializable)
     */
    public Object create( Class type, Serializable id )
        throws Exception
    {
        return create( type );
    }

    /**
     * Overwrite or implement method delete()
     *
     * @see com.cyclopsgroup.tornado.persist.PersistenceManager#delete(java.lang.Class, java.io.Serializable)
     */
    public void delete( Class type, Serializable id )
        throws Exception
    {
        Object entity = find( type, id );
        if ( entity != null )
        {
            delete( entity );
        }
    }

    /**
     * Overwrite or implement method delete()
     *
     * @see com.cyclopsgroup.tornado.persist.PersistenceManager#delete(java.lang.Object)
     */
    public void delete( Object entity )
        throws Exception
    {
        getSession().delete( entity );
    }

    /**
     * Overwrite or implement method find()
     *
     * @see com.cyclopsgroup.tornado.persist.PersistenceManager#find(java.lang.Class, java.io.Serializable)
     */
    public Object find( Class type, Serializable id )
        throws Exception
    {
        return getSession().get( type, id );
    }

    private Session getSession()
        throws Exception
    {
        return hibernate.getSession( dataSource );
    }

    /**
     * Overwrite or implement method load()
     *
     * @see com.cyclopsgroup.tornado.persist.PersistenceManager#load(java.lang.Class, java.io.Serializable)
     */
    public Object load( Class type, Serializable id )
        throws Exception
    {
        return getSession().load( type, id );
    }

    /**
     * Overwrite or implement method save()
     *
     * @see com.cyclopsgroup.tornado.persist.PersistenceManager#save(java.lang.Object)
     */
    public void save( Object entity )
        throws Exception
    {
        getSession().saveOrUpdate( entity );
    }

    /**
     * Overwrite or implement method saveNew()
     *
     * @see com.cyclopsgroup.tornado.persist.PersistenceManager#saveNew(java.lang.Object)
     */
    public void saveNew( Object entity )
        throws Exception
    {
        getSession().save( entity );
    }

    /**
     * Overwrite or implement method service()
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        hibernate = (HibernateService) serviceManager.lookup( HibernateService.ROLE );
    }

    /**
     * Overwrite or implement method update()
     *
     * @see com.cyclopsgroup.tornado.persist.PersistenceManager#update(java.lang.Object)
     */
    public void update( Object entity )
        throws Exception
    {
        getSession().update( entity );
    }
}
