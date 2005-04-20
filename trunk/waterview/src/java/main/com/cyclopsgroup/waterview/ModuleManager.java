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
     * TODO Add javadoc for this method
     *
     * @return
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
     * TODO Add javadoc for this method
     *
     * @param frameId
     * @return
     */
    Frame getFrame(String frameId);

    String[] getFrameIds();

    /**
     * Get layout by its id
     *
     * @param layoutId Layout id
     * @return Layout object
     */
    Layout getLayout(String layoutId);

    /**
     * TODO Add javadoc for this method
     *
     * @return
     */
    String[] getLayoutIds();

    /**
     * Get package name array
     *
     * @return Package name array
     */
    String[] getPackageNames();

    /**
     * TODO Add javadoc for this method
     *
     * @param frameId
     * @param frame
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
     * TODO Add javadoc for this method
     *
     * @param frameId
     */
    void setDefaultFrameId(String frameId);

    /**
     * Set default layout id
     *
     * @param layoutId Layout id
     */
    void setDefaultLayoutId(String layoutId);
}
