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

import java.util.HashMap;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.clib.lang.DefaultContext;
import com.cyclopsgroup.waterview.spi.JellyContextAdapter;
import com.cyclopsgroup.waterview.spi.View;
import com.cyclopsgroup.waterview.spi.taglib.BaseTag;

public class DisplayViewTag extends BaseTag
{
    private View view;

    /**
     * Overwrite or implement method doTag()
     * @see com.cyclopsgroup.waterview.spi.taglib.BaseTag#doTag(org.apache.avalon.framework.service.ServiceManager, org.apache.commons.jelly.XMLOutput)
     */
    protected void doTag(ServiceManager serviceManager, XMLOutput output)
            throws Exception
    {
        requireAttribute("view");
        JellyContextAdapter adapter = new JellyContextAdapter(getContext());
        DefaultContext ctx = new DefaultContext(new HashMap(), adapter);
        getView().render(getRuntime(), ctx);
    }

    /**
     * @return Returns the view.
     */
    public View getView()
    {
        return view;
    }

    /**
     * @param view The view to set.
     */
    public void setView(View view)
    {
        this.view = view;
    }
}
