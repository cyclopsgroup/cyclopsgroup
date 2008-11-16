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

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.cyclib.ValueParser;

/**
 * Runtime objects
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */

public interface UIRuntime
{

    /**
     * Get modifiable processor list
     * 
     * @return Modifiable list
     * 
     * @uml.property name="actions" 
     */
    List getActions();

    /**
     * Get base url for this waterview application
     * 
     * @return Base url
     * 
     * @uml.property name="applicationBaseUrl" 
     */
    String getApplicationBaseUrl();

    /**
     * Get content type of request
     * 
     * @return Content type of request
     * 
     * @uml.property name="contentType" 
     */
    String getContentType();

    /**
     * Method getLocale() in class WebRuntime
     * 
     * @return Current local object
     * 
     * @uml.property name="locale" 
     */
    Locale getLocale();

    /**
     * Get output print writer object
     * 
     * @return PrintWriter object
     * 
     * @uml.property name="output" 
     */
    PrintWriter getOutput();

    /**
     * Method getRenderTemplate() in class WebRuntime
     * 
     * @return Render template
     * 
     * @uml.property name="page" 
     */
    String getPage();

    /**
     * Base url for page
     * 
     * @return Base url for page
     * 
     * @uml.property name="pageBaseUrl" 
     */
    String getPageBaseUrl();

    /**
     * Get context for page rendering
     * 
     * @return Context interface
     * 
     * @uml.property name="pageContext"
     * @uml.associationEnd multiplicity="(0 1)"
     */
    Context getPageContext();

    /**
     * Get parameter parser for request parameters
     * 
     * @return Vaue parser interface
     * 
     * @uml.property name="requestParameters"
     * @uml.associationEnd multiplicity="(0 1)"
     */
    ValueParser getRequestParameters();

    /**
     * Get path in request
     * 
     * @return Path in request
     * 
     * @uml.property name="requestPath" 
     */
    String getRequestPath();

    /**
     * Method getComponentManager() in class UIRuntime
     * 
     * @return Service manager instance
     * 
     * @uml.property name="serviceManager"
     * @uml.associationEnd multiplicity="(0 1)"
     */
    ServiceManager getServiceManager();

    /**
     * Get context in session level
     * 
     * @return Context interface
     * 
     * @uml.property name="sessionContext"
     * @uml.associationEnd multiplicity="(0 1)"
     */
    Context getSessionContext();

    /**
     * Get time zone for current request
     * 
     * @return Time zone
     * 
     * @uml.property name="timeZone" 
     */
    TimeZone getTimeZone();

    /**
     * Get current user
     *
     * @return User interface
     */
    UIRuntimeUser getUser();

    /**
     * Set content type for response
     * 
     * @param contentType Content type
     * 
     * @uml.property name="contentType"
     */
    void setContentType(String contentType);

    /**
     * Method setRenderTemplate() in class WebRuntime
     * 
     * @param template
     * 
     * @uml.property name="page"
     */
    void setPage(String template);
}