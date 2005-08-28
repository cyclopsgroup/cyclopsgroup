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
package com.cyclopsgroup.waterview.web.taglib;

import com.cyclopsgroup.waterview.utils.TagPackage;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Tag package of web tags
 */
public class WebTagPackage extends TagPackage
{
    /**
     * Constructor for class WebTagPackage
     */
    public WebTagPackage()
    {
        addTag("JellyTableControl", JellyTableControlTag.class);
        addTag("TableControl", DefaultTableControlTag.class);
        addTag("Table", TableTag.class);
        addTag("Column", ColumnTag.class);
        addTag("CollectionTabularData", CollectionTabularDataTag.class);

        addTag("BlankImage", BlankImageTag.class);
    }
}