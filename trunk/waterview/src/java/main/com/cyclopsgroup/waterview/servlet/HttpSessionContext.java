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
package com.cyclopsgroup.waterview.servlet;

import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.iterators.EnumerationIterator;

import com.cyclopsgroup.clib.lang.Context;

/**
 * Session context
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class HttpSessionContext implements Context
{
    private HttpSession httpSession;

    /**
     * Constructor for class HttpSessionContext
     *
     * @param httpSession Valid http session
     */
    public HttpSessionContext(HttpSession httpSession)
    {
        this.httpSession = httpSession;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.Context#get(java.lang.String)
     */
    public Object get(String name)
    {
        return httpSession.getAttribute(name);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.Context#keys()
     */
    public Iterator keys()
    {
        return new EnumerationIterator(httpSession.getAttributeNames());
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.Context#put(java.lang.String, java.lang.Object)
     */
    public void put(String name, Object value)
    {
        httpSession.setAttribute(name, value);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.clib.lang.Context#remove(java.lang.String)
     */
    public void remove(String name)
    {
        httpSession.removeAttribute(name);
    }
}
