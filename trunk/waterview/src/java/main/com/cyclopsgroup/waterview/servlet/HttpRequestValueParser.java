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

import javax.servlet.http.HttpServletRequest;

import com.cyclopsgroup.gearset.beans.ValueParser;

/**
 * Servet request implemented value parser
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class HttpRequestValueParser extends ValueParser
{
    private HttpServletRequest httpServletRequest;

    /**
     * Constructor of HttpRequestValueParser
     * 
     * @param request
     *                   Servlet request
     */
    HttpRequestValueParser(HttpServletRequest request)
    {
        httpServletRequest = request;
    }

    /**
     * Override method doGetValue in super class of HttpRequestValueParser
     * 
     * @see com.cyclopsgroup.gearset.beans.ValueParser#doGetValue(java.lang.String)
     */
    protected String doGetValue(String valueName) throws Exception
    {
        return getHttpServletRequest().getParameter(valueName);
    }

    /**
     * Override method doGetValues in super class of HttpRequestValueParser
     * 
     * @see com.cyclopsgroup.gearset.beans.ValueParser#doGetValues(java.lang.String)
     */
    protected String[] doGetValues(String valueName) throws Exception
    {
        return getHttpServletRequest().getParameterValues(valueName);
    }

    /**
     * Getter method for property httpServletRequest
     * 
     * @return Returns the httpServletRequest.
     */
    public HttpServletRequest getHttpServletRequest()
    {
        return httpServletRequest;
    }
}