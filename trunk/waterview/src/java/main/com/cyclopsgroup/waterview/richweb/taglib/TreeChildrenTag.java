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
package com.cyclopsgroup.waterview.richweb.taglib;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.jelly.AbstractTag;
import com.cyclopsgroup.waterview.richweb.TreeNode;

/**
 * Tag to render children
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class TreeChildrenTag extends AbstractTag
{
    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.jelly.AbstractTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        TreeScriptTag treeScriptTag = (TreeScriptTag) findAncestorWithClass(TreeTag.class);
        String var = treeScriptTag.getVar();
        if (treeScriptTag == null)
        {
            throw new JellyTagException(
                    "TreeChildren must be in the TreeScript");
        }
        TreeNode node = (TreeNode) getContext().getVariable(var);
        TreeNode[] children = node.getChildren();
        for (int i = 0; i < children.length; i++)
        {
            TreeNode child = children[i];
            JellyContext jc = new JellyContext(getContext());
            jc.setVariable(var, child);
            treeScriptTag.getBody().run(jc, output);
        }
    }
}
