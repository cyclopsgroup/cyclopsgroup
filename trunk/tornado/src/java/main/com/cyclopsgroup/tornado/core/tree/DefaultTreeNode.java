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
package com.cyclopsgroup.tornado.core.tree;

import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Default implementation of tree node
 */
public class DefaultTreeNode implements TreeNode
{
    private Map children = ListOrderedMap.decorate(new Hashtable());

    private boolean expandable = false;

    private String id;

    /**
     * Create tree node with given ID
     * @param id
     */
    public DefaultTreeNode(String id)
    {
        this.id = id;
    }

    /**
     * Add child node
     * 
     * @param node Tree node object to add
     */
    public void addChild(TreeNode node)
    {
        children.put(node.getId(), node);
    }

    /**
     * Overwrite or implement method getChildren()
     * @see com.cyclopsgroup.tornado.core.tree.TreeNode#getChildren()
     */
    public TreeNode[] getChildren()
    {
        return (TreeNode[]) children.values().toArray(TreeNode.EMPTY_ARRAY);
    }

    /**
     * Overwrite or implement method getId()
     * @see com.cyclopsgroup.tornado.core.tree.TreeNode#getId()
     */
    public String getId()
    {
        return id;
    }

    public boolean isExpandable()
    {
        return expandable;
    }

    /**
     * Remove child node
     * 
     * @param nodeId Id of node
     */
    public void removeChild(String nodeId)
    {
        children.remove(nodeId);
    }

    /**
     * @param expandable The expandable to set.
     */
    public void setExpandable(boolean expandable)
    {
        this.expandable = expandable;
    }
}
