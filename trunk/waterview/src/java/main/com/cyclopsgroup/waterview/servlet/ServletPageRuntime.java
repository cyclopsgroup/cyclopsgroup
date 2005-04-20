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
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.service.ServiceManager;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.clib.lang.DefaultContext;
import com.cyclopsgroup.waterview.AbstractPageRuntime;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.RequestValueParser;

/**
 * Default implementation of WebRuntime
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class ServletPageRuntime extends AbstractPageRuntime implements
        PageRuntime
{
    private String applicationBaseUrl;

    private HttpServletRequest httpServletRequest;

    private HttpServletResponse httpServletResponse;

    private PrintWriter output;

    private String pageBaseUrl;

    private Context pageContext;

    private RequestValueParserAdapter requestValueParser;

    private ServiceManager serviceManager;

    /**
     * Default constructor of default web runtime
     * 
     * @param request Http request object
     * @param response Http response object
     * @param services ServiceManager object
     * @throws Exception Throw it out
     */
    ServletPageRuntime(HttpServletRequest request,
            HttpServletResponse response, ServiceManager services)
            throws Exception
    {
        httpServletRequest = request;
        httpServletResponse = response;
        output = new PrintWriter(response.getOutputStream());
        requestValueParser = new RequestValueParserAdapter(request);
        serviceManager = services;

        StringBuffer sb = new StringBuffer(request.getScheme());
        sb.append("://").append(request.getServerName());
        if (request.getServerPort() != 80)
        {
            sb.append(':').append(request.getServerPort());
        }
        sb.append(request.getContextPath());
        applicationBaseUrl = sb.toString();

        sb.append(request.getServletPath());
        pageBaseUrl = sb.toString();

        pageContext = new DefaultContext(new HashMap());
        pageContext.put("request", request);
        pageContext.put("response", response);
    }

    /**
     * Override method getBaseUrl in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.PageRuntime#getApplicationBaseUrl()
     */
    public String getApplicationBaseUrl()
    {
        return applicationBaseUrl;
    }

    /**
     * Override method getContentType in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.PageRuntime#getInputContentType()
     */
    public String getInputContentType()
    {
        return httpServletRequest.getContentType();
    }

    /**
     * Override method getLocale in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.PageRuntime#getLocale()
     */
    public Locale getLocale()
    {
        return httpServletRequest.getLocale();
    }

    /**
     * Override method getOutput in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.PageRuntime#getOutput()
     */
    public PrintWriter getOutput()
    {
        return output;
    }

    /**
     * Override method getPageBaseUrl in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.PageRuntime#getPageBaseUrl()
     */
    public String getPageBaseUrl()
    {
        return pageBaseUrl;
    }

    /**
     * Override method getUIContext in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.PageRuntime#getPageContext()
     */
    public Context getPageContext()
    {
        return pageContext;
    }

    /**
     * Override method getRequestParameters in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.PageRuntime#getRequestParameters()
     */
    public RequestValueParser getRequestParameters()
    {
        return requestValueParser;
    }

    /**
     * Override method getRequestPath in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.PageRuntime#getRequestPath()
     */
    public String getRequestPath()
    {
        return httpServletRequest.getPathInfo();
    }

    /**
     * Override method getServiceManager in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.PageRuntime#getServiceManager()
     */
    public ServiceManager getServiceManager()
    {
        return serviceManager;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRuntime#getTimeZone()
     */
    public TimeZone getTimeZone()
    {
        return TimeZone.getDefault();
    }

    /**
     * Override method setContentType in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.PageRuntime#setOutputContentType(java.lang.String)
     */
    public void setOutputContentType(String contentType)
    {
        httpServletResponse.setContentType(contentType);
    }
}