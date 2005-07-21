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

import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.avalon.framework.service.ServiceManager;

import com.cyclopsgroup.clib.lang.Context;

/**
 * Runtime objects
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */

public interface PageRuntime
{

    /** Name of applicationBase url in context */
    String CONTEXT_APPLICATION_BASE_NAME = "applicationBase";

    /** Name of pageBase url in context */
    String CONTEXT_PAGE_BASE_NAME = "pageBase";

    /** Name of pageContext in context */
    String CONTEXT_PAGE_CONTEXT_NAME = "pageContext";

    /** Name of page in context */
    String CONTEXT_PAGE_NAME = "page";

    /** Name of parameters in context */
    String CONTEXT_PARAMS_NAME = "params";

    /** Name of runtime in context */
    String CONTEXT_RUNTIME_NAME = "runtime";

    /** Name of this object in context */
    String NAME = PageRuntime.class.getName();

    /**
     * Get modifiable processor list
     * 
     * @return Modifiable list
     */
    List getActions();

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
    String getMimeType(String fileName);

    /**
     * Get output print writer
     *
     * @return PrintWriter object for http response
     */
    PrintWriter getOutput();

    String getPackage();

    /**
     * Method getRenderTemplate() in class WebRuntime
     * 
     * @return Render template
     */
    String getPage();

    /**
     * Base url for page
     * 
     * @return Base url for page
     */
    String getPageBaseUrl();

    /**
     * Get context for page rendering
     * 
     * @return Context interface
     */
    Context getPageContext();

    /**
     * Redirector
     *
     * @return Redirector object
     */
    Redirector getRedirector();

    /**
     * Get parameter parser for request parameters
     * 
     * @return Vaue parser interface
     */
    RequestValueParser getRequestParameters();

    /**
     * Get path in request
     * 
     * @return Path in request
     */
    String getRequestPath();

    /**
     * Method getComponentManager() in class UIRuntime
     * 
     * @return Service manager instance
     */
    ServiceManager getServiceManager();

    /**
     * Get session scope context
     *
     * @return Session scope context
     */
    Context getSessionContext();

    /**
     * Get unique ID of session
     */
    String getSessionId();

    /**
     * Get time zone for current request
     * 
     * @return Time zone
     */
    TimeZone getTimeZone();

    /**
     * Set content type for response
     * 
     * @param contentType Content type
     */
    void setOutputContentType(String contentType);

    /**
     * Method setRenderTemplate() in class WebRuntime
     * 
     * @param template
     */
    void setPage(String page);

    /**
     * Set the page context
     *
     * @param context Page context
     */
    void setPageContext(Context context);

    void setRedirector(Redirector redirector);
}