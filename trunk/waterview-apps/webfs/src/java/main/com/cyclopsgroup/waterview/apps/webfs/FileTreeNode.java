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
package com.cyclopsgroup.waterview.apps.webfs;

import java.io.File;
import java.io.FileFilter;

import com.cyclopsgroup.waterview.richweb.TreeNode;

/**
 * File implemented tree node
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class FileTreeNode implements TreeNode
{

    private File file;

    private FileFilter filter;

    private String path;

    /**
     * Constructor for class FileTreeNode
     *
     * @param path Internal path
     * @param file File object
     * @param filter File filter
     */
    public FileTreeNode(String path, File file, FileFilter filter)
    {
        this.path = path;
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
        File[] files = filter == null ? file.listFiles() : file
                .listFiles(filter);
        TreeNode[] ret = new TreeNode[files.length];
        for (int i = 0; i < files.length; i++)
        {
            File subfile = files[i];
            String filePath = path + "/" + subfile.getName();
            ret[i] = new FileTreeNode(filePath, subfile, filter);
        }
        return ret;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.richweb.TreeNode#getId()
     */
    public String getId()
    {
        return path;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.richweb.TreeNode#isExpandable()
     */
    public boolean isExpandable()
    {
        return file.isDirectory();
    }
}
