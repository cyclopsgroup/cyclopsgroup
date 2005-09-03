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

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * Localized paragraph tag
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class LocalizedParagraphTag extends TagSupport
{
    private boolean escape;

    private String id;

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag(XMLOutput output) throws Exception
    {
        invokeBody(output);
    }

    /**
     * Getter method for id
     *
     * @return Returns the id.
     */
    public String getId()
    {
        return id;
    }

    /**
     * Getter method for escape
     *
     * @return Returns the escape.
     */
    public boolean isEscape()
    {
        return escape;
    }

    /**
     * Setter method for escape
     *
     * @param escape The escape to set.
     */
    public void setEscape(boolean escape)
    {
        this.escape = escape;
    }

    /**
     * Setter method for id
     *
     * @param id The id to set.
     */
    public void setId(String id)
    {
        this.id = id;
    }
}
