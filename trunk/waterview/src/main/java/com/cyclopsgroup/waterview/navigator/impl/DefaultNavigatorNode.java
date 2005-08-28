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

import java.util.Collection;
import java.util.HashMap;

import com.cyclopsgroup.waterview.MapValueParser;
import com.cyclopsgroup.waterview.ValueParser;
import com.cyclopsgroup.waterview.navigator.NavigatorNode;
import com.cyclopsgroup.waterview.web.TreeNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Default implementation of navigator node
 */
class DefaultNavigatorNode implements NavigatorNode
{
    static final String DESCRIPTION_NAME = "description";

    static final String NAME_NAME = "name";

    static final String PAGE_NAME = "page";

    static final String TITLE_NAME = "title";

    private ValueParser attributes = new MapValueParser(new HashMap());

    private DefaultNavigatorHome navigatorHome;

    private String parentPath;

    private String path;

    DefaultNavigatorNode(DefaultNavigatorHome nav, String name,
            String parentPath)
    {
        if (parentPath != null)
        {
            this.path = parentPath + '/' + name;
        }
        else
        {
            this.path = name;
        }
        this.parentPath = parentPath;
        this.navigatorHome = nav;
    }

    /**
     * Overwrite or implement method in DefaultNavigatorNode
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getAttributes()
     */
    public ValueParser getAttributes()
    {
        return attributes;
    }

    /**
     * Overwrite or implement method in DefaultNavigatorNode
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getChildrenNodes()
     */
    public TreeNode[] getChildrenNodes()
    {
        Collection nodes = navigatorHome.getChildren(parentPath);
        return (TreeNode[]) nodes.toArray(TreeNode.EMPTY_ARRAY);
    }

    /**
     * Overwrite or implement method in DefaultNavigatorNode
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorNode#getDescription()
     */
    public String getDescription()
    {
        return getAttributes().getString(DESCRIPTION_NAME);
    }

    /**
     * Overwrite or implement method in DefaultNavigatorNode
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getNodeId()
     */
    public String getNodeId()
    {
        return path;
    }

    /**
     * Overwrite or implement method in DefaultNavigatorNode
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorNode#getPage()
     */
    public String getPage()
    {
        return getAttributes().getString(PAGE_NAME);
    }

    /**
     * Overwrite or implement method in DefaultNavigatorNode
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorNode#getParentNavigatorNode()
     */
    public NavigatorNode getParentNavigatorNode()
    {
        return navigatorHome.getNode(parentPath);
    }

    /**
     * Overwrite or implement method in DefaultNavigatorNode
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getParentNode()
     */
    public TreeNode getParentNode()
    {
        return getParentNavigatorNode();
    }

    /**
     * Get parent path
     *
     * @return Parent path
     */
    public String getParentPath()
    {
        return parentPath;
    }

    /**
     * Get current path
     *
     * @return Current path
     */
    public String getPath()
    {
        return path;
    }

    public String getTitle()
    {
        return getAttributes().getString(TITLE_NAME);
    }
}
