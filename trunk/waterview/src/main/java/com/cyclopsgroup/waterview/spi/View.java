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
package com.cyclopsgroup.waterview.spi;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.RuntimeData;

/**
 * Window is the reusable small area in page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface View
{
    /** Dummy view */
    View DUMMY = new View()
    {
        private static final String NAME = "dummy";

        /**
         * Overwrite or implement method getName()
         *
         * @see com.cyclopsgroup.waterview.spi.View#getName()
         */
        public String getName()
        {
            return NAME;
        }

        /**
         * Overwrite or implement method render()
         * @see com.cyclopsgroup.waterview.spi.View#render(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.Context)
         */
        public void render(RuntimeData runtime, Context viewContext)
                throws Exception
        {
            //do nothing
        }
    };

    /** Empty array */
    View[] EMPTY_ARRAY = new View[0];

    /**
     * Get unique name of this view
     *
     * @return Unique name of view
     */
    String getName();

    /**
     * Render the window with runtime information
     *
     * @param runtime UIRuntime object
     * @param viewContext Context for this window
     * @throws Exception Throw it out
     */
    void render(RuntimeData runtime, Context viewContext) throws Exception;
}