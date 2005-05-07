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

import java.io.FileFilter;

/**
 * Web file system facade component
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface WebFileSystem
{

    /**
     * Comment for <code>CURRENT_PATH</code>
     */
    String CURRENT_PATH = "cyclopsgroup.waterview.webfs.path";

    /**
     * Comment for <code>CURRENT_ROOT</code>
     */
    String CURRENT_ROOT = "cyclosgroup.waterview.webfs.root";

    /**
     * <code>ROLE</code>
     * ROLE name of this component
     */
    String ROLE = WebFileSystem.class.getName();

    /**
     * Add root object
     *
     * @param root FSRoot object
     */
    void addRoot(FileTreeRoot root);

    /**
     * Get file filter
     *
     * @return File filter for file displaying
     */
    FileFilter getFileFilter();

    /**
     * get FSRoot object
     *
     * @param rootId Root id
     * @return FSRoot object
     */
    FileTreeRoot getRoot(String rootId);

    /**
     * Get roots
     *
     * @return FSRoot array
     */
    FileTreeRoot[] getRoots();
}
