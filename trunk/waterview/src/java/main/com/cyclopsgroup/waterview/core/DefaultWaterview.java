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

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.collections.map.ListOrderedMap;

import com.cyclopsgroup.waterview.DynaViewFactory;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.Waterview;

/**
 * Default waterview implementation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultWaterview extends AbstractLogEnabled implements Waterview,
        Configurable, Initializable, Serviceable
{
    private String defaultPipelinePattern = ".*\\.jelly";

    private transient Map pipelineRoles = ListOrderedMap
            .decorate(new HashMap());

    private Map pipelines = ListOrderedMap.decorate(new Hashtable());

    private ServiceManager serviceManager;

    private Map viewFactories = new Hashtable();

    private transient Map viewFactoryRoles = new HashMap();

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        Configuration[] confs = conf.getChild("pipelines").getChildren(
                "pipeline");
        for (int i = 0; i < confs.length; i++)
        {
            Configuration c = confs[i];
            String pattern = c.getAttribute("pattern");
            String role = c.getAttribute("role");
            String viewFactoryRole = c.getAttribute("viewfactory", null);
            if (viewFactoryRole != null)
            {
                viewFactoryRoles.put(pattern, viewFactoryRole);
            }
            pipelineRoles.put(pattern, role);
        }
    }

    /**
     * Getter method for defaultPipeline
     *
     * @return Returns the defaultPipeline.
     */
    public String getDefaultPipelinePattern()
    {
        return defaultPipelinePattern;
    }

    /**
     * Get registered pipeline
     *
     * @param pattern String
     * @return Pipeline object
     */
    public Pipeline getPipeline(String pattern)
    {
        return (Pipeline) pipelines.get(pattern);
    }

    /**
     * Get view factory attached to a pattern
     *
     * @param pattern Path pattern Pattern of page
     * @return View factory object ViewFactory object
     */
    public DynaViewFactory getViewFactory(String pattern)
    {
        return (DynaViewFactory) viewFactories.get(pattern);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Waterview#handleRuntime(com.cyclopsgroup.waterview.PageRuntime)
     */
    public void handleRuntime(PageRuntime runtime) throws Exception
    {
        Pipeline pipeline = getPipeline(defaultPipelinePattern);
        DynaViewFactory viewFactory = getViewFactory(defaultPipelinePattern);
        for (Iterator i = pipelines.keySet().iterator(); i.hasNext();)
        {
            String pattern = (String) i.next();
            if (Pattern.matches('^' + pattern + '$', runtime.getRequestPath()))
            {
                pipeline = getPipeline(pattern);
                viewFactory = getViewFactory(pattern);
            }
        }
        if (pipeline == null)
        {
            throw new UnknownPageException(runtime.getRequestPath());
        }
        if (viewFactory != null)
        {
            runtime.getPageContext().put(DynaViewFactory.NAME, viewFactory);
        }
        pipeline.handleRuntime(runtime);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        for (Iterator i = pipelineRoles.keySet().iterator(); i.hasNext();)
        {
            String pattern = (String) i.next();

            String role = (String) pipelineRoles.get(pattern);
            Pipeline pipeline = (Pipeline) serviceManager.lookup(role);
            registerPipeline(pattern, pipeline);

            role = (String) viewFactoryRoles.get(pattern);
            if (role != null)
            {
                DynaViewFactory viewFactory = (DynaViewFactory) serviceManager
                        .lookup(role);
                registerViewFactory(pattern, viewFactory);
            }
        }
        pipelineRoles.clear();
        viewFactoryRoles.clear();
    }

    /**
     * Register pipeline with given pattern
     *
     * @param pattern Path pattern
     * @param pipeline Pipeline object
     */
    public void registerPipeline(String pattern, Pipeline pipeline)
    {
        pipelines.put(pattern, pipeline);
    }

    /**
     * Register view factory with given pattern
     *
     * @param pattern Path pattern
     * @param viewFactory View factory object
     */
    public void registerViewFactory(String pattern, DynaViewFactory viewFactory)
    {
        viewFactories.put(pattern, viewFactory);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        this.serviceManager = serviceManager;
    }

    /**
     * Set default pipeline pattern
     *
     * @param pattern Default pattern
     */
    public void setDefaultPipelinePattern(String pattern)
    {
        defaultPipelinePattern = pattern;
    }
}
