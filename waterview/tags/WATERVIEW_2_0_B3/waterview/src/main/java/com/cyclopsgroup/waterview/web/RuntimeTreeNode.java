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
package com.cyclopsgroup.waterview.web;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.Attributes;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Runtime instance of tree node
 */
public class RuntimeTreeNode
    implements StaticNode
{
    /** Empty array */
    public static final RuntimeTreeNode[] EMPTY_ARRAY = new RuntimeTreeNode[0];

    private Map children = ListOrderedMap.decorate( new HashMap() );

    private boolean expanded;

    private StaticNode node;

    private RuntimeTreeNode parentNode;

    /**
     * Constructor for class RuntimeTreeNode
     *
     * @param parentNode Parent runtime node
     * @param node Proxy tree node
     */
    public RuntimeTreeNode( RuntimeTreeNode parentNode, StaticNode node )
    {
        this.parentNode = parentNode;
        this.node = node;
    }

    /**
     * Collapse this node
     */
    public synchronized void collapse()
    {
        if ( !expanded )
        {
            return;
        }
        children.clear();
        expanded = false;
    }

    /**
     * Create child
     *
     * @param node Nested node
     * @return Created runtime node
     */
    protected RuntimeTreeNode doCreateChild( StaticNode node )
    {
        return new RuntimeTreeNode( this, node );
    }

    /**
     * Filter child nodes
     *
     * @param nodes All child nodes
     * @param data Runtime data
     * @return Array of result
     * @throws Exception
     */
    protected Node[] doFilter( Node[] nodes, RuntimeData data )
        throws Exception
    {
        return nodes;
    }

    /**
     * Expande this node
     * 
     * @param data RuntimeData
     * @throws Exception Throw it out
     */
    public synchronized void expand( RuntimeData data )
        throws Exception
    {
        if ( expanded )
        {
            return;
        }
        //TODO handle the none static node scenario
        Node[] childNodes = doFilter( node.getChildrenNodes(), data );
        for ( int i = 0; i < childNodes.length; i++ )
        {
            StaticNode node = (StaticNode) childNodes[i];
            RuntimeTreeNode n = doCreateChild( node );
            children.put( n.getNodeId(), n );
        }
        expanded = true;
    }

    /**
     * Overwrite or implement method in RuntimeTreeNode
     *
     * @see com.cyclopsgroup.waterview.web.StaticNode#getAttributes()
     */
    public Attributes getAttributes()
    {
        return node.getAttributes();
    }

    /**
     * Overwrite or implement method in RuntimeTreeNode
     *
     * @see com.cyclopsgroup.waterview.web.StaticNode#getChildrenNodes()
     */
    public Node[] getChildrenNodes()
    {
        return (Node[]) children.values().toArray( Node.EMPTY_ARRAY );
    }

    /**
     * Get static node it contains
     *
     * @return Node it contains
     */
    public StaticNode getContent()
    {
        return node;
    }

    /**
     * Get node or child node by id
     *
     * @param nodeId Node id
     * @return Node or null if not found
     */
    public RuntimeTreeNode getNodeById( String nodeId )
    {
        LinkedList buffer = new LinkedList();
        buffer.addLast( this );
        while ( !buffer.isEmpty() )
        {
            RuntimeTreeNode node = (RuntimeTreeNode) buffer.removeFirst();
            if ( StringUtils.equals( nodeId, node.getNodeId() ) )
            {
                return node;
            }
            buffer.addAll( node.children.values() );
        }
        return null;
    }

    /**
     * Overwrite or implement method getNodeId()
     *
     * @see com.cyclopsgroup.waterview.web.StaticNode#getNodeId()
     */
    public String getNodeId()
    {
        return node.getNodeId();
    }

    /**
     * Overwrite or implement method getParentNode()
     *
     * @see com.cyclopsgroup.waterview.web.StaticNode#getParentNode()
     */
    public Node getParentNode()
    {
        return parentNode;
    }

    /**
     * Overwrite or implement method isEnd()
     *
     * @see com.cyclopsgroup.waterview.web.StaticNode#isEnd()
     */
    public boolean isEnd()
    {
        return node.isEnd();
    }

    /**
     * Check if the node is expanded
     *
     * @return True if node is expanded
     */
    public boolean isExpanded()
    {
        return expanded;
    }
}
