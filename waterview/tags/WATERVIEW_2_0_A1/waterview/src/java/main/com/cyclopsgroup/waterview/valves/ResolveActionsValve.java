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
package com.cyclopsgroup.waterview.valves;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.collections.map.ListOrderedMap;

import com.cyclopsgroup.waterview.ActionResolver;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.PipelineContext;
import com.cyclopsgroup.waterview.Valve;

/**
 * Valve to run modules
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ResolveActionsValve extends AbstractLogEnabled implements Valve,
        Serviceable, Configurable, Initializable
{

    private Map actionResolverRoles = ListOrderedMap.decorate(new HashMap());

    private Map actionResolvers = ListOrderedMap.decorate(new Hashtable());

    private ServiceManager serviceManager;

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        Configuration[] confs = conf.getChild("resolvers").getChildren(
                "resolver");
        for (int i = 0; i < confs.length; i++)
        {
            Configuration c = confs[i];
            String pattern = c.getAttribute("pattern");
            String role = c.getAttribute("role");
            actionResolverRoles.put(pattern, role);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        for (Iterator i = actionResolverRoles.keySet().iterator(); i.hasNext();)
        {
            String pattern = (String) i.next();
            String role = (String) actionResolverRoles.get(pattern);
            ActionResolver resolver = (ActionResolver) serviceManager
                    .lookup(role);
            registerActionResolver(pattern, resolver);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Valve#invoke(com.cyclopsgroup.waterview.PageRuntime, com.cyclopsgroup.waterview.PipelineContext)
     */
    public void invoke(PageRuntime runtime, PipelineContext context)
            throws Exception
    {
        for (Iterator i = runtime.getActions().iterator(); i.hasNext();)
        {
            String actionName = (String) i.next();
            for (Iterator j = actionResolvers.keySet().iterator(); j.hasNext();)
            {
                String pattern = (String) j.next();
                if (Pattern.matches('^' + pattern + '$', actionName))
                {
                    ActionResolver resolver = (ActionResolver) actionResolvers
                            .get(pattern);
                    resolver.resolveAction(actionName, runtime);
                    break;
                }
            }
        }
        context.invokeNextValve(runtime);
    }

    /**
     * Register an action resolver
     *
     * @param pattern Action pattern
     * @param resolver Resolver object
     */
    public void registerActionResolver(String pattern, ActionResolver resolver)
    {
        actionResolvers.put(pattern, resolver);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        this.serviceManager = serviceManager;
    }
}
