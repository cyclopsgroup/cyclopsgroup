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

import java.util.Iterator;
import java.util.Vector;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.ArrayUtils;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.waterview.ActionResolver;
import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Default implementation of action resolver
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ModuleResolver extends AbstractLogEnabled implements Configurable,
        Serviceable
{
    /** Role name in container */
    public static final String ROLE = ModuleResolver.class.getName();

    private ActionResolver actionResolver;

    private String[] modulePackageNames;

    private Vector modulePackages;

    private ServiceManager serviceManager;

    /**
     * Override or implement method of parent class or interface
     * 
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        Configuration[] packages = conf.getChild("packages").getChildren(
                "package");
        modulePackages = new Vector(packages.length);
        for (int i = 0; i < packages.length; i++)
        {
            Configuration packageCon = packages[i];
            modulePackages.add(packageCon.getValue());
        }

        modulePackageNames = (String[]) modulePackages
                .toArray(ArrayUtils.EMPTY_STRING_ARRAY);

        String actionResolverRole = conf.getChild("action-resolver")
                .getAttribute("role");
        try
        {
            actionResolver = (ActionResolver) serviceManager
                    .lookup(actionResolverRole);
        }
        catch (Exception e)
        {
            getLogger().error("Can not get action resolver", e);
        }
    }

    /**
     * Get package names
     * 
     * @return String array of package name
     */
    public String[] getModulePackages()
    {
        return modulePackageNames;
    }

    /**
     * Resolve a given path with default resolver
     * 
     * @param path Path
     * @param runtime Runtime object
     * @param context Context object
     * @throws Exception
     */
    public void resolve(String path, UIRuntime runtime, Context context)
            throws Exception
    {
        for (Iterator i = modulePackages.iterator(); i.hasNext();)
        {
            String packageName = (String) i.next();
            if (actionResolver.exists(packageName, path))
            {
                actionResolver.resolve(packageName, path, runtime, context);
                break;
            }
        }
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager manager) throws ServiceException
    {
        serviceManager = manager;
    }
}