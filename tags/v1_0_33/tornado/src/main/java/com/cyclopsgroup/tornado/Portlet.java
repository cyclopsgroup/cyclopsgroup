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
package com.cyclopsgroup.tornado;

import com.cyclopsgroup.waterview.RuntimeData;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Portlet API
 */
public interface Portlet
{
    /**
     * Get description of portlet
     *
     * @return Portlet description
     */
    String getDescription();

    /**
     * Get portlet editor
     *
     * @return Portlet editor or null
     */
    PortletEditor getEditor();

    /**
     * Get runtime unique id of portlet
     *
     * @return Portlet ID
     */
    String getId();

    /**
     * Get title of portlet
     *
     * @return Portlet title
     */
    String getTitle();

    /**
     * Runtime data
     *
     * @param data Runtime data
     * @param context Portlet context
     * @throws Exception Throw it out
     */
    void render(RuntimeData data, PortletContext context) throws Exception;
}