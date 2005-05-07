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
import java.io.FileFilter;
import java.util.Hashtable;
import java.util.Map;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.collections.map.ListOrderedMap;

/**
 * Default implementation of web file system
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultWebFileSystem extends AbstractLogEnabled implements
        WebFileSystem, Configurable
{
    private FileFilter fileFilter = new DefaultFileFilter();

    private Map roots = ListOrderedMap.decorate(new Hashtable());

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.webfs.WebFileSystem#addRoot(com.cyclopsgroup.waterview.webfs.FileTreeRoot)
     */
    public void addRoot(FileTreeRoot root)
    {
        roots.put(root.getId(), root);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        Configuration[] rootConfs = conf.getChild("fileroots").getChildren(
                "root");
        for (int i = 0; i < rootConfs.length; i++)
        {
            Configuration c = rootConfs[i];
            String path = c.getAttribute("path");
            String id = c.getAttribute("id");
            FileTreeRoot root = new FileTreeRoot(id, new File(path),
                    getFileFilter());
            addRoot(root);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.webfs.WebFileSystem#getFileFilter()
     */
    public FileFilter getFileFilter()
    {
        return fileFilter;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.webfs.WebFileSystem#getRoot(java.lang.String)
     */
    public FileTreeRoot getRoot(String rootId)
    {
        return (FileTreeRoot) roots.get(rootId);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.webfs.WebFileSystem#getRoots()
     */
    public FileTreeRoot[] getRoots()
    {
        return (FileTreeRoot[]) roots.values()
                .toArray(FileTreeRoot.EMPTY_ARRAY);
    }
}
