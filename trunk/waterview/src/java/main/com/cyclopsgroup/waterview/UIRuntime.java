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

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cyclopsgroup.gearset.bean.ValueParser;
import com.cyclopsgroup.gearset.runtime.Context;

/**
 * Runtime objects
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public interface UIRuntime
{
    /**
     * Get original servlet request object
     * 
     * @return HttpServletRequest object
     */
    HttpServletRequest getHttpServletRequest();

    /**
     * Method getHttpServletResponse() in class Runtime
     * 
     * @return HttpServletResponse object
     */
    HttpServletResponse getHttpServletResponse();

    /**
     * Method getHttpSession() in class Runtime
     * 
     * @return HttpSession object
     */
    HttpSession getHttpSession();

    /**
     * Method getLocale() in class WebRuntime
     * 
     * @return Current local object
     */
    Locale getLocale();

    /**
     * Get modifiable processor list
     * 
     * @return Modifiable list
     */
    List getActions();

    /**
     * Get context for page rendering
     * 
     * @return Context interface
     */
    Context getRenderContext();

    /**
     * Method getRenderTemplate() in class WebRuntime
     * 
     * @return Render template
     */
    String getPage();

    /**
     * Get parameter parser for request parameters
     * 
     * @return Vaue parser interface
     */
    ValueParser getRequestParameters();

    /**
     * Method setRenderTemplate() in class WebRuntime
     * 
     * @param template
     */
    void setPage(String template);
}