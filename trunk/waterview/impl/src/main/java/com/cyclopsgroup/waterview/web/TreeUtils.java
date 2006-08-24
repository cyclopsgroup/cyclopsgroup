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
package com.cyclopsgroup.waterview.web;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Tree utils
 */
public final class TreeUtils
{
    /**
     * A row of node
     */
    public static class Row
    {
        private int depth;

        private boolean end = false;

        private StaticNode node;

        /**
         * Constructor for class NodeRow
         *
         * @param node TreeNode object
         * @param depth Depth of it
         */
        public Row( StaticNode node, int depth )
        {
            this.node = node;
            this.depth = depth;
        }

        /**
         * Get depth of node in tree
         *
         * @return Depth of tree
         */
        public int getDepth()
        {
            return depth;
        }

        /**
         * Get node object
         *
         * @return Tree node object
         */
        public StaticNode getNode()
        {
            return node;
        }

        /**
         * If the node is end node
         *
         * @return True if it's end node
         */
        public boolean isEnd()
        {
            return end;
        }

        /**
         * Set end node
         *
         * @param end End node or not
         */
        public void setEnd( boolean end )
        {
            this.end = end;
        }
    }

    /**
     * Flatten a given node with chiildrens
     *
     * @param node
     * @return Flattened node rows
     */
    public static List<Row> flattenTree( StaticNode node )
    {
        List<Row> ret = new ArrayList<Row>();
        flattenTree( node, ret, 1 );
        return ret;
    }

    private static void flattenTree( StaticNode node, List<Row> result, int depth )
    {
        Row row = new Row( node, depth );
        result.add( row );
        List<Node> children = node.getChildrenNodes();
        if ( children != null && !children.isEmpty() )
        {
            for ( Node n : children )
            {
                //TODO handle the none static node scenario
                StaticNode child = StaticNode.class.cast( n );
                flattenTree( child, result, depth + 1 );
            }
        }
        else
        {
            row.setEnd( true );
        }
    }
}
