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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.iterators.EnumerationIterator;

import com.cyclopsgroup.waterview.Context;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Servlet request implemented clib context
 */
public class ServletRequestContext
    implements Context
{
    private HttpServletRequest request;

    /**
     * Constructor
     * 
     * @param request Http servlet request
     */
    public ServletRequestContext( HttpServletRequest request )
    {
        this.request = request;
    }

    /**
     * Overwrite or implement method get()
     * @see com.cyclopsgroup.waterview.Context#get(java.lang.String)
     */
    public Object get( String name )
    {
        return request.getAttribute( name );
    }

    /**
     * Overwrite or implement method keys()
     * @see com.cyclopsgroup.waterview.Context#keys()
     */
    public Iterator keys()
    {
        return new EnumerationIterator( request.getAttributeNames() );
    }

    /**
     * Overwrite or implement method put()
     * @see com.cyclopsgroup.waterview.Context#put(java.lang.String, java.lang.Object)
     */
    public void put( String name, Object value )
    {
        request.setAttribute( name, value );
    }

    /**
     * Overwrite or implement method remove()
     * @see com.cyclopsgroup.waterview.Context#remove(java.lang.String)
     */
    public void remove( String name )
    {
        request.removeAttribute( name );
    }
}
