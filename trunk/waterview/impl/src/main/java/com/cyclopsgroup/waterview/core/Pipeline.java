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
package com.cyclopsgroup.waterview.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * Pipe line component
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class Pipeline
    extends AbstractLogEnabled
    implements Configurable, Initializable, Serviceable
{
    private ServiceManager serviceManager;

    private transient List<String> valveRoles;

    private Vector<Valve> valves = new Vector<Valve>();

    /**
     * Add valve
     *
     * @param valve Valve to add
     */
    public void addValve( Valve valve )
    {
        valves.add( valve );
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration conf )
        throws ConfigurationException
    {
        valveRoles = new ArrayList<String>();
        Configuration[] confs = conf.getChild( "valves" ).getChildren( "valve" );
        for ( int i = 0; i < confs.length; i++ )
        {
            Configuration c = confs[i];
            String role = c.getAttribute( "role" );
            valveRoles.add( role );
        }
    }

    /**
     * Handle runtime
     *
     * @param runtime Page runtime object
     * @throws Exception Throw it out
     */
    public void processRunData( RunDataSpi data )
        throws Exception
    {
        DefaultPipelineContext dpc = new DefaultPipelineContext( valves );
        dpc.invokeValve( data );
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        valves = new Vector<Valve>();
        for ( String valveRole: valveRoles )
        {
            Valve valve = (Valve) serviceManager.lookup( valveRole );
            valves.add( valve );
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        this.serviceManager = serviceManager;
    }
}
