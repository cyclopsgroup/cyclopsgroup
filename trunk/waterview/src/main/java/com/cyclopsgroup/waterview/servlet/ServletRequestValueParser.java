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
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.ArrayUtils;

import com.cyclopsgroup.waterview.RequestValueParser;

/**
 * Servet request implemented value parser
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class ServletRequestValueParser extends RequestValueParser
{
    private MultiHashMap extra = new MultiHashMap();

    private HttpServletRequest httpServletRequest;

    /**
     * Constructor of HttpRequestValueParser
     * 
     * @param request Servlet request
     */
    public ServletRequestValueParser(HttpServletRequest request)
    {
        httpServletRequest = request;
    }

    /**
     * Overwrite or implement method add()
     *
     * @see com.cyclopsgroup.waterview.ValueParser#add(java.lang.String, java.lang.String)
     */
    public void add(String name, String value)
    {
        extra.put(name, value);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RequestValueParser#doGetValue(java.lang.String)
     */
    protected String doGetValue(String name) throws Exception
    {
        String ret = httpServletRequest.getParameter(name);
        if (ret == null && extra.containsKey(name))
        {
            Collection c = (Collection) extra.get(name);
            if (!c.isEmpty())
            {
                ret = (String) c.iterator().next();
            }
        }
        return ret;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RequestValueParser#doGetValues(java.lang.String)
     */
    protected String[] doGetValues(String name) throws Exception
    {
        String[] ret = httpServletRequest.getParameterValues(name);
        if (extra.containsKey(name))
        {
            List list = new ArrayList((Collection) extra.get(name));
            CollectionUtils.addAll(list, ret);
            ret = (String[]) list.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        }
        return ret;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RequestValueParser#getFileItem(java.lang.String)
     */
    public FileItem getFileItem(String name)
    {
        return null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RequestValueParser#getFileItems(java.lang.String)
     */
    public FileItem[] getFileItems(String name)
    {
        return EMPTY_FILEITEM_ARRAY;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RequestValueParser#remove(java.lang.String)
     */
    public void remove(String name)
    {
        extra.remove(name);
    }
}