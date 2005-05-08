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

import com.cyclopsgroup.waterview.richweb.Tree;
import com.cyclopsgroup.waterview.richweb.TreeNode;

/**
 * File system root
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class FileTreeRoot implements Tree
{
    /**
     * Empty array
     */
    public static final FileTreeRoot[] EMPTY_ARRAY = new FileTreeRoot[0];

    private File file;

    private String id;

    private FileTreeNode rootNode;

    private String title;

    /**
     * Constructor for class FSRoot
     *
     * @param id
     * @param file
     * @param fileFilter
     */
    public FileTreeRoot(String id, File file, FileFilter fileFilter)
    {
        this.id = id;
        this.file = file;
        this.rootNode = new FileTreeNode(id, file, fileFilter);
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
     * Getter method for id
     *
     * @return Returns the id.
     */
    public String getId()
    {
        return id;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.richweb.Tree#getRootNode()
     */
    public TreeNode getRootNode()
    {
        return rootNode;
    }

    /**
     * Getter method for title
     *
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Setter method for title
     *
     * @param title The title to set.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
}
