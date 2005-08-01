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

import java.util.Hashtable;
import java.util.Iterator;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.spi.CacheManager;
import com.cyclopsgroup.waterview.spi.Frame;
import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.ModuleManager;

/**
 * Default implementation of module manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultModuleManager extends AbstractLogEnabled implements
        Configurable, ModuleManager, Serviceable
{
    private class DefaultPathModel implements PathModel
    {
        private String packageName, path;

        private DefaultPathModel(String packageName, String path)
        {
            this.packageName = packageName;
            this.path = path;
        }

        /**
         * Overwrite or implement method getPackage()
         * @see com.cyclopsgroup.waterview.spi.ModuleManager.PathModel#getPackage()
         */
        public String getPackage()
        {
            return packageName;
        }

        /**
         * Overwrite or implement method getPath()
         * @see com.cyclopsgroup.waterview.spi.ModuleManager.PathModel#getPath()
         */
        public String getPath()
        {
            return path;
        }

        /**
         * Overwrite or implement method toString()
         * @see java.lang.Object#toString()
         */
        public String toString()
        {
            return getPackage() + "|" + getPath();
        }
    }

    private CacheManager cacheManager;

    private String defaultFrameId = "waterview.DefaultDisplayFrame",
            defaultLayoutId = "waterview.DefaultLayout",
            defaultPackageName = "com.cyclopsgroup.waterview.ui";

    private Hashtable frames = new Hashtable(), layouts = new Hashtable(),
            packageNames = new Hashtable();

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        String layoutId = conf.getChild("default-layout").getValue(null);
        if (layoutId != null)
        {
            setDefaultLayoutId(layoutId);
        }
        String frameId = conf.getChild("default-frame").getValue(null);
        if (frameId != null)
        {
            setDefaultFrameId(frameId);
        }
        String defaultPackage = conf.getChild("default-package").getValue(null);
        if (defaultPackage != null)
        {
            setDefaultPackageName(defaultPackage);
        }
    }

    /**
     * Getter method for cacheManager
     *
     * @return Returns the cacheManager.
     */
    public CacheManager getCacheManager()
    {
        return cacheManager;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#getDefaultFrame()
     */
    public Frame getDefaultFrame()
    {
        return getFrame(getDefaultFrameId());
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#getDefaultFrameId()
     */
    public String getDefaultFrameId()
    {
        return defaultFrameId;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#getDefaultLayout()
     */
    public Layout getDefaultLayout()
    {
        return getLayout(getDefaultLayoutId());
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#getDefaultLayoutId()
     */
    public String getDefaultLayoutId()
    {
        return defaultLayoutId;
    }

    /**
     * @return Returns the defaultPackageName.
     */
    public String getDefaultPackageName()
    {
        return defaultPackageName;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#getFrame(java.lang.String)
     */
    public Frame getFrame(String frameId)
    {
        return (Frame) frames.get(frameId);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#getFrameIds()
     */
    public String[] getFrameIds()
    {
        return (String[]) frames.keySet()
                .toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#getLayout(java.lang.String)
     */
    public Layout getLayout(String layoutId)
    {
        return (Layout) layouts.get(layoutId);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#getLayoutIds()
     */
    public String[] getLayoutIds()
    {
        return (String[]) layouts.keySet().toArray(
                ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#getModule(java.lang.String)
     */
    public synchronized Module getModule(String modulePath)
    {
        if (getCacheManager().contains(this, modulePath))
        {
            return (Module) getCacheManager().get(this, modulePath);
        }
        Module ret = Module.EMPTY_MODULE;

        PathModel model = parsePath(modulePath);
        if (StringUtils.isNotEmpty(model.getPath()))
        {
            ret = getModule(model.getPackage(), model.getPath());
        }
        getCacheManager().put(this, modulePath, ret);
        return ret;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#getModule(java.lang.String, java.lang.String)
     */
    public Module getModule(String packageName, String modulePath)
    {
        Path pr = Path.parse(modulePath);
        String className = pr.getParentPath().replace('/', '.')
                + pr.getShortName();
        if (StringUtils.isNotEmpty(packageName))
        {
            className = packageName + '.' + className;
        }
        try
        {
            return (Module) Class.forName(className).newInstance();
        }
        catch (Exception ignored)
        {
            return null;
        }
    }

    /**
     * Overwrite or implement method getPackageName()
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#getPackageName(java.lang.String)
     */
    public String getPackageName(String aliasOrPackage)
    {
        if (packageNames.containsKey(aliasOrPackage))
        {
            return (String) packageNames.get(aliasOrPackage);
        }
        return aliasOrPackage;
    }

    /**
     * Overwrite or implement method parsePage()
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#parsePath(java.lang.String)
     */
    public PathModel parsePath(String page)
    {
        if (StringUtils.isEmpty(page))
        {
            return new DefaultPathModel(getDefaultPackageName(),
                    StringUtils.EMPTY);
        }
        String pagePackage = getDefaultPackageName();
        String path = page;
        String[] parts = StringUtils.split(page, '/');
        for (Iterator i = packageNames.keySet().iterator(); i.hasNext();)
        {
            String packageAlias = (String) i.next();
            String packageName = (String) packageNames.get(packageAlias);
            if (StringUtils.equals(parts[0], packageAlias)
                    || StringUtils.equals(parts[0], packageName))
            {
                pagePackage = packageName;
                path = page.substring(parts[0].length() + 1);
                break;
            }
        }
        return new DefaultPathModel(pagePackage, path);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#registerFrame(java.lang.String, com.cyclopsgroup.waterview.spi.Frame)
     */
    public void registerFrame(String frameId, Frame frame)
    {
        frames.put(frameId, frame);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#registerLayout(java.lang.String, com.cyclopsgroup.waterview.spi.Layout)
     */
    public void registerLayout(String layoutId, Layout layout)
    {
        layouts.put(layoutId, layout);
    }

    /**
     * Overwrite or implement method registerPackageAlias()
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#registerPackage(java.lang.String, java.lang.String)
     */
    public void registerPackage(String alias, String packageName)
    {
        packageNames.put(alias, packageName);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        CacheManager cm = (CacheManager) serviceManager
                .lookup(CacheManager.ROLE);
        setCacheManager(cm);
    }

    /**
     * Setter method for cacheManager
     *
     * @param cacheManager The cacheManager to set.
     */
    public void setCacheManager(CacheManager cacheManager)
    {
        this.cacheManager = cacheManager;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#setDefaultFrameId(java.lang.String)
     */
    public void setDefaultFrameId(String frameId)
    {
        defaultFrameId = frameId;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleManager#setDefaultLayoutId(java.lang.String)
     */
    public void setDefaultLayoutId(String layoutId)
    {
        defaultLayoutId = layoutId;
    }

    /**
     * @param defaultPackageName The defaultPackageName to set.
     */
    public void setDefaultPackageName(String defaultPackageName)
    {
        this.defaultPackageName = defaultPackageName;
    }
}
