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
package com.cyclopsgroup.waterview.jelly.valves;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * Module chain module
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
class ModuleChain implements Module
{
    private List modules = new ArrayList();

    /**
     * Append a module
     *
     * @param module
     */
    void addModule(Module module)
    {
        if (module != null)
        {
            modules.add(module);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Module#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.clib.lang.Context)
     */
    public void execute(RuntimeData pageRuntime, Context context)
            throws Exception
    {
        for (Iterator i = modules.iterator(); i.hasNext();)
        {
            Module module = (Module) i.next();
            module.execute(pageRuntime, context);
        }
    }

}
