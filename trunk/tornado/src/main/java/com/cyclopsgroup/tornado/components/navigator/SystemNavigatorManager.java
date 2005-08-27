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
package com.cyclopsgroup.tornado.components.navigator;

import java.util.Hashtable;

import org.apache.commons.lang.ArrayUtils;

/**
 * Default implementation of navigator manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class SystemNavigatorManager extends NavigatorManagerBase implements
        NavigatorManager
{

    private Hashtable navigators = new Hashtable();

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.tornado.components.navigator.NavigatorManager#getNavigator(java.lang.String)
     */
    public Navigator getNavigator(String name)
    {
        return (Navigator) navigators.get(name);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.tornado.components.navigator.NavigatorManager#getNavigatorNames()
     */
    public String[] getNavigatorNames()
    {
        return (String[]) navigators.keySet().toArray(
                ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.tornado.components.navigator.NavigatorManager#registerNavigator(java.lang.String, com.cyclopsgroup.tornado.component.navigator.Navigator)
     */
    public void registerNavigator(Navigator navigator)
    {
        navigators.put(navigator.getId(), navigator);
    }
}
