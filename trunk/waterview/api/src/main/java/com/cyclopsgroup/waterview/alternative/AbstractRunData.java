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
package com.cyclopsgroup.waterview.alternative;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.avalon.framework.service.ServiceManager;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Parameters;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.WaterviewSpi;

/**
 * Abstract base runtime class
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class AbstractRunData
    implements RunDataSpi
{
    private String applicationBaseUrl;

    private String inputContentType;

    private Locale locale = Locale.getDefault();

    private String pageBaseUrl;

    private String queryString;

    private String refererUrl;

    private Context requestContext;

    private Parameters requestParameters;

    private String requestPath;

    private final List<Request> requests = new ArrayList<Request>();

    private ServiceManager serviceManager;

    private Context sessionContext;

    private String sessionId;

    private TimeZone timeZone = TimeZone.getDefault();

    private final WaterviewSpi waterview;

    protected AbstractRunData( WaterviewSpi waterview )
    {
        this.waterview = waterview;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RunData#getApplicationBaseUrl()
     */
    public String getApplicationBaseUrl()
    {
        return applicationBaseUrl;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RunData#getInputContentType()
     */
    public String getInputContentType()
    {
        return inputContentType;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RunData#getLocale()
     */
    public Locale getLocale()
    {
        return locale;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RunData#getPageBaseUrl()
     */
    public String getPageBaseUrl()
    {
        return pageBaseUrl;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RunData#getParameters()
     */
    public Parameters getParameters()
    {
        return requestParameters;
    }

    /**
     * Getter method for property queryString
     *
     * @return Returns the queryString.
     */
    public String getQueryString()
    {
        return queryString;
    }

    /**
     * Getter method for property refererUrl
     *
     * @return Returns the refererUrl.
     */
    public String getRefererUrl()
    {
        return refererUrl;
    }

    /**
     * Overwrite or implement method getRequestContext()
     * @see com.cyclopsgroup.waterview.RunData#getRequestContext()
     */
    public Context getRequestContext()
    {
        return requestContext;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RunData#getRequestPath()
     */
    public String getRequestPath()
    {
        return requestPath;
    }

    public List<Request> getRequests()
    {
        return requests;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RunData#getServiceManager()
     */
    public ServiceManager getServiceManager()
    {
        return serviceManager;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RunData#getSessionContext()
     */
    public Context getSessionContext()
    {
        return sessionContext;
    }

    /**
     * Overwrite or implement method getSessionId()
     * @see com.cyclopsgroup.waterview.RunData#getSessionId()
     */
    public String getSessionId()
    {
        return sessionId;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RunData#getTimeZone()
     */
    public TimeZone getTimeZone()
    {
        return timeZone;
    }

    public WaterviewSpi getWaterview()
    {
        return waterview;
    }

    /**
     * Setter method for applicationBaseUrl
     *
     * @param applicationBaseUrl The applicationBaseUrl to set.
     */
    public void setApplicationBaseUrl( String applicationBaseUrl )
    {
        this.applicationBaseUrl = applicationBaseUrl;
    }

    /**
     * Setter method for inputContentType
     *
     * @param inputContentType The inputContentType to set.
     */
    public void setInputContentType( String inputContentType )
    {
        this.inputContentType = inputContentType;
    }

    /**
     * Overwrite or implement method setLocale()
     *
     * @see com.cyclopsgroup.waterview.RunData#setLocale(java.util.Locale)
     */
    public void setLocale( Locale locale )
    {
        this.locale = locale;
    }

    /**
     * Setter method for pageBaseUrl
     *
     * @param pageBaseUrl The pageBaseUrl to set.
     */
    public void setPageBaseUrl( String pageBaseUrl )
    {
        this.pageBaseUrl = pageBaseUrl;
    }

    /**
     * Setter method for requestParameters
     *
     * @param requestParameters The requestParameters to set.
     */
    public void setParams( Parameters requestParameters )
    {
        this.requestParameters = requestParameters;
    }

    /**
     * Setter method for property queryString
     *
     * @param queryString The queryString to set.
     */
    public void setQueryString( String queryString )
    {
        this.queryString = queryString;
    }

    /**
     * Setter method for property refererUrl
     *
     * @param refererUrl The refererUrl to set.
     */
    public void setRefererUrl( String refererUrl )
    {
        this.refererUrl = refererUrl;
    }

    /**
     * Set request context
     * @param pageContext Context object
     */
    public void setRequestContext( Context pageContext )
    {
        this.requestContext = pageContext;
    }

    /**
     * Setter method for requestPath
     *
     * @param requestPath The requestPath to set.
     */
    public void setRequestPath( String requestPath )
    {
        this.requestPath = requestPath;
    }

    /**
     * Setter method for serviceManager
     *
     * @param serviceManager The serviceManager to set.
     */
    public void setServiceManager( ServiceManager serviceManager )
    {
        this.serviceManager = serviceManager;
    }

    /**
     * Setter method for sessionContext
     *
     * @param sessionContext The sessionContext to set.
     */
    public void setSessionContext( Context sessionContext )
    {
        this.sessionContext = sessionContext;
    }

    /**
     * Set session ID
     * @param sessionId
     */
    public void setSessionId( String sessionId )
    {
        this.sessionId = sessionId;
    }

    /**
     * Setter method for timeZone
     *
     * @param timeZone The timeZone to set.
     */
    public void setTimeZone( TimeZone timeZone )
    {
        this.timeZone = timeZone;
    }
}