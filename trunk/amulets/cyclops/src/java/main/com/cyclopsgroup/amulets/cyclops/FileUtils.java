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
package com.cyclopsgroup.amulets.cyclops;

import java.io.File;
import java.io.IOException;

/**
 * Utilities for file manipulation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class FileUtils
{
    /**
     * Get relative path of a given file against a given folder
     *
     * @param file File
     * @param folder Folder
     * @return Relative path
     * @throws IOException
     */
    public static String getRelativePath(File file, File folder)
            throws IOException
    {
        String fullPath = file.getCanonicalPath();
        String folderPath = folder.getCanonicalPath();
        if (fullPath.startsWith(folderPath))
        {
            return fullPath.substring(folderPath.length());
        }
        throw new IllegalArgumentException(file + " is not under directory "
                + folder);
    }

    /**
     * Get relative path of a given file against a given folder
     *
     * @param file Given file object
     * @param folderPath Folder path
     * @return Relative path
     * @throws IOException File io exception
     */
    public static String getRelativePath(File file, String folderPath)
            throws IOException
    {
        File folder = new File(folderPath);
        if (!folder.isDirectory())
        {
            throw new IllegalArgumentException(folderPath
                    + " is not a valid directory");
        }
        return getRelativePath(file, folder);
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param filePath
     * @param folderPath
     * @return
     * @throws IOException
     */
    public static String getRelativePath(String filePath, String folderPath)
            throws IOException
    {
        File file = new File(filePath);
        return getRelativePath(file, folderPath);
    }
}