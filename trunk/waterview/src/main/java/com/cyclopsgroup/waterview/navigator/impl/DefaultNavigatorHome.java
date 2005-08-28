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

import java.io.StringWriter;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.jelly.parser.XMLParser;

import com.cyclopsgroup.waterview.navigator.NavigatorHome;
import com.cyclopsgroup.waterview.navigator.NavigatorNode;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Default implementation of navigator home
 */
public class DefaultNavigatorHome extends AbstractLogEnabled implements
        NavigatorHome, Initializable
{
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
        nodes.put(node.getPath(), node);
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

    DefaultNavigatorNode getNode(String path)
    {
        return (DefaultNavigatorNode) nodes.get(path);
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
        jc.registerTagLibrary("default", new NavigatorTagLibrary());

        XMLParser parser = new XMLParser();
        parser.setDefaultNamespaceURI("");
        StringWriter sw = new StringWriter();
        XMLOutput output = XMLOutput.createXMLOutput(sw);
        for (Enumeration en = getClass().getClassLoader().getResources(
                "META-INF/cyclopsgroup/waterview-navigation.xml"); en
                .hasMoreElements();)
        {
            URL resource = (URL) en.nextElement();
            getLogger().info("Reading navigation from " + resource);
            Script script = parser.parse(resource);
            script.run(jc, output);
            output.flush();
        }
        getLogger().info(sw.toString());
    }
}
