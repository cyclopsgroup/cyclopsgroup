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
package com.cyclopsgroup.waterview.navigator.impl;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.utils.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Node tag
 */
public class NodeTag
    extends TagSupport
{
    private String description;

    private boolean hidden = false;

    private String name;

    private String page;

    private String parentPath;

    private String path;

    private String title;

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
     * Getter method for field page
     *
     * @return Returns the page.
     */
    public String getPage()
    {
        return page;
    }

    /**
     * Getter method for field parentPath
     *
     * @return Returns the parentPath.
     */
    public String getParentPath()
    {
        return parentPath;
    }

    /**
     * Get full path of node
     *
     * @return Path of node
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Getter method for field title
     *
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Getter method for field hidden
     *
     * @return Returns the hidden.
     */
    public boolean isHidden()
    {
        return hidden;
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    @Override
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "title" );
        requireAttribute( "page" );

        if ( getParent() instanceof TreeTag )
        {
            parentPath = ( (TreeTag) getParent() ).getPosition();
        }
        else if ( getParent() instanceof NodeTag )
        {
            ( (NodeTag) getParent() ).requireAttribute( "name" );
            parentPath = ( (NodeTag) getParent() ).getPath();
        }
        else
        {
            throw new JellyTagException( "Parent tag must be tree or node" );
        }
        NavigationTag nt = (NavigationTag) findAncestorWithClass( NavigationTag.class );
        DefaultNavigatorNode node = new DefaultNavigatorNode( nt.getNavigator(), getName(), parentPath );
        path = node.getPath();
        node.getAttributes().set( DefaultNavigatorNode.PAGE_NAME, getPage() );
        node.getAttributes().set( DefaultNavigatorNode.TITLE_NAME, getTitle() );
        node.getAttributes().set( DefaultNavigatorNode.DESCRIPTION_NAME, getDescription() );
        node.getAttributes().set( DefaultNavigatorNode.HIDDEN_NAME, isHidden() ? "true" : "false" );

        nt.getNavigator().addNode( node );
        invokeBody( output );
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
     * Setter method for field hidden
     *
     * @param hidden The hidden to set.
     */
    public void setHidden( boolean hidden )
    {
        this.hidden = hidden;
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
     * Setter method for field page
     *
     * @param page The page to set.
     */
    public void setPage( String page )
    {
        this.page = page;
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

}
