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

import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Waterview;

/**
 * Main waterview servlet
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class WaterviewServlet extends HttpServlet
{
    private Waterview waterview;

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

    private void handleException(Throwable e, UIRuntime runtime)
            throws ServletException
    {
        throw new ServletException("Waterview error", e);
    }

    /**
     * Override method init in super class of MainServlet
     * 
     * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException
    {
        waterview = new Waterview();

        String propertiesFile = config.getInitParameter("waterview.properties");
        if (StringUtils.isEmpty(propertiesFile))
        {
            propertiesFile = "WEB-INF/waterview.properties";
        }
        try
        {
            ExtendedProperties props = new ExtendedProperties(propertiesFile);
            Enumeration e = config.getInitParameterNames();
            while (e.hasMoreElements())
            {
                String name = (String) e.nextElement();
                props.setProperty(name, config.getInitParameter(name));
            }
            waterview.init(props.subset("waterview"));
        }
        catch (Exception e)
        {
            throw new ServletException("Can not initialize waterview with "
                    + propertiesFile, e);
        }
    }

    private void internallyProcess(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException
    {
        DefaultWebRuntime runtime = new DefaultWebRuntime(request, response);
        try
        {
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