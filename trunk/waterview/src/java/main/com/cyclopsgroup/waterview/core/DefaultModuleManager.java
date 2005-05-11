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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.CacheManager;
import com.cyclopsgroup.waterview.Frame;
import com.cyclopsgroup.waterview.Layout;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.ModuleManager;
import com.cyclopsgroup.waterview.utils.Path;

/**
 * Default implementation of module manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultModuleManager extends AbstractLogEnabled implements
        Configurable, ModuleManager, Serviceable
{

    private CacheManager cacheManager;

    private String defaultFrameId = "waterview.DefaultDisplayFrame";

    private String defaultLayoutId = "waterview.DefaultLayout";

    private Hashtable frames = new Hashtable();

    private Hashtable layouts = new Hashtable();

    private String[] packageArray = ArrayUtils.EMPTY_STRING_ARRAY;

    /**
     * Add given package
     *
     * @param packageName
     */
    public synchronized void addPackage(String packageName)
    {
        List pkgs = new ArrayList();
        CollectionUtils.addAll(pkgs, packageArray);
        if (pkgs.contains(packageName))
        {
            return;
        }
        pkgs.add(packageName);
        packageArray = (String[]) pkgs.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        Configuration[] pkgs = conf.getChild("packages").getChildren("package");
        for (int i = 0; i < pkgs.length; i++)
        {
            Configuration c = pkgs[i];
            addPackage(c.getValue());
        }
        addPackage("");
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
     * @see com.cyclopsgroup.waterview.ModuleManager#getDefaultFrame()
     */
    public Frame getDefaultFrame()
    {
        return getFrame(getDefaultFrameId());
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleManager#getDefaultFrameId()
     */
    public String getDefaultFrameId()
    {
        return defaultFrameId;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleManager#getDefaultLayout()
     */
    public Layout getDefaultLayout()
    {
        return getLayout(getDefaultLayoutId());
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleManager#getDefaultLayoutId()
     */
    public String getDefaultLayoutId()
    {
        return defaultLayoutId;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleManager#getFrame(java.lang.String)
     */
    public Frame getFrame(String frameId)
    {
        return (Frame) frames.get(frameId);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleManager#getFrameIds()
     */
    public String[] getFrameIds()
    {
        return (String[]) frames.keySet()
                .toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleManager#getLayout(java.lang.String)
     */
    public Layout getLayout(String layoutId)
    {
        return (Layout) layouts.get(layoutId);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleManager#getLayoutIds()
     */
    public String[] getLayoutIds()
    {
        return (String[]) layouts.keySet().toArray(
                ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleManager#getModule(java.lang.String)
     */
    public synchronized Module getModule(String modulePath)
    {
        if (getCacheManager().contains(this, modulePath))
        {
            return (Module) getCacheManager().get(this, modulePath);
        }
        Module ret = Module.EMPTY_MODULE;
        String[] packages = getPackageNames();
        for (int i = 0; i < packages.length; i++)
        {
            String packageName = packages[i];
            Module module = getModule(modulePath, packageName);
            if (module != null)
            {
                ret = module;
                break;
            }
        }
        getCacheManager().put(this, modulePath, ret);
        return ret;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleManager#getModule(java.lang.String, java.lang.String)
     */
    public Module getModule(String modulePath, String packageName)
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
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleManager#getPackageNames()
     */
    public String[] getPackageNames()
    {
        return packageArray;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleManager#registerFrame(java.lang.String, com.cyclopsgroup.waterview.Frame)
     */
    public void registerFrame(String frameId, Frame frame)
    {
        frames.put(frameId, frame);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleManager#registerLayout(java.lang.String, com.cyclopsgroup.waterview.Layout)
     */
    public void registerLayout(String layoutId, Layout layout)
    {
        layouts.put(layoutId, layout);
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
     * @see com.cyclopsgroup.waterview.ModuleManager#setDefaultFrameId(java.lang.String)
     */
    public void setDefaultFrameId(String frameId)
    {
        defaultFrameId = frameId;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ModuleManager#setDefaultLayoutId(java.lang.String)
     */
    public void setDefaultLayoutId(String layoutId)
    {
        defaultLayoutId = layoutId;
    }
}
