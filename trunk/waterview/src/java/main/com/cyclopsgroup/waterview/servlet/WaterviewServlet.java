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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.PlexusContainer;

import com.cyclopsgroup.waterview.ServiceManagerAdapter;
import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Waterview;
import com.cyclopsgroup.waterview.WaterviewContainer;

/**
 * Main waterview servlet
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class WaterviewServlet extends HttpServlet
{
    private PlexusContainer container;

    private Log logger = LogFactory.getLog(getClass());

    private Waterview waterview;

    /**
     * Override method destroy in super class of WaterviewServlet
     * 
     * @see javax.servlet.Servlet#destroy()
     */
    public void destroy()
    {
        try
        {
            container.release(waterview);
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
        internallyProcess(request, response);
    }

    /**
     * Override method doPost in super class of WaterviewServlet
     * 
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        internallyProcess(request, response);
    }

    /**
     * Get plexus container
     * 
     * @return Plexus container object
     */
    public PlexusContainer getContainer()
    {
        return container;
    }

    private void handleException(Throwable e, UIRuntime runtime)
            throws ServletException, IOException
    {
        runtime.setContentType("text/html");
        runtime.getOutput().print("<html><body><pre>");
        e.printStackTrace(runtime.getOutput());
        runtime.getOutput().print("</pre></body></html>");
        runtime.getOutput().flush();
    }

    /**
     * Override method init in super class of MainServlet
     * 
     * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException
    {
        try
        {
            container = new WaterviewContainer();
            String basedir = config.getServletContext().getRealPath("");
            container.addContextValue("basedir", basedir);
            container.addContextValue("plexus.home", basedir);
            Enumeration i = config.getInitParameterNames();
            while (i.hasMoreElements())
            {
                String key = (String) i.nextElement();
                String value = config.getInitParameter(key);
                container.addContextValue(key, value);
            }
            try
            {
                container.initialize();
                container.start();
                waterview = (Waterview) container.lookup(Waterview.ROLE);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw new ServletException("Init plexus container error", e);
            }
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
    }

    private void internallyProcess(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException
    {
        ServletUIRuntime runtime = null;
        try
        {
            runtime = new ServletUIRuntime(request, response,
                    new ServiceManagerAdapter(container));
            waterview.process(runtime);
        }
        catch (Throwable e)
        {
            handleException(e, runtime);
        }
        finally
        {
            runtime.getOutput().flush();
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }
}