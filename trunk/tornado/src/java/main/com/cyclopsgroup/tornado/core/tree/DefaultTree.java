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

public class DefaultTree implements Tree
{
    private String id;

    private TreeNode rootNode;

    /**
     * @param id
     * @param node
     */
    public DefaultTree(String id, TreeNode node)
    {
        this.id = id;
        rootNode = node;
    }

    /**
     * Overwrite or implement method getId()
     * @see com.cyclopsgroup.tornado.core.tree.Tree#getId()
     */
    public String getId()
    {
        return id;
    }

    /**
     * Overwrite or implement method getRootNode()
     * @see com.cyclopsgroup.tornado.core.tree.Tree#getRootNode()
     */
    public TreeNode getRootNode()
    {
        return rootNode;
    }
}
