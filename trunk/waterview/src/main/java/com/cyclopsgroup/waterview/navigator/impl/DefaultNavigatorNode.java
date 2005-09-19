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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.MapValueParser;
import com.cyclopsgroup.waterview.ValueParser;
import com.cyclopsgroup.waterview.navigator.BaseNavigatorNode;
import com.cyclopsgroup.waterview.navigator.NavigatorNode;
import com.cyclopsgroup.waterview.web.TreeNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Default implementation of navigator node
 */
class DefaultNavigatorNode
    extends BaseNavigatorNode
{
    static final String DESCRIPTION_NAME = "description";

    static final String HIDDEN_NAME = "hidden";

    static final String NAME_NAME = "name";

    static final String PAGE_NAME = "page";

    static final String TITLE_NAME = "title";

    private ValueParser attributes = new MapValueParser( new HashMap() );

    private DefaultNavigatorService navigatorHome;

    private NavigatorNode[] parentNodes;

    private String parentPath;

    private String path;

    DefaultNavigatorNode( DefaultNavigatorService nav, String name, String parentPath )
    {
        if ( StringUtils.isNotEmpty( name ) )
        {
            if ( parentPath != null )
            {
                if ( parentPath.equals( "/" ) )
                {
                    this.path = "/" + name;
                }
                else
                {
                    this.path = parentPath + '/' + name;
                }
            }
            else
            {
                this.path = name;
            }
        }
        this.parentPath = parentPath;
        this.navigatorHome = nav;
    }

    /**
     * Overwrite or implement method in DefaultNavigatorNode
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getAttributes()
     */
    public ValueParser getAttributes()
    {
        return attributes;
    }

    /**
     * Overwrite or implement method in DefaultNavigatorNode
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getChildrenNodes()
     */
    public TreeNode[] getChildrenNodes()
    {
        Collection nodes = navigatorHome.getChildren( path );
        return (TreeNode[]) nodes.toArray( TreeNode.EMPTY_ARRAY );
    }

    /**
     * Overwrite or implement method in DefaultNavigatorNode
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorNode#getDescription()
     */
    public String getDescription()
    {
        return getAttributes().getString( DESCRIPTION_NAME );
    }

    /**
     * Overwrite or implement method in DefaultNavigatorNode
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getNodeId()
     */
    public String getNodeId()
    {
        if ( StringUtils.isEmpty( path ) )
        {
            return parentPath + getPage();
        }
        return path;
    }

    /**
     * Overwrite or implement method in DefaultNavigatorNode
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorNode#getPage()
     */
    public String getPage()
    {
        return getAttributes().getString( PAGE_NAME );
    }

    /**
     * Overwrite or implement method in DefaultNavigatorNode
     *
     * @see com.cyclopsgroup.waterview.web.TreeNode#getParentNode()
     */
    public TreeNode getParentNode()
    {
        return navigatorHome.getNodeByPath( parentPath );
    }

    /**
     * Overwrite or implement method getParentNodes()
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorNode#getParentNodes()
     */
    public synchronized NavigatorNode[] getParentNodes()
    {
        if ( parentNodes == null )
        {
            List parents = new ArrayList();
            NavigatorNode parent = (NavigatorNode) getParentNode();
            if ( parent != null )
            {
                CollectionUtils.addAll( parents, parent.getParentNodes() );
                parents.add( parent );
            }
            parentNodes = (NavigatorNode[]) parents.toArray( NavigatorNode.EMPTY_ARRAY );
        }
        return parentNodes;
    }

    /**
     * Get parent path
     *
     * @return Parent path
     */
    public String getParentPath()
    {
        return parentPath;
    }

    /**
     * Get current path
     *
     * @return Current path
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Overwrite or implement method getTitle()
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorNode#getTitle()
     */
    public String getTitle()
    {
        return getAttributes().getString( TITLE_NAME );
    }

    /**
     * Overwrite or implement method isHidden()
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorNode#isHidden()
     */
    public boolean isHidden()
    {
        return getAttributes().getBoolean( HIDDEN_NAME );
    }
}
