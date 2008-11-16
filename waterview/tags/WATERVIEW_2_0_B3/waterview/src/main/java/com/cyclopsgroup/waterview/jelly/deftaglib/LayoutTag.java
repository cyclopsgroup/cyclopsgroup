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

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.LookAndFeelService.PredefinedLayout;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * Layout tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class LayoutTag
    extends TagSupport
    implements PredefinedLayout
{

    private Layout layout;

    private String name;

    private String title;

    private String description;

    /**
     * Overwrite or implement method getDescription()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService.PredefinedLayout#getDescription()
     */
    public String getDescription()
    {
        return description;
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
     * Overwrite or implement method getTitle()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService.PredefinedLayout#getTitle()
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Setter method for field title
     *
     * @param title The title to set.
     */
    public void setTitle( String title )
    {
        this.title = title;
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
        invokeBody( output );
        if ( getLayout() == null )
        {
            throw new JellyTagException( "There must be a layout defined in layout tag" );
        }
        LookAndFeelService laf = (LookAndFeelService) getServiceManager().lookup( LookAndFeelService.ROLE );
        laf.registerLayout( this );
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
     * Overwrite or implement method getName()
     *
     * @see com.cyclopsgroup.waterview.spi.LookAndFeelService.PredefinedLayout#getName()
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

    /**
     * Overwrite or implement method render()
     *
     * @see com.cyclopsgroup.waterview.spi.Layout#render(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.Page)
     */
    public void render( RuntimeData data, Page page )
        throws Exception
    {
        getLayout().render( data, page );
    }
}
