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

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.cyclib.velocity.VelocityComponent;
import com.cyclopsgroup.cyclib.velocity.VelocityContextAdapter;
import com.cyclopsgroup.waterview.PageRenderer;
import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Velocity page renderer
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class VelocityPageRenderer extends AbstractLogEnabled implements
        PageRenderer, Serviceable
{

    private static final String CONTENT_TYPE = "text/html";

    private static String getTemplatePath(String packageName, String module)
    {
        StringBuffer templatePath = new StringBuffer();
        if (StringUtils.isNotEmpty(packageName))
        {
            templatePath.append(packageName.replace('.', '/'));
            templatePath.append("/");
        }
        templatePath.append(module).append(".vm");
        return templatePath.toString();
    }

    private VelocityComponent velocityComponent;

    /**
     * Override or implement method of parent class or interface
     * 
     * @see com.cyclopsgroup.waterview.PageRenderer#exists(java.lang.String, java.lang.String)
     */
    public boolean exists(String packageName, String module)
    {
        return velocityComponent.templateExists(getTemplatePath(packageName,
                module));
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
     * Override method render in super class of VelocityPageRenderer
     * 
     * @see com.cyclopsgroup.waterview.PageRenderer#render(com.cyclopsgroup.cyclib.Context, java.lang.String, java.lang.String, com.cyclopsgroup.waterview.UIRuntime)
     */
    public void render(Context context, String packageName, String module,
            UIRuntime runtime) throws Exception
    {
        VelocityContextAdapter vc = new VelocityContextAdapter(context);
        String templatePath = getTemplatePath(packageName, module);
        velocityComponent.mergeTemplate(templatePath, vc, runtime.getOutput());
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        velocityComponent = (VelocityComponent) serviceManager
                .lookup(VelocityComponent.ROLE);
    }
}