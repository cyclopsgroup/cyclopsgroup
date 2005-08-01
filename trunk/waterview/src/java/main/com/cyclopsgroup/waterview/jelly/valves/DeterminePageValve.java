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

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.jelly.JellyEngine;
import com.cyclopsgroup.waterview.spi.CacheManager;
import com.cyclopsgroup.waterview.spi.ModuleManager;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * Valve to find page object
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DeterminePageValve extends AbstractLogEnabled implements
        Configurable, Valve
{

    private static final Page EMPTY_PAGE = new Page();

    private String defaultPackage = "com.cyclopsgroup.waterview.ui";

    private String defaultPage = "Index.jelly";

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        String page = conf.getChild("default-page").getValue(null);
        if (page != null)
        {
            setDefaultPage(page);
        }
        String pkg = conf.getChild("default-package").getValue(null);
        if (pkg != null)
        {
            setDefaultPackage(pkg);
        }
    }

    /**
     * @return Returns the defaultPackage.
     */
    public String getDefaultPackage()
    {
        return defaultPackage;
    }

    /**
     * Getter method for defaultPage
     *
     * @return Returns the defaultPage.
     */
    public String getDefaultPage()
    {
        return defaultPage;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Valve#invoke(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.PipelineContext)
     */
    public void invoke(RuntimeData runtime, PipelineContext context)
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
            runtime.setPage(getDefaultPage());
            pagePath = getDefaultPage();
        }

        synchronized (this)
        {
            CacheManager cacheManager = (CacheManager) runtime
                    .getServiceManager().lookup(CacheManager.ROLE);
            page = (Page) cacheManager.get(this, pagePath);
            if (page == null)
            {
                ModuleManager mm = (ModuleManager) runtime.getServiceManager()
                        .lookup(ModuleManager.ROLE);
                ModuleManager.PathModel model = mm.parsePath(pagePath);
                JellyEngine je = (JellyEngine) runtime.getServiceManager()
                        .lookup(JellyEngine.ROLE);
                ModuleChain moduleChain = new ModuleChain();
                String fullPath = "page/" + pagePath;
                moduleChain.addModule(mm.getModule(fullPath));

                Script pageScript = je.getScript(model.getPackage(), fullPath,
                        null);
                if (pageScript != null)
                {
                    page = loadPage(pageScript, je);
                }
                String[] parts = StringUtils.split(pagePath, '/');
                for (int j = parts.length - 1; j >= 0; j--)
                {
                    parts[j] = "Default";
                    String[] newParts = new String[j + 1];
                    System.arraycopy(parts, 0, newParts, 0, j + 1);
                    String defaultPath = StringUtils.join(newParts, '/');
                    fullPath = "page/" + defaultPath;
                    pageScript = je.getScript(model.getPath(), fullPath, null);
                    if (pageScript != null)
                    {
                        page = loadPage(pageScript, je);
                        moduleChain.addModule(mm.getModule(model.getPackage(),
                                fullPath));
                        break;
                    }
                }
                if (page == null)
                {
                    page = EMPTY_PAGE;
                }
                page.setModule(moduleChain);
                cacheManager.put(this, pagePath, page);
            }
        }
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

    /**
     * @param defaultPackage The defaultPackage to set.
     */
    public void setDefaultPackage(String defaultPackage)
    {
        this.defaultPackage = defaultPackage;
    }

    /**
     * Setter method for defaultPage
     *
     * @param defaultPage The defaultPage to set.
     */
    public void setDefaultPage(String defaultPage)
    {
        this.defaultPage = defaultPage;
    }
}
