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
import java.util.Map;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionResolver;
import com.cyclopsgroup.waterview.Page;
import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Class implemented action resolver
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ClassActionResolver extends AbstractLogEnabled implements
        ActionResolver, Configurable
{
    private Map cache;

    private boolean cacheDisabled = false;

    /**
     * Override or implement method of parent class or interface
     * 
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        cacheDisabled = conf.getChild("cache-disabled")
                .getValueAsBoolean(false);
        int cacheSize = conf.getChild("cache-size").getValueAsInteger(-1);
        if (cacheSize > 1)
        {
            cache = new LRUMap(cacheSize);
        }
        else
        {
            cache = new Hashtable();
        }
    }

    private void execute(Object object, UIRuntime runtime, Context context)
            throws Exception
    {
        if (object instanceof Action)
        {
            ((Action) object).execute(runtime);
        }
        if (object instanceof Page)
        {
            ((Page) object).prepare(runtime, context);
        }
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see com.cyclopsgroup.waterview.ActionResolver#exists(java.lang.String, java.lang.String)
     */
    public boolean exists(String packageName, String moduleName)
    {
        String className = getClassName(packageName, moduleName);
        if (!cacheDisabled && cache.containsKey(className))
        {
            return true;
        }
        try
        {
            Class.forName(className);
            return true;
        }
        catch (ClassNotFoundException e)
        {
            return false;
        }
    }

    private String getClassName(String packageName, String moduleName)
    {
        String className = StringUtils.EMPTY;
        String modulePath = moduleName.replace('/', '.');
        if (StringUtils.isEmpty(packageName))
        {
            if (modulePath.charAt(0) == '.')
            {
                className = modulePath.substring(1);
            }
            else
            {
                className = modulePath;
            }
        }
        else
        {
            if (modulePath.charAt(0) != '.')
            {
                modulePath = '.' + modulePath;
            }
            className = packageName + modulePath;
        }
        return className;
    }

    /**
     * Override method resolve in super class of ClassActionResolver
     * 
     * @see com.cyclopsgroup.waterview.ActionResolver#resolve(java.lang.String, java.lang.String, com.cyclopsgroup.waterview.UIRuntime, com.cyclopsgroup.cyclib.Context)
     */
    public void resolve(String packageName, String moduleName,
            UIRuntime runtime, Context context) throws Exception
    {
        String className = getClassName(packageName, moduleName);
        if (!cacheDisabled && cache.containsKey(className))
        {
            Object object = (Action) cache.get(className);
            execute(object, runtime, context);
        }
        else
        {
            Object object = Class.forName(className).newInstance();
            if (!cacheDisabled)
            {
                cache.put(className, object);
            }
            execute(object, runtime, context);
        }
    }
}