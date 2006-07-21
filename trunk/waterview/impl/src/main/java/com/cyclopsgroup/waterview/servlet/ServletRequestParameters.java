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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.fileupload.FileItem;

import com.cyclopsgroup.waterview.Parameters;

/**
 * Servet request implemented value parser
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class ServletRequestParameters
    extends Parameters
{
    private MultiHashMap extra = new MultiHashMap();

    private HttpServletRequest httpServletRequest;

    /**
     * Override method doGetAttributeNames in class ServletRequestValueParser
     *
     * @see com.cyclopsgroup.waterview.Attributes#doGetAttributeNames()
     */
    protected Set<String> doGetAttributeNames()
    {
        HashSet<String> names = new HashSet<String>();
        CollectionUtils.addAll( names, httpServletRequest.getParameterNames() );
        names.addAll( extra.keySet() );
        return names;
    }

    /**
     * Constructor of HttpRequestValueParser
     * 
     * @param request Servlet request
     */
    public ServletRequestParameters( HttpServletRequest request )
    {
        httpServletRequest = request;
    }

    /**
     * Overwrite or implement method add()
     *
     * @see com.cyclopsgroup.waterview.Attributes#add(java.lang.String, java.lang.String)
     */
    @Override
    public void add( String name, String value )
    {
        extra.put( name, value );
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Parameters#doGetValue(java.lang.String)
     */
    @Override
    protected String doGetValue( String name )
        throws Exception
    {
        String ret = httpServletRequest.getParameter( name );
        if ( ret == null && extra.containsKey( name ) )
        {
            Collection c = (Collection) extra.get( name );
            if ( !c.isEmpty() )
            {
                ret = (String) c.iterator().next();
            }
        }
        return ret;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Parameters#doGetValues(java.lang.String)
     */
    @Override
    protected List<String> doGetValues( String name )
        throws Exception
    {
        List<String> list = new ArrayList<String>();
        CollectionUtils.addAll( list, httpServletRequest.getParameterValues( name ) );
        if ( extra.containsKey( name ) )
        {
            list.addAll( (Collection) extra.get( name ) );
        }
        return list;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Parameters#getFileItem(java.lang.String)
     */
    @Override
    public FileItem getFileItem( String name )
    {
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Parameters#getFileItems(java.lang.String)
     */
    @Override
    public FileItem[] getFileItems( String name )
    {
        return EMPTY_FILEITEM_ARRAY;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Parameters#remove(java.lang.String)
     */
    @Override
    public void remove( String name )
    {
        extra.remove( name );
    }
}