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
package com.cyclopsgroup.jlone.type;

import com.cyclopsgroup.jlone.Content;
import com.cyclopsgroup.jlone.Context;
import com.cyclopsgroup.jlone.persistence.DataDocument;

/**
 * Generic content factory interface
 * 
 * @author <a href="mailto:jiiiaqi@yahoo.com">Jiaqi Guo </a>
 * 
 * Developed with eclipse 3.0
 */
public interface ContentFactory
{
    /**
     * Create new content from context and given id
     *
     * @param id Long id value
     * @param context Context
     * @return Created content object
     */
    Content create(long id, Context context);

    /**
     * Load content from given DataDocument
     *
     * @param document DataDocument object
     * @return Content object
     */
    Content load(DataDocument document);
}