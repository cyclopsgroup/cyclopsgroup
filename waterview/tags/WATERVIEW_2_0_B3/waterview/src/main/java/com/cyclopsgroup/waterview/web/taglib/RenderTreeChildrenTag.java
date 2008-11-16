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
package com.cyclopsgroup.waterview.web.taglib;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.utils.TagSupport;
import com.cyclopsgroup.waterview.web.Node;
import com.cyclopsgroup.waterview.web.RuntimeTreeNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag to render tree children nodes
 */
public class RenderTreeChildrenTag
    extends TagSupport
{
    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        TreeTag tree = (TreeTag) getContext().getVariable( TreeTag.TREE_TAG );
        RuntimeTreeNode runtimeNode = (RuntimeTreeNode) getContext().getVariable( TreeTag.CURRENT_RUNTIME_NODE );
        JellyContext jc = new JellyContext( getContext() );
        Node[] nodes = runtimeNode.getChildrenNodes();
        for ( int i = 0; i < nodes.length; i++ )
        {
            RuntimeTreeNode node = (RuntimeTreeNode) nodes[i];
            jc.setVariable( tree.getVar(), tree.isRuntime() ? node : node.getContent() );
            jc.setVariable( TreeTag.CURRENT_RUNTIME_NODE, node );
            tree.getBody().run( jc, output );
        }
    }
}
