/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
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
package com.cyclopsgroup.jlone.fs.manager;

import java.io.File;

import com.cyclopsgroup.jlone.persistence.DataDocument;
import com.cyclopsgroup.jlone.persistence.PersistenceManager;

/**
 * TODO Add javadoc for class
 * 
 * @author <a href="mailto:jiiiaqi@yahoo.com">Jiaqi Guo </a>
 * 
 * Developed with eclipse 3.0
 */
public class FileSystemPersistenceManager implements PersistenceManager
{
    private File rootDirectory;

    /**
     * Override method getDataDocument in super class of FileSystemPersistenceManager
     * 
     * @see com.cyclopsgroup.jlone.persistence.PersistenceManager#getDataDocument()
     */
    public DataDocument getDataDocument()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override method load in super class of FileSystemPersistenceManager
     * 
     * @see com.cyclopsgroup.jlone.persistence.PersistenceManager#load(long)
     */
    public DataDocument load(long id)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Override method load in super class of FileSystemPersistenceManager
     * 
     * @see com.cyclopsgroup.jlone.persistence.PersistenceManager#load(java.lang.String)
     */
    public DataDocument load(String fullPath)
    {
        File file = new File(rootDirectory, fullPath);
        if (!file.exists())
        {
            return null;
        }
        return null;
    }
}