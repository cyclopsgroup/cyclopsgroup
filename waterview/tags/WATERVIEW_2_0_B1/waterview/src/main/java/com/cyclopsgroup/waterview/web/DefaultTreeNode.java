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
package com.cyclopsgroup.waterview.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import com.cyclopsgroup.waterview.MapValueParser;
import com.cyclopsgroup.waterview.ValueParser;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Default implementation of a node
 */
public class DefaultTreeNode implements TreeNode
{
    private ValueParser attributes = new MapValueParser(new HashMap());

    private Map children = ListOrderedMap.decorate(new HashMap());

    private boolean end;

    private String nodeId;

    private TreeNode parentNode;

    /**
     * Constructor for class DefaultTreeNode
     *
     * @param nodeId Unique ID of node
     * @param parentNode Parent node
     */
    public DefaultTreeNode(String nodeId, TreeNode parentNode)
    {
        this.nodeId = nodeId;
        this.parentNode = parentNode;
    }

    /**
     * Add child node
     *
     * @param child Child node
     */
    public void addChildNode(TreeNode child)
    {
        children.put(child.getNodeId(), child);
    }

    /**
     * Overwrite or implement method in DefaultTreeNode
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getAttributes()
     */
    public ValueParser getAttributes()
    {
        return attributes;
    }

    /**
     * Overwrite or implement method getChildrenNodes()
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getChildrenNodes()
     */
    public TreeNode[] getChildrenNodes()
    {
        return (TreeNode[]) children.values().toArray(EMPTY_ARRAY);
    }

    /**
     * Overwrite or implement method getNodeId()
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getNodeId()
     */
    public String getNodeId()
    {
        return nodeId;
    }

    /**
     * Overwrite or implement method getParentNode()
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getParentNode()
     */
    public TreeNode getParentNode()
    {
        return parentNode;
    }

    /**
     * Getter method for field end
     *
     * @return Returns the end.
     */
    public boolean isEnd()
    {
        return end;
    }

    /**
     * Setter method for field end
     *
     * @param end The end to set.
     */
    public void setEnd(boolean end)
    {
        this.end = end;
    }
}
