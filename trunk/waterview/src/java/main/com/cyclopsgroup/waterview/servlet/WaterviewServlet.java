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

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.embed.Embedder;

import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Waterview;

/**
 * Main waterview servlet
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class WaterviewServlet extends HttpServlet
{
    private Embedder embedder;

    private Log logger = LogFactory.getLog(getClass());

    /**
     * Override method destroy in super class of WaterviewServlet
     * 
     * @see javax.servlet.Servlet#destroy()
     */
    public void destroy()
    {
        try
        {
            embedder.stop();
        }
        catch (Exception e)
        {
            logger.error("Can not stop plexus container", e);
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
        return embedder.getContainer();
    }

    private void handleException(Throwable e, UIRuntime runtime)
            throws ServletException
    {
        e.printStackTrace();
    }

    /**
     * Override method init in super class of MainServlet
     * 
     * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException
    {
        try {
        String conf = config.getInitParameter("conf");
        if (StringUtils.isEmpty(conf))
        {
            conf = "WEB-INF/waterview-components.xml";
        }
        embedder = new Embedder();
        embedder.addContextValue("basedir", config.getServletContext()
                .getRealPath(""));
        Enumeration i = config.getInitParameterNames();
        Properties initProperties = new Properties();
        String waterviewHome = config.getServletContext().getRealPath("");
        initProperties.put("waterview.home", waterviewHome);
        while (i.hasMoreElements())
        {
            String key = (String) i.nextElement();
            String value = config.getInitParameter(key);
            embedder.addContextValue(key, value);
            initProperties.setProperty(key, value);
        }
        File file = new File(conf);
        if (!file.isFile())
        {
            file = new File(config.getServletContext().getRealPath(conf));
        }
        if (!file.isFile())
        {
            file = new File(conf);
        }
        if (!file.isFile())
        {
            throw new ServletException("Can not find configuration file "
                    + conf);
        }
        try
        {
            System.setProperties(initProperties);
            embedder.setConfiguration(file.toURL());
            embedder.start();
            Waterview waterview = (Waterview) embedder.lookup(Waterview.ROLE);
            waterview.getProperties().putAll(initProperties);
        }
        catch (Exception e)
        {
            throw new ServletException("Init plexus container error", e);
        }
        } catch (Throwable e) {
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
                    new ServiceManagerAdapter(embedder.getContainer()));
            Waterview waterview = (Waterview) getContainer().lookup(
                    Waterview.ROLE);
            waterview.process(runtime);
        }
        catch (Throwable e)
        {
            handleException(e, runtime);
        }
        finally
        {
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }
}