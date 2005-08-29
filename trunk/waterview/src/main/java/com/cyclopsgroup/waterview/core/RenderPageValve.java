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
package com.cyclopsgroup.waterview.core;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.ModuleManager;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.Theme;
import com.cyclopsgroup.waterview.spi.ThemeManager;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * Valve to render page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class RenderPageValve extends AbstractLogEnabled implements Valve
{
    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Valve#invoke(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.PipelineContext)
     */
    public void invoke(RuntimeData data, PipelineContext context)
            throws Exception
    {
        ModuleManager mm = (ModuleManager) data.getServiceManager().lookup(
                ModuleManager.ROLE);

        mm.runModule('/' + data.getPage().getPackageAlias() + "/page"
                + data.getPage().getPath(), data, data.getRequestContext());

        if (data.isStopped())
        {
            return;
        }

        Page page = (Page) data.getRequestContext().get(Page.NAME);
        if (page == null)
        {
            page = Page.DEFAULT;
        }
        data.setOutputContentType("text/html");
        Layout layout = page.getLayout();
        if (layout == null)
        {
            Theme theme = null;
            ThemeManager themes = (ThemeManager) data.getServiceManager()
                    .lookup(ThemeManager.ROLE);
            if (StringUtils.isNotEmpty(data.getThemeName()))
            {

                theme = themes.getTheme(data.getThemeName());
            }
            if (theme == null)
            {
                theme = themes.getTheme(ThemeManager.DEFAULT_THEME);
            }
            if (theme == null)
            {
                throw new NullPointerException(
                        "Theme is not property configured");
            }
            layout = theme.getLayout(Theme.LAYOUT_FOR_DEFAULT);
        }

        layout.render(data, page);
        context.invokeNextValve(data);
        data.getOutput().flush();
    }
}
