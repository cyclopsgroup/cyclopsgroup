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
package com.cyclopsgroup.waterview.jelly.valves;

import java.util.Hashtable;
import java.util.Map;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.ModuleManager;
import com.cyclopsgroup.waterview.Page;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.PipelineContext;
import com.cyclopsgroup.waterview.Valve;
import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.utils.MapUtils;

/**
 * Valve to find page object
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DeterminePageValve extends AbstractLogEnabled implements
        Configurable, Valve
{

    private static final Page EMPTY_PAGE = new Page();

    private Map pageCache = new Hashtable();

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        int cacheSize = conf.getChild("page-cache").getValueAsInteger(-1);
        pageCache = MapUtils.createCache(cacheSize);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Valve#invoke(com.cyclopsgroup.waterview.PageRuntime, com.cyclopsgroup.waterview.PipelineContext)
     */
    public synchronized void invoke(PageRuntime runtime, PipelineContext context)
            throws Exception
    {
        Page page = (Page) runtime.getPageContext().get(Page.NAME);
        if (page != null)
        {
            context.invokeNextValve(runtime);
            return;
        }
        String pagePath = runtime.getPage();
        if (StringUtils.isEmpty(pagePath))
        {
            throw new IllegalStateException("Page is not set yet");
        }
        page = (Page) pageCache.get(pagePath);
        if (page != null)
        {
            runtime.getPageContext().put(Page.NAME, page);
            context.invokeNextValve(runtime);
            return;
        }
        ModuleManager mm = (ModuleManager) runtime.getServiceManager().lookup(
                ModuleManager.ROLE);
        JellyEngine je = (JellyEngine) runtime.getServiceManager().lookup(
                JellyEngine.ROLE);
        ModuleChain moduleChain = new ModuleChain();
        String fullPath = "page/" + pagePath;
        moduleChain.addModule(mm.getModule(fullPath));
        String[] pkgs = mm.getPackageNames();

        for (int i = 0; i < pkgs.length; i++)
        {
            String pkg = pkgs[i];
            Script pageScript = je.getScript(fullPath, pkg, null);
            if (pageScript != null)
            {
                page = loadPage(pageScript, je);
                break;
            }
            String[] parts = StringUtils.split(pagePath, '/');
            for (int j = parts.length - 1; j >= 0; j--)
            {
                parts[j] = "Default";
                String[] newParts = new String[j + 1];
                System.arraycopy(parts, 0, newParts, 0, j + 1);
                String defaultPath = StringUtils.join(newParts, '/');
                fullPath = "page/" + defaultPath;
                pageScript = je.getScript(fullPath, pkg, null);
                if (pageScript != null)
                {
                    page = loadPage(pageScript, je);
                    moduleChain.addModule(mm.getModule(fullPath, pkg));
                    break;
                }
            }
            if (page != null)
            {
                break;
            }
        }
        if (page == null)
        {
            page = EMPTY_PAGE;
        }
        page.setModule(moduleChain);
        pageCache.put(pagePath, page);
        runtime.getPageContext().put(Page.NAME, page);
        context.invokeNextValve(runtime);
    }

    private Page loadPage(Script script, JellyEngine jellyEngine)
            throws JellyTagException
    {
        JellyContext jc = new JellyContext(jellyEngine.getGlobalContext());
        script.run(jc, XMLOutput.createDummyXMLOutput());
        return (Page) jc.getVariable(Page.NAME);
    }
}
