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

import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.collections.LRUMap;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;

import com.cyclopsgroup.waterview.PageRenderer;
import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Velocity page renderer
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class VelocityPageRenderer extends AbstractLogEnabled implements
        PageRenderer, Initializable
{

    private static final String CONTENT_TYPE = "text/html";

    private static String getTemplatePath(String packageName, String page)
    {
        StringBuffer templatePath = new StringBuffer();
        if (StringUtils.isNotEmpty(packageName))
        {
            templatePath.append(packageName.replace('.', '/'));
            templatePath.append("/");
        }
        templatePath.append(page);
        if (!page.endsWith(".vm"))
        {
            templatePath.append(".vm");
        }
        return templatePath.toString();
    }

    private LRUMap templateCache;

    private VelocityEngine velocityEngine;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRenderer#exists(java.lang.String, java.lang.String)
     */
    public boolean exists(String packageName, String page)
    {
        return velocityEngine
                .templateExists(getTemplatePath(packageName, page));
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRenderer#getContentType()
     */
    public String getContentType()
    {
        return CONTENT_TYPE;
    }

    /**
     * Override method initialize in super class of VelocityPageRenderer
     * 
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        velocityEngine = new VelocityEngine();
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("velocity.properties"));
        velocityEngine.init(props);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.PageRenderer#render(com.cyclopsgroup.waterview.UIRuntime, java.lang.String, java.lang.String)
     */
    public void render(UIRuntime runtime, String packageName, String page)
            throws Exception
    {
        Context vc = (Context) runtime.getUIContext().get("velocityContext");
        if (vc == null)
        {
            vc = new VelocityContextAdapter(runtime.getUIContext());
            runtime.getUIContext().put("velocityContext", vc);
        }
        String templatePath = getTemplatePath(packageName, page);
        OutputStreamWriter output = new OutputStreamWriter(runtime
                .getHttpServletResponse().getOutputStream());
        velocityEngine.mergeTemplate(templatePath, vc, output);
        output.flush();
    }
}