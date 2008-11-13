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
package com.cyclopsgroup.waterview.spi;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.TimeZone;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.DynamicLink;
import com.cyclopsgroup.waterview.Parameters;

/**
 * Abstract base runtime class
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class AbstractRunData
    implements RunDataSpi
{
    private Queue<String> actionQueue = new LinkedList<String>();

    private String applicationBaseUrl;

    private Throwable error;

    private String errorMessage;

    private String inputContentType;

    private List<InvalidInput> invalidInputs = new ArrayList<InvalidInput>();

    private DynamicLink link;

    private Locale locale = Locale.getDefault();

    private List<String> messages = new ArrayList<String>();

    private PrintWriter output;

    private OutputStream outputStream;

    private String pageBaseUrl;

    private String queryString;

    private String refererUrl;

    private Context requestContext;

    private Parameters requestParameters;

    private String requestPath;

    private Context sessionContext;

    private String sessionId;

    private TimeZone timeZone = TimeZone.getDefault();

    public Queue<String> getActionQueue()
    {
        return actionQueue;
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

    public Throwable getError()
    {
        return error;
    }

    public String getErrorMessage()
    {
        return errorMessage;
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

    public List<InvalidInput> getInvalidInputs()
    {
        return invalidInputs;
    }

    public DynamicLink getLink()
    {
        return link;
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

    public List<String> getMessages()
    {
        return messages;
    }

    public PrintWriter getOutput()
    {
        return output;
    }

    public OutputStream getOutputStream()
    {
        return outputStream;
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

    /**
     * Setter method for applicationBaseUrl
     *
     * @param applicationBaseUrl The applicationBaseUrl to set.
     */
    public void setApplicationBaseUrl( String applicationBaseUrl )
    {
        this.applicationBaseUrl = applicationBaseUrl;
    }

    public void setError( Throwable error )
    {
        this.error = error;
    }

    public void setErrorMessage( String errorMessage )
    {
        this.errorMessage = errorMessage;
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

    public void setLink( DynamicLink link )
    {
        this.link = link;
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

    public void setOutput( PrintWriter output )
    {
        this.output = output;
    }

    public void setOutputStream( OutputStream outputStream )
    {
        this.outputStream = outputStream;
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