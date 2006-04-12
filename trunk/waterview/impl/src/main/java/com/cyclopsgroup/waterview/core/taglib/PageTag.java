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
package com.cyclopsgroup.waterview.core.taglib;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * Page tag
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class PageTag
    extends TagSupport
{
    private Page page;

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    public void processTag( XMLOutput output )
        throws Exception
    {
        page = getRunData().getPageObject();
        if ( page == null )
        {
            throw new JellyTagException( "There must be a Page object defined here" );
        }
        invokeBody( output );
    }

    /**
     * Getter method for page
     *
     * @return Returns the page.
     */
    public Page getPage()
    {
        return page;
    }
}
