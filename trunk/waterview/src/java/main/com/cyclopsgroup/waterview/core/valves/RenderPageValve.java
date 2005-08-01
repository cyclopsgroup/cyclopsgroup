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
package com.cyclopsgroup.waterview.core.valves;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.collections.map.ListOrderedMap;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.CacheManager;
import com.cyclopsgroup.waterview.spi.DynaViewFactory;
import com.cyclopsgroup.waterview.spi.ModuleManager;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.Valve;
import com.cyclopsgroup.waterview.spi.View;

/**
 * Valve to render page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class RenderPageValve extends AbstractLogEnabled implements Valve,
        Serviceable
{

    private class CachedViewFactory implements DynaViewFactory
    {
        private DynaViewFactory proxy;

        private CachedViewFactory(DynaViewFactory proxy)
        {
            this.proxy = proxy;
        }

        /**
         * Overwrite or implement method createView()
         * @see com.cyclopsgroup.waterview.spi.DynaViewFactory#createView(java.lang.String, java.lang.String, com.cyclopsgroup.waterview.RuntimeData)
         */
        public synchronized View createView(String packageName,
                String viewPath, RuntimeData runtime) throws Exception
        {
            String key = proxy.hashCode() + '/' + packageName + '/' + viewPath;
            if (getCacheManager().contains(this, key))
            {
                return (View) getCacheManager().get(this, key);
            }
            View view = proxy.createView(packageName, viewPath, runtime);
            getCacheManager().put(this, key, view);
            return view;
        }
    }

    /** Role name of this valve*/
    public static final String ROLE = RenderPageValve.class.getName();

    private CacheManager cacheManager;

    private Map viewFactories = ListOrderedMap.decorate(new Hashtable());

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
     * @see com.cyclopsgroup.waterview.spi.Valve#invoke(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.PipelineContext)
     */
    public void invoke(RuntimeData runtime, PipelineContext context)
            throws Exception
    {
        DynaViewFactory viewFactory = null;
        for (Iterator i = viewFactories.keySet().iterator(); i.hasNext();)
        {
            String pattern = (String) i.next();
            if (Pattern.matches('^' + pattern + '$', runtime.getPage()))
            {
                viewFactory = (DynaViewFactory) viewFactories.get(pattern);
                break;
            }
        }
        if (viewFactory != null)
        {
            runtime.getPageContext().put(DynaViewFactory.NAME, viewFactory);
        }

        runtime.setOutputContentType("text/html");
        ModuleManager mm = (ModuleManager) runtime.getServiceManager().lookup(
                ModuleManager.ROLE);
        Page page = (Page) runtime.getPageContext().get(Page.NAME);
        if (page != null)
        {
            page.execute(runtime, runtime.getPageContext());
            mm.getDefaultFrame().execute(runtime, runtime.getPageContext());
            mm.getDefaultFrame().display(page, runtime);
        }
        context.invokeNextValve(runtime);
        runtime.getOutput().flush();
    }

    /**
     * Register a view factory
     *
     * @param pattern Pattern of page
     * @param viewFactory View factory object
     */
    public void registerViewFactory(String pattern, DynaViewFactory viewFactory)
    {
        viewFactories.put(pattern, new CachedViewFactory(viewFactory));
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
}
