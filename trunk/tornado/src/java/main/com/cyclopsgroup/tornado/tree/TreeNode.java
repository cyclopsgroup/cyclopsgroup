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

/**
 * Tree node interface
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface TreeNode
{
    /**
     * Empty array
     */
    TreeNode[] EMPTY_ARRAY = new TreeNode[0];

    /**
     * Get children nodes
     *
     * @return Children nodes
     */
    TreeNode[] getChildren();

    /**
     * Get unique id of this node
     *
     * @return Unique id of this node
     */
    String getId();

    /**
     * If this node is expandable or not
     *
     * @return True if this node is expandable
     */
    boolean isExpandable();
}
