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

import java.util.List;

import org.apache.commons.fileupload.FileItem;

/**
 * Typed value parser
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class Parameters
    extends Attributes
{
    /** Name of it in context */
    public static final String NAME = "params";

    /**
     * Get file item object
     *
     * @param name Name of field
     * @return FileItem object
     */
    public abstract FileItem getFileItem( String name );

    /**
     * Get array of file items
     *
     * @param name Name of variable
     * @return File item array FileItem array
     */
    public abstract List<FileItem> getFileItems( String name );
}