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
package com.cyclopsgroup.waterview.core.taglib;

import java.io.File;
import java.net.URL;

import org.apache.avalon.framework.service.ServiceManager;

import com.cyclopsgroup.waterview.View;
import com.cyclopsgroup.waterview.core.ResourceType;
import com.cyclopsgroup.waterview.core.ResourceView;
import com.cyclopsgroup.waterview.jelly.taglib.AbstractViewTag;

/**
 * View to show an existing resource
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ResourceViewTag extends AbstractViewTag
{
    private String path;

    private String type;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.jelly.taglib.AbstractViewTag#doCreateView(org.apache.avalon.framework.service.ServiceManager)
     */
    protected View doCreateView(ServiceManager serviceManager) throws Exception
    {
        requireAttribute("path");
        ResourceType resourceType = ResourceType.valueOf(getType());
        if (resourceType == null)
        {
            resourceType = ResourceType.RESOURCE;
        }
        URL resource = null;
        if (resourceType == ResourceType.FILE)
        {
            resource = new File(getPath()).toURL();
        }
        else if (resourceType == ResourceType.RESOURCE)
        {
            resource = getClass().getClassLoader().getResource(getPath());
        }
        else if (resourceType == ResourceType.URL)
        {
            resource = new URL(getPath());
        }
        else
        {
            throw new IllegalArgumentException("Resource type " + getType()
                    + " is unknown");
        }
        if (resource == null)
        {
            throw new IllegalArgumentException("Resource " + path
                    + " doesn't exist");
        }
        return new ResourceView(resource);
    }

    /**
     * Getter method for path
     *
     * @return Returns the path.
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Getter method for type
     *
     * @return Returns the type.
     */
    public String getType()
    {
        return type;
    }

    /**
     * Setter method for path
     *
     * @param path The path to set.
     */
    public void setPath(String path)
    {
        this.path = path;
    }

    /**
     * Setter method for type
     *
     * @param type The type to set.
     */
    public void setType(String type)
    {
        this.type = type;
    }
}
