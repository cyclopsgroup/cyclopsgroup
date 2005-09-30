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
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.navigator.NavigatorNode;
import com.cyclopsgroup.waterview.navigator.NavigatorService;
import com.cyclopsgroup.waterview.web.Node;
import com.cyclopsgroup.waterview.web.RuntimeTreeNode;
import com.cyclopsgroup.waterview.web.StaticNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Default implementation of navigator home
 */
public class DefaultNavigatorService
    extends AbstractLogEnabled
    implements NavigatorService, Initializable, Configurable
{
    private static final String NODE_RUNTIME_NAME = DefaultNavigatorService.class.getName();

    private Map pageIndex;

    private MultiMap parentPathIndex;

    private String path;

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

    /**
     * Overwrite or implement method configure()
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration conf )
        throws ConfigurationException
    {
        path = conf.getChild( "path" ).getValue( "META-INF/cyclopsgroup/waterview-navigation.xml" );
    }

    /**
     * Create runtime root node
     * 
     * @param data Runtime data
     * @return Runtiem tree node object
     */
    protected RuntimeTreeNode doCreateRuntimeRoot( RuntimeData data )
    {
        return new RuntimeTreeNode( null, getRootNode() );
    }

    Collection getChildren( String path )
    {
        Collection c = (Collection) parentPathIndex.get( path );
        return c == null ? Collections.EMPTY_SET : c;
    }

    /**
     * Overwrite or implement method getNodeByPage()
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorService#getNodeByPage(java.lang.String)
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
     * @see com.cyclopsgroup.waterview.navigator.NavigatorService#getRootNode()
     */
    public NavigatorNode getRootNode()
    {
        return rootNode;
    }

    /**
     * Overwrite or implement method getRuntimeNode()
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorService#getRuntimeNode(com.cyclopsgroup.waterview.RuntimeData)
     */
    public RuntimeTreeNode getRuntimeNode( RuntimeData data )
        throws Exception
    {
        RuntimeTreeNode root = (RuntimeTreeNode) data.getSessionContext().get( NODE_RUNTIME_NAME );
        if ( root == null )
        {
            root = doCreateRuntimeRoot( data );
            root.expand( data );
            Node[] children = root.getChildrenNodes();
            for ( int i = 0; i < children.length; i++ )
            {
                //TODO handle dynamic node
                StaticNode child = (StaticNode) children[i];
                ( (RuntimeTreeNode) child ).expand( data );
            }
            data.getSessionContext().put( NODE_RUNTIME_NAME, root );
        }
        return root;
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
        jc.setVariable( DefaultNavigatorService.class.getName(), this );
        jc.registerTagLibrary( "http://waterview.cyclopsgroup.com/navigator", new NavigatorTagLibrary() );
        for ( Enumeration en = getClass().getClassLoader().getResources( path ); en.hasMoreElements(); )
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
        Node[] nodes = node.getChildrenNodes();
        for ( int i = 0; i < nodes.length; i++ )
        {
            NavigatorNode child = (NavigatorNode) nodes[i];
            populateNode( child );
        }
    }

    /**
     * Overwrite or implement method refresh()
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorService#refresh(com.cyclopsgroup.waterview.RuntimeData)
     */
    public void refresh( RuntimeData data )
    {
        data.getSessionContext().remove( NODE_RUNTIME_NAME );
    }
}
