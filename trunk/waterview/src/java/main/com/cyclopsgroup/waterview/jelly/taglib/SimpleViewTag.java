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
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.DynaViewFactory;
import com.cyclopsgroup.waterview.View;

/**
 * Simple view tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class SimpleViewTag extends AbstractViewTag
{

    private String path;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.jelly.taglib.AbstractViewTag#doCreateView(org.apache.avalon.framework.service.ServiceManager)
     */
    protected View doCreateView(ServiceManager serviceManager) throws Exception
    {
        DynaViewFactory viewFactory = (DynaViewFactory) getContext()
                .getVariable(DynaViewFactory.NAME);

        if (viewFactory == null)
        {
            return null;
        }
        if (StringUtils.isEmpty(getPath()))
        {
            setPath("Index.jelly");
        }
        return viewFactory.createView(getPath(), getRuntime());
    }

    /**
     * Getter method for path
     *
     * @return Returns the path.
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Setter method for path
     *
     * @param path The path to set.
     */
    public void setPath(String path)
    {
        this.path = path;
    }
}
