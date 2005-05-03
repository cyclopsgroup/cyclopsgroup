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
import java.util.ArrayList;
import java.util.List;

import com.cyclopsgroup.waterview.richweb.TreeNode;

/**
 * File node
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class FileTreeNode implements TreeNode
{
    private List children;

    private File file;

    private FileFilter filter;

    /**
     * Constructor for class FileTreeNode
     *
     * @param file File object
     * @param filter File filter object
     */
    public FileTreeNode(File file, FileFilter filter)
    {
        this.file = file;
        this.filter = filter;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.richweb.TreeNode#getChildren()
     */
    public TreeNode[] getChildren()
    {
        if (children == null)
        {
            children = new ArrayList();
            if (file.isDirectory())
            {
                File[] subfiles = filter == null ? file.listFiles() : file
                        .listFiles(filter);
                for (int i = 0; i < subfiles.length; i++)
                {
                    File subfile = subfiles[i];
                    children.add(new FileTreeNode(subfile, filter));
                }
            }
            else
            {
                return TreeNode.EMPTY_ARRAY;
            }
        }
        return (TreeNode[]) children.toArray(TreeNode.EMPTY_ARRAY);
    }

    /**
     * Getter method for file
     *
     * @return Returns the file.
     */
    public File getFile()
    {
        return file;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.richweb.TreeNode#isExpandable()
     */
    public boolean isExpandable()
    {
        return getChildren().length > 0;
    }
}
