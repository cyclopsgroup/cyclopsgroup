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

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.PlexusContainer;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.RequestValueParser;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.Waterview;
import com.cyclopsgroup.waterview.util.WaterviewPlexusContainer;

/**
 * Main waterview servlet
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class WaterviewServlet extends HttpServlet
{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3544955458881534002L;

    private PlexusContainer container;

    private FileUpload fileUpload = new FileUpload();

    private Log logger = LogFactory.getLog(getClass());

    private ServiceManager serviceManager;

    private ServletConfig servletConfig;

    /**
     * Override method destroy in super class of WaterviewServlet
     * 
     * @see javax.servlet.Servlet#destroy()
     */
    public void destroy()
    {
        try
        {
            container.dispose();
        }
        catch (Exception e)
        {
            logger.error("Can properly stop plexus container", e);
        }
    }

    /**
     * Override method doGet in super class of WaterviewServlet
     * 
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        handleRequest(request, response);
    }

    /**
     * Do handle exception
     * @param request Http request
     * @param response Http response
     * @param e Throwable
     * @throws IOException Throw it out
     * @throws ServletException Throw it out
     */
    protected void doHandleException(HttpServletRequest request,
            HttpServletResponse response, Throwable e) throws IOException,
            ServletException
    {
        response.setContentType("text/html");
        response.getWriter().print(
                "<html><body><h2>Internal error occurs</h2><p><pre>");
        e.printStackTrace(response.getWriter());
        response.getWriter().print("</pre></p></body></html>");
    }

    /**
     * Handle request and response
     * 
     * @param request Http servlet request
     * @param response Http servlet response
     * @throws Exception
     */
    protected void doHandleRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        ServletPageRuntime data = null;
        data = new ServletPageRuntime(request, response, servletConfig
                .getServletContext(), fileUpload, serviceManager);
        Context ctx = data.getRequestContext();
        ctx.put(RuntimeData.NAME, data);
        ctx.put(RequestValueParser.NAME, data.getRequestParameters());
        ctx.put(RuntimeData.SERVICE_MANAGER_NAME, serviceManager);

        ctx.put("servletConfig", servletConfig);
        ctx.put("servletContext", servletConfig.getServletContext());
        ctx.put("request", request);
        ctx.put("response", response);
        ctx.put("requestContext", data.getRequestContext());

        Waterview waterview = (Waterview) container.lookup(Waterview.ROLE);
        waterview.handleRuntime(data);

        if (data.getRedirectUrl() != null)
        {
            response.sendRedirect(data.getRedirectUrl());
        }
    }

    /**
     * Override method doPost in super class of WaterviewServlet
     * 
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException
    {
        try
        {
            doHandleRequest(request, response);
        }
        catch (Exception e)
        {
            doHandleException(request, response, e);
        }
        finally
        {
            response.getWriter().flush();
            response.getWriter().close();
        }
    }

    /**
     * Override method init in super class of MainServlet
     * 
     * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException
    {
        servletConfig = config;
        String basedir = config.getServletContext().getRealPath("");
        Properties initProperties = new Properties();
        initProperties.setProperty("basedir", basedir);
        initProperties.setProperty("plexus.home", basedir);
        Enumeration i = config.getInitParameterNames();
        while (i.hasMoreElements())
        {
            String key = (String) i.nextElement();
            String value = config.getInitParameter(key);
            initProperties.setProperty(key, value);
        }
        try
        {
            container = new WaterviewPlexusContainer();
            serviceManager = new ServiceManagerAdapter(container);
            for (Iterator j = initProperties.keySet().iterator(); j.hasNext();)
            {
                String initPropertyName = (String) j.next();
                container.addContextValue(initPropertyName, initProperties
                        .get(initPropertyName));
            }

            container
                    .addContextValue(Waterview.INIT_PROPERTIES, initProperties);
            container.initialize();
            container.start();
        }
        catch (Exception e)
        {
            container.getLogger().fatalError("Can not start container", e);
        }
    }
}
