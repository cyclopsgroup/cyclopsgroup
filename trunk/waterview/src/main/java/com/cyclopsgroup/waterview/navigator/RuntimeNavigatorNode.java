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
package com.cyclopsgroup.waterview.navigator;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.web.RuntimeTreeNode;
import com.cyclopsgroup.waterview.web.TreeNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Runtime node for navigator nodes
 */
public final class RuntimeNavigatorNode
    extends RuntimeTreeNode
{
    private static final String KEY = RuntimeNavigatorNode.class.getName();

    /**
     * Get runtime instance
     *
     * @param data Runtime data
     * @return Runtime navigator node
     * @throws Exception Throw it out
     */
    public synchronized static RuntimeNavigatorNode getRoot( RuntimeData data )
        throws Exception
    {
        RuntimeNavigatorNode root = (RuntimeNavigatorNode) data.getSessionContext().get( KEY );
        if ( root == null )
        {
            NavigatorHome navigator = (NavigatorHome) data.getServiceManager().lookup( NavigatorHome.ROLE );
            root = new RuntimeNavigatorNode( null, navigator.getRootNode() );
            root.expand( data );
            TreeNode[] children = root.getChildrenNodes();
            for ( int i = 0; i < children.length; i++ )
            {
                TreeNode child = children[i];
                ( (RuntimeNavigatorNode) child ).expand( data );
            }
            data.getSessionContext().put( KEY, root );
        }
        return root;
    }

    /**
     * Refresh navigator
     *
     * @param data Runtime data
     */
    public static void refresh( RuntimeData data )
    {
        data.getSessionContext().remove( KEY );
    }

    private RuntimeNavigatorNode( RuntimeTreeNode parentNode, TreeNode node )
    {
        super( parentNode, node );
    }

    /**
     * Overwrite or implement method doCreateChild()
     *
     * @see com.cyclopsgroup.waterview.web.RuntimeTreeNode#doCreateChild(com.cyclopsgroup.waterview.web.TreeNode)
     */
    protected RuntimeTreeNode doCreateChild( TreeNode node )
    {
        return new RuntimeNavigatorNode( this, node );
    }
}
