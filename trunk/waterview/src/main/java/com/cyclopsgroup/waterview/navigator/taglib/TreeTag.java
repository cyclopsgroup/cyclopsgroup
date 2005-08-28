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
package com.cyclopsgroup.waterview.navigator.taglib;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.utils.BaseTagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tree tag
 */
public class TreeTag extends BaseTagSupport
{
    private String name;

    private String position;

    /**
     * Overwrite or implement method in TreeTag
     *
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        requireAttribute("name");
        requireAttribute("position");
        invokeBody(output);
    }

    /**
     * Get name of tree
     *
     * @return Name of tree
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get position
     *
     * @return Positon path
     */
    public String getPosition()
    {
        return position;
    }

    /**
     * Set name of tree
     *
     * @param name Name of tree
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Set position of parent
     *
     * @param position Parent position path
     */
    public void setPosition(String position)
    {
        this.position = position;
    }

}
