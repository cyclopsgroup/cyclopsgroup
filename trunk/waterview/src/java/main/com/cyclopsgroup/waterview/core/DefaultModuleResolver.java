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
import java.util.Map;
import java.util.Vector;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.lang.ArrayUtils;

import com.cyclopsgroup.waterview.ModuleResolver;
import com.cyclopsgroup.waterview.UIModule;
import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Default implementation of action resolver
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultModuleResolver extends AbstractLogEnabled implements
        ModuleResolver, Configurable
{

    private Map moduleCache;

    private Vector modulePackages;

    /**
     * Override method configure in super class of DefaultUIModuleResolver
     * 
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        int cacheSize = conf.getChild("cache-size").getValueAsInteger(0);
        if (cacheSize > 1)
        {
            moduleCache = new LRUMap(cacheSize);
        }
        else
        {
            moduleCache = null;
        }
        Configuration[] packages = conf.getChild("packages").getChildren(
                "package");
        modulePackages = new Vector(packages.length);
        for (int i = 0; i < packages.length; i++)
        {
            Configuration packageCon = packages[i];
            modulePackages.add(packageCon.getValue());
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleResolver#getModulePackages()
     */
    public String[] getModulePackages()
    {
        return (String[]) modulePackages.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleResolver#resolve(com.cyclopsgroup.waterview.UIRuntime, java.lang.String)
     */
    public void resolve(UIRuntime runtime, String path) throws Exception
    {
        String requestedPath = path;
        UIModule module = null;
        if (moduleCache != null && moduleCache.containsKey(path))
        {
            module = (UIModule) moduleCache.get(path);
        }

        if (module == null)
        {
            int lastDot = path.lastIndexOf('.');
            if (lastDot > 0)
            {
                path = path.substring(0, lastDot);
            }
            path = path.replace('/', '.');
            for (Iterator i = modulePackages.iterator(); i.hasNext();)
            {
                String packageName = (String) i.next();
                String fullName = packageName + "." + path;
                try
                {
                    module = (UIModule) Class.forName(fullName).newInstance();
                    break;
                }
                catch (ClassNotFoundException e)
                {
                    //do nothing
                }
            }
            if (module != null && moduleCache != null)
            {
                moduleCache.put(requestedPath, module);
            }
        }
        if (module != null)
        {
            module.process(runtime);
        }
    }
}