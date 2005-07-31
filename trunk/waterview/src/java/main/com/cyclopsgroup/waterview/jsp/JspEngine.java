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
package com.cyclopsgroup.waterview.jsp;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.core.valves.RenderPageValve;
import com.cyclopsgroup.waterview.spi.DynaViewFactory;
import com.cyclopsgroup.waterview.spi.View;

public class JspEngine extends AbstractLogEnabled implements Serviceable,
        DynaViewFactory
{
    public static final String ROLE = JspEngine.class.getName();

    /**
     * Overwrite or implement method createView()
     * @see com.cyclopsgroup.waterview.spi.DynaViewFactory#createView(java.lang.String, java.lang.String, com.cyclopsgroup.waterview.PageRuntime)
     */
    public View createView(String packageName, String viewPath,
            PageRuntime runtime) throws Exception
    {
        String path = "view/" + viewPath;
        if (StringUtils.isNotEmpty(packageName))
        {
            path = StringUtils.replace(packageName, ".", "/") + "/" + path;
        }
        return new JspView(path);
    }

    /**
     * Overwrite or implement method service()
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        RenderPageValve renderPageValve = (RenderPageValve) serviceManager
                .lookup(RenderPageValve.ROLE);
        renderPageValve.registerViewFactory(".+\\.jsp", this);
    }
}
