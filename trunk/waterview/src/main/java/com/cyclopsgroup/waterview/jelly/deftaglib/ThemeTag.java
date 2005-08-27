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
package com.cyclopsgroup.waterview.jelly.deftaglib;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.DefaultTheme;
import com.cyclopsgroup.waterview.utils.BaseTagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Theme tag
 */
public class ThemeTag extends BaseTagSupport
{
    private String description;

    private String name;

    private DefaultTheme theme;

    /**
     * Overwrite or implement method doTag()
     *
     * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(XMLOutput output) throws MissingAttributeException,
            JellyTagException
    {
        requireAttribute("name");
        theme = new DefaultTheme(name);
        if (StringUtils.isNotEmpty(getDescription()))
        {
            theme.setDescription(getDescription());
        }
        invokeBody(output);
        JellyEngine jellyEngine = (JellyEngine) getContext().getVariable(
                JellyEngine.ROLE);
        jellyEngine.registerTheme(theme);
    }

    /**
     * Getter method for field description
     *
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Getter method for field name
     *
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for field theme
     *
     * @return Returns the theme.
     */
    public DefaultTheme getTheme()
    {
        return theme;
    }

    /**
     * Setter method for field description
     *
     * @param description The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Setter method for field name
     *
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
