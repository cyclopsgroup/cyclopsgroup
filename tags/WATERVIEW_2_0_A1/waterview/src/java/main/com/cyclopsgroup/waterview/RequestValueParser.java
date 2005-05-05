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

import org.apache.commons.fileupload.FileItem;

import com.cyclopsgroup.clib.lang.ValueParser;

/**
 * Typed value parser
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public abstract class RequestValueParser extends ValueParser
{
    /**
     * Comment for <code>EMPTY_FILEITEM_ARRAY</code>
     */
    public static final FileItem[] EMPTY_FILEITEM_ARRAY = new FileItem[0];

    /**
     * Get file item object
     *
     * @param name Name of field
     * @return FileItem object
     */
    public abstract FileItem getFileItem(String name);

    /**
     * TODO Add javadoc for this method
     *
     * @param name
     * @return File item array
     */
    public abstract FileItem[] getFileItems(String name);
}