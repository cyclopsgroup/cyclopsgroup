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
package com.cyclopsgroup.amulets.cyclops;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import org.apache.commons.io.CopyUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Download something through http
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DownloadTag
{
    private String file;

    private String url;

    /**
     * Execute this tag
     *
     * @throws Exception Throw it out
     */
    public void execute() throws Exception
    {
        if (StringUtils.isEmpty(getFile()))
        {
            throw new IllegalArgumentException("file attribute is missing");
        }
        if (StringUtils.isEmpty(getUrl()))
        {
            throw new IllegalArgumentException("url attribute is missing");
        }
        File targetFile = new File(getFile());
        if (!targetFile.getParentFile().isDirectory())
        {
            targetFile.getParentFile().mkdirs();
        }
        if (targetFile.exists())
        {
            return;
        }
        URL u = new URL(getUrl());
        CopyUtils.copy(u.openStream(), new FileOutputStream(targetFile));
    }

    /**
     * Getter method for file
     *
     * @return Returns the file.
     */
    public String getFile()
    {
        return file;
    }

    /**
     * Getter method for url
     *
     * @return Returns the url.
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * Setter method for file
     *
     * @param file The file to set.
     */
    public void setFile(String file)
    {
        this.file = file;
    }

    /**
     * Setter method for url
     *
     * @param url The url to set.
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return "Downloader of " + getUrl() + " to " + getFile();
    }
}
