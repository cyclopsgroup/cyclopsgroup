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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.RuntimeData;

public class PageLink
{
    public static final String NAME = "link";

    private List actions = new ArrayList();

    private String page;

    private List parameters = new ArrayList();

    private RuntimeData runtime;

    /**
     * Constructor with a page runtime
     * 
     * @param runtime
     */
    public PageLink(RuntimeData runtime)
    {
        this.runtime = runtime;
        init();
    }

    public PageLink addAction(String action)
    {
        actions.add(action);
        return this;
    }

    public PageLink addAction(String packageName, String action)
    {
        actions.add(packageName + ":" + action);
        return this;
    }

    private void init()
    {
        actions.clear();
        parameters.clear();
        page = null;
    }

    public PageLink setPage(String page)
    {
        this.page = page;
        return this;
    }

    public PageLink setPage(String packageName, String page)
    {
        return setPage(packageName + ':' + page);
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer(runtime.getPageBaseUrl());
        List parts = new ArrayList();
        for (Iterator i = actions.iterator(); i.hasNext();)
        {
            String action = (String) i.next();
            parts.add(ParseURLValve.ACTION_INSTRUCTOR + '/' + action);
        }
        if (page != null)
        {
            parts.add(page);
        }
        String url = StringUtils.join(parts.iterator(), '/');
        if (!parts.isEmpty())
        {
            sb.append("/").append(url);
        }
        url = sb.toString();
        init();
        return url;
    }
}
