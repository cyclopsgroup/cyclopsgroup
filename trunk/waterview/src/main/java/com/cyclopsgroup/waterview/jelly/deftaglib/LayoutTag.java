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
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * Layout tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class LayoutTag
    extends TagSupport
{

    private Layout layout;

    private String name;

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    public void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "name" );
        requireParent( ThemeTag.class );
        invokeBody( output );
        if ( getLayout() == null )
        {
            throw new JellyTagException( "There must be a layout defined in layout tag" );
        }
        ( (ThemeTag) getParent() ).getTheme().setLayout( getName(), getLayout() );
    }

    /**
     * Getter method for layout
     *
     * @return Returns the layout.
     */
    public Layout getLayout()
    {
        return layout;
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
     * Setter method for layout
     *
     * @param layout The layout to set.
     */
    public void setLayout( Layout layout )
    {
        this.layout = layout;
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
}
