/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 *
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.ui.sysview;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.BaseServiceable;
import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.navigator.NavigatorService;
import com.cyclopsgroup.waterview.web.RuntimeTreeNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Module for default navigation node
 */
public class DefaultNavigationNode
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
        RuntimeTreeNode navigationNode = null;

        String nodeId = data.getParameters().getString( "node_id" );
        if ( StringUtils.isNotEmpty( nodeId ) )
        {
            NavigatorService nav = (NavigatorService) lookupComponent( NavigatorService.ROLE );
            navigationNode = nav.getRuntimeNode( data ).getNodeById( nodeId );
        }

        if ( navigationNode == null )
        {
            navigationNode = (RuntimeTreeNode) context.get( "navigationNode" );
        }

        if ( navigationNode == null )
        {
            throw new IllegalArgumentException( "No node is specified" );
        }

        context.put( "navigationNode", navigationNode );

        String action = data.getParameters().getString( "node_action" );
        if ( StringUtils.equals( "expand", action ) )
        {
            navigationNode.expand( data );
        }
        else if ( StringUtils.equals( "collapse", action ) )
        {
            navigationNode.collapse();
        }
    }
}
