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
package com.cyclopsgroup.jrepo;

import com.cyclopsgroup.jrepo.descriptor.ContentDescriptor;

/**
 * Content type
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ContentType
{
    private ContentDescriptor descriptor;

    private String name;

    private String title;

    /**
     * Get content descriptor
     *
     * @return Descriptor
     */
    public ContentDescriptor getDescriptor()
    {
        return descriptor;
    }

    /**
     * Getter method for name
     *
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for title
     *
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }
}