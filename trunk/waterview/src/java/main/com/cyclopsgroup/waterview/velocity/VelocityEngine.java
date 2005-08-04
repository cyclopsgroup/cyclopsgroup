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
package com.cyclopsgroup.waterview.velocity;

import java.io.StringWriter;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;

import com.cyclopsgroup.clib.site.velocity.VelocityFactory;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.ActionResolver;
import com.cyclopsgroup.waterview.spi.DynaViewFactory;
import com.cyclopsgroup.waterview.spi.ModuleManager;
import com.cyclopsgroup.waterview.spi.View;

/**
 * Velocity engine object
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class VelocityEngine extends AbstractLogEnabled implements Serviceable,
        DynaViewFactory, ActionResolver
{

    /** Role name of this component */
    public static final String ROLE = VelocityEngine.class.getName();

    private ModuleManager moduleManager;

    private VelocityFactory velocityFactory;

    /**
     * Overwrite or implement method createView()
     * @see com.cyclopsgroup.waterview.spi.DynaViewFactory#createView(java.lang.String, java.lang.String)
     */
    public View createView(String packageName, String viewPath)
            throws Exception
    {
        String path = "/view" + viewPath;
        Template template = getTemplate(packageName, path);
        return new VelocityView(template);
    }

    /**
     * Get velocity template with path and package
     *
     * @param templatePath Template path
     * @param packageName Package name
     * @return Velocity template object
     * @throws Exception Throw it out
     */
    public Template getTemplate(String packageName, String templatePath)
            throws Exception
    {
        String path = templatePath;
        if (StringUtils.isNotEmpty(packageName))
        {
            path = '/' + packageName.replace('.', '/') + templatePath;
        }
        path = path.substring(1);
        if (getVelocityFactory().getVelocityEngine().templateExists(path))
        {
            return getVelocityFactory().getVelocityEngine().getTemplate(path);
        }
        return null;
    }

    /**
     * Getter method for velocityFactory
     *
     * @return Returns the velocityFactory.
     */
    public VelocityFactory getVelocityFactory()
    {
        return velocityFactory;
    }

    /**
     * Overwrite or implement method resolveAction()
     * @see com.cyclopsgroup.waterview.spi.ActionResolver#resolveAction(java.lang.String, java.lang.String, com.cyclopsgroup.waterview.RuntimeData)
     */
    public void resolveAction(String packageName, String action,
            RuntimeData runtime) throws Exception
    {
        Template template = getTemplate(packageName, "action/" + action);
        if (template != null)
        {
            VelocityContextAdapter vc = new VelocityContextAdapter(runtime
                    .getRequestContext());
            template.merge(vc, new StringWriter());
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        setVelocityFactory((VelocityFactory) serviceManager
                .lookup(VelocityFactory.ROLE));
        moduleManager = (ModuleManager) serviceManager
                .lookup(ModuleManager.ROLE);

        String pattern = ".+\\.vm";

        moduleManager.registerActionResolver(pattern, this);

        moduleManager.registerDynaViewFactory(pattern, this);
    }

    /**
     * Setter method for velocityFactory
     *
     * @param velocityFactory The velocityFactory to set.
     */
    public void setVelocityFactory(VelocityFactory velocityFactory)
    {
        this.velocityFactory = velocityFactory;
    }
}
