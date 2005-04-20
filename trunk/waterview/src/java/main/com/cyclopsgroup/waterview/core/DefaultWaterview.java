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
package com.cyclopsgroup.waterview.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Valve;
import com.cyclopsgroup.waterview.Waterview;

/**
 * Default implementation of waterview
 *
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class DefaultWaterview extends AbstractLogEnabled implements Waterview,
Configurable, Serviceable, Initializable
{

    private Valve firstValve;

    private ServiceManager serviceManager;

    private List valveRoles;

    /**
     * Override method configure in super class of DefaultWaterview
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        Configuration[] valveConfs = conf.getChild("pipeline").getChildren("valve");
        valveRoles = new ArrayList(valveConfs.length);
        for (int i = 0; i < valveConfs.length; i++)
        {
            String valveRole = valveConfs[i].getAttribute("role");
            valveRoles.add(valveRole);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        boolean start = true;
        Valve previous = null;
        for (Iterator i = valveRoles.iterator(); i.hasNext();)
        {
            String valveRole = (String) i.next();
            Valve valve = (Valve) serviceManager.lookup(valveRole);
            if (start)
            {
                start = false;
                firstValve = valve;
            }
            else
            {
                previous.setNext(valve);
            }
            previous = valve;
        }
    }

    /**
     * Override method process in super class of DefaultWaterview
     *
     * @see com.cyclopsgroup.waterview.Waterview#process(com.cyclopsgroup.waterview.UIRuntime)
     */
    public void process(UIRuntime runtime) throws Exception
    {
        if (firstValve != null)
        {
            firstValve.invoke(runtime);
        }
    }

    /**
     * Override method service in super class of DefaultWaterview
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager sm) throws ServiceException
    {
        serviceManager = sm;
    }
}