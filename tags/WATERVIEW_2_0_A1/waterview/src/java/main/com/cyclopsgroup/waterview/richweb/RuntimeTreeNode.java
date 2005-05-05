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

/**
 * Runtime node
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public final class RuntimeTreeNode implements TreeNode
{

    private boolean expanded;

    private TreeNode node;

    private boolean selected;

    /**
     * Constructor for class TreeRuntimeNode
     *
     * @param node
     * @param expanded
     * @param selected
     */
    public RuntimeTreeNode(TreeNode node, boolean expanded, boolean selected)
    {
        this.node = node;
        this.expanded = expanded;
        this.selected = selected;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.richweb.TreeNode#getChildren()
     */
    public TreeNode[] getChildren()
    {
        return getNode().getChildren();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.richweb.TreeNode#getId()
     */
    public String getId()
    {
        return getNode().getId();
    }

    /**
     * Get node object
     *
     * @return Node object
     */
    public TreeNode getNode()
    {
        return node;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.richweb.TreeNode#isExpandable()
     */
    public boolean isExpandable()
    {
        return getNode().isExpandable();
    }

    /**
     * Getter method for expanded
     *
     * @return Returns the expanded.
     */
    public boolean isExpanded()
    {
        return expanded;
    }

    /**
     * Getter method for selected
     *
     * @return Returns the selected.
     */
    public boolean isSelected()
    {
        return selected;
    }
}
