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

import java.util.HashSet;
import java.util.Iterator;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.ModuleResolver;
import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Valve;

/**
 * Valve to run modules
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class RunActionsValve extends Valve implements Serviceable, Configurable
{

    private HashSet actionExtensions;

    private HashSet actionParameters;

    private String moduleCategory;

    private ModuleResolver moduleResolver;

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        Configuration[] confs = conf.getChildren("parameter-action");
        moduleCategory = conf.getChild("module-category").getValue("action");
        actionParameters = new HashSet(confs.length);
        for (int i = 0; i < confs.length; i++)
        {
            Configuration c = confs[i];
            actionParameters.add(c.getAttribute("parameter"));
        }
        confs = conf.getChildren("extension-action");
        actionExtensions = new HashSet(confs.length);
        for (int i = 0; i < confs.length; i++)
        {
            Configuration c = confs[i];
            actionParameters.add(c.getAttribute("extension"));
        }
    }

    /**
     * Override method process in super class of RunResolverValve
     * 
     * @see com.cyclopsgroup.waterview.Valve#invoke(com.cyclopsgroup.waterview.UIRuntime)
     */
    public void invoke(UIRuntime runtime) throws Exception
    {
        for (Iterator i = actionParameters.iterator(); i.hasNext();)
        {
            String param = (String) i.next();
            String action = runtime.getRequestParameters().getString(param);
            if (StringUtils.isNotEmpty(action))
            {
                moduleResolver.resolve(runtime, moduleCategory + '/' + action);
            }
        }
        for (Iterator i = actionExtensions.iterator(); i.hasNext();)
        {
            String suffix = '.' + (String) i.next();
            for (Iterator j = runtime.getActions().iterator(); j.hasNext();)
            {
                String action = (String) j.next();
                if (action.endsWith(suffix))
                {
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
        moduleResolver = (ModuleResolver) manager.lookup(ModuleResolver.ROLE);
    }
}