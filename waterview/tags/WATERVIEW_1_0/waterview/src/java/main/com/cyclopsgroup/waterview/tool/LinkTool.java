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
package com.cyclopsgroup.waterview.tool;

import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Link ui tool
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class LinkTool extends BaseUITool
{
    private static final String ENCODING = "UTF-8";

    private String applicationBaseUrl;

    private String currentPath;

    private String pageBaseUrl;

    private String pageSeparator;

    private StringBuffer path;

    private StringBuffer queryString;

    /**
     * Add action module
     * 
     * @param action Action path
     * @return Link tool itself
     */
    public LinkTool addAction(String action)
    {
        return addModule(action);
    }

    /**
     * Add generic module path
     * 
     * @param module Module path
     * @return Link tool itself
     */
    public LinkTool addModule(String module)
    {
        if (path == null)
        {
            path = new StringBuffer('/' + module);
        }
        else
        {
            path.append(pageSeparator).append(module);
        }
        return this;
    }

    /**
     * Add page module
     * 
     * @param page Page path
     * @return Link tool itself
     */
    public LinkTool addPage(String page)
    {
        return addModule(page);
    }

    /**
     * Add parameter into query string
     * 
     * @param name Parameter name
     * @param value Parameter value
     * @return Link itself
     * @throws Exception Throw it out
     */
    public LinkTool addParam(String name, Object value) throws Exception
    {
        String s = value == null ? StringUtils.EMPTY : value.toString();
        s = URLEncoder.encode(s, ENCODING);
        String part = name + "=" + s;
        if (queryString == null)
        {
            queryString = new StringBuffer(part);
        }
        else
        {
            queryString = new StringBuffer('&').append(part);
        }
        return this;
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see com.cyclopsgroup.waterview.tool.UITool#dispose(com.cyclopsgroup.waterview.UIRuntime)
     */
    public void dispose(UIRuntime runtime) throws Exception
    {
        queryString = null;
        path = null;
    }

    /**
     * Get resource url
     * 
     * $link.getResource("images/logo.gif") will return http://localhhost:8080/waterview/images/logo.gif
     * 
     * @param content Resource position
     * @return Full resource url
     */
    public String getResource(String content)
    {
        if (content.charAt(0) == '/')
        {
            return applicationBaseUrl + content;
        }
        return applicationBaseUrl + '/' + content;
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see com.cyclopsgroup.waterview.tool.UITool#initialize(com.cyclopsgroup.waterview.UIRuntime)
     */
    public void initialize(UIRuntime runtime) throws Exception
    {
        applicationBaseUrl = runtime.getApplicationBaseUrl();
        pageBaseUrl = runtime.getPageBaseUrl();
        currentPath = runtime.getRequestPath();
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer ret = new StringBuffer(pageBaseUrl);
        if (path == null)
        {
            ret.append(currentPath);
        }
        else
        {
            ret.append(path.toString());
            path = null;
        }
        if (queryString != null)
        {
            ret.append('&').append(queryString.toString());
            queryString = null;
        }
        return ret.toString();
    }
}