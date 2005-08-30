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
package com.cyclopsgroup.waterview.navigator.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.navigator.NavigatorHome;
import com.cyclopsgroup.waterview.navigator.NavigatorNode;
import com.cyclopsgroup.waterview.web.RuntimeTreeNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Default implementation of navigator home
 */
public class DefaultNavigatorHome extends AbstractLogEnabled implements
        NavigatorHome, Initializable
{
    private static final String NODE_NAME = DefaultNavigatorHome.class
            .getName()
            + "/RuntimeTreeNode";

    private static final String NODE_PATH_NAME = DefaultNavigatorHome.class
            .getName()
            + "/NodePath";

    private Map children;

    private Map nodes;

    private DefaultNavigatorNode rootNode;

    /**
     * Add node
     *
     * @param node Node to add
     */
    public void addNode(DefaultNavigatorNode node)
    {
        if (StringUtils.isNotEmpty(node.getPath()))
        {
            nodes.put(node.getPath(), node);
        }
        if (node.getParentPath() != null)
        {
            children.put(node.getParentPath(), node);
        }
    }

    Collection getChildren(String path)
    {
        Collection c = (Collection) children.get(path);
        return c == null ? Collections.EMPTY_SET : c;
    }

    /**
     * Get node based on path
     *
     * @param path Path for node
     * @return Node object or null if not found
     */
    public NavigatorNode getNode(String path)
    {
        if (StringUtils.isEmpty(path))
        {
            return null;
        }
        return (NavigatorNode) nodes.get(path);
    }

    /**
     * Overwrite or implement method getPath()
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorHome#getPath(com.cyclopsgroup.waterview.RuntimeData)
     */
    public synchronized NavigatorNode[] getPath(RuntimeData data)
    {
        List path = (List) data.getRequestContext().get(NODE_PATH_NAME);
        if (path == null)
        {
            NavigatorNode node = null;
            for (Iterator i = children.values().iterator(); i.hasNext();)
            {
                NavigatorNode n = (NavigatorNode) i.next();
                if (StringUtils.equals(n.getPage(), data.getPage()
                        .getFullPath()))
                {
                    node = n;
                    break;
                }
            }
            if (node == null)
            {
                path = (List) data.getSessionContext().get(NODE_PATH_NAME);
            }
            else
            {
                List list = new ArrayList();
                NavigatorNode n = node;
                while (n != null)
                {
                    list.add(0, n);
                    n = n.getParentNavigatorNode();
                }
                path = list;
            }
            data.getRequestContext().put(NODE_PATH_NAME, path);
        }
        data.getSessionContext().put(NODE_PATH_NAME, path);
        return (NavigatorNode[]) path.toArray(NavigatorNode.EMPTY_ARRAY);
    }

    /**
     * Overwrite or implement method in DefaultNavigatorHome
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorHome#getRootNode()
     */
    public NavigatorNode getRootNode()
    {
        return rootNode;
    }

    /**
     * Overwrite or implement method getRuntimeRootNode()
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorHome#getRuntimeRootNode(com.cyclopsgroup.waterview.RuntimeData)
     */
    public RuntimeTreeNode getRuntimeRootNode(RuntimeData data)
            throws Exception
    {
        RuntimeTreeNode node = null;
        synchronized (data)
        {
            node = (RuntimeTreeNode) data.getSessionContext().get(NODE_NAME);
            if (node == null)
            {
                node = new RuntimeTreeNode(null, getRootNode());
                node.expand(data);
                data.getSessionContext().put(NODE_NAME, node);
            }
        }
        return node;
    }

    /**
     * Overwrite or implement method in DefaultNavigatorHome
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        nodes = new Hashtable();
        children = new MultiHashMap();

        rootNode = new DefaultNavigatorNode(this, "/", null);
        addNode(rootNode);

        JellyContext jc = new JellyContext();
        jc.setVariable(getClass().getName(), this);
        jc.registerTagLibrary("http://waterview.cyclopsgroup.com/navigator",
                new NavigatorTagLibrary());
        for (Enumeration en = getClass().getClassLoader().getResources(
                "META-INF/cyclopsgroup/waterview-navigation.xml"); en
                .hasMoreElements();)
        {
            URL resource = (URL) en.nextElement();
            getLogger().info("Reading navigation from " + resource);
            jc.runScript(resource, XMLOutput.createDummyXMLOutput());
        }
    }

    /**
     * Overwrite or implement method isCurrent()
     *
     * @see com.cyclopsgroup.waterview.navigator.NavigatorHome#isCurrent(com.cyclopsgroup.waterview.navigator.NavigatorNode, com.cyclopsgroup.waterview.RuntimeData)
     */
    public boolean isCurrent(NavigatorNode node, RuntimeData data)
    {
        NavigatorNode[] path = getPath(data);
        for (int i = 0; i < path.length; i++)
        {
            NavigatorNode n = path[i];
            if (n.getNodeId().equals(node.getNodeId()))
            {
                return true;
            }
        }
        return false;
    }
}
