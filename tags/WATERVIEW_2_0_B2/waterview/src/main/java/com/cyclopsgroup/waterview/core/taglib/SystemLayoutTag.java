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

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.Theme;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * System predefined layout tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class SystemLayoutTag
    extends TagSupport
{
    private String name;

    /**
     * Getter method for property name
     *
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
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
        Page page = (Page) context.getVariable( Page.NAME );
        if ( page == null )
        {
            throw new JellyTagException( "JellyLayout must be in a page" );
        }
        LookAndFeelService tm = (LookAndFeelService) getServiceManager().lookup( LookAndFeelService.ROLE );
        String themeName = getRuntimeData().getThemeName();
        Theme theme = tm.getDefaultTheme();
        if ( StringUtils.isNotEmpty( themeName ) )
        {
            theme = tm.getTheme( themeName );
        }
        Layout layout = theme.getLayout( getName() );
        if ( layout == null )
        {
            throw new NullPointerException( "Layout [" + getName() + "] doesn't exist" );
        }
        page.setLayout( layout );
    }

    /**
     * Setter method for property name
     *
     * @param name The name to set.
     */
    public void setName( String name )
    {
        this.name = name;
    }
}
