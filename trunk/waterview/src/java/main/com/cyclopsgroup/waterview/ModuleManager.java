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
package com.cyclopsgroup.waterview;

/**
 * Module manager
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface ModuleManager
{
    /** Role name of the component */
    String ROLE = ModuleManager.class.getName();

    /**
     * Get default frame
     *
     * @return Frame object
     */
    Frame getDefaultFrame();

    /**
     * Get default frame id
     *
     * @return Frame id
     */
    String getDefaultFrameId();

    /**
     * Get default layout object
     *
     * @return Layout object
     */
    Layout getDefaultLayout();

    /**
     * Get default layout id
     *
     * @return Default layout id
     */
    String getDefaultLayoutId();

    /**
     * Get registered frame
     *
     * @param frameId
     * @return Frame object
     */
    Frame getFrame(String frameId);

    /**
     * Get id array of registered frames
     *
     * @return Frame id array
     */
    String[] getFrameIds();

    /**
     * Get layout by its id
     *
     * @param layoutId Layout id
     * @return Layout object
     */
    Layout getLayout(String layoutId);

    /**
     * Get id array of registered layouts
     *
     * @return All available layout ids
     */
    String[] getLayoutIds();

    /**
     * Get package name array
     *
     * @return Package name array
     */
    String[] getPackageNames();

    /**
     * Register frame object
     *
     * @param frameId Frame ID
     * @param frame Frame object
     */
    void registerFrame(String frameId, Frame frame);

    /**
     * Register layout object into waterview system
     *
     * @param layoutId Layout id
     * @param layout Layout object
     */
    void registerLayout(String layoutId, Layout layout);

    /**
     * Set default frame id
     *
     * @param frameId Frame id
     */
    void setDefaultFrameId(String frameId);

    /**
     * Set default layout id
     *
     * @param layoutId Layout id
     */
    void setDefaultLayoutId(String layoutId);
}
