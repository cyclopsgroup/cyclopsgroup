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
package com.cyclopsgroup.waterview.velocity;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

import com.cyclopsgroup.waterview.UIModuleResolver;
import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Runtime renderer used in template
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class RuntimeRenderer
{

    private Map cache;

    private UIRuntime uiRuntime;

    private VelocityEngine velocityEngine;

    /**
     * Constructor of RuntimeRenderer
     * 
     * @param runtime Runtime object
     * @param engine Velocity engine
     * @param templateCache Reference to the template cache
     */
    RuntimeRenderer(UIRuntime runtime, VelocityEngine engine, Map templateCache)
    {
        uiRuntime = runtime;
        velocityEngine = engine;
        this.cache = templateCache;
    }

    private Template getTemplate(String path) throws Exception
    {
        if (cache.containsKey(path))
        {
            return (Template) cache.get(path);
        }
        if (velocityEngine.templateExists(path))
        {
            Template template = velocityEngine.getTemplate(path);
            cache.put(path, template);
            return template;
        }
        return null;
    }

    private Template getTemplate(String prefix, String path) throws Exception
    {
        String fullName = prefix + "/" + path;
        Template template = getTemplate(fullName);
        if (template != null)
        {
            return template;
        }
        String[] paths = StringUtils.split(path, "/");
        int lastIndex = paths.length - 1;
        while (template == null && lastIndex >= 0)
        {
            paths[lastIndex] = "Default.vm";
            template = getTemplate(prefix + "/" + StringUtils.join(paths, "/"));
            paths[lastIndex] = null;
            lastIndex--;
        }
        return template;
    }

    /**
     * This method will be called in velocity page
     * 
     * @param prefix Prefix foler
     * @param path Page path
     * @return Empty string
     * @throws Exception Throw it out
     */
    public String render(String prefix, String path) throws Exception
    {
        UIModuleResolver actionResolver = (UIModuleResolver) uiRuntime
                .getServiceManager().lookup(UIModuleResolver.ROLE);
        String fullPath = prefix + "/" + path;
        actionResolver.resolve(fullPath, uiRuntime);
        Template template = getTemplate(prefix, path);
        if (template != null)
        {
            VelocityContextAdapter ctx = new VelocityContextAdapter(uiRuntime
                    .getUIContext());
            template.merge(ctx, uiRuntime.getHttpServletResponse().getWriter());
        }

        return StringUtils.EMPTY;
    }
}