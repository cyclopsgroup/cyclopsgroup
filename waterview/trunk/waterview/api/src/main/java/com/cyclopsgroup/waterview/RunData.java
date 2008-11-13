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
package com.cyclopsgroup.waterview;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Queue;
import java.util.TimeZone;

/**
 * Runtime objects
 *
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */

public interface RunData
{
    /** Locale name in session context */
    String LOCALE_NAME = RunData.class.getName() + "/locale";

    /** Message list name in session context */
    String MESSAGES_NAME = RunData.class.getName() + "/messags";

    /** Name of it in context */
    String NAME = "data";

    /**
     * Name of variable for view to open
     */
    String OPEN_VIEW_NAME = "openView";

    /** Page name in context */
    String PAGE_NAME = "page";

    /** Name for request context */
    String REQUEST_CONTEXT_NAME = "requestContext";

    /** Name of service manager in context */
    String SERVICE_MANAGER_NAME = "serviceManager";

    /**
     * @return Mutable List of action names
     */
    Queue<String> getActionQueue();

    /**
     * Get base url for this waterview application
     *
     * @return Base url
     */
    String getApplicationBaseUrl();

    /**
     * Get content type of request
     *
     * @return Content type of request
     */
    String getInputContentType();

    /**
     * Method getLocale() in class WebRuntime
     *
     * @return Current local object
     */
    Locale getLocale();

    /**
     * Get mime type of given path
     *
     * @param fileName Name of file
     * @return Mimetype
     */
    String getMimeType( String fileName );

    /**
     * Get output print writer
     *
     * @return PrintWriter object for http response
     */
    PrintWriter getOutput()
        throws IOException;

    /**
     * Get output stream
     *
     * @return Output stream
     */
    OutputStream getOutputStream()
        throws IOException;

    /**
     * Base url for page
     *
     * @return Base url for page
     */
    String getPageBaseUrl();

    /**
     * Get parameter parser for request parameters
     *
     * @return Vaue parser interface
     */
    Parameters getParameters();

    /**
     * Get query string
     *
     * @return Query string
     */
    String getQueryString();

    /**
     * Get url of referer
     *
     * @return Referer url
     */
    String getRefererUrl();

    /**
     * Get context for page rendering
     *
     * @return Context interface
     */
    Context getRequestContext();

    /**
     * Get path in request
     *
     * @return Path in request
     */
    String getRequestPath();

    /**
     * Get session scope context
     *
     * @return Session scope context
     */
    Context getSessionContext();

    /**
     * Get unique ID of session
     *
     * @return Session ID
     */
    String getSessionId();

    /**
     * Get time zone for current request
     *
     * @return Time zone
     */
    TimeZone getTimeZone();

    /**
     * Set locale
     *
     * @param locale Locale to set
     */
    void setLocale( Locale locale );

    /**
     * Set content type for response
     *
     * @param contentType Content type
     */
    void setOutputContentType( String contentType );
}