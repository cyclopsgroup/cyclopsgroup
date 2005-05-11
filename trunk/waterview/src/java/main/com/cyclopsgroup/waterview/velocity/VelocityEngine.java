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
import com.cyclopsgroup.waterview.ActionResolver;
import com.cyclopsgroup.waterview.DynaViewFactory;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.ModuleManager;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.View;

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
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.DynaViewFactory#createView(java.lang.String, com.cyclopsgroup.waterview.PageRuntime)
     */
    public View createView(String viewPath, PageRuntime runtime)
            throws Exception
    {
        String path = "view/" + viewPath;
        Template template = getTemplate(path);
        Module module = getModuleManager().getModule(path);
        return new VelocityView(template, module);
    }

    /**
     * Getter method for moduleManager
     *
     * @return Returns the moduleManager.
     */
    public ModuleManager getModuleManager()
    {
        return moduleManager;
    }

    /**
     * Get velocity template
     *
     * @param templatePath Template path
     * @return Template object
     * @throws Exception Throw it out
     */
    public Template getTemplate(String templatePath) throws Exception
    {
        String[] packageNames = getModuleManager().getPackageNames();
        for (int i = 0; i < packageNames.length; i++)
        {
            String packageName = packageNames[i];
            Template t = getTemplate(templatePath, packageName);
            if (t != null)
            {
                return t;
            }
        }
        return null;
    }

    /**
     * Get velocity template with path and package
     *
     * @param templatePath Template path
     * @param packageName Package name
     * @return Velocity template object
     * @throws Exception Throw it out
     */
    public Template getTemplate(String templatePath, String packageName)
            throws Exception
    {
        if (!templatePath.endsWith(".vm"))
        {
            Path pr = Path.parse(templatePath);
            templatePath = pr.getParentPath() + pr.getShortName() + ".vm";
        }
        String path = templatePath;
        if (StringUtils.isNotEmpty(packageName))
        {
            path = packageName.replace('.', '/') + '/' + templatePath;
        }
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
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ActionResolver#resolveAction(java.lang.String, com.cyclopsgroup.waterview.PageRuntime)
     */
    public void resolveAction(String action, PageRuntime runtime)
            throws Exception
    {
        Template template = getTemplate("action/" + action);
        if (template != null)
        {
            VelocityContextAdapter vc = new VelocityContextAdapter(runtime
                    .getPageContext());
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
        setModuleManager((ModuleManager) serviceManager
                .lookup(ModuleManager.ROLE));
    }

    /**
     * Setter method for moduleManager
     *
     * @param moduleManager The moduleManager to set.
     */
    public void setModuleManager(ModuleManager moduleManager)
    {
        this.moduleManager = moduleManager;
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
