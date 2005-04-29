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

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.Page;
import com.cyclopsgroup.waterview.jelly.AbstractTag;
import com.cyclopsgroup.waterview.jelly.ScriptLayout;

/**
 * Tag for layout
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class LayoutTag extends AbstractTag
{
    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.jelly.AbstractTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    public void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        Page page = (Page) getContext().getVariable(Page.NAME);
        if (isRendering())
        {
            invokeBody(output);
        }
        else
        {
            ScriptLayout layout = new ScriptLayout(getBody(), null);
            page.setLayout(layout);
        }
    }
}
