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
package com.cyclopsgroup.tornado.components.navigator;

/**
 * Navigator facade interface
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface NavigatorManager
{
    /**
     * Name of the default navigator
     */
    String DEFAULT_NAVIGATOR = "default";

    /** Role name in container */
    String ROLE = NavigatorManager.class.getName();

    /**
     * Get selected navigator
     *
     * @return Selected navigator object
     */
    Navigator getNavigator();

    /**
     * Get navigator with given name
     *
     * @param id
     * @return Navigator object
     */
    Navigator getNavigator(String id);

    /**
     * Get name of selected navigator
     *
     * @return Name of selected navigator
     */
    String getNavigatorName();

    /**
     * Get all available navigator names
     *
     * @return All available navigators
     */
    String[] getNavigatorNames();

    /**
     * Add navigator object
     *
     * @param navigator
     */
    void registerNavigator(Navigator navigator);

    /**
     * Set selected navigator name
     *
     * @param name Name of the navigator
     */
    void setNavigatorId(String name);
}
