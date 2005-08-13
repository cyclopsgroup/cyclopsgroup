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
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.avalon.framework.service.ServiceManager;

/**
 * Abstract base runtime class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class AbstractPageRuntime implements RuntimeData
{
    private List actions = new LinkedList();

    private String applicationBaseUrl;

    private String inputContentType;

    private Locale locale = Locale.getDefault();

    private PrintWriter output;

    private Path page;

    private String pageBaseUrl;

    private String redirectUrl;

    private Context requestContext;

    private RequestValueParser requestParameters;

    private String requestPath;

    private ServiceManager serviceManager;

    private Context sessionContext;

    private String sessionId;

    private boolean stopped;

    private TimeZone timeZone = TimeZone.getDefault();

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getActions()
     */
    public List getActions()
    {
        return actions;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getApplicationBaseUrl()
     */
    public String getApplicationBaseUrl()
    {
        return applicationBaseUrl;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getInputContentType()
     */
    public String getInputContentType()
    {
        return inputContentType;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getLocale()
     */
    public Locale getLocale()
    {
        return locale;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getOutput()
     */
    public PrintWriter getOutput()
    {
        return output;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getPage()
     */
    public Path getPage()
    {
        return page;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getPageBaseUrl()
     */
    public String getPageBaseUrl()
    {
        return pageBaseUrl;
    }

    /**
     * Overwrite or implement method getRedirectUrl()
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getRedirectUrl()
     */
    public String getRedirectUrl()
    {
        return redirectUrl;
    }

    /**
     * Overwrite or implement method getRequestContext()
     * @see com.cyclopsgroup.waterview.RuntimeData#getRequestContext()
     */
    public Context getRequestContext()
    {
        return requestContext;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getRequestParameters()
     */
    public RequestValueParser getRequestParameters()
    {
        return requestParameters;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getRequestPath()
     */
    public String getRequestPath()
    {
        return requestPath;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getServiceManager()
     */
    public ServiceManager getServiceManager()
    {
        return serviceManager;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getSessionContext()
     */
    public Context getSessionContext()
    {
        return sessionContext;
    }

    /**
     * Overwrite or implement method getSessionId()
     * @see com.cyclopsgroup.waterview.RuntimeData#getSessionId()
     */
    public String getSessionId()
    {
        return sessionId;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getTimeZone()
     */
    public TimeZone getTimeZone()
    {
        return timeZone;
    }

    /**
     * Overwrite or implement method isStopped()
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#isStopped()
     */
    public boolean isStopped()
    {
        return stopped;
    }

    /**
     * Setter method for applicationBaseUrl
     *
     * @param applicationBaseUrl The applicationBaseUrl to set.
     */
    public void setApplicationBaseUrl(String applicationBaseUrl)
    {
        this.applicationBaseUrl = applicationBaseUrl;
        if (getRequestContext() != null)
        {
            getRequestContext().put("applicationBase", applicationBaseUrl);
        }
    }

    /**
     * Setter method for inputContentType
     *
     * @param inputContentType The inputContentType to set.
     */
    public void setInputContentType(String inputContentType)
    {
        this.inputContentType = inputContentType;
    }

    /**
     * Setter method for locale
     *
     * @param locale The locale to set.
     */
    public void setLocale(Locale locale)
    {
        this.locale = locale;
    }

    /**
     * Setter method for output
     *
     * @param output The output to set.
     */
    public void setOutput(PrintWriter output)
    {
        this.output = output;
    }

    /**
     * Overwrite or implement method setPage()
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#setPage(com.cyclopsgroup.waterview.Path)
     */
    public void setPage(Path page)
    {
        getRequestContext().put(PAGE_NAME, page);
        this.page = page;
    }

    /**
     * Setter method for pageBaseUrl
     *
     * @param pageBaseUrl The pageBaseUrl to set.
     */
    public void setPageBaseUrl(String pageBaseUrl)
    {
        this.pageBaseUrl = pageBaseUrl;
        if (getRequestContext() != null)
        {
            getRequestContext().put("pageBase", pageBaseUrl);
        }
    }

    /**
     * Overwrite or implement method setRedirectUrl()
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#setRedirectUrl(java.lang.String)
     */
    public void setRedirectUrl(String url)
    {
        redirectUrl = url;
    }

    /**
     * Set request context
     * @param pageContext Context object
     */
    public void setRequestContext(Context pageContext)
    {
        this.requestContext = pageContext;
    }

    /**
     * Setter method for requestParameters
     *
     * @param requestParameters The requestParameters to set.
     */
    public void setRequestParameters(RequestValueParser requestParameters)
    {
        this.requestParameters = requestParameters;
    }

    /**
     * Setter method for requestPath
     *
     * @param requestPath The requestPath to set.
     */
    public void setRequestPath(String requestPath)
    {
        this.requestPath = requestPath;
    }

    /**
     * Setter method for serviceManager
     *
     * @param serviceManager The serviceManager to set.
     */
    public void setServiceManager(ServiceManager serviceManager)
    {
        this.serviceManager = serviceManager;
    }

    /**
     * Setter method for sessionContext
     *
     * @param sessionContext The sessionContext to set.
     */
    public void setSessionContext(Context sessionContext)
    {
        this.sessionContext = sessionContext;
    }

    /**
     * Set session ID
     * @param sessionId
     */
    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    /**
     * Setter method for timeZone
     *
     * @param timeZone The timeZone to set.
     */
    public void setTimeZone(TimeZone timeZone)
    {
        this.timeZone = timeZone;
    }

    /**
     * Overwrite or implement method stop()
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#stop()
     */
    public void stop()
    {
        stopped = true;
    }
}
