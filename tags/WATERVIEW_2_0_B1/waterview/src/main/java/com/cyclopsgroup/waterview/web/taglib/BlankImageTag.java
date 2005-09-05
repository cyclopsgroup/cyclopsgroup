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
package com.cyclopsgroup.waterview.web.taglib;

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Empty image tag
 */
public class BlankImageTag extends TagSupport
{
    private int width;

    private int height;

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        requireAttribute("width");
        requireAttribute("height");

        JellyEngine je = (JellyEngine) getServiceManager().lookup(
                JellyEngine.ROLE);
        getContext().setVariable("tag", this);
        je.getScript("/waterview/BlankImage.jelly").run(getContext(), output);
    }

    /**
     * Getter method for field height
     *
     * @return Returns the height.
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Setter method for field height
     *
     * @param height The height to set.
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * Getter method for field width
     *
     * @return Returns the width.
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Setter method for field width
     *
     * @param width The width to set.
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

}
