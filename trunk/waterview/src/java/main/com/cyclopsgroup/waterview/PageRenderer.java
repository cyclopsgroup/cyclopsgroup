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
package com.cyclopsgroup.waterview;

/**
 * Interface of page renderer
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public interface PageRenderer
{
    /**
     * Test if the specified page is available
     *
     * @param packageName Page resource package name
     * @param module Module path without page extension
     * @return Existing or not
     */
    boolean exists(String packageName, String module);

    /**
     * Get content type of rendered page
     *
     * @return String content type
     */
    String getContentType();

    /**
     * Render page to runtime
     *
     * @param packageName Page resource package name
     * @param runtime UI Runtime object
     * @param module Module path without page extension
     * @throws Exception Throw it out
     */
    void render(UIRuntime runtime, String packageName, String module)
            throws Exception;
}