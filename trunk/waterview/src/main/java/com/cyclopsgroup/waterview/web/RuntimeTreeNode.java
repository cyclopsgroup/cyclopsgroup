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

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.ValueParser;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Runtime instance of tree node
 */
public class RuntimeTreeNode implements TreeNode
{
    /** Empty array */
    public static final RuntimeTreeNode[] EMPTY_ARRAY = new RuntimeTreeNode[0];

    private Map children = ListOrderedMap.decorate(new HashMap());

    private boolean expanded;

    private TreeNode node;

    private RuntimeTreeNode parentNode;

    /**
     * Constructor for class RuntimeTreeNode
     *
     * @param parentNode Parent runtime node
     * @param node Proxy tree node
     */
    public RuntimeTreeNode(RuntimeTreeNode parentNode, TreeNode node)
    {
        this.parentNode = parentNode;
        this.node = node;
    }

    /**
     * Collapse this node
     */
    public void collapse()
    {
        if (!expanded)
        {
            return;
        }
        children.clear();
    }

    /**
     * Filter child nodes
     *
     * @param nodes All child nodes
     * @param data Runtime data
     * @return Array of result
     * @throws Exception
     */
    protected TreeNode[] doFilter(TreeNode[] nodes, RuntimeData data)
            throws Exception
    {
        return nodes;
    }

    /**
     * Expande this node
     * 
     * @param data RuntimeData
     * @throws Exception Throw it out
     */
    public synchronized void expand(RuntimeData data) throws Exception
    {
        if (expanded)
        {
            return;
        }
        TreeNode[] childNodes = doFilter(node.getChildrenNodes(), data);
        for (int i = 0; i < childNodes.length; i++)
        {
            TreeNode node = childNodes[i];
            RuntimeTreeNode n = new RuntimeTreeNode(this, node);
            children.put(n.getNodeId(), n);
        }
        expanded = true;
    }

    /**
     * Overwrite or implement method in RuntimeTreeNode
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getAttributes()
     */
    public ValueParser getAttributes()
    {
        return node.getAttributes();
    }

    /**
     * Overwrite or implement method in RuntimeTreeNode
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getChildrenNodes()
     */
    public TreeNode[] getChildrenNodes()
    {
        return (TreeNode[]) children.values().toArray(TreeNode.EMPTY_ARRAY);
    }

    /**
     * Overwrite or implement method getNodeId()
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getNodeId()
     */
    public String getNodeId()
    {
        return node.getNodeId();
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

}
