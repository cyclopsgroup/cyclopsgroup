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
package com.cyclopsgroup.waterview.richweb;

import java.util.HashSet;

/**
 * Runtime tree information
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class RuntimeTree
{
    /**
     * Name of this object
     */
    public static final String NAME = RuntimeTree.class.getName();

    private HashSet expandedNodes = new HashSet();

    private TreeNode selectedNode = null;

    /**
     * Collapse a given node
     *
     * @param node Node object
     */
    public void collapse(TreeNode node)
    {
        if (!node.isExpandable())
        {
            return;
        }
        expandedNodes.remove(node);
    }

    /**
     * Create new runtime node
     *
     * @param node
     * @return Runtime tree node
     */
    public RuntimeTreeNode createRuntimeNode(TreeNode node)
    {
        boolean expanded = isExpanded(node);
        boolean selected = node == getSelected();
        return new RuntimeTreeNode(node, expanded, selected);
    }

    /**
     * Expand a given node
     *
     * @param node Node object
     */
    public void expand(TreeNode node)
    {
        if (!node.isExpandable())
        {
            return;
        }
        expandedNodes.add(node);
    }

    /**
     * Getter method for selectedNode
     *
     * @return Returns the selectedNode.
     */
    public TreeNode getSelected()
    {
        return selectedNode;
    }

    /**
     * Is the given node expanded
     *
     * @param node Node object
     * @return If it's expanded
     */
    public boolean isExpanded(TreeNode node)
    {
        return expandedNodes.contains(node);
    }

    /**
     * Setter method for selectedNode
     *
     * @param selectedNode The selectedNode to set.
     */
    public void setSelected(TreeNode selectedNode)
    {
        this.selectedNode = selectedNode;
    }
}
