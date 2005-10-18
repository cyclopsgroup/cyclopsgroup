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

import com.cyclopsgroup.waterview.spi.taglib.TagSupport;
import com.cyclopsgroup.waterview.web.Node;
import com.cyclopsgroup.waterview.web.RuntimeTreeNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Tag to show a tree
 */
public class TreeTag
    extends TagSupport
{
    static final String CURRENT_RUNTIME_NODE = TreeTag.class.getName() + "/currentRuntimeNode";

    static final String TREE_TAG = TreeTag.class.getName() + "/treeTag";

    private Node root;

    private String var = "node";

    private boolean runtime;

    /**
     * Getter method for property runtime
     *
     * @return Returns the runtime.
     */
    public boolean isRuntime()
    {
        return runtime;
    }

    /**
     * Getter method for property root
     *
     * @return Returns the root.
     */
    public Node getRoot()
    {
        return root;
    }

    /**
     * Getter method for property var
     *
     * @return Returns the var.
     */
    public String getVar()
    {
        return var;
    }

    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "var" );
        requireAttribute( "root" );
        RuntimeTreeNode runtimeNode;
        runtime = false;
        if ( getRoot() instanceof RuntimeTreeNode )
        {
            runtimeNode = (RuntimeTreeNode) getRoot();
            runtime = true;
        }
        else
        {
            //TODO not supported yet
            throw new UnsupportedOperationException();
        }

        JellyContext jc = new JellyContext( getContext() );
        jc.setVariable( getVar(), runtime ? runtimeNode : runtimeNode.getContent() );
        jc.setVariable( CURRENT_RUNTIME_NODE, runtimeNode );
        jc.setVariable( TREE_TAG, this );

        getBody().run( jc, output );
    }

    /**
     * Setter method for property root
     *
     * @param root The root to set.
     */
    public void setRoot( Node root )
    {
        this.root = root;
    }

    /**
     * Setter method for property var
     *
     * @param var The var to set.
     */
    public void setVar( String var )
    {
        this.var = var;
    }
}
