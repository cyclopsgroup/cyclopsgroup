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

import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;

import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Runtime renderer used in template
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class RuntimeRenderer
{
    private UIRuntime uiRuntime;

    private VelocityEngine velocityEngine;

    RuntimeRenderer(UIRuntime runtime, VelocityEngine engine)
    {
        uiRuntime = runtime;
        velocityEngine = engine;
    }

    private URL getTemplateResource(String prefix, String path)
    {
        String fullName = "template/" + prefix + "/" + path;
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
        URL resource = classLoader.getResource(fullName);
        if (resource != null)
        {
            return resource;
        }
        String[] paths = StringUtils.split(path, "/");
        int lastIndex = paths.length - 1;
        while (resource == null && lastIndex >= 0)
        {
            paths[lastIndex] = "Default.vm";
            resource = classLoader.getResource(StringUtils.join(paths, "/"));
            paths[lastIndex] = null;
            lastIndex--;
        }
        return resource;
    }

    public String render(String prefix, String path) throws Exception
    {
        //TODO do something to execute module

        URL resource = getTemplateResource(prefix, path);
        if (resource == null)
        {
            return "Can not find resource [" + prefix + "/" + path + "]";
        }
        VelocityContextAdapter ctx = new VelocityContextAdapter(uiRuntime
                .getUIContext());
        velocityEngine.evaluate(ctx, uiRuntime.getHttpServletResponse()
                .getWriter(), "error.vm", new InputStreamReader(resource
                .openStream()));
        return StringUtils.EMPTY;
    }
}