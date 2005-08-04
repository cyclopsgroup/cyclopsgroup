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

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.View;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * JSP implemented view
 */
public class JspView implements View
{
    private String path;

    /**
     * Constructor of JspView with a given path
     * @param path
     */
    public JspView(String path)
    {
        this.path = path;
    }

    /**
     * Overwrite or implement method render()
     * @see com.cyclopsgroup.waterview.spi.View#render(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.Context)
     */
    public void render(RuntimeData data, Context viewContext) throws Exception
    {
        JspEngine je = (JspEngine) data.getServiceManager().lookup(
                JspEngine.ROLE);
        je.renderJsp(path, data, viewContext);
    }
}
