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

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.utils.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Theme tag
 */
public class ThemeTag
    extends TagSupport
{
    private String description;

    private String iconset;

    private String name;

    private String stylesheet;

    private ThemeProxy theme;

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
     * Getter method for property iconSet
     *
     * @return Returns the iconSet.
     */
    public String getIconset()
    {
        return iconset;
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
     * Getter method for property stylesheet
     *
     * @return Returns the stylesheet.
     */
    public String getStylesheet()
    {
        return stylesheet;
    }

    /**
     * Getter method for field theme
     *
     * @return Returns the theme.
     */
    public ThemeProxy getTheme()
    {
        return theme;
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    public void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "name" );
        requireAttribute( "iconset" );
        requireAttribute( "stylesheet" );

        LookAndFeelService laf = (LookAndFeelService) getServiceManager().lookup( LookAndFeelService.ROLE );
        theme = new ThemeProxy( getName(), laf, getIconset(), getStylesheet() );
        theme.setDescription( getDescription() );
        invokeBody( output );
        laf.registerTheme( theme );
    }

    /**
     * Setter method for field description
     *
     * @param description The description to set.
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * Setter method for property iconSet
     *
     * @param iconSet The iconSet to set.
     */
    public void setIconset( String iconSet )
    {
        this.iconset = iconSet;
    }

    /**
     * Setter method for field name
     *
     * @param name The name to set.
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * Setter method for property stylesheet
     *
     * @param stylesheet The stylesheet to set.
     */
    public void setStylesheet( String stylesheet )
    {
        this.stylesheet = stylesheet;
    }
}
