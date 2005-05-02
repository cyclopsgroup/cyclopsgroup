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
package com.cyclopsgroup.waterview.webfs;

import java.io.File;

/**
 * File system root
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class FSRoot
{
    /**
     * Empty array
     */
    public static final FSRoot[] EMPTY_ARRAY = new FSRoot[0];

    private File file;

    private String id;

    private String title;

    /**
     * Constructor for class FSRoot
     *
     * @param id
     * @param file
     */
    public FSRoot(String id, File file)
    {
        this.id = id;
        this.file = file;
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
