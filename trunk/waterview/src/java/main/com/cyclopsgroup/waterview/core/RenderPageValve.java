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

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.waterview.DelegatePageRenderer;
import com.cyclopsgroup.waterview.PageRenderer;
import com.cyclopsgroup.waterview.UIModuleResolver;
import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Valve;

/**
 * Valve to render page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class RenderPageValve extends AbstractLogEnabled implements Valve,
        Configurable, Serviceable
{
    private String entry;

    private UIModuleResolver moduleResolver;

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
                DelegatePageRenderer delegate = new DelegatePageRenderer(
                        renderer);
                renderers.put(extension, delegate);
            }
            catch (Exception e)
            {
                getLogger().error("Page renderer [" + role + "] loading error",
                        e);
            }
        }
        entry = conf.getChild("entry").getValue("layout");
    }

    /**
     * Override method process in super class of RenderPageValve
     * 
     * @see com.cyclopsgroup.waterview.Valve#process(com.cyclopsgroup.waterview.UIRuntime)
     */
    public void process(UIRuntime runtime) throws Exception
    {
        String page = runtime.getPage();
        DelegatePageRenderer renderer = null;
        String extension = null;
        for (Iterator i = renderers.keySet().iterator(); i.hasNext();)
        {
            extension = (String) i.next();
            String suffix = ',' + extension;
            if (page.endsWith(suffix))
            {
                renderer = (DelegatePageRenderer) renderers.get(extension);
                break;
            }
        }
        if (renderer == null)
        {
            return;
        }
        runtime.getUIContext().put("delegateRenderer", renderer);
        runtime.getUIContext().put("pageExtension", extension);
        renderer.render(runtime, moduleResolver, entry, page);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager manager) throws ServiceException
    {
        serviceManager = manager;
        moduleResolver = (UIModuleResolver) manager
                .lookup(UIModuleResolver.ROLE);
    }
}