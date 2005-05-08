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
package com.cyclopsgroup.waterview.valves;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.PipelineContext;
import com.cyclopsgroup.waterview.Valve;

/**
 * Valve to prepare information contained by URL
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ParseURLValve extends AbstractLogEnabled implements Valve,
        Configurable, Serviceable
{

    private String defaultPage = "Index.jelly";

    private RenderPageValve pageRender;

    private String separator = "|";

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        String page = conf.getChild("default-page").getValue(null);
        if (page != null)
        {
            setDefaultPage(page);
        }
        String sep = conf.getChild("separator").getValue(null);
        if (sep != null)
        {
            setSeparator(sep);
        }
    }

    /**
     * Getter method for defaultPage
     *
     * @return Returns the defaultPage.
     */
    public String getDefaultPage()
    {
        return defaultPage;
    }

    /**
     * Get default page for no specified page
     *
     * @param runtime Runtime object
     * @return Page path
     */
    protected String getDefaultPage(PageRuntime runtime)
    {
        return getDefaultPage();
    }

    /**
     * Getter method for separator
     *
     * @return Returns the separator.
     */
    public String getSeparator()
    {
        return separator;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Valve#invoke(com.cyclopsgroup.waterview.PageRuntime, com.cyclopsgroup.waterview.PipelineContext)
     */
    public void invoke(PageRuntime runtime, PipelineContext context)
            throws Exception
    {
        Context ctx = runtime.getPageContext();
        ctx.put("runtime", runtime);
        ctx.put("context", ctx);
        ctx.put("params", runtime.getRequestParameters());
        ctx.put("applicationBase", runtime.getApplicationBaseUrl());
        ctx.put("pageBase", runtime.getPageBaseUrl());
        String requestPath = runtime.getRequestPath();
        if (requestPath.charAt(0) == '/')
        {
            requestPath = requestPath.substring(1);
        }
        String[] parts = null;
        if (requestPath.indexOf(getSeparator()) == -1)
        {
            parts = new String[] { requestPath };
        }
        else
        {
            parts = StringUtils.split(requestPath, getSeparator());
        }
        String pagePath = null;
        for (int i = 0; i < parts.length; i++)
        {
            String part = parts[i];
            if (isPagePath(part))
            {
                pagePath = part;
            }
            else
            {
                runtime.getActions().add(part);
            }
        }
        if (StringUtils.isEmpty(pagePath))
        {
            pagePath = getDefaultPage(runtime);
        }
        runtime.setPage(pagePath);
        ctx.put("page", pagePath);
        context.invokeNextValve(runtime);
    }

    /**
     * If path is for page
     *
     * @param path Page path
     * @return True if view factory for path is defined
     */
    protected boolean isPagePath(String path)
    {
        return pageRender.isPage(path);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        pageRender = (RenderPageValve) serviceManager
                .lookup(RenderPageValve.ROLE);
    }

    /**
     * Setter method for defaultPage
     *
     * @param defaultPage The defaultPage to set.
     */
    public void setDefaultPage(String defaultPage)
    {
        this.defaultPage = defaultPage;
    }

    /**
     * Setter method for separator
     *
     * @param separator The separator to set.
     */
    public void setSeparator(String separator)
    {
        this.separator = separator;
    }
}