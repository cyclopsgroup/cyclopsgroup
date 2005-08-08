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
package com.cyclopsgroup.waterview.jelly.taglib;

import java.io.File;
import java.net.URL;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.taglib.BaseTag;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag to run a given script
 */
public class JellyScriptTag extends BaseTag
{
    private boolean fileSystem = false;

    private String path;

    /**
     * Overwrite or implement method doTag()
     * @see com.cyclopsgroup.waterview.spi.taglib.BaseTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        requireAttribute("path");
        URL resource = null;
        if (isFileSystem())
        {
            File file = new File(getPath());
            if (file.isFile())
            {
                resource = file.toURL();
            }
        }
        else
        {
            resource = getClass().getClassLoader().getResource(getPath());
        }
        if (resource == null)
        {
            output.writeCDATA("Resource " + getPath() + " can not be found in "
                    + (isFileSystem() ? "file system" : "classpath"));
            return;
        }
        getContext().runScript(resource, output);
    }

    /**
     * @return Returns the path.
     */
    public String getPath()
    {
        return path;
    }

    /**
     * @return Returns the fileSystem.
     */
    public boolean isFileSystem()
    {
        return fileSystem;
    }

    /**
     * @param fileSystem The fileSystem to set.
     */
    public void setFileSystem(boolean fileSystem)
    {
        this.fileSystem = fileSystem;
    }

    /**
     * @param path The path to set.
     */
    public void setPath(String path)
    {
        this.path = path;
    }

}
