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
package com.cyclopsgroup.waterview.jelly;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.TagSupport;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.cyclib.jelly.TagUtils;
import com.cyclopsgroup.waterview.core.RenderPageValve.RuntimeRenderer;

/**
 * Include tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class IncludeTag extends TagSupport
{
    private String category;

    private String page;

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        TagUtils.requireAttribute("category", category);
        TagUtils.requireAttribute("page", page);
        RuntimeRenderer renderer = (RuntimeRenderer) context
                .getVariable(RuntimeRenderer.NAME);
        try
        {
            renderer.render(category, page);
        }
        catch (Exception e)
        {
            throw new JellyTagException("Render page [" + category + "/" + page
                    + "] exception", e);
        }
    }

    /**
     * Getter method for category
     *
     * @return Returns the category.
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Getter method for page
     *
     * @return Returns the page.
     */
    public String getPage()
    {
        return page;
    }

    /**
     * Setter method for category
     *
     * @param category The category to set.
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * Setter method for page
     *
     * @param page The page to set.
     */
    public void setPage(String page)
    {
        this.page = page;
    }
}