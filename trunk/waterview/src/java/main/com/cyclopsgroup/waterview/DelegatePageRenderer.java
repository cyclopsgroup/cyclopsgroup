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
package com.cyclopsgroup.waterview;

import org.apache.commons.lang.StringUtils;

/**
 * Page renderer adapter for internal usage
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class DelegatePageRenderer
{
    private PageRenderer renderer;

    /**
     * Constructor for class DelegatePageRenderer
     *
     * @param renderer Page renderer object
     */
    public DelegatePageRenderer(PageRenderer renderer)
    {
        this.renderer = renderer;
    }

    /**
     * Internal page render method
     *
     * @param runtime UIRuntime object
     * @param moduleResolver Module resolver
     * @param packageName Package of page
     * @param page Page path
     * @throws Exception Throw it out
     */
    public void render(UIRuntime runtime, UIModuleResolver moduleResolver,
            String packageName, String page) throws Exception
    {
        String prefix = packageName + '/';
        String fullPage = prefix + page;
        String extension = (String) runtime.getUIContext().get("pageExtension");
        String moduleName = StringUtils.left(fullPage, fullPage.length()
                - extension.length() - 1);
        moduleResolver.resolve(runtime, moduleName);
        if (renderer.pageExists(fullPage))
        {
            renderer.render(runtime, fullPage);
        }
        else
        {
            String[] parts = StringUtils.split(page, '/');
            int lastIndex = parts.length - 1;
            String defaultPageName = "Default." + extension;
            parts[lastIndex] = defaultPageName;
            String newPage = StringUtils.join(parts, '/');
            fullPage = prefix + newPage;
            boolean found = renderer.pageExists(fullPage);
            while (!found && lastIndex > 0)
            {
                parts[lastIndex] = null;
                lastIndex--;
                parts[lastIndex] = defaultPageName;
                newPage = StringUtils.join(parts, '/');
                fullPage = prefix + newPage;
                found = renderer.pageExists(fullPage);
            }
            if (found)
            {
                moduleName = StringUtils.left(fullPage, fullPage.length()
                        - extension.length() - 1);
                moduleResolver.resolve(runtime, moduleName);
                renderer.render(runtime, fullPage);
            }
        }
    }
}