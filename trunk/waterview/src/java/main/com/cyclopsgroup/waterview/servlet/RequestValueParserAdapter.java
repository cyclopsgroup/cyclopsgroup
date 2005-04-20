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

import com.cyclopsgroup.waterview.RequestValueParser;

/**
 * Servet request implemented value parser
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
class RequestValueParserAdapter extends RequestValueParser
{
    private HttpServletRequest httpServletRequest;

    /**
     * Constructor of HttpRequestValueParser
     * 
     * @param request Servlet request
     */
    RequestValueParserAdapter(HttpServletRequest request)
    {
        httpServletRequest = request;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RequestValueParser#add(java.lang.String, java.lang.String)
     */
    public void add(String name, String value)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RequestValueParser#doGetValue(java.lang.String)
     */
    protected String doGetValue(String name) throws Exception
    {
        return httpServletRequest.getParameter(name);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RequestValueParser#doGetValues(java.lang.String)
     */
    protected String[] doGetValues(String name) throws Exception
    {
        return httpServletRequest.getParameterValues(name);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RequestValueParser#remove(java.lang.String)
     */
    public void remove(String name)
    {
        throw new UnsupportedOperationException();
    }
}