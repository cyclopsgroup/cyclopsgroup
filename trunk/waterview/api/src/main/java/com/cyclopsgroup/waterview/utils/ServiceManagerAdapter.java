/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
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
package com.cyclopsgroup.waterview.utils;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.PlexusContainer;

/**
 * Simple adapter for service manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ServiceManagerAdapter
    implements ServiceManager
{
    private PlexusContainer container;

    private Log logger = LogFactory.getLog( getClass() );

    /**
     * Constructor of ServiceManagerAdapter
     * 
     * @param container Plexus container instance
     */
    public ServiceManagerAdapter( PlexusContainer container )
    {
        this.container = container;
    }

    /**
     * Override method hasService in super class of ServiceManagerAdapter
     * 
     * @see org.apache.avalon.framework.service.ServiceManager#hasService(java.lang.String)
     */
    public boolean hasService( String role )
    {
        return container.hasComponent( role );
    }

    /**
     * Override method lookup in super class of ServiceManagerAdapter
     * 
     * @see org.apache.avalon.framework.service.ServiceManager#lookup(java.lang.String)
     */
    public Object lookup( String role )
        throws ServiceException
    {
        try
        {
            return container.lookup( role );
        }
        catch ( Exception e )
        {
            throw new ServiceException( role, "Can not find component", e );
        }
    }

    /**
     * Override method release in super class of ServiceManagerAdapter
     * 
     * @see org.apache.avalon.framework.service.ServiceManager#release(java.lang.Object)
     */
    public void release( Object service )
    {
        try
        {
            container.release( service );
        }
        catch ( Exception e )
        {
            logger.warn( "Can not release component " + service, e );
        }
    }
}