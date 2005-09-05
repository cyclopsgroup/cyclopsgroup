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
package com.cyclopsgroup.waterview.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.AbstractRuntimeData;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.ModuleManager;

/**
 * Default implementation of WebRuntime
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class ServletRuntimeData extends AbstractRuntimeData implements
        RuntimeData
{

    private ServletContext context;

    private HttpServletResponse response;

    /**
     * Default constructor of default web runtime
     * 
     * @param request Http request object
     * @param response Http response object
     * @param context Http servlet context
     * @param fileUpload File upload component
     * @param services ServiceManager object
     * @throws Exception Throw it out
     */
    ServletRuntimeData(HttpServletRequest request,
            HttpServletResponse response, ServletContext context,
            FileUpload fileUpload, ServiceManager services) throws Exception
    {
        this.response = response;
        this.context = context;

        setQueryString(request.getQueryString());
        setRefererUrl(request.getHeader("referer"));

        //Session Context
        setSessionContext(new HttpSessionContext(request.getSession()));
        setSessionId(request.getSession().getId());

        setRequestContext(new ServletRequestContext(request));

        //Request path
        String requestPath = request.getPathInfo();
        setRequestPath(requestPath == null ? StringUtils.EMPTY : requestPath);

        //Output
        setOutput(response.getWriter());

        //Request value parser
        if (FileUpload.isMultipartContent(request))
        {
            setParams(new MultipartServletRequestValueParser(
                    request, fileUpload));
        }
        else
        {
            setParams(new ServletRequestValueParser(request));
        }

        //Service manager
        setServiceManager(services);

        //Application base url
        StringBuffer sb = new StringBuffer(request.getScheme());
        sb.append("://").append(request.getServerName());
        if (request.getServerPort() != 80)
        {
            sb.append(':').append(request.getServerPort());
        }
        sb.append(request.getContextPath());
        setApplicationBaseUrl(sb.toString());

        //Page base url
        sb.append(request.getServletPath());
        setPageBaseUrl(sb.toString());
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getMimeType(java.lang.String)
     */
    public String getMimeType(String fileName)
    {
        return context.getMimeType(fileName);
    }

    /**
     * Override method setContentType in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.RuntimeData#setOutputContentType(java.lang.String)
     */
    public void setOutputContentType(String contentType)
    {
        response.setContentType(contentType);
    }

    /**
     * Overwrite or implement method setPage()
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#setPage(java.lang.String)
     */
    public void setPage(String page) throws Exception
    {
        ModuleManager modules = (ModuleManager) getServiceManager().lookup(
                ModuleManager.ROLE);
        setPage(modules.parsePath(page));
    }
}