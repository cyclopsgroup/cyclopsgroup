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
import java.util.Map;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.ActionResolver;
import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Valve;

/**
 * Valve to run modules
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class RunActionsValve extends Valve implements Serviceable, Configurable
{

    private Map extensionActionResolvers = ListOrderedMap
            .decorate(new Hashtable());

    private String moduleCategory;

    private ModuleResolver moduleResolver;

    private Map parameterActionResolvers = ListOrderedMap
            .decorate(new Hashtable());

    private ServiceManager serviceManager;

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        moduleCategory = conf.getChild("module-category").getValue("action");

        Configuration[] resolvers = conf.getChildren("resolver");
        for (int i = 0; i < resolvers.length; i++)
        {
            Configuration resolverConf = resolvers[i];
            String ext = resolverConf.getAttribute("extension", null);
            String param = resolverConf.getAttribute("parameter", null);
            String role = resolverConf.getAttribute("role");
            try
            {
                ActionResolver resolver = (ActionResolver) serviceManager
                        .lookup(role);
                if (ext != null)
                {
                    extensionActionResolvers.put(ext, resolver);
                }
                if (param != null)
                {
                    parameterActionResolvers.put(param, resolver);
                }
            }
            catch (Exception e)
            {
                getLogger().error("Specified resolver not exists", e);
            }
        }
    }

    /**
     * Override method process in super class of RunResolverValve
     * 
     * @see com.cyclopsgroup.waterview.Valve#invoke(com.cyclopsgroup.waterview.UIRuntime)
     */
    public void invoke(UIRuntime runtime) throws Exception
    {
        String[] packageNames = moduleResolver.getModulePackages();
        for (Iterator i = parameterActionResolvers.keySet().iterator(); i
                .hasNext();)
        {
            String param = (String) i.next();
            String action = runtime.getRequestParameters().getString(param);
            action += moduleCategory + '/' + action;
            ActionResolver resolver = (ActionResolver) parameterActionResolvers
                    .get(param);
            if (StringUtils.isNotEmpty(action))
            {
                for (int j = 0; j < packageNames.length; j++)
                {
                    String packageName = packageNames[j];
                    if (resolver.exists(packageName, action))
                    {
                        resolver.resolve(packageName, action, runtime);
                        break;
                    }
                }
            }
        }
        for (Iterator i = extensionActionResolvers.keySet().iterator(); i
                .hasNext();)
        {
            String ext = (String) i.next();
            ActionResolver resolver = (ActionResolver) extensionActionResolvers
                    .get(ext);
            String suffix = '.' + ext;
            for (Iterator j = runtime.getActions().iterator(); j.hasNext();)
            {
                String action = moduleCategory + '/' + (String) j.next();
                if (action.endsWith(suffix))
                {
                    for (int k = 0; k < packageNames.length; k++)
                    {
                        String packageName = packageNames[k];
                        if (resolver.exists(packageName, action))
                        {
                            resolver.resolve(packageName, action, runtime);
                            break;
                        }
                    }
                    String module = StringUtils.chomp(action);
                    moduleResolver.resolve(runtime, moduleCategory + '/'
                            + module);
                }
            }
        }
        invokeNext(runtime);
    }

    /**
     * Override method service in super class of RunActionsValve
     * 
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager manager) throws ServiceException
    {
        serviceManager = manager;
        moduleResolver = (ModuleResolver) manager.lookup(ModuleResolver.ROLE);
    }
}