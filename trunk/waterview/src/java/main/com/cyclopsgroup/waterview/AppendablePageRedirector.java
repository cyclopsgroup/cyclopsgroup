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

public class AppendablePageRedirector extends PageRedirector
{
    private String page;

    private StringBuffer queryString = new StringBuffer();

    public AppendablePageRedirector addQueryData(String name, int number)
    {
        return addQueryData(name, String.valueOf(number));
    }

    public AppendablePageRedirector addQueryData(String name, long number)
    {
        return addQueryData(name, String.valueOf(number));
    }

    public AppendablePageRedirector addQueryData(String name, Object object)
    {
        if (queryString.length() > 0)
        {
            queryString.append('&');
        }
        queryString.append(name).append('=').append(object);
        return this;
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
        return queryString.toString();
    }
}
