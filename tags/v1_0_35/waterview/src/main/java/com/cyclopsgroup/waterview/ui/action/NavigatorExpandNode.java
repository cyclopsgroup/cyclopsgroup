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
package com.cyclopsgroup.waterview.ui.action;

import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.navigator.NavigatorService;
import com.cyclopsgroup.waterview.web.RuntimeTreeNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Action to expand navigator node
 */
public class NavigatorExpandNode
    implements Action
{
    /**
     * Overwrite or implement method execute()
     *
     * @see com.cyclopsgroup.waterview.Action#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.ActionContext)
     */
    public void execute( RuntimeData data, ActionContext context )
        throws Exception
    {
        NavigatorService navigator = (NavigatorService) data.getServiceManager().lookup( NavigatorService.ROLE );
        RuntimeTreeNode nav = navigator.getRuntimeNode( data );
        String nodeId = data.getParams().getString( "navigator_node_id" );
        RuntimeTreeNode node = (RuntimeTreeNode) nav.getNodeById( nodeId );
        node.expand( data );
        context.setTargetUrl( data.getRefererUrl() );
    }
}
