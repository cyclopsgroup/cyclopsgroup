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
package com.cyclopsgroup.waterview;

import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Page redirector which accepts a given page and an optional query string
 */
public class SimplePageRedirector extends PageRedirector
{
    private String page;

    private String queryString;

    /**
     * @param page
     */
    public SimplePageRedirector(String page)
    {
        this(page, StringUtils.EMPTY);
    }

    /**
     * @param page
     * @param string
     */
    public SimplePageRedirector(String page, String string)
    {
        this.page = page;
        queryString = string;
    }

    /**
     * Overwrite or implement method getPage()
     * @see com.cyclopsgroup.waterview.PageRedirector#getPage()
     */
    public String getPage()
    {
        return page;
    }

    /**
     * Overwrite or implement method getQueryString()
     * @see com.cyclopsgroup.waterview.PageRedirector#getQueryString()
     */
    public String getQueryString()
    {
        return queryString;
    }
}
