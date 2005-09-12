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

import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.navigator.NavigatorHome;
import com.cyclopsgroup.waterview.navigator.NavigatorNode;
import com.cyclopsgroup.waterview.web.TreeNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Default implementation of navigator home
 */
public class DefaultNavigatorHome
    extends AbstractLogEnabled
    implements NavigatorHome, Initializable
{

    private Map pageIndex;

    private MultiMap parentPathIndex;

    private Map pathIndex;

    private DefaultNavigatorNode rootNode;

    /**
     * Add node
     *
     * @param node Node to add
     */
    public void addNode( DefaultNavigatorNode node )
    {
        if ( StringUtils.isNotEmpty( node.getPath() ) )
        {
            pathIndex.put( node.getPath(), node );
        }
        if ( node.getParentPath() != null )
        {
            parentPathIndex.put( node.getParentPath(), node );
        }
        pageIndex.put( node.getPage(), node );
    }

    Collection getChildren( String path )
    {
        Collection c = (Collection) parentPathIndex.get( path );
        return c == null ? Collections.EMPTY_SET : c;
    }

    /**
     * Overwrite or implement method getNodeByPage()
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorHome#getNodeByPage(java.lang.String)
     */
    public NavigatorNode getNodeByPage( String page )
    {
        return (NavigatorNode) pageIndex.get( page );
    }

    /**
     * Get node based on path
     *
     * @param path Path for node
     * @return Node object or null if not found
     */
    NavigatorNode getNodeByPath( String path )
    {
        if ( StringUtils.isEmpty( path ) )
        {
            return null;
        }
        return (NavigatorNode) pathIndex.get( path );
    }

    /**
     * Overwrite or implement method in DefaultNavigatorHome
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorHome#getRootNode()
     */
    public NavigatorNode getRootNode()
    {
        return rootNode;
    }

    /**
     * Overwrite or implement method in DefaultNavigatorHome
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        pathIndex = new Hashtable();
        parentPathIndex = new MultiHashMap();
        pageIndex = new Hashtable();

        rootNode = new DefaultNavigatorNode( this, "/", null );
        rootNode.getAttributes().set( DefaultNavigatorNode.PAGE_NAME, "/Index.jelly" );
        rootNode.getAttributes().set( DefaultNavigatorNode.TITLE_NAME, "Start" );
        addNode( rootNode );

        JellyContext jc = new JellyContext();
        jc.setVariable( getClass().getName(), this );
        jc.registerTagLibrary( "http://waterview.cyclopsgroup.com/navigator", new NavigatorTagLibrary() );
        for ( Enumeration en = getClass().getClassLoader()
            .getResources( "META-INF/cyclopsgroup/waterview-navigation.xml" ); en.hasMoreElements(); )
        {
            URL resource = (URL) en.nextElement();
            getLogger().info( "Reading navigation from " + resource );
            jc.runScript( resource, XMLOutput.createDummyXMLOutput() );
        }
        populateNode( rootNode );
    }

    private void populateNode( NavigatorNode node )
    {
        node.getParentNodes();
        TreeNode[] nodes = node.getChildrenNodes();
        for ( int i = 0; i < nodes.length; i++ )
        {
            NavigatorNode child = (NavigatorNode) nodes[i];
            populateNode( child );
        }
    }
}
