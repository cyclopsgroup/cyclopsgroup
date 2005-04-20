/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
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
package com.cyclopsgroup.waterview;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.avalon.framework.service.ServiceManager;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.clib.lang.DefaultContext;
import com.cyclopsgroup.clib.site.avalon.FakeServiceManager;

/**
 * Fake page runtime object
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class FakePageRuntime extends AbstractPageRuntime implements PageRuntime
{

    private PrintWriter output;

    private Context pageContext = new DefaultContext(new HashMap());

    private RequestValueParser parameters = new MapRequestValueParser();

    private FakeServiceManager serviceManager = new FakeServiceManager();

    /**
     * Constructor for class FakePageRuntime
     *
     * @param output Output
     */
    public FakePageRuntime(PrintWriter output)
    {
        this.output = output;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRuntime#getApplicationBaseUrl()
     */
    public String getApplicationBaseUrl()
    {
        return "http://localhost:8080/waterview";
    }

    /**
     * Get fake service manager
     *
     * @return Service manager
     */
    public FakeServiceManager getFakeServiceManager()
    {
        return serviceManager;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRuntime#getInputContentType()
     */
    public String getInputContentType()
    {
        return "text/html";
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRuntime#getLocale()
     */
    public Locale getLocale()
    {
        return Locale.getDefault();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRuntime#getOutput()
     */
    public PrintWriter getOutput()
    {
        return output;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRuntime#getPageBaseUrl()
     */
    public String getPageBaseUrl()
    {
        return "http://localhost:8080/waterview/servlet/waterview";
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRuntime#getPageContext()
     */
    public Context getPageContext()
    {
        return pageContext;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRuntime#getRequestParameters()
     */
    public RequestValueParser getRequestParameters()
    {
        return parameters;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRuntime#getRequestPath()
     */
    public String getRequestPath()
    {
        return "/index.html";
    }

    /**
     * Override or implement method of parent class or interface
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
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRuntime#setOutputContentType(java.lang.String)
     */
    public void setOutputContentType(String contentType)
    {
    }
}
