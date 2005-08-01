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
package com.cyclopsgroup.waterview.jelly.taglib;

import java.util.HashMap;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.clib.lang.DefaultContext;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.jelly.AbstractTag;
import com.cyclopsgroup.waterview.spi.PanelContent;
import com.cyclopsgroup.waterview.spi.View;

/**
 * View tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class AbstractViewTag extends AbstractTag
{

    /**
     * Create view object
     *
     * @param serviceManager Service manager object
     * @return View object
     * @throws Exception Throw it out
     */
    protected abstract View doCreateView(ServiceManager serviceManager)
            throws Exception;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.jelly.AbstractTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        View view = doCreateView(serviceManager);
        if (isRendering())
        {
            RuntimeData runtime = getRuntime();
            if (view == null)
            {
                runtime.getOutput().println("<p>View is not created!</p>");
            }
            else
            {
                DefaultContext viewContext = new DefaultContext(new HashMap(),
                        runtime.getRequestContext());
                view.execute(runtime, viewContext);
                view.render(runtime, viewContext);
            }
        }
        else if (view != null)
        {
            requireParent(PanelContentTag.class);
            PanelContent panelContent = ((PanelContentTag) getParent())
                    .getPanelContent();
            panelContent.addView(view);
        }
    }
}
