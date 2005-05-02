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
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.jelly.AbstractTag;
import com.cyclopsgroup.waterview.richweb.Tree;
import com.cyclopsgroup.waterview.richweb.TreeRuntime;

/**
 * Tag to show a tree object
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class TreeTag extends AbstractTag
{

    private String nodeVar;

    private Tree tree;

    private Script treeScript;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.jelly.AbstractTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        requireAttribute("tree");
        invokeBody(output);
        if (treeScript == null || nodeVar == null)
        {
            throw new JellyTagException(
                    "A TreeScript tag must be defined in Tree tag");
        }

        String treeRuntimeName = "cyclopsgropu.waterview.tree_"
                + getBody().hashCode();
        TreeRuntime treeRuntime = (TreeRuntime) getRuntime()
                .getSessionContext().get(treeRuntimeName);
        if (treeRuntime == null)
        {
            treeRuntime = new TreeRuntime();
            getRuntime().getSessionContext().put(treeRuntimeName, treeRuntime);
        }

        JellyContext jc = new JellyContext(getContext());
        jc.setVariable(TreeRuntime.NAME, treeRuntime);
        jc.setVariable(nodeVar, treeRuntime.createRuntimeNode(tree
                .getRootNode()));
        treeScript.run(jc, output);
    }

    /**
     * Getter method for tree
     *
     * @return Returns the tree.
     */
    public Tree getTree()
    {
        return tree;
    }

    /**
     * Setter method for nodeVar
     *
     * @param nodeVar The nodeVar to set.
     */
    void setNodeVar(String nodeVar)
    {
        this.nodeVar = nodeVar;
    }

    /**
     * Setter method for tree
     *
     * @param tree The tree to set.
     */
    public void setTree(Tree tree)
    {
        this.tree = tree;
    }

    /**
     * Setter method for treeScript
     *
     * @param treeScript The treeScript to set.
     */
    void setTreeScript(Script treeScript)
    {
        this.treeScript = treeScript;
    }
}
