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
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.service.ServiceManager;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.cyclib.DefaultContext;
import com.cyclopsgroup.cyclib.ValueParser;
import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Default implementation of WebRuntime
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class ServletUIRuntime implements UIRuntime
{

    private LinkedList actions = new LinkedList();

    private String applicationBaseUrl;

    private HttpServletRequest httpServletRequest;

    private HttpServletResponse httpServletResponse;

    private PrintWriter output;

    private String page;

    private String pageBaseUrl;

    private RequestValueParserAdapter requestValueParser;

    private ServiceManager serviceManager;

    private Context sessionContext;

    private Context uiContext;

    /**
     * Default constructor of default web runtime
     * 
     * @param request Http request object
     * @param response Http response object
     * @param services ServiceManager object
     * @throws Exception Throw it out
     */
    ServletUIRuntime(HttpServletRequest request, HttpServletResponse response,
            ServiceManager services) throws Exception
    {
        httpServletRequest = request;
        httpServletResponse = response;
        output = new PrintWriter(response.getOutputStream());
        requestValueParser = new RequestValueParserAdapter(request);
        sessionContext = new SessionContextAdapter(request.getSession());
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

        DefaultContext ctx = new DefaultContext();
        ctx.put("runtime", this);
        ctx.put("context", ctx);
        ctx.put("params", requestValueParser);
        ctx.put("request", request);
        ctx.put("response", response);
        uiContext = ctx;
    }

    /**
     * Override method getProcessors in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getActions()
     */
    public List getActions()
    {
        return actions;
    }

    /**
     * Override method getBaseUrl in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getApplicationBaseUrl()
     */
    public String getApplicationBaseUrl()
    {
        return applicationBaseUrl;
    }

    /**
     * Override method getContentType in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getContentType()
     */
    public String getContentType()
    {
        return httpServletRequest.getContentType();
    }

    /**
     * Override method getHttpServletRequest in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getHttpServletRequest()
     */
    /*
     * public HttpServletRequest getHttpServletRequest() { return httpServletRequest; }
     */

    /**
     * Override method getHttpServletResponse in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getHttpServletResponse()
     */
    /*
     * public HttpServletResponse getHttpServletResponse() { return httpServletResponse; }
     */

    /**
     * Override method getHttpSession in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getHttpSession()
     */
    /*
     * public HttpSession getHttpSession() { return getHttpServletRequest().getSession(true); }
     */

    /**
     * Override method getLocale in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getLocale()
     */
    public Locale getLocale()
    {
        return httpServletRequest.getLocale();
    }

    /**
     * Override method getOutput in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getOutput()
     */
    public PrintWriter getOutput()
    {
        return output;
    }

    /**
     * Override method getRenderTemplate in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getPage()
     */
    public String getPage()
    {
        return page;
    }

    /**
     * Override method getPageBaseUrl in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getPageBaseUrl()
     */
    public String getPageBaseUrl()
    {
        return pageBaseUrl;
    }

    /**
     * Override method getRequestParameters in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getRequestParameters()
     */
    public ValueParser getRequestParameters()
    {
        return requestValueParser;
    }

    /**
     * Override method getRequestPath in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getRequestPath()
     */
    public String getRequestPath()
    {
        return httpServletRequest.getPathInfo();
    }

    /**
     * Override method getServiceManager in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getServiceManager()
     */
    public ServiceManager getServiceManager()
    {
        return serviceManager;
    }

    /**
     * Override method getSessionContext in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getSessionContext()
     */
    public Context getSessionContext()
    {
        return sessionContext;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.UIRuntime#getTimeZone()
     */
    public TimeZone getTimeZone()
    {
        return TimeZone.getDefault();
    }

    /**
     * Override method getUIContext in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#getUIContext()
     */
    public Context getUIContext()
    {
        return uiContext;
    }

    /**
     * Override method setContentType in super class of ServletUIRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#setContentType(java.lang.String)
     */
    public void setContentType(String contentType)
    {
        httpServletResponse.setContentType(contentType);
    }

    /**
     * Override method setRenderTemplate in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.UIRuntime#setPage(java.lang.String)
     */
    public void setPage(String template)
    {
        page = template;
    }
}