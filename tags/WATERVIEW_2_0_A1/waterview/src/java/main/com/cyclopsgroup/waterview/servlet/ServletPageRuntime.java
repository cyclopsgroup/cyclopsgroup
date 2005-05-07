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

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.clib.lang.DefaultContext;
import com.cyclopsgroup.waterview.AbstractPageRuntime;
import com.cyclopsgroup.waterview.PageRuntime;

/**
 * Default implementation of WebRuntime
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class ServletPageRuntime extends AbstractPageRuntime implements
        PageRuntime
{

    private HttpServletRequest request;

    private HttpServletResponse response;

    /**
     * Default constructor of default web runtime
     * 
     * @param request Http request object
     * @param response Http response object
     * @param fileUpload File upload component
     * @param services ServiceManager object
     * @throws Exception Throw it out
     */
    ServletPageRuntime(HttpServletRequest request,
            HttpServletResponse response, FileUpload fileUpload,
            ServiceManager services) throws Exception
    {
        this.response = response;
        this.request = request;
        setSessionContext(new HttpSessionContext(request.getSession()));

        String requestPath = request.getPathInfo();
        if (StringUtils.isEmpty(requestPath) || requestPath.equals("/"))
        {
            requestPath = "Index.jelly";
        }
        else if (requestPath.charAt(0) == '/')
        {
            requestPath = requestPath.substring(1);
        }
        setRequestPath(requestPath);

        setOutput(new PrintWriter(response.getOutputStream()));
        if (FileUpload.isMultipartContent(request))
        {
            setRequestParameters(new MultipartServletRequestValueParser(
                    request, fileUpload));
        }
        else
        {
            setRequestParameters(new ServletRequestValueParser(request));
        }
        setServiceManager(services);

        StringBuffer sb = new StringBuffer(request.getScheme());
        sb.append("://").append(request.getServerName());
        if (request.getServerPort() != 80)
        {
            sb.append(':').append(request.getServerPort());
        }
        sb.append(request.getContextPath());
        setApplicationBaseUrl(sb.toString());

        sb.append(request.getServletPath());
        setPageBaseUrl(sb.toString());

        Context pageContext = new DefaultContext(new HashMap());
        pageContext.put("request", request);
        pageContext.put("response", response);
        setPageContext(pageContext);
    }

    /**
     * Getter method for request
     *
     * @return Returns the request.
     */
    public HttpServletRequest getRequest()
    {
        return request;
    }

    /**
     * Getter method for response
     *
     * @return Returns the response.
     */
    public HttpServletResponse getResponse()
    {
        return response;
    }

    /**
     * Override method setContentType in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.PageRuntime#setOutputContentType(java.lang.String)
     */
    public void setOutputContentType(String contentType)
    {
        getResponse().setContentType(contentType);
    }
}