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

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.waterview.Resolver;
import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Waterview;

/**
 * Default implementation of waterview
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class DefaultWaterview extends AbstractLogEnabled implements Waterview,
        Configurable, Serviceable
{

    private Resolver defaultResolver;

    private Properties properties;

    private Hashtable resolvers;

    private ServiceManager serviceManager;

    private Vector valves;

    /**
     * Override method configure in super class of DefaultWaterview
     * 
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        properties = new Properties();
        properties.putAll(System.getProperties());

        Configuration[] valveConfs = conf.getChild("pipeline").getChildren(
                "valve");
        valves = new Vector();
        for (int i = 0; i < valveConfs.length; i++)
        {
            String valveName = valveConfs[i].getValue();
            try
            {
                Valve valve = (Valve) Class.forName(valveName).newInstance();
                valves.add(valve);
            }
            catch (Exception e)
            {
                getLogger().error("Valve " + valveName + " Can not be loaded ",
                        e);
            }
        }
        Configuration[] resolverConfs = conf.getChild("resolvers").getChildren(
                "resolver");
        resolvers = new Hashtable();
        for (int i = 0; i < resolverConfs.length; i++)
        {
            Configuration resolverConf = resolverConfs[i];
            try
            {
                Resolver resolver = (Resolver) Class.forName(
                        resolverConf.getValue()).newInstance();
                resolvers.put(resolverConf.getAttribute("extension"), resolver);
            }
            catch (Exception e)
            {
                getLogger().error("Create resolver error", e);
            }
        }
    }

    /**
     * Override method getDefaultResolver in super class of DefaultWaterview
     * 
     * @see com.cyclopsgroup.waterview.Waterview#getDefaultResolver()
     */
    public Resolver getDefaultResolver()
    {
        return defaultResolver;
    }

    /**
     * Override method getProperties in super class of DefaultWaterview
     * 
     * @see com.cyclopsgroup.waterview.Waterview#getProperties()
     */
    public Properties getProperties()
    {
        return properties;
    }

    /**
     * Override method getResolver in super class of DefaultWaterview
     * 
     * @see com.cyclopsgroup.waterview.Waterview#getResolver(java.lang.String)
     */
    public Resolver getResolver(String extension)
    {
        return (Resolver) resolvers.get(extension);
    }

    /**
     * Override method process in super class of DefaultWaterview
     * 
     * @see com.cyclopsgroup.waterview.Waterview#process(com.cyclopsgroup.waterview.UIRuntime)
     */
    public void process(UIRuntime runtime) throws Exception
    {
        for (Iterator i = valves.iterator(); i.hasNext();)
        {
            Valve valve = (Valve) i.next();
            valve.process(runtime);
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