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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main waterview servlet
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class WaterviewServlet extends HttpServlet
{
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

    private void doProcess(HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        DefaultWebRuntime runtime = new DefaultWebRuntime(request, response);
        runtime.getRenderContext().put("runtime", runtime);
        runtime.getRenderContext().put("context", runtime.getRenderContext());
        System.out.println(request.getPathInfo());
    }

    private void handleException(Throwable e)
    {
        e.printStackTrace(System.err);
    }

    /**
     * Override method init in super class of MainServlet
     * 
     * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException
    {
    }

    private void internallyProcess(HttpServletRequest request,
            HttpServletResponse response) throws IOException
    {
        try
        {
            doProcess(request, response);
        }
        catch (Throwable e)
        {
            handleException(e);
        }
        finally
        {
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }
}