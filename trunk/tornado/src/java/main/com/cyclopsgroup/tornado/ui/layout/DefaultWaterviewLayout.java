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
package com.cyclopsgroup.tornado.ui.layout;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.tornado.components.navigator.Navigator;
import com.cyclopsgroup.tornado.components.navigator.NavigatorManager;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.PageRuntime;

public class DefaultWaterviewLayout implements Module
{
    /**
     * Overwrite or implement method execute()
     * @see com.cyclopsgroup.waterview.Module#execute(com.cyclopsgroup.waterview.PageRuntime, com.cyclopsgroup.clib.lang.Context)
     */
    public void execute(PageRuntime run, Context ctx) throws Exception
    {
        NavigatorManager nm = (NavigatorManager) run.getServiceManager()
                .lookup(NavigatorManager.ROLE);
        Navigator navigator = nm.getNavigator();
        ctx.put("navigator", navigator);
    }
}
