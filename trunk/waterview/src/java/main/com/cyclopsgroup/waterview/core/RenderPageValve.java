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
import java.util.ResourceBundle;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.waterview.PageNotFoundException;
import com.cyclopsgroup.waterview.PageRenderer;
import com.cyclopsgroup.waterview.RuntimePageRenderer;
import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Valve;

/**
 * Valve to render page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class RenderPageValve extends Valve implements Configurable, Serviceable
{
    /**
     * Implementation of runtime page renderer
     */
    public class RuntimeRenderer implements RuntimePageRenderer
    {
        private PageRenderer renderer;

        private UIRuntime runtime;

        private String suffix;

        private RuntimeRenderer(PageRenderer renderer, UIRuntime runtime,
                String extension)
        {
            this.renderer = renderer;
            this.runtime = runtime;
            this.suffix = '.' + extension;
            runtime.getUIContext().put(NAME, this);
        }

        private void render(Context context, String packageName,
                String modulePath, UIRuntime runtime) throws Exception
        {
            LocalizationTool parent = (LocalizationTool) context
                    .get(localizationName);
            LocalizationTool localization = new LocalizationTool(parent,
                    ResourceBundle.getBundle(packageName + "/" + modulePath
                            + "_ResourceBundle", runtime.getLocale()));
            context.put(localizationName, localization);
            renderer.render(context, packageName, modulePath, runtime);
            if (parent == null)
            {
                context.remove(localizationName);
            }
            else
            {
                context.put(localizationName, parent);
            }
        }

        /**
         * Render method
         * 
         * @param category Page package name
         * @param page Page path
         * @throws Exception Throw it out to page renderer
         */
        public void render(String category, String page) throws Exception
        {
            String module = StringUtils.chomp(page, suffix);
            String path = category + '/' + module;
            moduleResolver.resolve(path, runtime, runtime.getUIContext());
            String[] packages = moduleResolver.getModulePackages();
            boolean found = false;
            for (int i = 0; i < packages.length; i++)
            {
                String packageName = packages[i];
                if (renderer.exists(packageName, path))
                {
                    found = true;
                    //TODO: Change it to use new context soon
                    render(runtime.getUIContext(), packageName, path, runtime);
                    break;
                }
                String[] parts = StringUtils.split(page, '/');
                for (int j = parts.length - 1; j >= 0; j--)
                {
                    parts[j] = "Default";
                    path = category + '/' + StringUtils.join(parts);
                    if (renderer.exists(packageName, path))
                    {
                        found = true;
                        moduleResolver.resolve(path, runtime, runtime
                                .getUIContext());
                        render(runtime.getUIContext(), packageName, path,
                                runtime);
                        break;
                    }
                    parts[j] = null;
                }
                if (found)
                {
                    break;
                }
            }
            if (!found)
            {
                throw new PageNotFoundException(category + "/" + page);
            }
        }
    }

    private String defaultCategory;

    private String localizationName;

    private ModuleResolver moduleResolver;

    private Hashtable renderers;

    private ServiceManager serviceManager;

    /**
     * Override method configure in super class of RenderPageValve
     * 
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        Configuration[] renderConfs = conf.getChildren("renderer");
        renderers = new Hashtable(renderConfs.length);
        for (int i = 0; i < renderConfs.length; i++)
        {
            Configuration renderConf = renderConfs[i];
            String extension = renderConf.getAttribute("extension");
            String role = renderConf.getAttribute("role");
            try
            {
                PageRenderer renderer = (PageRenderer) serviceManager
                        .lookup(role);
                renderers.put(extension, renderer);
            }
            catch (Exception e)
            {
                logger.error("Page renderer [" + role + "] loading error", e);
            }
        }
        defaultCategory = conf.getChild("default-category").getValue("layout");
        localizationName = conf.getChild("localization").getAttribute("name",
                "resourceBundle");
    }

    /**
     * Override method process in super class of RenderPageValve
     * 
     * @see com.cyclopsgroup.waterview.Valve#invoke(com.cyclopsgroup.waterview.UIRuntime)
     */
    public void invoke(UIRuntime runtime) throws Exception
    {
        String page = runtime.getPage();
        String extension = null;
        PageRenderer renderer = null;
        for (Iterator i = renderers.keySet().iterator(); i.hasNext();)
        {
            extension = (String) i.next();
            String suffix = '.' + extension;
            if (page.endsWith(suffix))
            {
                renderer = (PageRenderer) renderers.get(extension);
                break;
            }
        }
        if (renderer == null)
        {
            throw new PageNotFoundException(page);
        }
        runtime.setContentType(renderer.getContentType());
        RuntimeRenderer r = new RuntimeRenderer(renderer, runtime, extension);
        r.render(defaultCategory, page);
        runtime.getOutput().flush();
        invokeNext(runtime);
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager manager) throws ServiceException
    {
        serviceManager = manager;
        moduleResolver = (ModuleResolver) manager.lookup(ModuleResolver.ROLE);
    }
}