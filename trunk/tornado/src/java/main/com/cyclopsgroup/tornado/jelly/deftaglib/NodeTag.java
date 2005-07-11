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
package com.cyclopsgroup.tornado.jelly.deftaglib;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.clib.lang.xml.ClibTagSupport;

public class NodeTag extends ClibTagSupport
{
    private String name;

    private String page;

    public void doTag(XMLOutput arg0) throws MissingAttributeException,
            JellyTagException
    {
        requireAttribute("name");
        requireAttribute("page");
        
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the page.
     */
    public String getPage()
    {
        return page;
    }

    /**
     * @param page The page to set.
     */
    public void setPage(String page)
    {
        this.page = page;
    }
}
