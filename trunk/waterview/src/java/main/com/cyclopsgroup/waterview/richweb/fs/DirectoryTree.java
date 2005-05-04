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
package com.cyclopsgroup.waterview.richweb.fs;

import java.io.File;
import java.io.FileFilter;

import com.cyclopsgroup.waterview.richweb.Tree;
import com.cyclopsgroup.waterview.richweb.TreeNode;

/**
 * File system directory tree
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DirectoryTree implements Tree
{
    private FileTreeNode root;

    /**
     * Constructor for class DirectoryTree
     *
     * @param directory Directory object
     */
    public DirectoryTree(File directory)
    {
        this(directory, null);
    }

    /**
     * Constructor for class DirectoryTree
     *
     * @param directory Root directory
     * @param filter Filter object
     */
    public DirectoryTree(File directory, FileFilter filter)
    {
        root = new FileTreeNode(directory, filter);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.richweb.Tree#getId()
     */
    public String getId()
    {
        return root.getFile().getAbsolutePath();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.richweb.Tree#getRootNode()
     */
    public TreeNode getRootNode()
    {
        return root;
    }
}
