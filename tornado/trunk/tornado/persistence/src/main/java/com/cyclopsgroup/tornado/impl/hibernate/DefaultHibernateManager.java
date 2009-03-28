/* ==========================================================================
 * Copyright 2002-2006 Cyclops Group Software Foundation
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

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.tornado.hibernate.HibernateManager;
import com.cyclopsgroup.tornado.hibernate.HibernateService;
import com.cyclopsgroup.tornado.hibernate.NoSuchHibernateConfiguredException;

/**
 * Default implementation of hibernate manager
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 */
public class DefaultHibernateManager
    extends AbstractLogEnabled
    implements HibernateManager, Configurable, Serviceable, Initializable
{
    private HashSet<String> hibernateServiceRoles = new HashSet<String>();

    private Hashtable<String, HibernateService> hibernateServices = new Hashtable<String, HibernateService>();

    private ServiceManager serviceManager;

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateManager#closeSessions()
     */
    public void closeSessions()
        throws Exception
    {
        for ( HibernateService hs : hibernateServices.values() )
        {
            hs.closeSession();
        }
    }

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateManager#commitTransaction()
     */
    public void commitTransaction()
        throws Exception
    {
        for ( HibernateService hs : hibernateServices.values() )
        {
            hs.commitTransaction();
        }
    }

    /**
     * Overwrite or implement parent method
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration conf )
        throws ConfigurationException
    {
        Configuration[] confs = conf.getChild( "hibernate-services" ).getChildren( "service" );
        for ( int i = 0; i < confs.length; i++ )
        {
            Configuration hibernateConf = confs[i];
            String role = hibernateConf.getAttribute( "role" );
            hibernateServiceRoles.add( role );
        }
    }

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateManager#getDefaultHibernateService()
     */
    public HibernateService getDefaultHibernateService()
        throws NoSuchHibernateConfiguredException
    {
        return getHibernateService( DEFAULT_HIBERNATE );
    }

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateManager#getHibernateService(java.lang.String)
     */
    public HibernateService getHibernateService( String name )
        throws NoSuchHibernateConfiguredException
    {
        HibernateService hs = hibernateServices.get( name );
        if ( hs == null )
        {
            throw new NoSuchHibernateConfiguredException( name );
        }
        return hs;
    }

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateManager#getServiceNames()
     */
    public Set<String> getServiceNames()
    {
        return hibernateServices.keySet();
    }

    /**
     * Overwrite or implement parent method
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        for ( String role : hibernateServiceRoles )
        {
            HibernateService service = (HibernateService) serviceManager.lookup( role );
            hibernateServices.put( service.getName(), service );
        }
    }

    /**
     * Overwrite or implement parent method
     *
     * @see com.cyclopsgroup.tornado.hibernate.HibernateManager#rollbackTransaction()
     */
    public void rollbackTransaction()
        throws Exception
    {
        for ( HibernateService hs : hibernateServices.values() )
        {
            hs.rollbackTransaction();
        }
    }

    /**
     * Overwrite or implement parent method
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        this.serviceManager = serviceManager;
    }
}
