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

import java.util.HashSet;
import java.util.Iterator;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Valve;

/**
 * Valve to prepare information contained by URL
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ParseURLValve extends Valve implements Configurable
{
    private String defaultPage;

    private HashSet pageExtensions;

    /**
     * Override or implement method of parent class or interface
     * 
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        defaultPage = conf.getChild("default-page").getValue("Index.vm");
        Configuration[] exts = conf.getChild("page-extensions").getChildren(
                "page-extension");
        pageExtensions = new HashSet(exts.length);
        for (int i = 0; i < exts.length; i++)
        {
            pageExtensions.add(exts[i].getValue());
        }
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see com.cyclopsgroup.waterview.Valve#invoke(com.cyclopsgroup.waterview.UIRuntime)
     */
    public void invoke(UIRuntime runtime) throws Exception
    {
        String pathInfo = runtime.getRequestPath();
        if (StringUtils.isEmpty(pathInfo))
        {
            pathInfo = defaultPage;
        }
        String[] parts = StringUtils.split(pathInfo, '|');
        String page = null;
        for (int i = 0; i < parts.length; i++)
        {
            String part = parts[i];
            if (part.charAt(0) == '/')
            {
                part = part.substring(1);
            }
            if (i == parts.length - 1)
            {
                boolean pageFound = false;
                for (Iterator j = pageExtensions.iterator(); j.hasNext();)
                {
                    String extension = (String) j.next();
                    if (part.endsWith('.' + extension))
                    {
                        page = part;
                        pageFound = true;
                        break;
                    }
                }
                if (!pageFound)
                {
                    runtime.getActions().add(part);
                }
            }
            else
            {
                runtime.getActions().add(part);
            }
        }
        if (StringUtils.isEmpty(page))
        {
            page = defaultPage;
        }
        runtime.setPage(page);
        runtime.getUIContext().put("page", page);
        invokeNext(runtime);
    }
}