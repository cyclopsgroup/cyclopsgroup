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
package com.cyclopsgroup.waterview.tool;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.collections.map.ListOrderedMap;

import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Valve;

/**
 * Tools handling valve
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class PopulateToolsValve extends Valve implements Configurable
{

    private class ToolDef
    {

        private String className;

        private ToolLifecycle lifecycle;

        private String name;

        private ToolDef(Configuration c) throws ConfigurationException
        {
            name = c.getAttribute("name");
            String lifecycleName = c.getAttribute("lifecycle", "request");
            lifecycle = ToolLifecycle.valueOf(lifecycleName);
            className = c.getAttribute("class");
        }
    }

    private Map applicationTools;

    private Map toolDefinitions;

    /**
     * Override or implement method of parent class or interface
     * 
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        int repoSize = conf.getChild("application-tools")
                .getAttributeAsInteger("size", -1);
        if (repoSize <= 0)
        {
            applicationTools = new Hashtable();
        }
        else
        {
            applicationTools = new LRUMap(repoSize);
        }
        Configuration[] toolConfs = conf.getChild("tools").getChildren("tool");
        toolDefinitions = ListOrderedMap.decorate(new Hashtable(
                toolConfs.length));
        for (int i = 0; i < toolConfs.length; i++)
        {
            Configuration toolConf = toolConfs[i];
            ToolDef def = new ToolDef(toolConf);
            toolDefinitions.put(def.name, def);
        }
    }

    private UITool createApplicationTool(UIRuntime runtime, ToolDef def)
            throws Exception
    {
        if (applicationTools.containsKey(def.name))
        {
            return (UITool) applicationTools.get(def.name);
        }
        UITool tool = (UITool) Class.forName(def.className).newInstance();
        tool.initialize(runtime);
        applicationTools.put(def.name, tool);
        return tool;
    }

    private UITool createRequestTool(UIRuntime runtime, ToolDef def)
            throws Exception
    {
        UITool tool = (UITool) Class.forName(def.className).newInstance();
        tool.initialize(runtime);
        return tool;
    }

    private UITool createSessionTool(UIRuntime runtime, ToolDef def)
            throws Exception
    {
        String name = "waterview_tool_" + def.name;
        UITool tool = (UITool) runtime.getSessionContext().get(name);
        if (tool == null)
        {
            tool = (UITool) Class.forName(def.className).newInstance();
            tool.initialize(runtime);
            runtime.getSessionContext().put(name, tool);
        }
        return tool;
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see com.cyclopsgroup.waterview.Valve#invoke(com.cyclopsgroup.waterview.UIRuntime)
     */
    public void invoke(UIRuntime runtime) throws Exception
    {
        Stack processedTools = new Stack();
        for (Iterator i = toolDefinitions.values().iterator(); i.hasNext();)
        {
            ToolDef def = (ToolDef) i.next();
            UITool tool = null;
            try
            {
                if (def.lifecycle == ToolLifecycle.REQUEST)
                {
                    tool = createRequestTool(runtime, def);
                }
                else if (def.lifecycle == ToolLifecycle.SESSION)
                {
                    tool = createSessionTool(runtime, def);
                }
                else if (def.lifecycle == ToolLifecycle.APPLICATION)
                {
                    tool = createApplicationTool(runtime, def);
                }
                tool.setName(def.name);
                processedTools.push(tool);
                runtime.getPageContext().put(def.name, tool);
                if (tool instanceof RequestListener)
                {
                    ((RequestListener) tool).prepareForRequest(runtime);
                }
            }
            catch (Exception e)
            {
                getLogger().warn("Tool initialization error", e);
            }
        }

        try
        {
            invokeNext(runtime);
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            while (!processedTools.isEmpty())
            {
                UITool tool = (UITool) processedTools.pop();
                runtime.getPageContext().remove(tool.getName());
                try
                {
                    if (tool instanceof RequestListener)
                    {
                        ((RequestListener) tool).disposeForRequest(runtime);
                    }
                }
                catch (Exception e)
                {
                    getLogger().warn("Tool disposing error", e);
                }
            }
        }
    }
}