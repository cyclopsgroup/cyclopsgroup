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
package com.cyclopsgroup.waterview.velocity;

import java.util.Properties;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.collections.LRUMap;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;

import com.cyclopsgroup.waterview.DelegatePageRenderer;
import com.cyclopsgroup.waterview.PageRenderer;
import com.cyclopsgroup.waterview.UIModuleResolver;
import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Velocity page renderer
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class VelocityPageRenderer extends AbstractLogEnabled implements
        PageRenderer, Initializable, Configurable, Serviceable
{

    private class Renderer
    {
        private UIRuntime runtime;

        private Renderer(UIRuntime runtime)
        {
            this.runtime = runtime;
        }

        /**
         * This method will be called from VM page
         *
         * @param packageName Page package name
         * @param page Page path
         * @return Empty string for outputing
         * @throws Exception Throw it out
         */
        public String render(String packageName, String page) throws Exception
        {
            DelegatePageRenderer renderer = (DelegatePageRenderer) runtime
                    .getUIContext().get("delegateRenderer");
            renderer.render(runtime, moduleResolver, packageName, page);
            return StringUtils.EMPTY;
        }
    }

    private UIModuleResolver moduleResolver;

    private LRUMap templateCache;

    private VelocityEngine velocityEngine;

    /**
     * Override method configure in super class of VelocityPageRenderer
     * 
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        int cacheSize = conf.getChild("cache-size").getValueAsInteger(1000);
        templateCache = new LRUMap(cacheSize);
    }

    /**
     * Override method initialize in super class of VelocityPageRenderer
     * 
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        velocityEngine = new VelocityEngine();
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("velocity.properties"));
        velocityEngine.init(props);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRenderer#pageExists(java.lang.String)
     */
    public boolean pageExists(String page)
    {
        return velocityEngine.templateExists(page);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRenderer#render(com.cyclopsgroup.waterview.UIRuntime, java.lang.String)
     */
    public void render(UIRuntime runtime, String page) throws Exception
    {
        Renderer renderer = (Renderer) runtime.getUIContext().get("renderer");
        if (renderer == null)
        {
            renderer = new Renderer(runtime);
            runtime.getUIContext().put("renderer", renderer);
        }
        VelocityContextAdapter ctx = new VelocityContextAdapter(runtime
                .getUIContext());
        velocityEngine.mergeTemplate(page, ctx, runtime
                .getHttpServletResponse().getWriter());
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        moduleResolver = (UIModuleResolver) serviceManager
                .lookup(UIModuleResolver.ROLE);
    }
}