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

import java.util.Vector;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.Page;
import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Default implementation of action resolver
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ModuleManager extends AbstractLogEnabled implements Configurable
{
    /** Role name in container */
    public static final String ROLE = ModuleManager.class.getName();

    private String[] modulePackageNames = ArrayUtils.EMPTY_STRING_ARRAY;

    private Vector modulePackages = new Vector();

    /**
     * Add module package
     *
     * @param packageName Package name
     */
    public void addModulePackage(String packageName)
    {
        modulePackages.add(packageName);
        modulePackageNames = (String[]) modulePackages
                .toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        Configuration[] packages = conf.getChild("packages").getChildren(
                "package");
        for (int i = 0; i < packages.length; i++)
        {
            Configuration packageCon = packages[i];
            addModulePackage(packageCon.getValue());
        }
    }

    /**
     * Get action module object
     *
     * @param moduleName Path of module
     * @return Action object or null if not found
     */
    public Action getActionModule(String moduleName)
    {
        Action action = null;
        for (int i = 0; i < modulePackageNames.length; i++)
        {
            String packageName = modulePackageNames[i];
            action = getActionModule(packageName, moduleName);
            if (action != null)
            {
                break;
            }
        }
        return action;
    }

    /**
     * Get action module object
     *
     * @param packageName Package name
     * @param moduleName Path of module
     * @return Action object or null if not found
     */
    public Action getActionModule(String packageName, String moduleName)
    {
        Object module = getModule(packageName, moduleName);
        return (module instanceof Action) ? (Action) module : null;
    }

    private Object getModule(String packageName, String moduleName)
    {
        String className = moduleName.replace('/', '.');
        if (StringUtils.isNotEmpty(packageName))
        {
            if (className.charAt(0) == '.')
            {
                className = packageName + className;
            }
            else
            {
                className = packageName + '.' + className;
            }
        }
        try
        {
            return Class.forName(className).newInstance();
        }
        catch (Exception e)
        {
            return null;
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
     * Get page module object
     *
     * @param moduleName Path of module
     * @return Page object or null if not found
     */
    public Page getPageModule(String moduleName)
    {
        Page page = null;
        for (int i = 0; i < modulePackageNames.length; i++)
        {
            String packageName = modulePackageNames[i];
            page = getPageModule(packageName, moduleName);
            if (page != null)
            {
                break;
            }
        }
        return page;
    }

    /**
     * Get page module object
     *
     * @param packageName Package name
     * @param moduleName Path of module
     * @return Page object or null if not found
     */
    public Page getPageModule(String packageName, String moduleName)
    {
        Object module = getModule(packageName, moduleName);
        return (module instanceof Page) ? (Page) module : null;
    }

    /**
     * Remove package name
     *
     * @param packageName Package name
     */
    public void removeModulePackage(String packageName)
    {
        if (modulePackages.contains(packageName))
        {
            modulePackages.remove(packageName);
            modulePackageNames = (String[]) modulePackages
                    .toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        }
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param moduleName
     * @param runtime
     * @throws Exception
     */
    public void resolveAction(String moduleName, UIRuntime runtime)
            throws Exception
    {
        Action action = getActionModule(moduleName);
        if (action != null)
        {
            action.execute(runtime);
        }
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param moduleName
     * @param runtime
     * @param pageContext
     * @throws Exception
     */
    public void resolvePage(String moduleName, UIRuntime runtime,
            Context pageContext) throws Exception
    {
        Page page = getPageModule(moduleName);
        if (page != null)
        {
            page.prepare(runtime, pageContext);
        }
    }
}