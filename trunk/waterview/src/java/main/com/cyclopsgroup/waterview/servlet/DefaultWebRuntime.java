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

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cyclopsgroup.gearset.bean.ValueParser;
import com.cyclopsgroup.gearset.runtime.Context;
import com.cyclopsgroup.gearset.runtime.InheritableContext;
import com.cyclopsgroup.waterview.WebRuntime;

/**
 * Default implementation of WebRuntime
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class DefaultWebRuntime implements WebRuntime
{
    private HttpServletRequest httpServletRequest;

    private HttpServletResponse httpServletResponse;

    private LinkedList processors = new LinkedList();

    private InheritableContext renderContext;

    private String renderTemplate = "index.html";

    private HttpRequestValueParser requestValueParser;

    /**
     * Default constructor of default web runtime
     * 
     * @param request Http request object
     * @param response Http response object
     */
    DefaultWebRuntime(HttpServletRequest request, HttpServletResponse response)
    {
        httpServletRequest = request;
        httpServletResponse = response;
        requestValueParser = new HttpRequestValueParser(request);
    }

    /**
     * Override method getHttpServletRequest in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.WebRuntime#getHttpServletRequest()
     */
    public HttpServletRequest getHttpServletRequest()
    {
        return httpServletRequest;
    }

    /**
     * Override method getHttpServletResponse in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.WebRuntime#getHttpServletResponse()
     */
    public HttpServletResponse getHttpServletResponse()
    {
        return httpServletResponse;
    }

    /**
     * Override method getHttpSession in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.WebRuntime#getHttpSession()
     */
    public HttpSession getHttpSession()
    {
        return getHttpServletRequest().getSession(true);
    }

    /**
     * Override method getLocale in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.WebRuntime#getLocale()
     */
    public Locale getLocale()
    {
        return Locale.getDefault();
    }

    /**
     * Override method getProcessors in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.WebRuntime#getProcessors()
     */
    public List getProcessors()
    {
        return processors;
    }

    /**
     * Override method getRenderContext in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.WebRuntime#getRenderContext()
     */
    public Context getRenderContext()
    {
        return renderContext;
    }

    /**
     * Override method getRenderTemplate in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.WebRuntime#getPage()
     */
    public String getPage()
    {
        return renderTemplate;
    }

    /**
     * Override method getRequestParameters in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.WebRuntime#getRequestParameters()
     */
    public ValueParser getRequestParameters()
    {
        return requestValueParser;
    }

    /**
     * Override method setRenderTemplate in super class of DefaultWebRuntime
     * 
     * @see com.cyclopsgroup.waterview.WebRuntime#setPage(java.lang.String)
     */
    public void setPage(String template)
    {
        renderTemplate = template;
    }
}