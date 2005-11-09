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
package com.cyclopsgroup.waterview.ui;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.navigator.NavigatorNode;
import com.cyclopsgroup.waterview.navigator.NavigatorService;
import com.cyclopsgroup.waterview.web.Node;
import com.cyclopsgroup.waterview.web.RuntimeTreeNode;
import com.cyclopsgroup.waterview.web.TreeUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Module for default layout
 */
public class DefaultLayout
    extends BaseServiceable
    implements Module
{
    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Module#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.Context)
     */
    public void execute( RuntimeData data, Context context )
        throws Exception
    {
        NavigatorService navigator = (NavigatorService) lookupComponent( NavigatorService.ROLE );
        RuntimeTreeNode root = navigator.getRuntimeNode( data );
        Node[] tabNodes = root.getChildrenNodes();
        context.put( "tabNodes", tabNodes );

        context.put( "navigator", navigator );
        context.put( "currentNavigatorNode", navigator.getNodeByPage( data.getPage().getFullPath() ) );

        RuntimeTreeNode selectedNode = null;
        for ( int i = 0; i < tabNodes.length; i++ )
        {
            RuntimeTreeNode runtimeNode = (RuntimeTreeNode) tabNodes[i];
            NavigatorNode node = (NavigatorNode) runtimeNode.getContent();
            if ( !StringUtils.equals( node.getPage(), data.getPage().getFullPath() ) )
            {
                NavigatorNode n = navigator.getNodeByPage( data.getPage().getFullPath() );
                if ( n != null && !n.isParent( node.getPage() ) )
                {
                    continue;
                }
            }
            selectedNode = runtimeNode;
            context.put( "selectedNode", selectedNode );
            break;
        }
        if ( selectedNode != null )
        {
            context.put( "navigatorRows", TreeUtils.flattenTree( selectedNode ) );
        }
    }
}