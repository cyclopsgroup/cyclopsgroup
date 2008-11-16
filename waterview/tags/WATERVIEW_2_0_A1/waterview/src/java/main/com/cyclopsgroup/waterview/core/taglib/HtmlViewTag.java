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
package com.cyclopsgroup.waterview.core.taglib;

import org.apache.avalon.framework.service.ServiceManager;

import com.cyclopsgroup.waterview.View;
import com.cyclopsgroup.waterview.core.StaticView;
import com.cyclopsgroup.waterview.jelly.AbstractViewTag;

/**
 * View to directly show html
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class HtmlViewTag extends AbstractViewTag
{

    private boolean escape = false;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.jelly.AbstractViewTag#doCreateView(org.apache.avalon.framework.service.ServiceManager)
     */
    protected View doCreateView(ServiceManager serviceManager) throws Exception
    {
        return new StaticView(getBodyText(isEscape()));
    }

    /**
     * Getter method for excape
     *
     * @return Returns the excape.
     */
    public boolean isEscape()
    {
        return escape;
    }

    /**
     * Setter method for excape
     *
     * @param excape The excape to set.
     */
    public void setEscape(boolean excape)
    {
        this.escape = excape;
    }
}
