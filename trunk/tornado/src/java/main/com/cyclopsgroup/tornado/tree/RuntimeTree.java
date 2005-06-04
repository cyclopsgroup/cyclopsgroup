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
package com.cyclopsgroup.tornado.tree;

import java.util.HashSet;

import org.apache.commons.lang.StringUtils;

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

    private String selectedNodeId = null;

    /**
     * Collapse a given node
     *
     * @param nodeId Node object
     */
    public void collapse(String nodeId)
    {
        expandedNodes.remove(nodeId);
    }

    /**
     * Create new runtime node
     *
     * @param node
     * @return Runtime tree node
     */
    public RuntimeTreeNode createRuntimeNode(TreeNode node)
    {
        boolean expanded = isExpanded(node.getId());
        boolean selected = StringUtils.equals(node.getId(), getSelected());
        return new RuntimeTreeNode(node, expanded, selected);
    }

    /**
     * Expand a given node
     *
     * @param nodeId Node object id
     */
    public void expand(String nodeId)
    {
        expandedNodes.add(nodeId);
    }

    /**
     * Getter method for selectedNode
     *
     * @return Returns the selectedNode.
     */
    public String getSelected()
    {
        return selectedNodeId;
    }

    /**
     * Is the given node expanded
     *
     * @param nodeId Node object
     * @return If it's expanded
     */
    public boolean isExpanded(String nodeId)
    {
        return expandedNodes.contains(nodeId);
    }

    /**
     * Setter method for selectedNode
     *
     * @param selectedNodeId The selectedNode to set.
     */
    public void setSelected(String selectedNodeId)
    {
        this.selectedNodeId = selectedNodeId;
    }
}
