/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
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
package com.cyclopsgroup.waterview.manager;

import com.cyclopsgroup.waterview.Action;

/**
 * Default implementation of action resolver
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class DefaultActionResolver implements ActionResolver
{
    /**
     * Override method resolve in super class of DefaultActionResolver
     * 
     * @see com.cyclopsgroup.waterview.manager.ActionResolver#resolve(java.lang.String, java.lang.String)
     */
    public Action resolve(String path, String packageName)
    {
        String rpath = path.replace('/', '.');
        if (!rpath.startsWith("."))
        {
            rpath += '.';
        }
        String className = packageName + rpath;
        try
        {
            return (Action) Class.forName(className).newInstance();
        }
        catch (Exception ignored)
        {
            return null;
        }
    }
}