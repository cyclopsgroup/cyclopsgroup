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

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Valve;

/**
 * Valve to parse URL
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class ParseURLValve extends AbstractLogEnabled implements Valve,
        Configurable
{

    private HashSet actionExtensions = new HashSet();

    private String defaultPage;

    private HashSet pageExtensions = new HashSet();

    private String pathSeparator;

    /**
     * Override method configure in super class of ParseURLValve
     * 
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        pathSeparator = conf.getChild("path-separator").getValue(",");
        defaultPage = conf.getChild("default-page").getValue("index.html");
        Configuration[] extensions = conf.getChild("extensions").getChildren();
        for (int i = 0; i < extensions.length; i++)
        {
            Configuration ext = extensions[i];
            if (StringUtils.equals(ext.getName(), "page"))
            {
                pageExtensions.add(ext.getValue());
            }
            else if (StringUtils.equals(ext.getName(), "action"))
            {
                actionExtensions.add(ext.getValue());
            }
        }
    }

    /**
     * Override method process in super class of ParseURLValve
     * 
     * @see com.cyclopsgroup.waterview.Valve#process(com.cyclopsgroup.waterview.UIRuntime)
     */
    public boolean process(UIRuntime runtime) throws Exception
    {
        String path = runtime.getHttpServletRequest().getPathInfo();
        if (StringUtils.isEmpty(path))
        {
            path = defaultPage;
        }
        System.out.println(path);
        String[] paths = StringUtils.split(path, pathSeparator);
        for (int i = 0; i < paths.length; i++)
        {
            String subPath = paths[i];
            int lastDot = subPath.lastIndexOf('.');
            if (lastDot == -1)
            {
                continue;
            }
            String ext = subPath.substring(lastDot + 1);
            if (actionExtensions.contains(ext))
            {
                runtime.getActions().add(subPath);
                System.out.println("action -->" + subPath);
            }
            else if (pageExtensions.contains(ext))
            {
                runtime.setPage(subPath);
                System.out.println("page -->" + subPath);
            }
        }
        if (StringUtils.isEmpty(runtime.getPage()))
        {
            runtime.setPage(defaultPage);
        }
        return true;
    }
}