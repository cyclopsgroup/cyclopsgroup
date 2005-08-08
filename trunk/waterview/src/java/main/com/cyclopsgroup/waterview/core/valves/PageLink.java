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
package com.cyclopsgroup.waterview.core.valves;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Link tool
 */
public class PageLink
{
    /** Do action instruction */
    public static final String ACTION_INSTRUCTOR = "!do!";

    /** Encoding for parameters */
    public static final String ENCODING = "UTF-8";

    /** Name of this tool */
    public static final String NAME = "link";

    /** Show page instruction */
    public static final String PAGE_INSTRUCTOR = "!show!";

    private RuntimeData data;

    private boolean external = false;

    private boolean pageSet = false;

    private StringBuffer queryString;

    private StringBuffer requestPath = new StringBuffer();

    /**
     * Constructor for type LinkTool
     *
     * @param data Runtime data
     */
    public PageLink(RuntimeData data)
    {
        this.data = data;
    }

    /**
     * Add action for current link
     * 
     * @param action Action path
     * @return Link itself
     */
    public PageLink addAction(String action)
    {
        external = false;
        requestPath.append('/').append(ACTION_INSTRUCTOR).append(action);
        return this;
    }

    /**
     * Add query parameter
     * 
     * @param name Name of parameter
     * @param value Value of parameter
     * @return Link itself
     * @throws UnsupportedEncodingException Throw it out
     */
    public PageLink addQueryData(String name, Object value)
            throws UnsupportedEncodingException
    {
        if (queryString == null)
        {
            queryString = new StringBuffer();
        }
        else
        {
            queryString.append('&');
        }
        String v = value == null ? StringUtils.EMPTY : value.toString();
        queryString.append(name).append('=').append(
                URLEncoder.encode(v, ENCODING));
        return this;
    }

    /**
     * Set page
     *
     * @param path
     * @return Link tool itself
     */
    public PageLink setPage(String path)
    {
        if (pageSet)
        {
            return this;
        }
        requestPath.append('/').append(PAGE_INSTRUCTOR).append(path);
        pageSet = true;
        external = false;
        return this;
    }

    /**
     * Set resource path
     *
     * @param path Path of resource
     * @return It self
     */
    public PageLink setResource(String path)
    {
        requestPath = new StringBuffer(path);
        external = true;
        return this;
    }

    /**
     * Override method LinkTool in supper class
     *
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer url = new StringBuffer();
        if (external)
        {
            url.append(data.getApplicationBaseUrl());
        }
        else
        {
            url.append(data.getPageBaseUrl());
        }
        if (requestPath.length() == 0 && !external)
        {
            url.append(data.getRequestPath());
        }
        else
        {
            url.append(requestPath);
        }

        if (queryString != null)
        {
            url.append('?').append(queryString);
        }
        String fullUrl = url.toString();

        queryString = null;
        pageSet = false;
        requestPath = new StringBuffer();
        external = false;
        return fullUrl;
    }
}
