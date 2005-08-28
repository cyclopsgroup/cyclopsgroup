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
package com.cyclopsgroup.waterview.navigator.impl;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.utils.BaseTagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Node tag
 */
public class NodeTag extends BaseTagSupport
{
    private String name;

    private String page;

    private String title;

    private String description;

    private String parentPath;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPage()
    {
        return page;
    }

    public void setPage(String page)
    {
        this.page = page;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Overwrite or implement method in NodeTag
     *
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        requireAttribute("name");
        requireAttribute("title");

        if (getParent() instanceof TreeTag)
        {
            parentPath = ((TreeTag) getParent()).getPosition();
        }
        else if (getParent() instanceof NodeTag)
        {
            parentPath = ((NodeTag) getParent()).getParentPath();
        }
        else
        {
            throw new JellyTagException("Parent tag must be tree or node");
        }
        NavigationTag nt = (NavigationTag) findAncestorWithClass(NavigationTag.class);
        DefaultNavigatorNode node = new DefaultNavigatorNode(nt.getNavigator(),
                getName(), parentPath);
        invokeBody(output);
    }

    public String getParentPath()
    {
        return parentPath;
    }
}
